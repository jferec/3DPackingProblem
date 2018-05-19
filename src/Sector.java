import java.util.ArrayList;

public class Sector {
    final int x;
    final int y;
    int height;

    Sector(int x, int y) {
        this.x = x;
        this.y = y;
        height = 0;
    }
    public int getX() {
    return x;
}

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public static void createSectors(ArrayList <Sector> sectors, Bin bin, int xMax, int yMax){
        for(Cuboid c : bin.getCuboids()) {
            c.rotateToSmallestHeight();
            if(c.getX() < c.getY()) {
                c.rotatePlaneZ();
            }
            if(c.getX() > xMax)
                xMax = c.getX();
            if(c.getY() > yMax)
                yMax = c.getY();
            if(xMax == bin.getMaxBoxX() && yMax == bin.getMaxBoxY())
                break;
        }

        for (int i = 0; i <= bin.getX() - xMax; i += xMax) {
            for (int j = 0; j <= bin.getY() - yMax; j += yMax) {
                sectors.add(new Sector(i, j));
            }
        }
    }

    public static void fit(Cuboid c, Sector s){
        c.setBinPosition(s.getX(), s.getY(), s.getHeight());
        s.setHeight(s.getHeight() + c.getZ());
        if(s.getHeight() > c.getBin().getHeight())
            c.getBin().setHeight(s.getHeight());
    }




}
