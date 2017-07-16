package servlet.refreshMap;

import servlet.abstrait.GeneralResponse;
import bean.Terrain;

/**
 * reponse de raffraichissement de la map
 * 
 * @author Mayitabel
 * 
 */
public class RefreshMapServletResponse extends GeneralResponse {
	private Terrain terrain;

	/**
	 * @return the terrain
	 */
	public Terrain getTerrain() {
		return terrain;
	}

	/**
	 * @param terrain
	 *            the terrain to set
	 */
	public void setTerrain(final Terrain terrain) {
		this.terrain = terrain;
	}
}
