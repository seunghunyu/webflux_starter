package com.webflux.starter.schedulers;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class NewBoundedSchedulerSample {
    public static void main(String[] args){
        Flux.range(1,5)
                .map(i->{
                    System.out.println("Processing Value : " + i + " on thread: " + Thread.currentThread().getName());
                    return i;
                })
                .publishOn(Schedulers.newBoundedElastic(2, 100, "bounded-elastic"))
                .subscribe(i -> System.out.println("Received value : " + i + " on thread: " + Thread.currentThread().getName()));
    }

}
