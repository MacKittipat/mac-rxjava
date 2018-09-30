package com.mackittipat.rxjava;

import io.reactivex.Observable;
import org.junit.Test;

public class OperatorErrorTest {

    @Test
    public void testOnErrorReturnItem() {
        Observable<Integer> source = Observable.just(1, 3, 0, 2, 4);
        source
                .map(i -> 100 / i)
                .onErrorReturnItem(-1)
                .subscribe(
                        s -> System.out.println("NEXT : " + s),
                        e -> System.out.println("ERROR : " + e.getMessage()),
                        () -> System.out.println("COMPLETE"));
    }

    @Test
    public void testOnErrorReturn() {
        Observable<Integer> source = Observable.just(1, 3, 0, 2, 4);
        source
                .map(i -> 100 / i)
                .onErrorReturn(s -> -1)
                .subscribe(
                        s -> System.out.println("NEXT : " + s),
                        e -> System.out.println("ERROR : " + e.getMessage()),
                        () -> System.out.println("COMPLETE"));
    }

    @Test
    public void testRetry() {
        Observable<Integer> source = Observable.just(1, 3, 0, 2, 4);
        source
                .map(i -> 100 / i)
                .retry(3)
                .subscribe(
                        s -> System.out.println("NEXT : " + s),
                        e -> System.out.println("ERROR : " + e.getMessage()),
                        () -> System.out.println("COMPLETE"));
    }


}
