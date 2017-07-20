package bean;


/**
 * Classe representant la liste des couches d'un terrain
 * 
 * @author Mayitabel
 * 
 */
public class Layers {
    private Tuile sousSol;
    private Tuile sol;
    private Tuile layer1;

    public Layers(final String sousSol, final String sol, final String layer1) {
        this.sousSol = new Tuile("sousSol", sousSol);
        this.sol = new Tuile("sol", sol);
        this.layer1 = new Tuile("layer1", layer1);
    }

    /**
     * @return the sousSol
     */
    public Tuile getSousSol() {
        return sousSol;
    }

    /**
     * @param sousSol
     *            the sousSol to set
     */
    public void setSousSol(final Tuile sousSol) {
        this.sousSol = sousSol;
    }

    /**
     * @return the sol
     */
    public Tuile getSol() {
        return sol;
    }

    /**
     * @param sol
     *            the sol to set
     */
    public void setSol(final Tuile sol) {
        this.sol = sol;
    }

    /**
     * @return the layer1
     */
    public Tuile getLayer1() {
        return layer1;
    }

    /**
     * @param layer1
     *            the layer1 to set
     */
    public void setLayer1(final Tuile layer1) {
        this.layer1 = layer1;
    }

    public Tuile getTuileByLayer(final String layerName) {
        switch (layerName) {
            case "sousSol":
                return sousSol;
            case "sol":
                return sol;
            case "layer1":
                return layer1;
            default:
                return null;
        }
    }
}
