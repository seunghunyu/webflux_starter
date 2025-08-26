package com.webflux.starter.subscribe.buffer;

import reactor.core.publisher.Flux;

public class BufferWhileSample {
    public static void main(String[] args){
        Flux<Integer> flux = Flux.range(1, 10);

        //Predicate가 true를 반환할 때까지의 버퍼를 생성
        flux.bufferWhile(i -> i < 5)
                .subscribe(bufferedData ->{
                    System.out.println("Buffered Data: " + bufferedData);
                });
    }

}
