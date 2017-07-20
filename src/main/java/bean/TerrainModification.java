package bean;


public class TerrainModification extends Element {
    private String id;
    private String layer;
    
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
     * @return the layer
     */
    public String getLayer() {
        return layer;
    }
    
    /**
     * @param layer
     *            the layer to set
     */
    public void setLayer(final String layer) {
        this.layer = layer;
    }
}
