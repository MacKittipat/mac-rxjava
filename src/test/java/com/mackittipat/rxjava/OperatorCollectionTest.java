package com.mackittipat.rxjava;

import io.reactivex.Observable;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class OperatorCollectionTest {

    @Test
    public void testToList() {
        Observable<Integer> source = Observable.range(1, 10);
        source
                .toList()
                .subscribe(s -> System.out.println("SUCCESS : " + s));
    }

    @Test
    public void testToSortedList() {
        Observable<Integer> source = Observable.just(3, 2, 5, 1, 4);
        source
                .toSortedList()
                .subscribe(s -> System.out.println("SUCCESS : " + s));
    }

    @Test
    public void testMap() {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gramma", "Delta", "Epsilon");
        source
                .toMap(s -> s.charAt(0), String::length)
                .subscribe(s -> System.out.println("SUCCESS : " + s));
    }

    @Test
    public void testCollect() {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gramma", "Delta", "Epsilon");
        source
                .collect(LinkedHashSet::new, HashSet::add)
                .subscribe(s -> System.out.println("SUCCESS : " + s));
    }

}
