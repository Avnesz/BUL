package servlet.refreshMap;

import bean.TerrainChanged;

/**
 * Requete de connexion
 * 
 * @author Mayitabel
 * 
 */
public class RefreshMapRequest {
    private String proprietaire;
    private long lastVersion;
    private TerrainChanged modifications;

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
    public TerrainChanged getModifications() {
        return modifications;
    }

    /**
     * @param modifications the modifications to set
     */
    public void setModifications(TerrainChanged modifications) {
        this.modifications = modifications;
    }
}
