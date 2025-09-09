package com.webflux.starter.reactor;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        MyPublisher publisher = new MyPublisher();
        MySubscriber subscriber = new MySubscriber();

        publisher.subscribe(subscriber);

        //데이터 발행
        publisher.notifySubscribers(new Message(0, "Initail Message"));

        Thread.sleep(200);
        publisher.close();
    }

}
