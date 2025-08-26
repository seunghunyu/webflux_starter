package com.webflux.starter.subscribe;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class SubscribeWithBaseSubscriberSample {
    public static void main(String[] args){
        Flux<String> flux = Flux.just("A","B","C","D");

        //Custom Subscriber 구현
        BaseSubscriber<String> subscriber = new BaseSubscriber<String>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
//                super.hookOnSubscribe(subscription);
                System.out.println("Subscribed");
                request(1);
            }

            @Override
            protected void hookOnNext(String value) {
//                super.hookOnNext(value);
                System.out.println("Received: " + value);
                if("B".equals(value)){
                    System.out.println("Received 'B' , requesting 2 more items ");
                    request(2);
                }else{
                    System.out.println("Requesting 1 more item");
                    request(1);
                }
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



        //Subscriber를 사용한 subscirbe, 이 과정에서 내부적으로 Subscription을 생성하고 onSubscribe 메소드를 통해 전달함.
        flux.subscribe(subscriber);
    }
}
