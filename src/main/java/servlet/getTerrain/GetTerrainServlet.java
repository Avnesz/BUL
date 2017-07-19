package servlet.getTerrain;

import java.io.IOException;
import java.util.logging.Level;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;

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
public class GetTerrainServlet extends AbstractServlet<String, GetTerrainResponse> {
    private static final long serialVersionUID = -4647019705021722992L;
    private final Logger logger = new Logger(GetTerrainServlet.class.getName());

    @Override
    protected GetTerrainResponse doGet(final String request) throws ServletException, IOException {
        return null;
    }

    @Override
    protected GetTerrainResponse doPost(final String request) throws ServletException, IOException {
        final GetTerrainResponse response = new GetTerrainResponse();

        try {
            String proprietaire = request;
            if (StringUtils.isEmpty(proprietaire) || "null".equals(proprietaire)) {
                final Player player = SessionUtils.getInstance(httpRequest).getPlayer();
                proprietaire = player.getLogin();
            }
            final Terrain terrain = TerrainDAO.getInstance().getTerrain(proprietaire, true);
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
        return data;
    }

}
