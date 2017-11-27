package org.drulabs.rx.demo;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * Created by kaushald on 25/11/17.
 */
public class HotObservableDemo implements Executable {

    private ConnectableObservable<Long> hotObv;

    public HotObservableDemo(){
        hotObv = Observable.interval(500, TimeUnit.MILLISECONDS).publish();
    }

    @Override
    public void prepare() {
        hotObv.connect();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute() {
        hotObv.subscribeOn(Schedulers.io())
                .doOnNext(i -> System.out.println("HoT First: " + i))
                .subscribe();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        hotObv.subscribeOn(Schedulers.io())
                .doOnNext(i -> System.out.println("HoT 2nd: " + i))
                .subscribe();
    }
}
