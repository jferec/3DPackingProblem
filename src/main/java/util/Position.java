package util;

public class Position {

    private int x;
    private int y;
    private long z;

    public Position(int x, int y, long z){

        this.x = x;
        this.y = y;
        this.z = z;

    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public long getZ() {
        return z;
    }

    public void setZ(long z) {
        this.z = z;
    }

}
