package benchmark;

import guillotine.SystematicSearch;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import util.Bin;
import util.Cuboid;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
/*
@Fork(value = 1)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@BenchmarkMode(Mode.SingleShotTime)
public class SystematicSearchBenchmark {
    @org.openjdk.jmh.annotations.State(Scope.Thread)
    static public class State {
        Bin bin;
        Random random;

        @Param({"800", "1000"})
        int maxSize;

        @Param({"3", "4", "5", "6"})
        int count;

        SystematicSearch systematicSearch;


        @Setup(Level.Trial)
        public void prepare() {
            systematicSearch = new SystematicSearch();
            random = new Random();
            bin = new Bin(1000, 1000);
            bin.getCuboids().clear();
            IntStream.range(0, count).forEach(n -> {
                bin.add(new Cuboid(random.nextInt(maxSize) + 1, random.nextInt(maxSize) + 1, random.nextInt(maxSize) + 1, bin));
            });
        }

        @Setup(Level.Iteration)
        public void prep(){
            bin.getCuboids().clear();
            IntStream.range(0, count).forEach(n -> {
                bin.add(new Cuboid(random.nextInt(maxSize) + 1, random.nextInt(maxSize) + 1, random.nextInt(maxSize) + 1, bin));
            });
        }
    }


    @Benchmark
    @Warmup(iterations = 1)
    @Measurement(iterations = 3)
    public void naive(State s, Blackhole bh) {
        bh.consume(s.systematicSearch.solve(s.bin));
    }

}
*/