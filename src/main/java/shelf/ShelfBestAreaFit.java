package shelf;

import util.Algorithm;
import util.Bin;
import util.Cuboid;

import java.util.ArrayList;
import java.util.Comparator;

public class ShelfBestAreaFit extends Algorithm {

    private ArrayList<ZShelf> zShelves;

    public ShelfBestAreaFit(){
        zShelves = new ArrayList<>();
    }

    public ArrayList<ZShelf> getZShelves() {
        return zShelves;
    }

    @Override
    public Bin solve(Bin bin) {
        bin.getCuboids().sort(Comparator.comparing(Cuboid::getZ).reversed());
        for(Cuboid c: bin.getCuboids()){
            fit(c, getZShelves());
        }
        return bin;
    }

    void fit(Cuboid c, ArrayList<ZShelf> shelves) {
        EmptySpace bestEmptySpace = findBestFit(c, shelves);
        if(bestEmptySpace != null){
            bestEmptySpace.horizontalSplit(c);
            return;
        }
        else {
            boolean y = false;
            for (ZShelf z : getZShelves()){
                c.setVertical();
                y = z.createYShelf(c);
                if(y) {
                    return;
                }
            }
        }
        createZShelf(c);
    }



    private int areaFit(Cuboid c, EmptySpace e){
        return e.getArea() - c.getZPlaneArea();
    }

    private boolean checkZPlaneFit(Cuboid c, EmptySpace e){
        return ((c.getX() <=  e.getX() && c.getY()<= e.getY()) || (c.getX() <= e.getY() && c.getY() <= e.getX()));
    }

    /***
     *
     * @param c Cuboid that is added
     */

    private void createZShelf(Cuboid c){
        getZShelves().add(new ZShelf(c));
    }

    private EmptySpace findBestFit(Cuboid c, ArrayList<ZShelf> shelves){
        EmptySpace bestEmptySpace = null;
        int bestFit = Integer.MAX_VALUE;
        for (ZShelf z : shelves) {
            for (YShelf y : z.getYShelves()) {
                for (EmptySpace e : y.getEmptySpaces()) {
                    int aF = areaFit(c, e);
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
