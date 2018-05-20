package shelf;
import util.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class ShelfBestAreaFit extends Algorithm {

    private ArrayList<ZShelf> zShelves;

    public ShelfBestAreaFit(){
        zShelves = new ArrayList<>();
    }

    public ArrayList<ZShelf> getzShelves() {
        return zShelves;
    }

    @Override
    public Bin solve(Bin bin) {
        bin.getCuboids().sort(Comparator.comparing(Cuboid::getZ).reversed());
        for(Cuboid c: bin.getCuboids()){
            fit(c, getzShelves() ,bin.getX(), bin.getY());
        }
        return bin;
    }

    void fit(Cuboid c, ArrayList<ZShelf> shelves, int binX, int binY) {

        EmptySpace bestEmptySpace = findBestFit(c, shelves);
        if(bestEmptySpace != null){
            bestEmptySpace.getShelf().split(bestEmptySpace, c);
            return;
        }
        else {
            YShelf y = null;
            for (ZShelf z : getzShelves()){
                y = z.openNewShelf(c, binX);
                if(y != null) {
                    findBestFit(c, getzShelves());
                    return;
                }
            }

        }
        createZShelf(c, binX, binY);

    }



    int areaFit(Cuboid c, EmptySpace e){
        return e.getArea() - c.getZPlaneArea();
    }

    boolean checkZPlaneFit(Cuboid c, EmptySpace e){
        return ((c.getX() < e.getX() && c.getY() < e.getY()) || (c.getX()<e.getY() && c.getY() < e.getX()));
    }



    void createZShelf(Cuboid c, int x, int y){
        ZShelf z = new ZShelf(c.getZ(), x, y);
        getzShelves().add(z);
        YShelf s = z.openNewShelf(c, x);
        findBestFit(c, getzShelves());
    }

    EmptySpace findBestFit(Cuboid c, ArrayList<ZShelf> shelves){
        EmptySpace bestEmptySpace = null;
        int bestFit = c.getZPlaneArea();
        for (ZShelf z : shelves) {
            for (YShelf y : z.getXyShelves()) {
                for (EmptySpace e : y.getEmptySpaces()) {
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
