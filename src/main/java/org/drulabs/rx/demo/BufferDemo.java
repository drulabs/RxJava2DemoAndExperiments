package org.drulabs.rx.demo;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.util.List;

/**
 * Created by kaushald on 25/11/17.
 */
public class BufferDemo implements Executable {

    Observable<List<Integer>> source;

    public BufferDemo() {
        source = Observable.fromArray(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 1).buffer(5);
    }

    @Override
    public void prepare() {

        source = source.subscribeOn(Schedulers.io())
                .doOnComplete(() -> System.out.print("Buffer complete"))
                .doOnNext(integers -> {
                    System.out.println("BufferDemo: onNext received. " + integers);
                    System.out.println("BufferDemo: onNext processed");
                });

    }

    @Override
    public void execute() {

        source.subscribe(new Observer<List<Integer>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Integer> integers) {
                System.out.println("BufferDemo: Observer received. " + integers);
                System.out.println("BufferDemo: Observer processed");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }
}
