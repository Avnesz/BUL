package bean;


/**
 * Classe representant une tuile d'un terrain
 * 
 * @author Mayitabel
 * 
 */
public class Tuile extends Element {
    private String id;
    private long version;
    private String idModifier;

    public Tuile(final String id) {
        this.id = id;
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
     * @return the version
     */
    public long getVersion() {
        return version;
    }

    /**
     * @param version
     *            the version to set
     */
    public void setVersion(final long version) {
        this.version = version;
    }

    /**
     * @return the idModifier
     */
    public String getIdModifier() {
        return idModifier;
    }

    /**
     * @param idModifier
     *            the idModifier to set
     */
    public void setIdModifier(final String idModifier) {
        this.idModifier = idModifier;
    }
}
