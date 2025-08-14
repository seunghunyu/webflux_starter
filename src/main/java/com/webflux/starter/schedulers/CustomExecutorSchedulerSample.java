package com.webflux.starter.schedulers;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomExecutorSchedulerSample {
    public static void main(String[] args){

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Flux.range(1,5)
                .map(i->{
                    System.out.println("Processing Value : " + i + " on thread: " + Thread.currentThread().getName());
                    return i;
                })
                .subscribeOn(Schedulers.fromExecutorService(executorService))
                .subscribe(i -> System.out.println("Received value : " + i + " on thread: " + Thread.currentThread().getName()));

        executorService.shutdown();
    }

}
