package com.mackittipat.rxjava;

import io.reactivex.Observable;
import io.reactivex.observables.GroupedObservable;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class MergeTest {

    @Test
    public void testMerge() {
        Observable<Integer> source = Observable.just(1, 3, 5, 7, 9);
        Observable<Integer> source2 = Observable.range(1, 10);

        Observable<Integer> source3 = Observable.merge(source, source2);
        source3.subscribe(
                s -> System.out.println("NEXT : " + s),
                e -> System.out.println("ERROR : " + e.getMessage()),
                () -> System.out.println("COMPLETE"));
    }

    @Test
    public void testMergeWith() {
        Observable<Integer> source = Observable.just(1, 3, 5, 7, 9);
        Observable<Integer> source2 = Observable.range(1, 10);

        Observable<Integer> source3 = source.mergeWith(source2);
        source3.subscribe(
                s -> System.out.println("NEXT : " + s),
                e -> System.out.println("ERROR : " + e.getMessage()),
                () -> System.out.println("COMPLETE"));
    }

    @Test
    public void testFlatMap() {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        source
                .flatMap(s -> Observable.fromArray(s.split("")))
                .subscribe(
                        s -> System.out.println("NEXT : " + s),
                        e -> System.out.println("ERROR : " + e.getMessage()),
                        () -> System.out.println("COMPLETE"));

    }

    @Test
    public void testFlatMap2() {
        Observable<String> source = Observable.just("1,A", "2,B", "3,C", "4,D", "5,E");
        source
                .flatMap(s -> Observable.fromArray(s.split(",")))
                .filter(s -> Character.isDigit(s.charAt(0)))
                .subscribe(
                        s -> System.out.println("NEXT : " + s),
                        e -> System.out.println("ERROR : " + e.getMessage()),
                        () -> System.out.println("COMPLETE"));

    }

    @Test
    public void testConcat() {
        Observable<Integer> source = Observable.just(1, 3, 5, 7, 9);
        Observable<Integer> source2 = Observable.range(1, 10);

        Observable<Integer> source3 = Observable.concat(source, source2);
        source3.subscribe(
                s -> System.out.println("NEXT : " + s),
                e -> System.out.println("ERROR : " + e.getMessage()),
                () -> System.out.println("COMPLETE"));
    }

    @Test
    public void testConcatWith() {
        Observable<Integer> source = Observable.just(1, 3, 5, 7, 9);
        Observable<Integer> source2 = Observable.range(1, 10);

        Observable<Integer> source3 = source.concatWith(source2);
        source3.subscribe(
                s -> System.out.println("NEXT : " + s),
                e -> System.out.println("ERROR : " + e.getMessage()),
                () -> System.out.println("COMPLETE"));
    }

    @Test
    public void testConcatMap() {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        source
                .concatMap(s -> Observable.fromArray(s.split("")))
                .subscribe(
                        s -> System.out.println("NEXT : " + s),
                        e -> System.out.println("ERROR : " + e.getMessage()),
                        () -> System.out.println("COMPLETE"));

    }

    @Test
    public void testAmb() throws InterruptedException {
        Observable<String> source = Observable.interval(1, TimeUnit.SECONDS)
                .map(i -> "Source 1 = " + i);
        Observable<String> source2 = Observable.interval(200, TimeUnit.MILLISECONDS)
                .map(i -> "Source 2 = " + i);

        Observable<String> source3 = Observable.amb(Arrays.asList(source, source2));
        source3.subscribe(
                s -> System.out.println("NEXT : " + s),
                e -> System.out.println("ERROR : " + e.getMessage()),
                () -> System.out.println("COMPLETE"));

        Thread.sleep(3000);
    }

    @Test
    public void testZip() {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        Observable<Integer> source2 = Observable.range(1, 5);

        Observable<String> source3 = Observable.zip(source, source2, (s1, s2) -> s1 + "-" + s2);
        source3.subscribe(
                s -> System.out.println("NEXT : " + s),
                e -> System.out.println("ERROR : " + e.getMessage()),
                () -> System.out.println("COMPLETE"));
    }

    @Test
    public void testZipWith() {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        Observable<Integer> source2 = Observable.range(1, 5);

        Observable<String> source3 = source.zipWith(source2, (s1, s2) -> s1 + "-" + s2);
        source3.subscribe(
                s -> System.out.println("NEXT : " + s),
                e -> System.out.println("ERROR : " + e.getMessage()),
                () -> System.out.println("COMPLETE"));
    }

    @Test
    public void testGroupBy() {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        Observable<GroupedObservable<Integer, String>> sourceGroupBy = source.groupBy(String::length);

        sourceGroupBy.subscribe(
                s -> {
                    System.out.println("Length : " + s.getKey());
                    s.subscribe(s2 -> System.out.println(" " + s2));
                },
                e -> System.out.println("ERROR : " + e.getMessage()),
                () -> System.out.println("COMPLETE"));

        System.out.println("----------");

        sourceGroupBy
                .flatMapSingle(grp -> grp.toList())
                .subscribe(
                        s -> System.out.println("NEXT : " + s),
                        e -> System.out.println("ERROR : " + e.getMessage()),
                        () -> System.out.println("COMPLETE"));;
    }
}
