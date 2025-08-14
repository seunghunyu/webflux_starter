package com.webflux.starter.controller;

import com.webflux.starter.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Slf4j
@RestController
@RequestMapping("/argument")
public class MethodArgumentExample {

    @PostMapping("/echo")
    public Mono<String> echo(ServerHttpRequest request){
        return request
                .getBody()
                .map(
                        dataBuffer -> {
                            byte[] bytes = new byte[dataBuffer.readableByteCount()];
                            dataBuffer.read(bytes);
                            return new String(bytes, StandardCharsets.UTF_8);
                        })
                .collectList()
                .map(list -> String.join("",list));
    }

    @GetMapping("/custom-response")
    public Mono<Void> customResponse(ServerHttpResponse response){
        response.setStatusCode(HttpStatus.ACCEPTED);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.getHeaders().add("X-Custom-Header", "Custom-Header");

        String json = "{\"message\": \"Hello, WebFlux!\"}";
        return response.writeWith(Mono.just(response.bufferFactory().wrap(json.getBytes(StandardCharsets.UTF_8))));
    }

    @GetMapping("/user-agent")
    public Mono<String> getUserAgent(ServerWebExchange exchange){ // ServerHttpRequest, ServerHttpResponse 둘다 사용하는 경우
        String userAgent = exchange.getRequest().getHeaders().getFirst("User-Agent");
        return Mono.just("User-Agent: " + (userAgent != null ? userAgent : "Unknown"));
    }

}
