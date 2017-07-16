package servlet.refreshMap;

import bean.Terrain;

/**
 * Requete de raffraichissement de la map
 * 
 * @author Mayitabel
 * 
 */
public class RefreshMapServletRequest {
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
