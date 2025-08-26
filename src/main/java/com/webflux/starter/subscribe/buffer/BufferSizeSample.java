package com.webflux.starter.subscribe.buffer;

import reactor.core.publisher.Flux;

public class BufferSizeSample {
    public void main(String[] args){
        Flux<Integer> fastPublisher = Flux.range(1, 10);


        //데이터를 묶어 버퍼링
        fastPublisher
                .buffer(3)
                .subscribe(bufferedData -> {
                    System.out.println("Buffered data : " + bufferedData);
                });

    }
}
