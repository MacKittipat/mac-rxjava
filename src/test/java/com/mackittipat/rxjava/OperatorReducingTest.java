package com.mackittipat.rxjava;

import io.reactivex.Observable;
import org.junit.Test;

public class OperatorReducingTest {

    @Test
    public void testCount() {
        Observable<Integer> source = Observable.just(1, 2, 3, 4, 5);
        source
                .count()
                .subscribe(s -> System.out.println("SUCCESS : " + s));
    }

    @Test
    public void testReduce() {
        Observable<Integer> source = Observable.just(1, 2, 3, 4, 5);
        source
                .reduce((sum, next) -> sum + next)
                .subscribe(s -> System.out.println("SUCCESS : " + s));
    }

    @Test
    public void testAll() {
        Observable<Integer> source = Observable.just(1, 2, 3, 4, 5);
        source
                .all(s -> s > 6)
                .subscribe(s -> System.out.println("SUCCESS : " + s));
    }

    @Test
    public void testAny() {
        Observable<Integer> source = Observable.just(1, 2, 3, 4, 5);
        source
                .any(s -> s > 3)
                .subscribe(s -> System.out.println("SUCCESS : " + s));
    }

    @Test
    public void testContains() {
        Observable<Integer> source = Observable.just(1, 2, 3, 4, 5);
        source
                .contains(2)
                .subscribe(s -> System.out.println("SUCCESS : " + s));
    }
}
