package util;

import java.util.ArrayList;

public class Bin {


    final private int x;
    final private int y;
    final private int maxBoxX;
    final private int maxBoxY;
    private double height;
    private ArrayList<Cuboid> cuboids = new ArrayList<Cuboid>();



    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getMaxBoxX() {
        return maxBoxX;
    }

    public int getMaxBoxY() {
        return maxBoxY;
    }

    public Bin(int x, int y, int maxBoxX, int maxBoxY)
    {
        if(x <= 0 || y<= 0)
            throw new IllegalArgumentException("Length or width is negative or equal 0");
        else
        {
            this.maxBoxX = maxBoxX;
            this.maxBoxY = maxBoxY;
            this.x = x;
            this.y = y;
        }
        height = 0;
    }

    public void add(Cuboid box)
    {
        this.cuboids.add(box);
    }

    public ArrayList<Cuboid> getCuboids() {
        return cuboids;
    }

    public void setCuboids(ArrayList<Cuboid> cuboids) {
        this.cuboids = cuboids;
    }

}
