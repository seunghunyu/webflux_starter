package com.webflux.starter.subscribe;

import reactor.core.publisher.Flux;
import reactor.util.context.Context;

public class SubscribeWithConsumerSample {
    public static void main(String[] arsgs){
        Flux<String> flux = Flux.just("A","B","C")
                .doOnNext(data -> System.out.println("Processing: " + data))
                .contextWrite(context -> {
                    if(context.hasKey("request-id")){
                        System.out.println("Request ID found in context: " + context.get("request-id"));
                    }
                    return context;
                });

        flux.subscribe(
                data-> System.out.println("Received: " + data),
                error -> System.out.println("Error Received: " + error),
                () -> System.out.println("Completed!"),
                Context.of("request-id","1234") //initialContext 설정
        );

    }
}
