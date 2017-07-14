package bdd;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;

import servlet.abstrait.GeneralException;
import utils.Constantes;
import utils.JsonUtils;
import bean.Terrain;

import com.google.gson.Gson;

public class TerrainDAO {
	private static TerrainDAO instance;
	private final Map<String, Terrain> mapTerrain = new HashMap<String, Terrain>();

	private TerrainDAO() throws GeneralException {
	}

	/**
	 * Singleton
	 * 
	 * @return
	 * @throws GeneralException
	 */
	public static synchronized TerrainDAO getInstance() throws GeneralException {
		if (instance == null) {
			instance = new TerrainDAO();
		}
		return instance;
	}

	/**
	 * Permet de restorer les donnees utilisateur Si aucun terrain n'existe, on
	 * en creer un nouveau
	 * 
	 * @param login
	 * 
	 * @throws GeneralException
	 */
	public Terrain restore(final String login) throws GeneralException {
		final Terrain terrain;
		boolean newTerrain = false;

		String terrainPath = Constantes.TERRAIN_PATH.replace(":login", login);
		if (!JsonUtils.exists(terrainPath)) {
			final int randomNumber = ThreadLocalRandom.current().nextInt(0, 2);
			terrainPath = Constantes.TERRAIN_VIERGE_PATH.replace(":id", String.valueOf(randomNumber));
			newTerrain = true;
		}
		final String json = JsonUtils.load(terrainPath);
		final Gson gson = Constantes.GSON;
		terrain = gson.fromJson(json, Terrain.class);

		mapTerrain.put(login, terrain);

		if (newTerrain) {
			refresh(login);
		}

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
	 * Renvoi le terrain du joueur donn�e
	 * 
	 * @param login
	 * @return
	 * @throws GeneralException
	 */
	public Terrain getTerrain(final String login) throws GeneralException {
		Terrain terrain = mapTerrain.get(login);
		if (terrain == null) {
			terrain = restore(login);
		}
		return terrain;
	}
}
