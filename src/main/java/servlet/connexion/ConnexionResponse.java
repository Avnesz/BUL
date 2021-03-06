package servlet.connexion;

import servlet.abstrait.GeneralResponse;

/**
 * reponse de connexion
 * 
 * @author Mayitabel
 * 
 */
public class ConnexionResponse extends GeneralResponse {
    private String token;

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     *            the token to set
     */
    public void setToken(final String token) {
        this.token = token;
    }
}
