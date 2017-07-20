package servlet.refreshMap;

import java.util.List;

import bean.TerrainModification;
import filter.checkConnect.ConnectFilterRequest;

/**
 * Requete de connexion
 * 
 * @author Mayitabel
 * 
 */
public class RefreshMapRequest extends ConnectFilterRequest {
    private String proprietaire;
    private long lastVersion;
    private List<TerrainModification> modifications;

    /**
     * @return the proprietaire
     */
    public String getProprietaire() {
        return proprietaire;
    }

    /**
     * @param proprietaire
     *            the proprietaire to set
     */
    public void setProprietaire(final String proprietaire) {
        this.proprietaire = proprietaire;
    }

    /**
     * @return the lastVersion
     */
    public long getLastVersion() {
        return lastVersion;
    }

    /**
     * @param lastVersion
     *            the lastVersion to set
     */
    public void setLastVersion(final long lastVersion) {
        this.lastVersion = lastVersion;
    }

    /**
     * @return the modifications
     */
    public List<TerrainModification> getModifications() {
        return modifications;
    }

    /**
     * @param modifications the modifications to set
     */
    public void setModifications(final List<TerrainModification> modifications) {
        this.modifications = modifications;
    }

}
