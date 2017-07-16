package servlet.getTerrain;

import java.io.IOException;
import java.util.logging.Level;

import javax.servlet.ServletException;

import servlet.abstrait.AbstractServlet;
import servlet.abstrait.GeneralException;
import utils.Logger;
import utils.SessionUtils;
import bdd.TerrainDAO;
import bean.Player;
import bean.Terrain;

/**
 * Controller permettant de recuperer un terrain
 * 
 * @author Mayitabel
 * 
 */
public class GetTerrainServlet extends AbstractServlet<String, GetTerrainServletResponse> {
	private static final long serialVersionUID = -4647019705021722992L;
	private final Logger logger = new Logger(GetTerrainServlet.class.getName());

	@Override
	protected GetTerrainServletResponse doGet(final String request) throws ServletException, IOException {
		return null;
	}

	@Override
	protected GetTerrainServletResponse doPost(final String request) throws ServletException, IOException {
		final GetTerrainServletResponse response = new GetTerrainServletResponse();

		try {
			final Player player = SessionUtils.getInstance(httpRequest).getPlayer();
			final Terrain terrain = TerrainDAO.getInstance().getTerrain(player.getLogin());
			response.setTerrain(terrain);
		} catch (final GeneralException e) {
			response.setCodeRetour(-1);
			response.setMessage("Impossible de recuperer le terrain");
			logger.log(Level.WARNING, "Impossible de recuperer le terrain : " + e.getMessage());
		}

		return response;
	}

	@Override
	protected String getRequest(final String data) {
		return null;
	}

}
