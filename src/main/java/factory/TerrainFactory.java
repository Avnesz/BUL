package factory;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Layers;
import bean.Terrain;
import bean.TerrainVierge;

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
        terrainDTO.setLayers(createLayers(terrainVierge));
        terrainDTO.setVersion(new Date().getTime());
        return terrainDTO;
    }

    /**
     * Transforme les couches du terrain vierge en terrain classique
     * 
     * @param terrainVierge
     * @return
     */
    private Map<Integer, Map<Integer, Layers>> createLayers(final TerrainVierge terrainVierge) {
        final List<List<String>> sousSolLayer = terrainVierge.getSousSol();
        final List<List<String>> solLayer = terrainVierge.getSol();
        final List<List<String>> layer1Layer = terrainVierge.getLayer1();

        final Map<Integer, Map<Integer, Layers>> newLayers = new HashMap<Integer, Map<Integer, Layers>>();
        for (int y = 0; y < sousSolLayer.size(); y++) {
            final Map<Integer, Layers> newLine = new HashMap<>();
            final List<String> sousSolLine = sousSolLayer.get(y);
            final List<String> solLine = solLayer.get(y);
            final List<String> layer1Line = layer1Layer.get(y);
            for (int x = 0; x < sousSolLine.size(); x++) {
                newLine.put(x, new Layers(sousSolLine.get(x), solLine.get(x), layer1Line.get(x)));
            }
            newLayers.put(y, newLine);
        }
        return newLayers;
    }
}
