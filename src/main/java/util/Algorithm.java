package util;

import java.io.*;

abstract public class Algorithm{
    public abstract Bin solve(Bin bin);
    public void saveResult(Bin bin, String filePath) throws IOException {
            int i = 1;
            StringBuilder sb = new StringBuilder();
            for (Cuboid c : bin.getCuboids()){
                Position p = c.getBinPosition();
                sb.append(i).append(".\t(").append(c.getX()).append(", ").append(c.getY()).append(", ").append(c.getZ()).append(")\t(").append(p.getX()).append(", ").append(p.getY()).append(", ").append(p.getZ()).append(")\n");
                i++;
            }
            PrintWriter pw = new PrintWriter(new FileWriter(filePath), true);
            pw.write("Cuboid\tSize(x,y,z)\tBin Position(x,y,z)\n");
            pw.write(sb.toString());
            pw.close();
            System.out.printf("Accuracy: %.2f%c", bin.getFill(), '%');
    }
}
