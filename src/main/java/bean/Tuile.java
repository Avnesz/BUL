package bean;


/**
 * Classe representant une tuile d'un terrain
 * 
 * @author Mayitabel
 * 
 */
public class Tuile extends Element {
    private String layerName;
    private String id;
    private long version;
    private String idModifier;

    public Tuile(final String layerName, final String id) {
        this.setLayerName(layerName);
        this.setId(id);
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
     * @return the layerName
     */
    public String getLayerName() {
        return layerName;
    }

    /**
     * @param layerName the layerName to set
     */
    public void setLayerName(final String layerName) {
        this.layerName = layerName;
    }
}
