package com.webflux.starter.subscribe.take;

import reactor.core.publisher.Flux;

import java.sql.SQLOutput;
import java.time.Duration;

public class TakeUntilOtherSample {
    public static void main(String[] args) throws InterruptedException {
        Flux<Long> flux = Flux.interval(Duration.ofMillis(100));

        Flux<Long> other = Flux.interval(Duration.ofSeconds(1)).take(1);

        //종료 신호가 올 때까지 데이터 방출
        flux.takeUntilOther(other)
                        .subscribe(data -> System.out.println("Received:" + data));


        //값이 5가 될 때까지 데이터 방출
        flux.takeUntil(i -> i == 5)
                .subscribe(data -> System.out.println("Received: " + data));

        Thread.sleep(2000);
    }

}
