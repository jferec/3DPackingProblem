package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Bin {

    final private int x;
    final private int y;
    private long h;
    private ArrayList<Cuboid> cuboids;
    private static short idx;

    static
    {
        idx = 1;
    }

    public void increaseH(long value){
        setH(getH() + value);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public long getH() {
        return h;
    }

    public void setH(long h) {
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
        cuboids = new ArrayList<>();
    }

    public Bin(Bin bin){
        ArrayList<Cuboid> copyCuboids = new ArrayList<>();
        for(Cuboid c : bin.getCuboids()){
            copyCuboids.add(new Cuboid(c, this));
        }
        this.setCuboids(copyCuboids);
        this.x = bin.getX();
        this.y = bin.getY();
        this.h = bin.getH();
    }

    public void add(Cuboid box)
    {
        this.cuboids.add(box);
    }

    public ArrayList<Cuboid> getCuboids() {
        return cuboids;
    }

    private int getZPlaneArea(){
        return getX()*getY();
    }

    private double getVolume(){
        return getZPlaneArea()*getH();
    }

    public double getFill(){
        double v = 0;
        for (Cuboid c : getCuboids()){
            v+=c.getVolume();
        }
        return v/getVolume()*100;
    }

    public boolean nextPermutation(){

        int i = cuboids.size()- 2;
        while(i >= 0 && cuboids.get(i).getIndex() >= cuboids.get(i + 1).getIndex())
            i--;
        //If pivot doesn't exist start with first permutation (reverse order)
        if(i < 0){
            Collections.reverse(cuboids);
            return false;
        }
        //Else find second pivot (first place in table from the right that is higher or equal to pivot)
        else {
            int j = cuboids.size() - 1;
            while (cuboids.get(j).getIndex() <= cuboids.get(i).getIndex())
                j--;
            //Swap pivots and reverse suffix
            Collections.swap(cuboids, i, j);
            reverse(cuboids, i + 1, cuboids.size() - 1);
        }
        for(Cuboid c: cuboids)
        System.out.print(c.getIndex() + ", ");
        System.out.println();
        return true;
    }


    public static void reverse(ArrayList<Cuboid> arr, int beg, int end){
        while(beg < end){
            Collections.swap(arr, beg, end);
            beg++;
            end--;
        }
    }

    public static short getIdx() {
        return idx++;
    }


    public void setCuboids(ArrayList<Cuboid> cuboids) {
        this.cuboids = cuboids;
    }



}
