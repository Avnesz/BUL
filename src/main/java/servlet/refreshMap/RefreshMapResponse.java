package servlet.refreshMap;

import java.util.List;

import servlet.abstrait.GeneralResponse;
import bean.TerrainModification;

/**
 * reponse de raffraichissement de terrain
 * 
 * @author Mayitabel
 * 
 */
public class RefreshMapResponse extends GeneralResponse {
    private List<TerrainModification> modifications;

    /**
     * @return the modifications
     */
    public List<TerrainModification> getModifications() {
        return modifications;
    }

    /**
     * @param modifications
     *            the modifications to set
     */
    public void setModifications(final List<TerrainModification> modifications) {
        this.modifications = modifications;
    }
}
