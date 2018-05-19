import java.util.ArrayList;


/***
 * Step 1:
 * Rotate each Cuboid in the Bin to smallest height
 * Step 2:
 * Find
 */
public class BruteForceAlgorithm extends Algorithm{

    @Override
    public Bin solve(Bin bin)
    {
        int  xMax = 0, yMax = 0;
        ArrayList<Sector> sectors = new ArrayList<>();
        Sector.createSectors(sectors, bin, xMax, yMax);

        int i = 0;

        for(Cuboid c : bin.getCuboids()){
            Sector.fit(c, sectors.get(i));
            i = (i + 1)%sectors.size();
        }

        return bin;
    }

}


