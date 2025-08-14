package com.webflux.starter.basic;

import com.webflux.starter.user.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class BasicRouter {
    @Bean
    public RouterFunction<ServerResponse> basicRoute(){
        return RouterFunctions.route()
                .GET("/api/router/hello",
                        request -> ServerResponse.ok().bodyValue("Hello, seunghun!")
                )
                .GET(
                        "/api/router/hello/{name}",
                        request -> {
                            String name = request.pathVariable("name");
                            return ServerResponse.ok().bodyValue("Hello," + name + "!");
                })
                .GET(
                        "/api/router/greet",
                        request -> {
                            String name = request.queryParam("name").orElse("Guest");
                            return ServerResponse.ok().bodyValue("Hello," + name + "!");
                        }
                )
                .GET("/api/router/check-header",
                        request -> {
                            String requestId = request.headers().firstHeader("X-Request-Id");
                            String response =
                                    (requestId == null) ? "No Request Id found" : "Request Id:" + requestId;
                            return ServerResponse.ok().bodyValue(response);
                        }
                ).POST("/api/router/create",
                        request -> request.bodyToMono(User.class)
                                .flatMap(
                                        user->ServerResponse.ok().bodyValue("User Created:" + user.getName()))
                ).build();
    }


    @Bean
    public RouterFunction<ServerResponse> basicRoutes(){
        return RouterFunctions.route()
                .GET("/api/router/hello",
                        request -> ServerResponse.ok().bodyValue("Hello, seunghun!")).build();
    }

    @Bean
    public RouterFunction<ServerResponse> basicRoutes2(){
        return RouterFunctions.route()
                .GET("/api/router/hello/{name}",
                        request -> {
                            String name = request.pathVariable("name");
                            return ServerResponse.ok().bodyValue("Hello, seunghun!");
                        }).build();
    }
    @Bean
    public RouterFunction<ServerResponse> basicRoutes3(){
        return RouterFunctions.route()
                .GET("/api/router/check-header",
                        request -> {
                            String requestId = request.headers().firstHeader("X-Request-Id");
                            String response =
                                    (requestId == null) ? "No Request Id found" : "Request Id:" + requestId;
                            return ServerResponse.ok().bodyValue(response);
                        }).build();
    }

    @Bean
    public RouterFunction<ServerResponse> basicRoutes4(){
        return RouterFunctions.route()
                .POST("/api/router/create",
                        request -> request.bodyToMono(User.class)
                                .flatMap(
                                        user->ServerResponse.ok().bodyValue("User Created:" + user.getName()))).build();
    }
}
