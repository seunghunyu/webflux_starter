package com.webflux.starter.subscribe.take;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class TakeDurationSample {
    public static void main(String[] args) throws InterruptedException {
        //500ms마다 데이털르 방출하는 Flux
        Flux<Long> flux = Flux.interval(Duration.ofMillis(500));

        //2초 동안 데이터 방출
        flux.take(Duration.ofSeconds(2))
                .subscribe(data -> System.out.println("Received: " + data));

        Thread.sleep(3000);
    }

}
