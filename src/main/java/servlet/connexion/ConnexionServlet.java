package servlet.connexion;

import java.io.IOException;
import java.util.logging.Level;

import javax.servlet.ServletException;

import servlet.abstrait.AbstractServlet;
import servlet.abstrait.GeneralException;
import utils.Constantes;
import utils.Logger;
import utils.SessionUtils;
import ws.bebel.BebelWS;
import ws.bebel.connexion.ConnexionWSRequest;
import ws.bebel.connexion.ConnexionWSResponse;
import bdd.PlayerDAO;
import bean.Player;

/**
 * Controller permettant de se connecter
 * 
 * @author Mayitabel
 * 
 */
public class ConnexionServlet extends AbstractServlet<ConnexionRequest, ConnexionResponse> {
	private static final long serialVersionUID = -4647019705021722992L;
	private final Logger logger = new Logger(ConnexionServlet.class.getName());
	private final BebelWS bebelWs = new BebelWS();

	@Override
	protected ConnexionResponse doGet(final ConnexionRequest request) throws ServletException,
			IOException {
		return null;
	}

	@Override
	protected ConnexionResponse doPost(final ConnexionRequest request) throws ServletException,
			IOException {
		final ConnexionResponse response = new ConnexionResponse();
        final String login = request.getLogin();

		final ConnexionWSRequest wsRequest = new ConnexionWSRequest();
        wsRequest.setLogin(login);
		wsRequest.setMdp(request.getMdp());

		ConnexionWSResponse wsResponse;
		try {
			wsResponse = bebelWs.callConnexion(wsRequest);

			if (wsResponse.getCodeRetour() == 0) {
				final String token = wsResponse.getToken();
				response.setToken(token);

                final PlayerDAO playerDAO = PlayerDAO.getInstance();

                Player player = playerDAO.getPlayer(login);
                if (player == null) {
                    player = Player.newUser(login);
                    playerDAO.saveUser(player);
                }

                SessionUtils.getInstance(httpRequest).connectUser(token, player);
			} else {
				throw new GeneralException(wsResponse.getCodeRetour(), wsResponse.getMessage());
			}
		} catch (final GeneralException e) {
			logger.log(Level.WARNING, e.getMessage());
			response.setCodeRetour(e.getCodeRetour());
			response.setMessage(e.getMessage());
		}

		return response;
	}

	@Override
	protected ConnexionRequest getRequest(final String data) {
		return Constantes.GSON.fromJson(data, ConnexionRequest.class);
	}

}
