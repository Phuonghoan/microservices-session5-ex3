package org.example.apigateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(
            ServerWebExchange exchange,
            GatewayFilterChain chain
    ) {

        // Lấy path của request
        String path = exchange.getRequest()
                .getPath()
                .value();

        // In thông tin request ra Console
        System.out.println("Incoming request to: " + path);

        // Cho request tiếp tục đi đến service đích
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
