public class Cuboid {

    private int x;
    private int y;
    private int z;
    private Position binPosition;

    Cuboid(int x, int y, int z)
    {
        if(x > 0 && y > 0 && z > 0) {
            this.x = x;
            this.y = y;
            this.z = z;
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
    public void rotatePlaneZ(){
        int tmp = getX();
        setX(getY());
        setY(tmp);
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

    public Position getBinPosition() {
        return binPosition;
    }
}
