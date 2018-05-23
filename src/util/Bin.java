package util;

import java.util.ArrayList;

public class Bin {


    final private int x;
    final private int y;
    private int h;
    private ArrayList<Cuboid> cuboids = new ArrayList<Cuboid>();


    public void increaseH(int value){
        setH(getH() + value);
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

    public void setH(int h) {
        this.h = h;
    }

    public Bin(int x, int y)
    {
        if(x <= 0 || y<= 0)
            throw new IllegalArgumentException("Length or width is negative or equal 0");
        else
        {
            this.x = x;
            this.y = y;
        }
        h = 0;
    }

    public void add(Cuboid box)
    {
        this.cuboids.add(box);
    }

    public ArrayList<Cuboid> getCuboids() {
        return cuboids;
    }


}
