package com.github.tkachuko.origramming.interview.strings.contains;

import com.github.tkachuko.origramming.interview.strings.Strings;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.infra.Blackhole;

@SuppressWarnings("unused")
public class Benchmarks implements Texts {

    private static final String HUGE_HALF_SUBSTRING = TEXT.substring(TEXT.length() / 2, TEXT.length());
    private static final String HUGE_THIRD_SUBSTRING = TEXT.substring(TEXT.length() / 3, TEXT.length());
    private static final String HUGE_TENTH_SUBSTRING = TEXT.substring(TEXT.length() / 10, TEXT.length());
    private static final String HUGE_THOUSAND_SUBSTRING = TEXT.substring(TEXT.length() / 1000, TEXT.length());


    // Benchmarks for huge text sample and different (in size) substring candidates

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    public void javaContainsPerformanceCheckOnHugeTextWithHalfSubstring(Blackhole blackhole) {
        blackhole.consume(TEXT.contains(HUGE_HALF_SUBSTRING));
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    public void customContainsPerformanceCheckOnHugeTextWithHalfSubstring(Blackhole blackhole) {
        Strings.isSubstring(TEXT, HUGE_HALF_SUBSTRING);
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    public void javaContainsPerformanceCheckOnHugeTextWithThirdSubstring(Blackhole blackhole) {
        blackhole.consume(TEXT.contains(HUGE_THIRD_SUBSTRING));
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    public void customContainsPerformanceCheckOnHugeTextWithThirdSubstring(Blackhole blackhole) {
        blackhole.consume(Strings.isSubstring(TEXT, HUGE_THIRD_SUBSTRING));
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    public void javaContainsPerformanceCheckOnHugeTextWithTenthSubstring(Blackhole blackhole) {
        blackhole.consume(TEXT.contains(HUGE_TENTH_SUBSTRING));
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    public void customContainsPerformanceCheckOnHugeTextWithTenthSubstring(Blackhole blackhole) {
        blackhole.consume(Strings.isSubstring(TEXT, HUGE_TENTH_SUBSTRING));
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    public void javaContainsPerformanceCheckOnHugeTextWithThousandsthSubstring(Blackhole blackhole) {
        blackhole.consume(TEXT.contains(HUGE_THOUSAND_SUBSTRING));
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    public void customContainsPerformanceCheckOnHugeTextWithThousandsthSubstring(Blackhole blackhole) {
        blackhole.consume(Strings.isSubstring(TEXT, HUGE_THOUSAND_SUBSTRING));
    }
}
