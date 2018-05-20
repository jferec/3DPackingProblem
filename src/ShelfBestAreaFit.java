import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class ShelfBestAreaFit extends Algorithm {
    @Override
    Bin solve(Bin bin) {
        bin.getCuboids().sort(Comparator.comparing(Cuboid::getZ).reversed());
        ArrayList<ZShelf> zShelves = new ArrayList<>();



            return bin;
    }

    void fit(Cuboid c, ArrayList<ZShelf> shelves) {
        int bestFit = c.getZPlaneArea();
        YShelf.EmptySpace bestEmptySpace = null;
        for (ZShelf z : shelves) {
            for (YShelf y : z.getXyShelves()) {
                for (YShelf.EmptySpace e : y.emptySpaces) {
                    int aF = areaFit(c,e);
                    if(checkZPlaneFit(c, e) && aF < bestFit) {
                        bestFit = aF;
                        bestEmptySpace = e;
                    }

                }
            }
        }
        if(bestEmptySpace != null){
            bestEmptySpace.getShelf().split(bestEmptySpace, c);
        }
        else {
            ZShelf z = shelves.get(shelves.size() - 1);
            z.addShelf(new YShelf(z));

    }

    }

    int areaFit(Cuboid c, YShelf.EmptySpace e){
        return e.getArea() - c.getZPlaneArea();
    }

    boolean checkZPlaneFit(Cuboid c, YShelf.EmptySpace e){
        if(c.getX() < e.getX() && c.getY() < e.getY())
            return true;
        c.rotatePlaneZ();
        return (c.getX() < e.getX() && c.getY() < e.getY());
    }


    ZShelf openZShelf(Cuboid c) {
        if (c.getX() < c.getY())
            c.rotatePlaneZ();
        return new ZShelf(c.getY());
    }

    class ZShelf {
        private final int height;

        private ArrayList<YShelf> xyShelves;

        ZShelf(int height) {
            this.height = height;
            xyShelves = new ArrayList<>();
        }

        public ArrayList<YShelf> getXyShelves() {
            return xyShelves;
        }

        void addShelf(YShelf yShelf) {
            xyShelves.add(yShelf);
        }
    }

    class YShelf {

        private final ZShelf zShelf;
        private Set<EmptySpace> emptySpaces;



        YShelf( ZShelf zShelf) {
            this.zShelf = zShelf;
            this.emptySpaces = new HashSet<>();
        }

        void split(EmptySpace e, Cuboid c) {
            try {
                emptySpaces.remove(e);
                emptySpaces.add(new EmptySpace(e.getXs() + c.getX(), e.getXs(), e.getYs() + c.getY(), e.getYe(), this));
                emptySpaces.add(new EmptySpace(e.getXs(), e.getXe(), e.getYs() + c.getY(), e.getYe(), this));
            } catch (Exception ex) {
                System.out.println("empty");
            }
            //setbinaustaw
            c.setBinPosition(e.getXs(), e.getYs(), e.getShelf().getzShelf().height);

        }



        public ZShelf getzShelf() {
            return zShelf;
        }

        public Set<EmptySpace> getEmptySpaces() {
            return emptySpaces;
        }

        class EmptySpace {

            private int xs;
            private int xe;
            private int ys;
            private int ye;
            final private YShelf shelf;



            EmptySpace(int xs, int xe, int ys, int ye, YShelf shelf) {
                if (xs == xe || ys == ye)
                    throw new IllegalArgumentException(" ");
                this.xs = xs;
                this.xe = xe;
                this.ys = ys;
                this.ye = ye;
                this.shelf = shelf;
            }

            int getArea(){
                return getX()*getY();
            }

            public YShelf getShelf() {
                return shelf;
            }

            public int getXs() {
                return xs;
            }

            public void setXs(int xs) {
                this.xs = xs;
            }

            public int getXe() {
                return xe;
            }

            public void setXe(int xe) {
                this.xe = xe;
            }

            public int getYs() {
                return ys;
            }

            public void setYs(int ys) {
                this.ys = ys;
            }

            public int getYe() {
                return ye;
            }

            public void setYe(int ye) {
                this.ye = ye;
            }

            public int getX(){
                return getXe()-getXs();
            }

            public int getY(){
                return getYe()-getYs();
            }
        }
    }
}
