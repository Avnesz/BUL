package bean;

import java.util.List;

public class TerrainChanged {
    private List<Tuile> sousSol;
    private List<Tuile> sol;
    private List<Tuile> layer1;

    /**
     * @return the sousSol
     */
    public List<Tuile> getSousSol() {
        return sousSol;
    }

    /**
     * @param sousSol
     *            the sousSol to set
     */
    public void setSousSol(final List<Tuile> sousSol) {
        this.sousSol = sousSol;
    }

    /**
     * @return the sol
     */
    public List<Tuile> getSol() {
        return sol;
    }

    /**
     * @param sol
     *            the sol to set
     */
    public void setSol(final List<Tuile> sol) {
        this.sol = sol;
    }

    /**
     * @return the layer1
     */
    public List<Tuile> getLayer1() {
        return layer1;
    }

    /**
     * @param layer1
     *            the layer1 to set
     */
    public void setLayer1(final List<Tuile> layer1) {
        this.layer1 = layer1;
    }
}
