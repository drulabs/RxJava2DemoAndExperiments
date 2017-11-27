package org.drulabs.rx;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

import java.util.concurrent.TimeUnit;

/**
 * Created by kaushald on 23/11/17.
 */
public class RxRun {

    public static void main(String[] args) {
        System.out.println("\nStarting program...............................\n");

        Observable<String> os = Observable.just("KK", "Rx", "DD", "Java");
        Observable<Long> coldObv = Observable.interval(500, TimeUnit.MILLISECONDS);
        ConnectableObservable<Long> hotObv = Observable.interval(500, TimeUnit.MILLISECONDS).publish();

        os.flatMap(s -> Observable.fromArray(s.split("")))
                .flatMap(s -> Observable.fromArray(s.split("")))
                .map(s -> s + "x")
                .doOnNext(System.out::println)
                .doOnComplete(() -> System.out.println("Finished"))
                .subscribe();

        hotObv.subscribeOn(Schedulers.io())
                .doOnNext(i -> System.out.println("HoT First: " + i))
                .subscribe();

        hotObv.connect();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n.................................................\n");

        //coldObv.doOnNext(i -> System.out.println("Second: " + i)).subscribe();
        hotObv.subscribeOn(Schedulers.io())
                .doOnNext(i -> System.out.println("HoT 2nd: " + i))
                .subscribe();

        os.flatMap(s -> Observable.fromArray(s.split("")))
                .map(s -> s + "y")
                .doOnNext(s -> System.out.println(s))
                .doOnComplete(() -> System.out.println("Finished"))
                .subscribe();

        System.out.println("\nTerminated.......................................\n");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        PublishSubject<Integer> pubSource = PublishSubject.create();

        pubSource.subscribe(i -> {
            System.out.println("First subscriber: " + i);
        });

        pubSource.onNext(1);
        pubSource.onNext(2);
        pubSource.onNext(3);

        pubSource.subscribe(i -> System.out.println("2nd subscriber: " + i)
                , throwable -> System.out.println("2nd subscriber: ERROR: " + throwable.getLocalizedMessage())
                , () -> System.out.println("2nd subscriber: complete"));

        pubSource.onNext(4);
        pubSource.onNext(5);
        pubSource.onComplete();

    }

}
