package com.mackittipat.rxjava;

import io.reactivex.Observable;
import org.junit.Test;

public class OperatorSuppressingTest {

    @Test
    public void testFilter() {
        Observable<Integer> source = Observable.just(1, 2, 3, 4, 5);
        source
                .filter(s -> s > 2)
                .subscribe(
                        s -> System.out.println("NEXT : " + s),
                        e -> System.out.println("ERROR : " + e.getMessage()),
                        () -> System.out.println("COMPLETE"));
    }

    @Test
    public void testTake() {
        Observable<Integer> source = Observable.just(1, 2, 3, 4, 5);
        source
                .take(2)
                .subscribe(
                        s -> System.out.println("NEXT : " + s),
                        e -> System.out.println("ERROR : " + e.getMessage()),
                        () -> System.out.println("COMPLETE"));
    }

    @Test
    public void testSkip() {
        Observable<Integer> source = Observable.just(1, 2, 3, 4, 5);
        source
                .skip(2)
                .subscribe(
                        s -> System.out.println("NEXT : " + s),
                        e -> System.out.println("ERROR : " + e.getMessage()),
                        () -> System.out.println("COMPLETE"));
    }

    @Test
    public void testDistinct() {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gramma", "Delta", "Epsilon");
        source
                .map(String::length)
                .distinct()
                .subscribe(
                        s -> System.out.println("NEXT : " + s),
                        e -> System.out.println("ERROR : " + e.getMessage()),
                        () -> System.out.println("COMPLETE"));
    }


    @Test
    public void testElementAt() {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gramma", "Delta", "Epsilon");
        source
                .elementAt(2)
                .subscribe(
                        s -> System.out.println("NEXT : " + s),
                        e -> System.out.println("ERROR : " + e.getMessage()),
                        () -> System.out.println("COMPLETE"));
    }

}
