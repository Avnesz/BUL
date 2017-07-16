package enums;

import java.util.Date;

/**
 * Classe representant une tuile d'un terrain
 * 
 * @author Mayitabel
 * 
 */
public enum Tuile {
	// @formatter:off
		HERBE("herbe", false), 
		EAU("eau", true), 
		TROU("trou", true), 
		FLEUR("fleur", false), 
		BUISSON_VERT("buisson-vert", true), 
		BUISSON_BLEU("buisson-bleu", true), 
		BUISSON_JAUNE("buisson-jaune", true), 
		BUISSON_ORANGE("buisson-orange", true), 
		BUISSON_ROUGE("buisson-rouge", true);
	// @formatter:on

	private String id;
	private boolean colision;
	private String dateModification;

	private Tuile(final String id, final boolean colision) {
		this.id = id;
		this.colision = colision;
		this.setDateModification(new Date().toString());
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final String id) {
		this.id = id;
	}

	/**
	 * @return the colision
	 */
	public boolean isColision() {
		return colision;
	}

	/**
	 * @param colision
	 *            the colision to set
	 */
	public void setColision(final boolean colision) {
		this.colision = colision;
	}

	/**
	 * @return the dateModification
	 */
	public String getDateModification() {
		return dateModification;
	}

	/**
	 * @param dateModification
	 *            the dateModification to set
	 */
	public void setDateModification(final String dateModification) {
		this.dateModification = dateModification;
	}

	public static synchronized Tuile getById(final String id) {
		for (final Tuile tuile : values()) {
			if (tuile.id.equals(id)) {
				return tuile;
			}
		}
		return null;
	}
}
