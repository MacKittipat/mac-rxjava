package com.mackittipat.rxjava;

import io.reactivex.Observable;
import org.junit.Test;

import java.util.Comparator;

public class OperatorTransformingTest {

    @Test
    public void testMap() {
        Observable<Integer> source = Observable.just(1, 2, 3, 4, 5);
        source
                .map(s -> s * 2)
                .subscribe(
                        s -> System.out.println("NEXT : " + s),
                        e -> System.out.println("ERROR : " + e.getMessage()),
                        () -> System.out.println("COMPLETE"));
    }

    @Test
    public void testDefaultIfEmpty() {
        Observable<Integer> source = Observable.just(1, 2, 3, 4, 5);
        source
                .filter(i -> i > 10)
                .defaultIfEmpty(99)
                .subscribe(
                        s -> System.out.println("NEXT : " + s),
                        e -> System.out.println("ERROR : " + e.getMessage()),
                        () -> System.out.println("COMPLETE"));
    }

    @Test
    public void testSwitchIfEmpty() {
        Observable<Integer> source = Observable.just(1, 2, 3, 4, 5);
        Observable<Integer> source2 = Observable.just(6, 7, 8, 9, 10);
        source
                .filter(i -> i > 7)
                .switchIfEmpty(source2)
                .filter(i -> i > 7)
                .subscribe(
                        s -> System.out.println("NEXT : " + s),
                        e -> System.out.println("ERROR : " + e.getMessage()),
                        () -> System.out.println("COMPLETE"));
    }

    @Test
    public void testSorted() {
        Observable<Integer> source = Observable.just(5, 1, 4, 2, 3);
        source
                .sorted(Comparator.reverseOrder())
                .subscribe(
                        s -> System.out.println("NEXT : " + s),
                        e -> System.out.println("ERROR : " + e.getMessage()),
                        () -> System.out.println("COMPLETE"));
    }

    @Test
    public void testRepeat() {
        Observable<Integer> source = Observable.just(1, 2, 3, 4, 5);
        source
                .repeat(3)
                .subscribe(
                        s -> System.out.println("NEXT : " + s),
                        e -> System.out.println("ERROR : " + e.getMessage()),
                        () -> System.out.println("COMPLETE"));
    }

    @Test
    public void testScan() {
        Observable<Integer> source = Observable.just(1, 2, 3, 4, 5);
        source
                .scan((accu, next) -> accu + next)
                .subscribe(
                        s -> System.out.println("NEXT : " + s),
                        e -> System.out.println("ERROR : " + e.getMessage()),
                        () -> System.out.println("COMPLETE"));
    }
}
