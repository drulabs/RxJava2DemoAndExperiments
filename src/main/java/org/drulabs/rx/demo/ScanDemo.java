package org.drulabs.rx.demo;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kaushald on 25/11/17.
 */
public class ScanDemo implements Executable {

    private Observable<Integer> source;

    public ScanDemo() {
        source = Observable.just(1, 2, 3, 4, 5);
    }

    @Override
    public void prepare() {
        source = source.map(integer -> integer * 10);
    }

    @Override
    public void execute() {
        Disposable d = source.subscribeOn(Schedulers.io())
                .scan((x, y) -> x + y)
                .doOnNext(integer -> System.out.println("Scan: " + integer))
                .doOnComplete(() -> System.out.println("Scan: onComplete"))
                .subscribe();
    }
}
