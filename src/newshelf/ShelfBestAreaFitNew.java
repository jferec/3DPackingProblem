package newshelf;

import shelf.EmptySpace;
import shelf.YShelf;
import shelf.ZShelf;
import util.Algorithm;
import util.Bin;
import util.Cuboid;

import java.util.ArrayList;
import java.util.Comparator;

public class ShelfBestAreaFitNew extends Algorithm {

    private ArrayList<ZShelfNew> zShelves;

    public ShelfBestAreaFitNew(){
        zShelves = new ArrayList<>();
    }

    public ArrayList<ZShelfNew> getzShelves() {
        return zShelves;
    }

    @Override
    public Bin solve(Bin bin) {
        bin.getCuboids().sort(Comparator.comparing(Cuboid::getZ).reversed());
        for(Cuboid c: bin.getCuboids()){
            fit(c, getzShelves() , bin);
        }
        return bin;
    }

    void fit(Cuboid c, ArrayList<ZShelfNew> shelves, Bin bin) {

        EmptySpaceNew bestEmptySpace = findBestFit(c, shelves);
        if(bestEmptySpace != null){
            bestEmptySpace.getShelf().split(bestEmptySpace, c);
            return;
        }
        else {
            YShelfNew y = null;
            for (ZShelfNew z : getzShelves()){
                y = z.openNewShelf(c, bin.getX(), bin.getY());
                if(y != null) {
                    break;
                }
            }

        }
        createZShelf(c, bin);

    }



    int areaFit(Cuboid c, EmptySpaceNew e){
        return e.getArea() - c.getZPlaneArea();
    }

    boolean checkZPlaneFit(Cuboid c, EmptySpaceNew e){
        return ((c.getX() < e.getX() && c.getY() < e.getY()) || (c.getX()<e.getY() && c.getY() < e.getX()));
    }



    void createZShelf(Cuboid c, Bin bin){
        ZShelfNew z = new ZShelfNew(c, bin.getX(), bin.getY(), (int)bin.getHeight() );
        getzShelves().add(z);
    }

    EmptySpaceNew findBestFit(Cuboid c, ArrayList<ZShelfNew> shelves){
        EmptySpaceNew bestEmptySpace = null;
        int bestFit = c.getZPlaneArea();
        for (ZShelfNew z : shelves) {
            for (YShelfNew y : z.getXyShelves()) {
                for (EmptySpaceNew e : y.getEmptySpaces()) {
                    int aF = areaFit(c,e);
                    if(checkZPlaneFit(c, e) && aF < bestFit) {
                        bestFit = aF;
                        bestEmptySpace = e;
                    }

                }
            }
        }
        return bestEmptySpace;
    }



}
