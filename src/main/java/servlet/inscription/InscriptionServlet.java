package servlet.inscription;

import java.io.IOException;
import java.util.logging.Level;

import javax.servlet.ServletException;

import servlet.abstrait.AbstractServlet;
import utils.Constantes;
import utils.Logger;
import utils.TokenUtils;

/**
 * Controller permettant de s'inscrire
 * 
 * @author Mayitabel
 * 
 */
public class InscriptionServlet extends AbstractServlet<InscriptionServletRequest, InscriptionServletResponse> {
    private static final long serialVersionUID = -4647019705021722992L;
    private final Logger logger = new Logger(InscriptionServlet.class.getName());
    private final InscriptionValidator validator = new InscriptionValidator();

    @Override
    protected InscriptionServletResponse doGet(final InscriptionServletRequest request) throws ServletException,
            IOException {
        return null;
    }

    @Override
    protected InscriptionServletResponse doPost(final InscriptionServletRequest request) throws ServletException,
            IOException {
        final InscriptionServletResponse response = new InscriptionServletResponse();
        try {
            if (validator.checkRequest(request)) {
                throw new InscriptionException(1, "Utilisateur inconnu");
            }

            if (!validator.checkPassword(user, request.getMdp())) {
                throw new InscriptionException(2, "Mot de passe incorrecte.");
            }

            if (!user.isVerified()) {
                throw new InscriptionException(3, "Veuillez valider votre inscription en verifiant vos mail.");
            }

            // Token de connexion
            response.setToken(TokenUtils.getInstance().generateToken(null));
            connectUser(response.getToken());
        } catch (final InscriptionException e) {
            logger.log(Level.WARNING, e.getMessage());
            response.setCodeRetour(e.getCodeRetour());
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @Override
    protected InscriptionServletRequest getRequest(final String data) {
        return Constantes.GSON.fromJson(data, InscriptionServletRequest.class);
    }

}
