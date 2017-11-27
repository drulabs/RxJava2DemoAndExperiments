package org.drulabs.rx;

import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;
import org.reactivestreams.Publisher;

import java.util.concurrent.Callable;

/**
 * Created by kaushald on 23/11/17.
 */
public class Tester {

    private TextView textView;

    class TextView {
        void setText(String text) {

        }
    }

    public Tester() {
        Observable<String> x = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("");
                e.onComplete();
            }
        });

        Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(SingleEmitter<String> e) throws Exception {

            }
        });
        Schedulers.io();

        x.groupBy(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Exception {
                return null;
            }
        }).subscribe(new Consumer<GroupedObservable<Integer, String>>() {
            @Override
            public void accept(GroupedObservable<Integer, String> isgo) throws Exception {
                isgo.getKey();
            }
        });
        Observable.defer(new Callable<ObservableSource<?>>() {
            @Override
            public ObservableSource<Integer> call() throws Exception {
                return Observable.just(1, 2, 3, 4);
            }
        });

        Completable c;
        Maybe<String> ms;
        MaybeObserver mo;
        ObservableSource os;
        SingleSource ss;
        MaybeSource mss;
        CompletableSource cs = Completable.complete();
        Flowable<String> fs = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {

            }
        }, BackpressureStrategy.DROP);
        fs.compose(new FlowableTransformer<String, Long>() {
            @Override
            public Publisher<Long> apply(Flowable<String> upstream) {
                return null;
            }
        });

        x.compose(new ObservableTransformer<String, Integer>() {
            @Override
            public ObservableSource<Integer> apply(Observable<String> upstream) {
                return null;
            }
        });

        Disposable d = x.subscribe(new Consumer<String>() {
                                       @Override
                                       public void accept(String s) throws Exception {

                                       }
                                   }, new Consumer<Throwable>() {
                                       @Override
                                       public void accept(Throwable throwable) throws Exception {

                                       }
                                   }
        );

        x.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        Single<String> xy;


        x.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {

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
