package reactorWeb;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class TestScheduler {

	@Test
	public void testScheduling() {
		Flux.range(0, 10)
		//.log()
		.publishOn(Schedulers.newParallel("my1"))
		.log()
		.subscribeOn(Schedulers.newElastic("my2"))
		.log()
		.blockLast();
	}
}
