package bean;

import java.util.ArrayList;
import java.util.List;

public class Terrain {
    private List<List<String>> couche1 = new ArrayList<List<String>>();

    public static Terrain newTerrain() {
        final Terrain terrain = new Terrain();
        
        final List<List<String>> couche1 = terrain.getCouche1();
        
        for (int i=0; i<10; i++) {
            final List<String> line = new ArrayList<String>();
            for (int j=0; j<10; j++) {
                if (i==5 && j == 5) {
                    line.add("");
                }else {
                    line.add("eau");
                }
            }
            couche1.add(line);
        }
        
        return terrain;
    }

    /**
     * @return the couche1
     */
    public List<List<String>> getCouche1() {
        return couche1;
    }

    /**
     * @param couche1
     *            the couche1 to set
     */
    public void setCouche1(final List<List<String>> couche1) {
        this.couche1 = couche1;
    }

}
