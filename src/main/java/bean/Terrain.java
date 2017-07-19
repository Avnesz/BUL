package bean;

import java.util.Map;

public class Terrain {
    private long version;
    private String proprietaire;
    private Map<Integer, Map<Integer, Tuile>> sousSol;
    private Map<Integer, Map<Integer, Tuile>> sol;
    private Map<Integer, Map<Integer, Tuile>> layer1;

	/**
     * @return the version
     */
    public long getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(long version) {
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
     * @return the sousSol
     */
    public Map<Integer, Map<Integer, Tuile>> getSousSol() {
        return sousSol;
    }

    /**
     * @param sousSol
     *            the sousSol to set
     */
    public void setSousSol(final Map<Integer, Map<Integer, Tuile>> sousSol) {
        this.sousSol = sousSol;
    }

    /**
     * @return the sol
     */
    public Map<Integer, Map<Integer, Tuile>> getSol() {
        return sol;
    }

    /**
     * @param sol
     *            the sol to set
     */
    public void setSol(final Map<Integer, Map<Integer, Tuile>> sol) {
        this.sol = sol;
    }

    /**
     * @return the layer1
     */
    public Map<Integer, Map<Integer, Tuile>> getLayer1() {
        return layer1;
    }

    /**
     * @param layer1
     *            the layer1 to set
     */
    public void setLayer1(final Map<Integer, Map<Integer, Tuile>> layer1) {
        this.layer1 = layer1;
    }
}
