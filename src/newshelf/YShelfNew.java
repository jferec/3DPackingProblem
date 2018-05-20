package newshelf;

import shelf.EmptySpace;
import shelf.ZShelf;
import util.Cuboid;

import java.util.HashSet;
import java.util.Set;

public class YShelfNew {

    private final int x;
    private final int y;
    private final int h;
    private final ZShelfNew zShelf;
    private Set<EmptySpaceNew> emptySpaces;


    /***
     *
     * @param zShelf
     * @param c
     * @param binX
     * @param h Height of the bottom of the shelf in respect to the bottom od ZShelf
     */
    YShelfNew(ZShelfNew zShelf, Cuboid c, int binX, int h) {
        this.x = binX;
        this.y = c.getY();
        this.h = h;
        this.zShelf = zShelf;
        this.emptySpaces = new HashSet<>();
        emptySpaces.add(new EmptySpaceNew(0, 0, getX(), getY(), this));
        c.setBinPosition(0, h, getzShelf().getH());
    }

    void split(EmptySpaceNew e, Cuboid c) {
        try {
            if(e.getYs() + c.getY() < h)
            emptySpaces.add(new EmptySpaceNew(e.getXs(), e.getYs()+c.getY(), e.getXe(), e.getYe(), this));
            if(e.getXs() + c.getX() < x)
            emptySpaces.add(new EmptySpaceNew(e.getXs() + c.getY(), e.getYs(), e.getXe(), e.getYs() + c.getY(), this));
        } catch (Exception ex) {
            System.out.println("empty");
        }
        c.setBinPosition(e.getXs(), e.getYs() + h, e.getShelf().getzShelf().getH());
        emptySpaces.remove(e);
    }



    public Set<EmptySpaceNew> getEmptySpaces() {
        return emptySpaces;
    }


    public ZShelfNew getzShelf() {
        return zShelf;
    }


    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }

    public int getH() {
        return h;
    }



}