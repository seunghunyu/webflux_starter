package com.webflux.starter.basic;

import com.webflux.starter.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/controller")
public class BasicController {

    @GetMapping("/hello")
    public Mono<String> hello(){
        return Mono.just("Hello, seunghun!");
    }

    @GetMapping("/hello/{name}")
    public Mono<String> hello(@PathVariable String name){
        return Mono.just("Hello, " + name + "!");
    }

    @GetMapping("/greet")
    public Mono<String> greet(@RequestParam(defaultValue = "Guest") String name){
        return Mono.just("Hello," + name + "!");
    }

    @GetMapping("/check-header")
    public Mono<String> checkHeader(@RequestHeader(name = "X-Request-ID", required = false) String requestId){
        if(requestId == null){
            return Mono.just("X-Request-ID is null");
        }
        return Mono.just("Request Id:" + requestId);
    }

    @PostMapping("/create")
    public Mono<ResponseEntity<String>> create(@RequestBody Mono<User> userMono){
        return userMono.map(user->ResponseEntity.ok("User created: "+ user.getName()));
    }


}
