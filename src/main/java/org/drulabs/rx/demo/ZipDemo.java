package org.drulabs.rx.demo;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kaushald on 25/11/17.
 */
public class ZipDemo implements Executable {

    private Observable<Integer> sourceA;
    private Observable<String> sourceB;
    private Observable<String> source;

    public ZipDemo() {
        sourceA = Observable.just(1, 2, 3, 4, 5);
        sourceB = Observable.just("A", "B", "C", "D", "E");
    }

    @Override
    public void prepare() {
        source = sourceA.zipWith(sourceB, (integer, s) -> integer + s);
    }

    @Override
    public void execute() {
        source.subscribeOn(Schedulers.io())
                .doOnNext(val -> System.out.println("Zip: " + val))
                .doOnComplete(() -> System.out.println("Zip: onComplete"))
                .subscribe();
    }
}
