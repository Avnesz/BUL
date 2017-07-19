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
import bean.Terrain;
import bean.TerrainChanged;
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

    public void update(final String idModifier, final String proprietaire, final TerrainChanged terrainChanged)
            throws GeneralException {
        final Terrain oldTerrain = getTerrain(proprietaire, false);
        updateLayer(idModifier, oldTerrain.getSousSol(), terrainChanged.getSousSol());
        updateLayer(idModifier, oldTerrain.getSol(), terrainChanged.getSol());
        updateLayer(idModifier, oldTerrain.getLayer1(), terrainChanged.getLayer1());
    }

    private void updateLayer(final String idModifier, final Map<Integer, Map<Integer, Tuile>> oldLayer,
            final List<Tuile> newLayer) {
        for (final Tuile newTile : newLayer) {
            final Tuile oldTile = oldLayer.get(newTile.getY()).get(newTile.getX());
            oldTile.setId(newTile.getId());
            oldTile.setIdModifier(idModifier);
            oldTile.setVersion(new Date().getTime());
        }
    }

    public TerrainChanged getNewVersion(final String proprietaire, final long lastVersion, final String login)
            throws GeneralException {
        final Terrain terrain = getTerrain(proprietaire, false);

        final TerrainChanged newTerrain = new TerrainChanged();
        newTerrain.setSousSol(getNewLayer(terrain.getSousSol(), lastVersion, login));
        newTerrain.setSol(getNewLayer(terrain.getSol(), lastVersion, login));
        newTerrain.setLayer1(getNewLayer(terrain.getLayer1(), lastVersion, login));

        return newTerrain;
    }

    private List<Tuile> getNewLayer(final Map<Integer, Map<Integer, Tuile>> layer,
            final long lastVersion, final String login) {
        final List<Tuile> newLayer = new ArrayList<>();
        for (final Entry<Integer, Map<Integer, Tuile>> lineEntry : layer.entrySet()) {
            final Map<Integer, Tuile> line = lineEntry.getValue();
            for (final Entry<Integer, Tuile> tileEntry : line.entrySet()) {
                final Tuile tile = tileEntry.getValue();
                if (tile.getVersion() > lastVersion) {
                    tile.setX(tileEntry.getKey());
                    tile.setY(lineEntry.getKey());
                    newLayer.add(tile);
                }
            }
        }
        return newLayer;
    }
}
