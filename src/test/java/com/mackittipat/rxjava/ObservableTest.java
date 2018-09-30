package com.mackittipat.rxjava;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.operators.observable.ObservableRange;
import io.reactivex.observables.ConnectableObservable;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class ObservableTest {

    @Test
    public void testObservableJust() {
        Observable<String> source = Observable.just("1", "2", "3", "4", "5");
        source.subscribe(s -> System.out.println("NEXT : " + s));
    }

    @Test
    public void testObservableCreate() {
        Observable<String> source = Observable.create(emitter -> {
            emitter.onNext("1");
            emitter.onNext("2");
            emitter.onNext("3");
            emitter.onError(new Exception("There is something wrong!"));
            emitter.onNext("4");
            emitter.onNext("5");
            emitter.onComplete();

        });
        source.subscribe(
                s -> System.out.println("NEXT : " + s),
                e -> System.out.println("ERROR : " + e.getMessage())
        );
    }

    @Test
    public void testObservableIterable() {
        Observable<String> source = Observable.fromIterable(Arrays.asList("1", "2", "3", "4", "5"));
        source.subscribe(s -> System.out.println("NEXT : " + s));
    }

    @Test
    public void testObservableRange() {
        Observable<Integer> source = Observable.range(1,10);
        source.subscribe(s -> System.out.println("NEXT : " + s));
    }

    @Test
    public void testObservableInterval() throws InterruptedException {
        Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS);
        source.subscribe(s -> System.out.println("NEXT : " + s));
        Thread.sleep(10000);
    }

    @Test
    public void testObserver() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("NEXT : " + s);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("ERROR : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("COMPLETE");
            }
        };

        Observable<String> source = Observable.just("1", "2", "3", "4", "5");
        source.subscribe(observer);
    }

    @Test
    public void testColdObservable() {
        Observable<Integer> source = Observable.just(1, 2, 3, 4, 5);
        source.map(i -> i * 2).subscribe(s -> System.out.println("1 NEXT : " + s));
        source.filter(i -> i > 2).subscribe(s -> System.out.println("2 NEXT : " + s));
        source.subscribe(s -> System.out.println("3 NEXT : " + s));
    }

    @Test
    public void testHotObservable() {
        Observable<Integer> source = Observable.just(1, 2, 3, 4, 5);
        ConnectableObservable<Integer> hotSource = source.publish();
        hotSource.map(i -> i * 2).subscribe(s -> System.out.println("1 NEXT : " + s));
        hotSource.subscribe(s -> System.out.println("2 NEXT : " + s));
        hotSource.connect();
    }

    @Test
    public void testSingle() {
        Single<Integer> source = Single.just(1);
        source.subscribe(s -> System.out.println("NEXT : " + s));
    }

}
