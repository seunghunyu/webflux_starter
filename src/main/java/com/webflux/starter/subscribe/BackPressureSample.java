package com.webflux.starter.subscribe;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;

public class BackPressureSample {
    public static void main(String[] args){
        Flux<Integer> fastPublisher = Flux.range(1, 100)
                .doOnRequest(n -> System.out.println("Requesting " + n + "items")) //요청할 때 로그
                .doOnNext(i -> System.out.println("Publishing: " + i)); //데이터가 방출될 때 로그

        //Custom Subscriber 구현
        BaseSubscriber<Integer> slowSubscriber = new BaseSubscriber<Integer>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
//                super.hookOnSubscribe(subscription);
                System.out.println("Subscribed with slow processing");
                request(1);
            }
            @Override
            protected void hookOnNext(Integer value) {
//                super.hookOnNext(value);
                System.out.println("Received: " + value);
                try{
                    Thread.sleep(1000); // 데이터를 처리하는 데 시간이 걸리는 시뮬레이션을 하기 위해 sleep
                }catch (InterruptedException e ){
                    e.printStackTrace();
                }
                //하나의 데이터를 처리한 후, 다음 데이터 하나를 요청
                request(1);
            }

            @Override
            protected void hookOnError(Throwable throwable) {
//                super.hookOnError(throwable);
                System.err.println("Error occurred: " +  throwable.getMessage());
            }

            @Override
            protected void hookOnComplete() {
//                super.hookOnComplete();
                System.out.println("Completed!");
            }

            @Override
            protected void hookOnCancel() {
//                super.hookOnCancel();
                System.out.println("Subcription cancelled");
            }
        };


        fastPublisher.subscribe(slowSubscriber);
    }
}
