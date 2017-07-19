package servlet.refreshMap;

import servlet.abstrait.GeneralResponse;
import bean.TerrainChanged;

/**
 * reponse de raffraichissement de terrain
 * 
 * @author Mayitabel
 * 
 */
public class RefreshMapResponse extends GeneralResponse {
    private TerrainChanged newTerrain;

    /**
     * @return the newTerrain
     */
    public TerrainChanged getNewTerrain() {
        return newTerrain;
    }

    /**
     * @param newTerrain the newTerrain to set
     */
    public void setNewTerrain(final TerrainChanged newTerrain) {
        this.newTerrain = newTerrain;
    }
}
