package shelf;

import util.Cuboid;

/***
 * Class represents empty space on a YShelf object. EmptySpace (rectangle) is defined in 2D by 4 vertices - Xs,Ys represent starting point
 * and Xe, Ye - ending point.
 */
public class EmptySpace {

    private int xs;
    private int xe;
    private int ys;
    private int ye;
    final private YShelf shelf;

    EmptySpace(int xs, int ys, int xe, int ye, YShelf shelf) {
        if (xs == xe || ys == ye)
            throw new IllegalArgumentException(" ");
        this.xs = xs;
        this.xe = xe;
        this.ys = ys;
        this.ye = ye;
        this.shelf = shelf;
    }

    /***
     * Operation splits horizontally
     * @param c
     */
    void horizontalSplit(Cuboid c) {

            if(c.getX() > getX() || c.getY() > getY())
                c.rotatePlaneZ();
            if(getYs() + c.getY() < getYe())
                getShelf().getEmptySpaces().add(new EmptySpace(getXs(), getYs()+c.getY(), getXe(), getYe(), getShelf()));
            if(getXs() + c.getX() < getXe())
                getShelf().getEmptySpaces().add(new EmptySpace(getXs() + c.getX(), getYs(), getXe(), getYs() + c.getY(), getShelf()));

        c.setBinPosition(getXs(), getYs() + getShelf().getH(), getShelf().getzShelf().getH());
        if(c.getBinPosition().getX() + c.getX() > c.getBin().getX() || c.getBinPosition().getY() + c.getY() > c.getBin().getY() || c.getBinPosition().getZ() + c.getZ() > c.getBin().getH())
            throw new IllegalStateException();
        if(c.getBinPosition().getX() < 0 || c.getBinPosition().getY() < 0 || c.getBinPosition().getZ() < 0)
            throw new IllegalStateException();
        getShelf().getEmptySpaces().remove(this);
    }


    void verticalSplit(Cuboid c) {

            if(c.getX() > getX() || c.getY() > getY())
                c.rotatePlaneZ();
            if(getYs() + c.getY() < getYe())
                getShelf().getEmptySpaces().add(new EmptySpace(getXs(), getYs()+c.getY(), getXs() + c.getX(), getYe(), getShelf()));
            if(getXs() + c.getX() < getXe())
                getShelf().getEmptySpaces().add(new EmptySpace(getXs() + c.getX(), getYs(), getXe(), getYs(), getShelf()));

        c.setBinPosition(getXs(), getYs() + getShelf().getH(), getShelf().getzShelf().getH());
        getShelf().getEmptySpaces().remove(this);
    }


    int getArea(){
        return getX()*getY();
    }

    private YShelf getShelf() {
        return shelf;
    }

    private int getXs() {
        return xs;
    }

    private int getXe() {
        return xe;
    }

    private int getYs() {
        return ys;
    }

    private int getYe() {
        return ye;
    }

    int getX(){
        return getXe()-getXs();
    }

    int getY(){
        return getYe()-getYs();
    }




}