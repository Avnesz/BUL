package factory;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Terrain;
import bean.TerrainVierge;
import bean.Tuile;

/**
 * Classe utilitaire permettant de gerer les terrains
 * 
 * @author snesztler
 * 
 */
public class TerrainFactory {
	private static TerrainFactory instance;

	private TerrainFactory() {
	}

    public static synchronized TerrainFactory getInstance() {
		if (instance == null) {
			instance = new TerrainFactory();
		}
		return instance;
	}

    /**
     * Permet de creer un nouveau terrain à partir d'un terrain vierge
     * 
     * @param terrainVierge
     */
    public Terrain createNewTerrain(final String proprietaire, final TerrainVierge terrainVierge) {
        final Terrain terrainDTO = new Terrain();
        terrainDTO.setProprietaire(proprietaire);
        terrainDTO.setSousSol(createNewLayer(terrainVierge.getSousSol()));
        terrainDTO.setSol(createNewLayer(terrainVierge.getSol()));
        terrainDTO.setLayer1(createNewLayer(terrainVierge.getLayer1()));
        terrainDTO.setVersion(new Date().getTime());
        return terrainDTO;
    }

    private Map<Integer, Map<Integer, Tuile>> createNewLayer(final List<List<String>> layer) {
        final Map<Integer, Map<Integer, Tuile>> newLayer = new HashMap<Integer, Map<Integer, Tuile>>();
        for (int y = 0; y < layer.size(); y++) {
            final Map<Integer, Tuile> newLine = new HashMap<>();
            final List<String> line = layer.get(y);
            for (int x = 0; x < line.size(); x++) {
                newLine.put(x, new Tuile(line.get(x)));
            }
            newLayer.put(y, newLine);
        }
        return newLayer;
    }
}
