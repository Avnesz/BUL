package utils;

import javax.servlet.http.HttpServletRequest;

import bean.Player;

/**
 * Classe utilitaire permettant de gerer la session
 * 
 * @author snesztler
 * 
 */
public class SessionUtils {
    private static final String USER_TOKEN = "USER_TOKEN";
    private static final String PLAYER_CONNECT = "PLAYER_CONNECT";
	private static SessionUtils instance;

	private SessionUtils() {
	}

	public static synchronized SessionUtils getInstance(final HttpServletRequest httpRequest) {
		if (instance == null) {
			instance = new SessionUtils();
		}
		instance.httpRequest = httpRequest;
		return instance;
	}

	/**
	 * Requete http
	 */
	protected HttpServletRequest httpRequest;

    /**
     * Connect le joueur en session
     * 
     * @param token
     * @param player
     */
    public void connectUser(final String token, final Player player) {
        httpRequest.getSession().setAttribute(USER_TOKEN, token);
        httpRequest.getSession().setAttribute(PLAYER_CONNECT, player);
	}

    /**
     * Renvoit le joueur connecté
     * 
     * @return
     */
    public Player getPlayer() {
        return (Player) httpRequest.getSession().getAttribute(PLAYER_CONNECT);
    }

    /**
     * Renvoi le token du joueur connecté
     * 
     * @return
     */
    public String getToken() {
        return (String) httpRequest.getSession().getAttribute(USER_TOKEN);
	}

}
