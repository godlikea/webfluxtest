package com.kai.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
/**
 * 第一个响应式
 * @author ggk
 * @data 2019年3月27日上午8:55:53
 */
@RestController
@RequestMapping("/mono")
public class OneController {

	@GetMapping("/look")
	public Mono<String> getHelloWord(){
		return Mono.just("this is look");
	}
}
