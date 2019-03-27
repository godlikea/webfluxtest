package com.kai.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.kai.handler.TimeHandler;

/**
 * RouterFunction
 * @author ggk
 * @data 2019年3月27日上午10:04:24
 */
@Configuration
public class RouterConfig {
	
	@Autowired
	private TimeHandler timeHandler;
	
	@Bean
	public RouterFunction<ServerResponse> timeRouter(){
		return RouterFunctions
				.route(GET("/time"),req -> timeHandler.getTime(req))
				.andRoute(GET("/data"),timeHandler::getToday)
				.andRoute(GET("/times"), timeHandler::sendTimePerSec);
	}

	private RequestPredicate GET(String string) {
		return RequestPredicates.GET(string);
	}
}
