package naive;

import util.*;
import java.util.ArrayList;
import java.util.Comparator;

public class NaiveWithSorting extends Algorithm {

    @Override
    public Bin solve(Bin bin) {
        ArrayList<Sector> sectors = new ArrayList<>();
        Sector.createSectors(sectors, bin);

        int i = 0;
        for(Cuboid c : bin.getCuboids()){
            Sector.fit(c, findLowestSector(sectors));
            i = (i + 1)%sectors.size();
        }
        return bin;
    }

    private Sector findLowestSector(ArrayList<Sector> sectors){
       sectors.sort(Comparator.comparing(Sector::getHeight));
       return sectors.get(0);
    }
}
