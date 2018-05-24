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

    @Override
    public Bin solve(Bin bin)
    {
        ArrayList<Sector> sectors = new ArrayList<>();
        Sector.createSectors(sectors, bin);

        int i = 0;

        for(Cuboid c : bin.getCuboids()){
            Sector.fit(c, sectors.get(i));
            i = (i + 1)%sectors.size();
        }

        return bin;
    }

}


