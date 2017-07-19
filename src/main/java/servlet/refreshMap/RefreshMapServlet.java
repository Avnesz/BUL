package servlet.refreshMap;

import java.io.IOException;
import java.util.logging.Level;

import javax.servlet.ServletException;

import servlet.abstrait.AbstractServlet;
import servlet.abstrait.GeneralException;
import utils.Constantes;
import utils.Logger;
import utils.SessionUtils;
import bdd.TerrainDAO;
import bean.TerrainChanged;

/**
 * Controller permettant de se connecter
 * 
 * @author Mayitabel
 * 
 */
public class RefreshMapServlet extends AbstractServlet<RefreshMapRequest, RefreshMapResponse> {
	private static final long serialVersionUID = -4647019705021722992L;
	private final Logger logger = new Logger(RefreshMapServlet.class.getName());

	@Override
	protected RefreshMapResponse doGet(final RefreshMapRequest request) throws ServletException,
			IOException {
		return null;
	}

	@Override
	protected RefreshMapResponse doPost(final RefreshMapRequest request) throws ServletException,
			IOException {
		final RefreshMapResponse response = new RefreshMapResponse();

        final String login = SessionUtils.getInstance(httpRequest).getPlayer().getLogin();

        /**
         * Si le proprietaire est null, cela signifie que l'utilisateur connecté est le proprietaire
         */
        if (request.getProprietaire() == null) {
            request.setProprietaire(login);
        }

        /**
         * D'abord si l'utilisateur possede les droits de modifications on modifie
         */
        try {
            TerrainDAO.getInstance().update(login, request.getProprietaire(), request.getModifications());
        } catch (final GeneralException e) {
            logger.log(Level.WARNING, "Impossible de raffraichir le terrain de : " + request.getProprietaire()
                    + " avec " + login);
            response.setCodeRetour(-1);
            response.setMessage("Impossible de raffraichir le terrain");
        }

        /**
         * Puis on recharge
         */
        try {
            final TerrainChanged newTerrain = TerrainDAO.getInstance().getNewVersion(request.getProprietaire(),
                    request.getLastVersion(), login);
            response.setNewTerrain(newTerrain);
        } catch (final GeneralException e) {
            logger.log(Level.WARNING,
                    "Impossible de recuperer la nouvelle version du terrain de : " + request.getProprietaire()
                            + " avec " + login);
            response.setCodeRetour(-1);
            response.setMessage("Impossible de recuperer la nouvelle version du terrain");
        }

		return response;
	}

	@Override
	protected RefreshMapRequest getRequest(final String data) {
		return Constantes.GSON.fromJson(data, RefreshMapRequest.class);
	}

}
