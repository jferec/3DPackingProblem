package benchmark;

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
public class DataProofBenchmark  {

    @org.openjdk.jmh.annotations.State(Scope.Thread)
    static public class State {
        Bin bin;
        Random random;

        @Param({"500"})
        int maxSize;

        @Param({"15000"})
        int count;

        ShelfBestAreaFit shelfBestAreaFit;

        @Setup(Level.Iteration)
        public void prepare() {
            shelfBestAreaFit = new ShelfBestAreaFit();
            random = new Random();
            bin = new Bin(1000, 1000);
            bin.getCuboids().clear();
            IntStream.range(0, count).forEach(n -> {
                bin.add(new Cuboid(random.nextInt(maxSize) + 1, random.nextInt(maxSize) + 1, random.nextInt(maxSize) + 1, bin));
            });
        }
    }


    @Benchmark
    @Warmup(iterations = 2)
    @Measurement(iterations = 10)
    public void naive(State s, Blackhole bh) {
        bh.consume(s.shelfBestAreaFit.solve(s.bin));
    }

}