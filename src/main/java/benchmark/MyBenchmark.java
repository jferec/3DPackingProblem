package benchmark;

import naive.Naive;
import naive.NaiveWithSorting;
import org.openjdk.jmh.annotations.*;
import shelf.ShelfBestAreaFit;
import util.Bin;
import util.Cuboid;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Fork(value = 1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
public class MyBenchmark {

    @State(Scope.Thread)
    static public class XDState {
        Bin bin;
        Random random;
        @Param({"10", "100", "1000", "10000"})
        int count;
        @Param({"250", "550"})
        int maxSize;
        ShelfBestAreaFit shelfBestAreaFit;
        Naive naive;
        NaiveWithSorting naiveWithSorting;


        @Setup(Level.Trial)
        public void prepare() {
            shelfBestAreaFit = new ShelfBestAreaFit();
            naive = new Naive();
            naiveWithSorting = new NaiveWithSorting();
            random = new Random();
            bin = new Bin(1000, 1000);
        }

        @Setup(Level.Iteration)
        public void prepIter(){
            bin.getCuboids().clear();
            IntStream.range(0, count).forEach(n -> {
                bin.add(new Cuboid(random.nextInt(count) + 1, random.nextInt(count) + 1, random.nextInt(count) + 1, bin));
            });
        }


    }
    @Benchmark
    public Bin shelf(XDState xd) {
        return xd.shelfBestAreaFit.solve(xd.bin);
    }

    @Benchmark
    public Bin naive(XDState xd) {
        return xd.naive.solve(xd.bin);
    }

    @Benchmark
    public Bin naiveWithSorting(XDState xd) {
        return xd.naiveWithSorting.solve(xd.bin);
    }


}



