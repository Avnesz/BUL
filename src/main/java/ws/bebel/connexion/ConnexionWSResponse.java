package ws.bebel.connexion;

import servlet.abstrait.GeneralResponse;

/**
 * Reponse du WS de connexion bebel
 * 
 * @author snesztler
 *
 */
public class ConnexionWSResponse extends GeneralResponse {
    private String token;

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(final String token) {
        this.token = token;
    }
}
