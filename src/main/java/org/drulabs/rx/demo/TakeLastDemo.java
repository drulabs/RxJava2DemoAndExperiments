package org.drulabs.rx.demo;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kaushald on 25/11/17.
 */
public class TakeLastDemo implements Executable {

    private Observable<Integer> source;

    public TakeLastDemo() {
        source = Observable.range(1, 50);
    }

    @Override
    public void prepare() {
        source = source.map(integer -> integer * 10);
    }

    @Override
    public void execute() {
        source.subscribeOn(Schedulers.io())
                .takeLast(5)
                .doOnNext(integer -> System.out.println("Scan: " + integer))
                .doOnComplete(() -> System.out.println("Scan: onComplete"))
                .subscribe();
    }
}
