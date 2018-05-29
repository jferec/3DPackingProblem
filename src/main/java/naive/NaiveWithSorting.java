package naive;

import util.*;
import java.util.ArrayList;
import java.util.Comparator;

public class NaiveWithSorting extends Algorithm {

    /**
     * 1. Method initially splits Bin bin Z plane area into Sectors.
     * 2. All cuboids in the bin are assigned into sectors one by one into the sector of smallest height.
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
            Sector.fit(c, findLowestSector(sectors));
            i = (i + 1)%sectors.size();
        }
        return bin;
    }

    /**
     * Method returns Sector of the smallest height.
     * @param sectors - ArrayList with all sectors.
     * @return - Sector that has the smallest height.
     */
    private Sector findLowestSector(ArrayList<Sector> sectors){
       if(sectors.isEmpty())
           return null;
       Sector min = sectors.get(0);
       for(Sector s: sectors){
           if (s.getHeight() < min.getHeight())
               min = s;
       }
       return min;
    }
}
