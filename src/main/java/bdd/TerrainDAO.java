package bdd;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;

import servlet.abstrait.GeneralException;
import utils.Constantes;
import utils.JsonUtils;
import bean.Layers;
import bean.Terrain;
import bean.TerrainModification;
import bean.TerrainVierge;
import bean.Tuile;

import com.google.gson.Gson;

import factory.TerrainFactory;

public class TerrainDAO {
    private static TerrainDAO instance;
    private final Map<String, Terrain> mapTerrain = new HashMap<String, Terrain>();

    private TerrainDAO() {
    }

    /**
     * Singleton
     * 
     * @return
     * @throws GeneralException
     */
    public static synchronized TerrainDAO getInstance() {
        if (instance == null) {
            instance = new TerrainDAO();
        }
        return instance;
    }

    /**
     * Permet de restorer les donnees utilisateur Si aucun terrain n'existe, on
     * en creer un nouveau
     * 
     * @param proprietaire
     * 
     * @throws GeneralException
     */
    public Terrain restore(final String proprietaire) throws GeneralException {
        final Gson gson = Constantes.GSON;
        final Terrain terrain;
        String terrainPath = Constantes.TERRAIN_PATH.replace(":login", proprietaire);
        if (JsonUtils.exists(terrainPath)) {
            final String json = JsonUtils.load(terrainPath);
            terrain = gson.fromJson(json, Terrain.class);
        } else {
            final int randomNumber = ThreadLocalRandom.current().nextInt(0, 2);
            terrainPath = Constantes.TERRAIN_VIERGE_PATH.replace(":id", String.valueOf(randomNumber));
            final String json = JsonUtils.load(terrainPath);
            final TerrainVierge terrainVierge = gson.fromJson(json, TerrainVierge.class);

            terrain = TerrainFactory.getInstance().createNewTerrain(proprietaire, terrainVierge);
            saveTerrain(proprietaire, terrain);
        }
        mapTerrain.put(proprietaire, terrain);
        return terrain;
    }

    /**
     * Permet de raffraichir les donnees utilisateur
     * 
     * @throws GeneralException
     */
    public void refresh(final String login) throws GeneralException {
        if (login == null) {
            for (final Entry<String, Terrain> entry : mapTerrain.entrySet()) {
                saveTerrain(entry.getKey(), entry.getValue());
            }
        } else if (mapTerrain.get(login) != null) {
            saveTerrain(login, mapTerrain.get(login));
        }
    }

    /**
     * Permet d'enregistrer le terrain d'un utilisateur en base
     * 
     * @param login
     * @param terrain
     * @throws GeneralException
     */
    private void saveTerrain(final String login, final Terrain terrain) throws GeneralException {
        final Gson gson = Constantes.GSON;
        final String json = gson.toJson(terrain);
        final String terrainPath = Constantes.TERRAIN_PATH.replace(":login", login);
        JsonUtils.save(terrainPath, json);
    }

    /**
     * Renvoi le terrain du joueur donnee
     * 
     * @param proprietaire
     * @return
     * @throws GeneralException
     */
    public Terrain getTerrain(final String proprietaire, final boolean createIfNotExist) throws GeneralException {
        Terrain terrain = mapTerrain.get(proprietaire);
        if (terrain == null && createIfNotExist) {
            terrain = restore(proprietaire);
        }
        return terrain;
    }

    /**
     * Permet d'inclure des mise à jour au terrain d'un joueur
     * 
     * @param idModifier
     * @param proprietaire
     * @param modifications
     * @throws GeneralException
     */
    public void update(final String idModifier, final String proprietaire,
            final List<TerrainModification> modifications) throws GeneralException {
        final Map<Integer, Map<Integer, Layers>> oldTerrain = getTerrain(proprietaire, false).getLayers();
        for (final TerrainModification modification : modifications) {
            final Layers oldLayers = oldTerrain.get(modification.getY()).get(modification.getX());
            updateTile(idModifier, oldLayers.getTuileByLayer(modification.getLayer()), modification);
        }
    }

    /**
     * Mise à jour d'une tuile
     * 
     * @param idModifier
     * @param oldTile
     * @param modification
     */
    private void updateTile(final String idModifier, final Tuile oldTile, final TerrainModification modification) {
        oldTile.setId(modification.getId());
        oldTile.setIdModifier(idModifier);
        oldTile.setVersion(new Date().getTime());
    }

    /**
     * Renvoi la nouvelle version du terrain
     * 
     * @param proprietaire
     * @param lastVersion
     * @param login
     * @return
     * @throws GeneralException
     */
    public List<TerrainModification> getNewVersion(final String proprietaire, final long lastVersion,
            final String login)
            throws GeneralException {
        final List<TerrainModification> modifications = new ArrayList<>();
        final Map<Integer, Map<Integer, Layers>> terrain = getTerrain(proprietaire, false).getLayers();

        for (final Entry<Integer, Map<Integer, Layers>> lineEntry : terrain.entrySet()) {
            final Map<Integer, Layers> line = lineEntry.getValue();
            for (final Entry<Integer, Layers> tileEntry : line.entrySet()) {
                final Layers layers = tileEntry.getValue();
                final int x = tileEntry.getKey();
                final int y = lineEntry.getKey();
                addModification(modifications, x, y, lastVersion, layers.getSousSol(), "sousSol");
                addModification(modifications, x, y, lastVersion, layers.getSol(), "sol");
                addModification(modifications, x, y, lastVersion, layers.getLayer1(), "layer1");
            }
        }

        return modifications;
    }

    /**
     * Ajoute une modification a la liste
     * 
     * @param modifications
     * @param x
     * @param y
     * @param lastVersion
     * @param tuile
     * @param layerName
     */
    private void addModification(final List<TerrainModification> modifications, final int x, final int y,
            final long lastVersion, final Tuile tuile, final String layerName) {
        if (tuile.getVersion() > lastVersion) {
            final TerrainModification modification = new TerrainModification();
            modification.setX(x);
            modification.setY(y);
            modification.setId(tuile.getId());
            modification.setLayer(layerName);
            modifications.add(modification);
        }
    }
}
