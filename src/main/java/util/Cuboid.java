package util;

import util.Bin;

import java.util.Arrays;
import java.util.Collections;

public class Cuboid {

    private int x;
    private int y;
    private int z;
    private Bin bin;
    private Position binPosition;

    public Cuboid(int x, int y, int z, Bin bin)
    {
        if(x > 0 && y > 0 && z > 0) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.bin = bin;
            binPosition = null;
        }
        else
            throw new IllegalArgumentException("X, Y or Z argument is negative or equal to zero");
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    private void setX(int x) {
        this.x = x;
    }

    private void setY(int y) {
        this.y = y;
    }

    private void setZ(int z) {
        this.z = z;
    }

    public Bin getBin() {
        return bin;
    }


    public void rotateToSmallestHeight()
    {
        if(!((this.getZ() <= this.getY())&&(this.getZ() <= this.getX())))
        {
            int tmp = getZ();
            if(this.getY() <= this.getX())
            {
                setZ(getY());
                setY(tmp);
            }
            else
            {
                setZ(getX());
                setX(tmp);
            }
        }
    }

    private void nextOrientation(Integer [] arr){
        //Find pivot (i)
        int i = arr.length - 2;
        while(i > 0 && arr[i] >= arr[i+1])
            i--;
        //If pivot doesn't exist start with first permutation (reverse order)
        if(i < 0){
            Arrays.sort(arr, Collections.reverseOrder());
        }
        //Else find second pivot (first place in table from the right that is higher or equal to pivot)
        else {
            int j = arr.length - 1;
            while (arr[j] <= arr[i])
                j--;
            //Swap pivots and reverse suffix
            swap(arr, i , j);
            reverse(arr, i + 1, arr.length);
        }
    }

    private void swap(Integer[] arr, int iA, int iB){
        int tmp = arr[iA];
        arr[iA] = arr[iB];
        arr[iB] = tmp;
    }

    private void reverse(Integer[] arr, int beg, int end){
        for(int i = beg; i < (end-beg)/2; i++){
            swap(arr, i, end - i);
        }
    }

    public void rotatePlaneZ(){
        int tmp = getX();
        setX(getY());
        setY(tmp);
    }

    public void setVertical(){
        if(getX() > getY())
            rotatePlaneZ();
    }

    public void setHorizontal(){
        if(getY() > getX())
            rotatePlaneZ();
    }

    public int countOrientations(){
        int count = 6;
        if(getX() == getY())
            count/=2;
        if(getX() == getZ())
            count/=2;
        if((getY() == getZ()) && count != 1)
            count/=2;
        return count;
    }

    public void setBinPosition(double x, double y, double z)
    {
        if(this.binPosition == null)
            binPosition = new Position(x, y, z);
        else
        {
            binPosition.setX(x);
            binPosition.setY(y);
            binPosition.setZ(z);
        }
    }
    public int getVolume(){
        return getX()*getY()*getZ();
    }

    public Position getBinPosition() {
        return binPosition;
    }

    public int getZPlaneArea(){
        return getX()*getY();
    }
}
