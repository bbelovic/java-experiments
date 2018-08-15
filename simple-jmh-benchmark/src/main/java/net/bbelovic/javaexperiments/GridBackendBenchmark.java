package net.bbelovic.javaexperiments;

import net.bbelovic.adventofcode.Puzzle;
import net.bbelovic.adventofcode.day6.*;
import org.openjdk.jmh.annotations.*;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
public class GridBackendBenchmark {
    private String input = "turn on 0,0 through 999,999";

    private Puzzle<String, Long> vectorGridBackend = new Day6Puzzle<>(new VectorGrid(1_000_000, 1000),
            new DefaultInstructionsParser<>(LightOperation::valueOf));

    private Puzzle<String, Long> matrixGridBackend =
            new Day6Puzzle<>(new MatrixGrid(1000, 1000),
                    new DefaultInstructionsParser<>(LightOperation::valueOf));

    private Puzzle<String, Long> bitGridBackend = new Day6Puzzle<>(new BitGrid(1_000_000, 1000),
            new DefaultInstructionsParser<>(LightOperation::valueOf));


    @Benchmark
    public Long testVectorGridBackend() {
        return vectorGridBackend.solve(input);
    }

    @Benchmark
    public Long testMatrixGridBackend() {
        return matrixGridBackend.solve(input);
    }

    @Benchmark
    public Long testBitGridBackend() {
        return bitGridBackend.solve(input);
    }

}
