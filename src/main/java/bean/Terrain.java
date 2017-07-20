package bean;

import java.util.Map;

public class Terrain {
    private long version;
    private String proprietaire;
    private Map<Integer, Map<Integer, Layers>> layers;

	/**
     * @return the version
     */
    public long getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(final long version) {
        this.version = version;
    }

    /**
     * @return the proprietaire
     */
    public String getProprietaire() {
        return proprietaire;
    }

    /**
     * @param proprietaire the proprietaire to set
     */
    public void setProprietaire(final String proprietaire) {
        this.proprietaire = proprietaire;
    }

    /**
     * @return the layers
     */
    public Map<Integer, Map<Integer, Layers>> getLayers() {
        return layers;
    }

    /**
     * @param layers the layers to set
     */
    public void setLayers(Map<Integer, Map<Integer, Layers>> layers) {
        this.layers = layers;
    }
}
