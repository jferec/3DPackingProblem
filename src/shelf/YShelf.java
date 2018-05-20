package shelf;

import util.Cuboid;
import java.util.HashSet;
import java.util.Set;

public class YShelf {

    private final int x;
    private final int height;
    private final ZShelf zShelf;
    private Set<EmptySpace> emptySpaces;


    YShelf(ZShelf zShelf, int x, int height) {
        this.x = x;
        this.height = height;
        this.zShelf = zShelf;
        this.emptySpaces = new HashSet<>();
        emptySpaces.add(new EmptySpace(0, x, 0, height, this));
    }

    void split(EmptySpace e, Cuboid c) {
        try {
            if(e.getXs() + c.getX() < height)
            emptySpaces.add(new EmptySpace(e.getXs() + c.getX(), e.getXs(), e.getYs(), e.getYe(), this));
            if(e.getYs() + c.getY() < x)
            emptySpaces.add(new EmptySpace(e.getXs(), e.getXe(), e.getYs() + c.getY(), e.getYe(), this));
        } catch (Exception ex) {
            System.out.println("empty");
        }
        c.setBinPosition(e.getXs(), e.getYs(), e.getShelf().getzShelf().getH());
        emptySpaces.remove(e);
    }


    public ZShelf getzShelf() {
        return zShelf;
    }

    public Set<EmptySpace> getEmptySpaces() {
        return emptySpaces;
    }


}