package benchmark;
/*
import naive.Naive;
import naive.NaiveWithSorting;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import shelf.ShelfBestAreaFit;
import util.Bin;
import util.Cuboid;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Fork(value = 1)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@BenchmarkMode(Mode.SingleShotTime)
public class NaiveBenchmark {

    @State(Scope.Thread)
    static public class XDState {
        Bin bin;
        Random random;

        @Param({"250", "500", "750"})
        int maxSize;

        @Param({"1000000", "2000000", "3000000", "4000000", "5000000",
                "6000000", "7000000", "8000000", "9000000", "10000000",
                "11000000", "12000000", "13000000", "14000000", "15000000",
                "16000000", "17000000", "18000000", "19000000", "20000000"})
        int count;

        Naive naive;
        NaiveWithSorting naiveWithSorting;

        @Setup(Level.Trial)
        public void prepare() {
            naive = new Naive();
            naiveWithSorting = new NaiveWithSorting();
            random = new Random();
            bin = new Bin(1000, 1000);
            bin.getCuboids().clear();
            IntStream.range(0, count).forEach(n -> {
                bin.add(new Cuboid(random.nextInt(maxSize) + 1, random.nextInt(maxSize) + 1, random.nextInt(maxSize) + 1, bin));
            });
        }

    }

    @Benchmark
    @Warmup(iterations = 10)
    @Measurement(iterations = 5)
    public void naive(XDState xd, Blackhole bh) {
         bh.consume(xd.naive.solve(xd.bin));
    }

    @Benchmark
    @Warmup(iterations = 10)
    @Measurement(iterations = 5)
    public void naiveWithSorting(XDState xd, Blackhole bh) {
        bh.consume(xd.naiveWithSorting.solve(xd.bin));
    }


}


*/
