package shelf;

import util.Bin;
import util.Cuboid;

import java.util.ArrayList;


/***
 * occupiedH - is a floor for a new yShelf
 */
public class ZShelf {

    private final int x;
    private final int y;
    private final int h;
    private final int z;
    private int occupiedH;
    private ArrayList<YShelf> yShelves;

    ZShelf(Cuboid c) {
        Bin b = c.getBin();
        this.x = b.getX();
        this.y = b.getY();
        this.h = b.getH();
        this.z = c.getZ();
        this.occupiedH = 0;
        b.increaseH(c.getZ());
        yShelves = new ArrayList<>();
        yShelves.add(new YShelf(this, c));
    }

    public void increaseOccupiedH(int value){
        setOccupiedH(getOccupiedH() + value);
    }

    public ArrayList<YShelf> getYShelves() {
        return yShelves;
    }

    public int getH() {
        return h;
    }

    public int getOccupiedH() {
        return occupiedH;
    }

    public void setOccupiedH(int occupiedH) {
        this.occupiedH = occupiedH;
    }

    boolean createYShelf(Cuboid c){
        if(checkNewShelfFit(c)) {
            YShelf y = new YShelf(this, c);
            getYShelves().add(y);
            return true;
        }
        else return false;
    }

    private boolean checkNewShelfFit(Cuboid c){
        c.setHorizontal();
        return (c.getY() + getOccupiedH() < getY());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public int getZ() {
        return z;
    }


}
