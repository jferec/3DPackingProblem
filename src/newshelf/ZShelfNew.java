package newshelf;

import shelf.YShelf;
import util.Cuboid;

import java.util.ArrayList;


/***
 * currH - is a floor for a new yShelf
 */
public class ZShelfNew {

    private final int x;
    private final int y;
    private final int h;
    private int currH;
    private ArrayList<YShelfNew> yShelves;

    ZShelfNew(Cuboid c, int x, int y, int h) {
        this.h = h;
        this.x = x;
        this.y = y;
        yShelves = new ArrayList<>();
        getXyShelves().add(new YShelfNew(this, c, x, y));
        this.currH = y;
    }

    public ArrayList<YShelfNew> getXyShelves() {
        return yShelves;
    }

    void addShelf(YShelfNew yShelf) {
        yShelves.add(yShelf);
    }

    public int getH() {
        return h;
    }

    public int getCurrH() {
        return currH;
    }

    public void setCurrH(int currH) {
        this.currH = currH;
    }

    YShelfNew openNewShelf(Cuboid c, int xBin, int yBin){
        if(checkNewShelfFit(c)) {
            currH += c.getY();
            YShelfNew y = new YShelfNew(this, c,  xBin, yBin);
            getXyShelves().add(y);
            return y;
        }
        else return null;
    }

    boolean checkNewShelfFit(Cuboid c){
        if(c.getY() < h - currH)
            return true;
        else
            c.rotatePlaneZ();
        return c.getY() < h - currH;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}
