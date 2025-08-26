package com.webflux.starter.subscribe.buffer;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class BufferDurationSample {
    public void main(String[] args) throws InterruptedException {
        Flux<Long> fastPublisher = Flux.interval(Duration.ofMillis(100)).take(10);

        //데이터를 묶어 버퍼링
        fastPublisher
                .buffer(3)
                .subscribe(bufferedData -> {
                    System.out.println("Buffered data : " + bufferedData);
                });

        //Flux가 끝날 때까지 대기
        Thread.sleep(3000);
        
    }
}
