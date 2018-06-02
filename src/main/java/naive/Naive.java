package naive;

import java.util.ArrayList;
import util.*;

/***
 * Step 1:
 * Rotate each util.Cuboid in the util.Bin to smallest height
 * Step 2:
 * Find
 */
public class Naive extends Algorithm {

    /**
     * 1. Method initially splits Bin bin Z plane area into Sectors.
     * 2. All cuboids in the bin are assigned into sectors one by one.
     *
     * @param bin
     * @return
     */
    @Override
    public Bin solve(Bin bin) {
        bin.setH(0);
        ArrayList<Sector> sectors = new ArrayList<>();
        Sector.createSectors(sectors, bin);

        int i = 0;
        for(Cuboid c : bin.getCuboids()){
            Sector.fit(c, sectors.get(i));
            i = (i + 1)%sectors.size();
        }
        System.out.println("Naive solution ---ACCURACY : " + bin.getFill()+ "---");
        return bin;
    }

}


