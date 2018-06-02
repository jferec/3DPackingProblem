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
public class ShelfBenchmark  {

    @org.openjdk.jmh.annotations.State(Scope.Thread)
    static public class State {
        Bin bin;
        Random random;

        @Param({"250", "500", "750"})
        int maxSize;

        @Param({"2500", "5000", "7500", "10000", "12500",
                "15000", "17500", "20000", "22500", "25000", "27500",
                "30000", "32500", "35000", "37500", "40000",
                "42500", "45000", "47500", "50000"})
        int count;

        ShelfBestAreaFit shelfBestAreaFit;

        @Setup(Level.Trial)
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
        @Measurement(iterations = 1)
        public void naive(State s, Blackhole bh) {
            bh.consume(s.shelfBestAreaFit.solve(s.bin));
        }

    }

