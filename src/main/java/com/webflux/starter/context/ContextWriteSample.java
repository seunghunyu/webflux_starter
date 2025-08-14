package com.webflux.starter.context;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

@Slf4j
public class ContextWriteSample {
    public void main(String[] args){
        Mono<String> result =
                Mono.just("Hello")
                        .flatMap(
                                message ->
                                        Mono.deferContextual(
                                                contextView -> {
                                                    String initial = contextView.getOrDefault("key", "default");
                                                    String another = contextView.getOrDefault("anotherkey", "default");
                                                    log.info("#1 :" + initial + ", " + another);
                                                    return Mono.just("Mono: " + initial + ", "+ another);
                                                }
                                        )
                        )
                        .contextWrite(Context.of("key", "InitialValue"))
                        .contextWrite(context -> context.put("anotherKey", "NewValue"))
                        .flatMap(
                                message ->
                                        Mono.deferContextual(
                                            contextView -> {
                                                String initial = contextView.getOrDefault("key", "default");
                                                String another = contextView.getOrDefault("anotherkey", "default");
                                                log.info("#2 :" +initial + ", " + another);
                                                return Mono.just("Mono: " + initial + ", "+ another);
                                            }
                                        )
                        );

        result.subscribe(System.out::println);
    }

}
