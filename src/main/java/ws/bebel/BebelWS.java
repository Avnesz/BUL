package ws.bebel;

import servlet.abstrait.GeneralException;
import utils.CommunConstantes;
import ws.abstrait.AbstractWS;
import ws.bebel.connexion.ConnexionWSRequest;
import ws.bebel.connexion.ConnexionWSResponse;

public class BebelWS extends AbstractWS {
	public ConnexionWSResponse callConnexion(final ConnexionWSRequest request) throws GeneralException {
		return call("/connexion", request, ConnexionWSResponse.class);
	}

	@Override
	public String getUrl() {
		return CommunConstantes.BEBEL_WS();
	}
}
