package servlet.refreshMap;

import java.io.IOException;

import javax.servlet.ServletException;

import servlet.abstrait.AbstractServlet;
import utils.Constantes;
import utils.Logger;
import utils.SessionUtils;
import bdd.TerrainDAO;
import bean.Terrain;

/**
 * Controller permettant de se connecter
 * 
 * @author Mayitabel
 * 
 */
public class RefreshMapServlet extends AbstractServlet<RefreshMapServletRequest, RefreshMapServletResponse> {
	private static final long serialVersionUID = -4647019705021722992L;
	private final Logger logger = new Logger(RefreshMapServlet.class.getName());

	@Override
	protected RefreshMapServletResponse doGet(final RefreshMapServletRequest request) throws ServletException,
			IOException {
		return null;
	}

	@Override
	protected RefreshMapServletResponse doPost(final RefreshMapServletRequest request) throws ServletException,
			IOException {
		final RefreshMapServletResponse response = new RefreshMapServletResponse();
		final String login = SessionUtils.getInstance(httpRequest).getPlayer().getLogin();
		final Terrain newTerrain = request.getTerrain();
		final Terrain oldTerrain = TerrainDAO.getInstance().getTerrain(login);

		oldTerrain.refresh(newTerrain);

		return response;
	}

	@Override
	protected RefreshMapServletRequest getRequest(final String data) {
		return Constantes.GSON.fromJson(data, RefreshMapServletRequest.class);
	}

}
