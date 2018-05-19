import java.util.ArrayList;
import java.util.Comparator;

public class BruteForceAlgorithmWithSorting extends Algorithm {

    @Override
    Bin solve(Bin bin) {
        int  xMax = 0, yMax = 0;
        ArrayList<Sector> sectors = new ArrayList<>();
        Sector.createSectors(sectors, bin, xMax, yMax);

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
