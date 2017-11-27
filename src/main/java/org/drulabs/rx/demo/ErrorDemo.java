package org.drulabs.rx.demo;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kaushald on 25/11/17.
 */
public class ErrorDemo implements Executable {

    private Observable<Integer> sourcePre;
    private Observable<Integer> sourcePost;
    private Observable<Integer> sourceErr;

    private Observable<Integer> source;

    public ErrorDemo() {
        sourcePre = Observable.fromArray(1, 2, 3, 4);
        sourceErr = Observable.error(new Exception("Some sorta error"));
        sourcePost = Observable.fromArray(5, 6, 7, 8);
    }


    @Override
    public void prepare() {
        source = sourcePre.concatWith(sourceErr).concatWith(sourcePost);
    }

    @Override
    public void execute() {
//        sourcePost.subscribe();
        source.subscribeOn(Schedulers.io())
                .doOnComplete(() -> System.out.println("Error: onComplete"))
                .doOnNext(integer -> System.out.println("NOT Error: " + integer))
                .subscribe();

    }
}
