package shelf;

import org.junit.Assert;
import util.Cuboid;

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
        System.out.println("s: " + xs + " " + ys + "| e: " + xe + " " + ye);
    }

    void split(Cuboid c) {
        try {
            if(c.getX() > getX() || c.getY() > getY())
                c.rotatePlaneZ();
            if(getYs() + c.getY() < getYe())
                getShelf().getEmptySpaces().add(new EmptySpace(getXs(), getYs()+c.getY(), getXe(), getYe(), getShelf()));
            if(getXs() + c.getX() < getXe())
                getShelf().getEmptySpaces().add(new EmptySpace(getXs() + c.getX(), getYs(), getXe(), getYs() + c.getY(), getShelf()));
        } catch (Exception ex) {
            System.out.println("empty");
        }
        c.setBinPosition(getXs(), getYs() + getShelf().getH(), getShelf().getzShelf().getH());
        System.out.println("Cuboid "  + "(" + c.getX() + " " + c.getY() + " " + c.getZ() + ")" + c.getBinPosition().getX() + " " +  c.getBinPosition().getY() + " " +  c.getBinPosition().getZ());
        getShelf().getEmptySpaces().remove(this);
    }


    int getArea(){
        return getX()*getY();
    }

    YShelf getShelf() {
        return shelf;
    }

    int getXs() {
        return xs;
    }

    void setXs(int xs) {
        this.xs = xs;
    }

    int getXe() {
        return xe;
    }

    void setXe(int xe) {
        this.xe = xe;
    }

    int getYs() {
        return ys;
    }

    void setYs(int ys) { this.ys = ys; }

    int getYe() {
        return ye;
    }

    void setYe(int ye) {
        this.ye = ye;
    }

    int getX(){
        return getXe()-getXs();
    }

    int getY(){
        return getYe()-getYs();
    }




}