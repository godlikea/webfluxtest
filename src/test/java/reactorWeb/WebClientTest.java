package reactorWeb;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class WebClientTest {

	@Test
	public void testWebClient() throws InterruptedException {
		WebClient client = WebClient.create("http://localhost:8010");
		Mono<String> mono = client.get()
		.uri("/mono/look")
		.retrieve()
		.bodyToMono(String.class);
		mono.subscribe(System.out::println);
		TimeUnit.SECONDS.sleep(1);
	}
	
	@Test
	public void testTime() {
		 WebClient build = WebClient.builder().baseUrl("http://localhost:8010").build();
		 build.get().uri("/times")
		 .accept(MediaType.TEXT_EVENT_STREAM)
		 .retrieve()
		 .bodyToFlux(String.class)
		 .log()
		 .take(10)
		 .blockLast();
	}
}
