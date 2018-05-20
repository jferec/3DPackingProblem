package shelf;

import util.Cuboid;

import java.util.ArrayList;

public class ZShelf {

    private final int x;
    private final int y;
    private final int h;
    private int currH;
    private ArrayList<YShelf> xyShelves;

    ZShelf(int height, int x, int y) {
        this.h = height;
        this.x = x;
        this.y = y;
        xyShelves = new ArrayList<>();
    }

    public ArrayList<YShelf> getXyShelves() {
        return xyShelves;
    }

    void addShelf(YShelf yShelf) {
        xyShelves.add(yShelf);
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

    YShelf openNewShelf(Cuboid c, int xBin){
        if(checkNewShelfFit(c)) {
            currH += c.getY();
            YShelf y = new YShelf(this, xBin, c.getY());
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
