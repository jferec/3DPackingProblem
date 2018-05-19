import java.util.ArrayList;
import java.util.Comparator;

/***
 * Step 1:
 * Rotate each Cuboid in the Bin to smallest height
 * Step 2:
 * Find
 */
public class BruteAlgoritm {

    public Bin execute(Bin bin)
    {
        int  xMax = 0, yMax = 0;
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

        ArrayList<Sector> sectors = new ArrayList<>();

        for (int i = 0; i <= bin.getX() - xMax; i += xMax){
            for(int j = 0; j <= bin.getY() - yMax; j+= yMax){
                System.out.println(i  + " : " + j);
                sectors.add(new Sector(i, j));
            }
        }

        int i = 0;
        for(Cuboid c : bin.getCuboids()){
            fit(bin, c, sectors.get(i));
            i = (i + 1)%sectors.size();
        }

        return bin;
    }


    private void fit(Bin b, Cuboid c, Sector s){
        c.setBinPosition(s.getX(), s.getY(), s.getHeight());
        s.setHeight(s.getHeight() + c.getZ());
        if(s.getHeight() > b.getHeight())
            b.setHeight(s.getHeight());
        System.out.println("Cuboin bin position:" + c.getBinPosition().getX() + " " + c.getBinPosition().getY() + " " + " " + c.getBinPosition().getZ());
    }

    private class Sector{
        final int x;
        final int y;
        int height;

        Sector(int x, int y){
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

    }
}


