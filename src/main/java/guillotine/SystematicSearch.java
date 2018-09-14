package guillotine;

import util.Algorithm;
import util.Bin;
import util.Cuboid;
import util.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;


public class SystematicSearch extends Algorithm {

    private ArrayList<Cuboid> boxes;
    private ArrayList<Position> candidates;

   public SystematicSearch(){
       boxes = new ArrayList<>();
       candidates = new ArrayList<>();
       candidates.add(new Position(0,0,0));
   }

    @Override
    public Bin solve(Bin bin) {
       bin.setH(0);
       BestBinWrapper bw = new BestBinWrapper();
       boolean b = true;
            while(b){
                runInnerLoop(0, bin, bw);
                b = bin.nextPermutation();
            }
        System.out.println("Systematic search - ACCURACY : " + bw.getmBin().getFill());
        return bw.getmBin();
    }


    public void fit(Cuboid c, Position v){
        int vx = v.getX(), vy = v.getY(), cx = c.getX(), cy = c.getY(), cz = c.getZ();
        long vz = v.getZ();
        c.setBinPosition(vx, vy, vz);
        if(vz + cz > c.getBin().getH()){
            c.getBin().setH(vz + cz);
        }
        Position[] cands = new Position[]{new Position(vx + cx, vy, vz), new Position(vx, vy + cy, vz), new Position(vx, vy , vz + cz)};
        Collections.addAll(getCandidates(), cands);
        getBoxes().add(c);
        getCandidates().remove(v);
    }


    public Position findBestFit(Cuboid c){
        long minH = Integer.MAX_VALUE;
        Position bestP = null;
        Iterator i = getCandidates().iterator();
        while (i.hasNext() && minH != 0){
            Position candidate = (Position) i.next();
            if(collisionCheck(candidate, c) && candidate.getZ() < minH){
                minH = candidate.getZ();
                bestP = candidate;
            }
        }
        if(bestP == null){
            bestP = new Position(0, 0, c.getBin().getH());
            getCandidates().add(bestP);
        }
        return bestP;
    }

    public boolean collisionCheck(Position v, Cuboid c){
        int vx = v.getX(), vy = v.getY(), cx = c.getX(), cy = c.getY(), cz = c.getZ();
        long vz = v.getZ();

        if (!checkBinBoundaries(v, c))
            return false;
        for(Cuboid box: getBoxes()) {

                if (!((vx >= box.getBinPosition().getX() + box.getX() || vx + cx <= box.getBinPosition().getX())
                    ||
                    ((vy >= box.getBinPosition().getY() + box.getY() || vy + cy <= box.getBinPosition().getY()))
                    ||
                    ((vz >= box.getBinPosition().getZ() + box.getZ() || vz + cz <= box.getBinPosition().getZ())))) {
                return false;
            }
        }
        return true;
    }

    public void prepIteration(Bin bin){
        getBoxes().clear();
        getCandidates().clear();
        getCandidates().add(new Position(0,0,0));
        bin.setH(0);
    }

    public ArrayList<Position> getCandidates() {
        return candidates;
    }

    public boolean checkBinBoundaries(Position v, Cuboid c){
        Bin b = c.getBin();
        return (v.getX() + c.getX() <= b.getX() && v.getY() + c.getY() <= b.getY());
    }


    public ArrayList<Cuboid> getBoxes() {
        return boxes;
    }

    void runInnerLoop(int N, Bin bin, BestBinWrapper cw){
            for (int i = 0; i < 6; i++) {
                if(N < bin.getCuboids().size() - 1) {
                    runInnerLoop(N + 1, bin, cw);
                }
                else if(N == bin.getCuboids().size() - 1)
                {
                    for (Cuboid cb : bin.getCuboids()) {
                        //System.out.println(cb.getX() + " " + cb.getY() + " " + cb.getZ());
                        fit(cb, findBestFit(cb));
                    }

                    if (bin.getH() < cw.getMinH()) {

                        cw.setmBin(new Bin(bin));
                        cw.setMinH(bin.getH());

                    }
                    prepIteration(bin);
                }
                bin.getCuboids().get(N).nextOrientation();
            }

        }

}


    class BestBinWrapper {
        public Bin getmBin() {
            return mBin;
        }

        public void setmBin(Bin mBin) {
            this.mBin = mBin;
        }

        public long getMinH() {
            return minH;
        }

        public void setMinH(long minH) {
            this.minH = minH;
        }

        private Bin mBin;
        private long minH;

        BestBinWrapper(){
            this.mBin = null;
            minH = Integer.MAX_VALUE;
        }
    }

