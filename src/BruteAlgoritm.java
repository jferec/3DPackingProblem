public class BruteAlgoritm {

    static public Bin execute(Bin bin)
    {
        for(Cuboid box : bin.getCuboids()) {
            box.rotateToSmallestHeight();
            box.setBinPosition((double)box.getX()/2,(double)box.getY()/2, bin.getHeight() + (double)box.getZ()/2);
            bin.setHeight(bin.getHeight() + box.getZ());

        }
        return bin;
    }

}


