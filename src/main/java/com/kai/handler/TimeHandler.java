package com.kai.handler;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 时间处理
 * @author ggk
 * @data 2019年3月27日上午9:54:39
 */
@Component
public class TimeHandler {
	/**
	 * 获取时间
	 * @author ggk
	 * @data 2019年3月27日上午9:58:47
	 * @param request
	 * @return
	 */
	public Mono<ServerResponse> getTime(ServerRequest request){
		return ServerResponse.ok()
				.contentType(MediaType.TEXT_PLAIN)
				.body(Mono.just("Now is"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))),String.class);
	}
	/**
	 * 获取当天时间
	 * @author ggk
	 * @data 2019年3月27日上午10:02:16
	 * @param request
	 * @return
	 */
	public Mono<ServerResponse> getToday(ServerRequest request){
		return ServerResponse.ok()
				.contentType(MediaType.TEXT_PLAIN)
				.body(Mono.just("Today is"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))), String.class);
	}
	/**
	 * SSE 服务器推送
	 * @author ggk
	 * @data 2019年3月27日上午10:27:18
	 * @param request
	 * @return
	 */
	public Mono<ServerResponse> sendTimePerSec(ServerRequest request){
		return ServerResponse.ok()
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(Flux.interval(Duration.ofSeconds(1))
				.map(l ->LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))),String.class);
	}
}
