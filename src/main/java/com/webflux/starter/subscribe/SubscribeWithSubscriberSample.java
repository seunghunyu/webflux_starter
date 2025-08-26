package com.webflux.starter.subscribe;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

public class SubscribeWithSubscriberSample {
    public static void main(String[] args){
        Flux<String> flux = Flux.just("A","B","C");

        //Custom Subscriber 구현
        Subscriber<String> subscriber = new Subscriber<String>() {
            private Subscription subscription;

            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription = subscription;
                System.out.println("Subscribed!");
                // 요청할 데이터 개수 설정(2개의 데이터만 요청)
                subscription.request(2);
            }

            @Override
            public void onNext(String data) {
                System.out.println("Received: " + data);
                //원하는 로직에 따라 추가 요청 또는 구독 취소 가능
                if(data.equals("B")){
                    System.out.println("Canceling subscription after receiving 'B'");
                    subscription.cancel(); // 구독 취소
                }
            }

            @Override
            public void onError(Throwable throwable) {
                System.err.println("Error occurred : " + throwable.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("Completed ! ");
            }
        };
        //Subscriber를 사용한 subscirbe, 이 과정에서 내부적으로 Subscription을 생성하고 onSubscribe 메소드를 통해 전달함.
        flux.subscribe(subscriber);
    }
}
