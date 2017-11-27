package org.drulabs.rx.demo;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kaushald on 25/11/17.
 */
public class MapFilterReduceDemo implements Executable {

    private Observable<Integer> source;

    public MapFilterReduceDemo() {
        source = Observable.range(1, 20);
    }

    @Override
    public void prepare() {
        source = source.subscribeOn(Schedulers.computation())
                .map(integer -> integer * 3)
                .filter(integer -> integer < 10);

    }

    @Override
    public void execute() {
        source.doOnNext(integer -> System.out.println("MapFilterReduce: " + integer))
                .doOnComplete(() -> System.out.println("Complete"))
                .reduce((x, y) -> x + y)
                .doOnSuccess(integer -> System.out.println("Reduced: " + integer))
                .subscribe();
    }
}
