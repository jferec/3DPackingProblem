package benchmark;
/*
public class AccuracyComparison {
}
*/


import guillotine.SystematicSearch;
import naive.Naive;
import shelf.ShelfBestAreaFit;
import util.Bin;
import util.Cuboid;

import java.util.Random;
import java.util.stream.IntStream;

public class AccuracyComparison {

    static public void main(String args[]){
       AccuracyComparison ac =  new AccuracyComparison();
       ac.test(5);
    }

    Random random = new Random();
    Naive naive = new Naive();
    ShelfBestAreaFit shelfBestAreaFit = new ShelfBestAreaFit();
    SystematicSearch systematicSearch = new SystematicSearch();
    Bin bin = new Bin(1000, 1000);
    int maxEdge = 1000;

    void test(int count){

        for(int i = 0; i < 10; i++){
            bin.getCuboids().clear();
            IntStream.range(0, count).forEach((n)->{
                bin.add(new Cuboid(random.nextInt(maxEdge) + 1,random.nextInt(maxEdge) + 1,random.nextInt(maxEdge) + 1, bin));
            });

            naive.solve(bin);

            bin.setH(0);
            shelfBestAreaFit.solve(bin);

            bin.setH(0);
            systematicSearch.solve(bin);

            bin.setH(0);

        }

    }



}