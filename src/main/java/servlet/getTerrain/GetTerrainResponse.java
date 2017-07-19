package servlet.getTerrain;

import servlet.abstrait.GeneralResponse;
import bean.Terrain;

/**
 * reponse de recuperation du terrain
 * 
 * @author Mayitabel
 * 
 */
public class GetTerrainResponse extends GeneralResponse {
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
