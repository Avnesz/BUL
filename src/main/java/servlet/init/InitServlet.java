package servlet.init;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import servlet.abstrait.GeneralException;
import utils.Logger;
import bdd.PlayerDAO;
import bdd.TerrainDAO;

/**
 * Controller permettant de se connecter
 * 
 * @author Mayitabel
 * 
 */
public class InitServlet extends HttpServlet {
    private static final int SECOND = 1000;
    private static final int MINUTE = SECOND * 60;
    // private static final int HOUR = MINUTE * 60;
    // private static final int DAY = HOUR * 24;

    private static final long serialVersionUID = -4647019705021722992L;
	private final Logger logger = new Logger(InitServlet.class.getName());

	@Override
    public void init() throws ServletException {
        super.init();

        final Timer time = new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    logger.log(Level.INFO, "Sauvegarde du server");
                    PlayerDAO.getInstance().refresh();
                    TerrainDAO.getInstance().refresh(null);
                } catch (final GeneralException e) {
                    logger.log(Level.WARNING, "Impossible de sauvegarder les données du server");
                }
            }
        }, 0, MINUTE);
	}

}
