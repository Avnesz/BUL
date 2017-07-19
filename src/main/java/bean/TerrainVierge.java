package bean;

import java.util.List;

public class TerrainVierge {
    private List<List<String>> sousSol;
    private List<List<String>> sol;
    private List<List<String>> layer1;
    /**
     * @return the sousSol
     */
    public List<List<String>> getSousSol() {
        return sousSol;
    }
    
    /**
     * @param sousSol
     *            the sousSol to set
     */
    public void setSousSol(final List<List<String>> sousSol) {
        this.sousSol = sousSol;
    }
    /**
     * @return the sol
     */
    public List<List<String>> getSol() {
        return sol;
    }
    
    /**
     * @param sol
     *            the sol to set
     */
    public void setSol(final List<List<String>> sol) {
        this.sol = sol;
    }
    /**
     * @return the layer1
     */
    public List<List<String>> getLayer1() {
        return layer1;
    }
    
    /**
     * @param layer1
     *            the layer1 to set
     */
    public void setLayer1(final List<List<String>> layer1) {
        this.layer1 = layer1;
    }
}
