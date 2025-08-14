package com.webflux.starter.schedulers;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class NewSingleSchedulerSample {
    public static void main(String[] args){
        Flux.range(1,5)
                .map(i->{
                    System.out.println("Processing Value : " + i + " on thread: " + Thread.currentThread().getName());
                    return i;
                })
                .subscribeOn(Schedulers.newSingle("custom-single-thread"))
                .subscribe(i -> System.out.println("Received value : " + i + " on thread: " + Thread.currentThread().getName()));
    }

}
