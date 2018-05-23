package shelf;

import util.Cuboid;

import java.util.HashSet;
import java.util.Set;

public class YShelf {

    private final int x;
    private final int y;
    private final int h;
    private final ZShelf zShelf;
    private Set<EmptySpace> emptySpaces;



    YShelf(ZShelf zShelf, Cuboid c) {
        this.x = c.getBin().getX();
        this.y = c.getY();
        this.zShelf = zShelf;
        this.h = zShelf.getOccupiedH();

        this.emptySpaces = new HashSet<>();
        EmptySpace e = new EmptySpace(0, 0, getX(), getY(), this);
        emptySpaces.add(e);
        e.split(c);
        zShelf.increaseOccupiedH(c.getY());

        System.out.println("YShelf " + getX() + " " + getY() + " " + getH() );
    }



    public Set<EmptySpace> getEmptySpaces() {
        return emptySpaces;
    }


    public ZShelf getzShelf() {
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