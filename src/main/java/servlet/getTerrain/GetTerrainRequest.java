package servlet.getTerrain;

import filter.checkConnect.ConnectFilterRequest;

/**
 * requete de recuperation du terrain
 * 
 * @author Mayitabel
 * 
 */
public class GetTerrainRequest extends ConnectFilterRequest {
    private String proprietaire;

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
}
