package naive;

import util.Bin;
import util.Cuboid;

import java.util.ArrayList;

class Sector {
    private final int x;
    private final int y;
    private int height;

    private Sector(int x, int y) {
        this.x = x;
        this.y = y;
        height = 0;
    }

    private int getX() {
    return x;
}

    private int getY() {
        return y;
    }

    int getHeight() {
        return height;
    }

    private void setHeight(int height) {
        this.height = height;
    }

    /**
     * Method rotates all the Cuboid objects in the bin to the smallest height. Bin Z plane is divided into sectors,
     * which are calculated based on maximal area taken by any Cuboid c in the Bin bin.
     * @param sectors - ArrayList<Sector> stores all calculated sectors.
     * @param bin - bin that we are assigning sectors to.
     */
    static void createSectors(ArrayList <Sector> sectors, Bin bin){
        int xMax = 0, yMax = 0;
        for(Cuboid c : bin.getCuboids()) {
            c.rotateToSmallestHeight();
            if(c.getX() < c.getY()) {
                c.rotatePlaneZ();
            }
            if(c.getX() > xMax)
                xMax = c.getX();
            if(c.getY() > yMax)
                yMax = c.getY();
        }

        for (int i = 0; i <= bin.getX() - xMax; i += xMax) {
            for (int j = 0; j <= bin.getY() - yMax; j += yMax) {
                sectors.add(new Sector(i, j));
            }
        }
    }

    /**
     * Method assigns Cuboid c to Sector s, accordingly increasing it's height.
     * @param c - Cuboid object to be put into Sector s.
     * @param s - Sector that Cuboid c gonna be assigned to.
     */
    static void fit(Cuboid c, Sector s){
        c.setBinPosition(s.getX(), s.getY(), s.getHeight());
        s.setHeight(s.getHeight() + c.getZ());
        if(s.getHeight() > c.getBin().getH())
            c.getBin().setH(s.getHeight());
    }

}
