package reactorWeb;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactorWeb.MyEventSource.MyEvent;

/**
 * 自定义数据流
 * 
 * @author ggk
 * @data 2019年3月29日上午10:36:45
 */
public class TestGen {

	@Test
	public void testGenerate() {
		final AtomicInteger ai = new AtomicInteger(1);
		Flux.generate(sink -> {
			sink.next(ai.get() + ":" + new Date());
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (ai.incrementAndGet() >= 5) {
				sink.complete();
			}
		}).subscribe(System.out::println);
	}

	@Test
	public void testGenerate1() {
		Flux.generate(() ->1,
						(count,sink) ->{
							sink.next(count+":"+new Date());
							try {
								TimeUnit.SECONDS.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(count>=5) {
								sink.complete();
							}
							return count+1;
						}).subscribe(System.out::println);
	}
	@Test
	public void testGenerate2() {
		Flux.generate(() ->1,
				(count,sink) ->{
					sink.next(count+":"+new Date());
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(count>=5) {
						sink.complete();
					}
					return count+1;
				},System.out::println)
				.subscribe(System.out::println);
	}
	@Test
	public void testCreate() throws InterruptedException {
		MyEventSource es=new MyEventSource();
		Flux.create(sink ->{es.register(new MyEventListener() {
			public void onNewEvent(MyEvent event) {
				sink.next(event);
				
			}
			public void onEventStopped() {
				sink.complete();
			}
		});}).subscribe(System.out::println);
		
		for(int i=0;i<20;i++) {
			Random rendom=new Random();
			TimeUnit.MILLISECONDS.sleep(rendom.nextInt(1000));
			es.newEvent(new MyEventSource.MyEvent(new Date(),"Event-"+i));
		}
		es.eventStopped();
	}
	
}
