package shelf;

import util.Cuboid;

import java.util.HashSet;
import java.util.Set;

class YShelf {

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
        zShelf.increaseOccupiedH(c.getY());
        this.emptySpaces = new HashSet<>();
        EmptySpace e = new EmptySpace(0, 0, getX(), getY(), this);
        emptySpaces.add(e);
        e.horizontalSplit(c);
    }

    Set<EmptySpace> getEmptySpaces() {
        return emptySpaces;
    }

    ZShelf getzShelf() {
        return zShelf;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    int getH() {
        return h;
    }



}