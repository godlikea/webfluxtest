package reactorWeb;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactorWeb.MyEventSource.MyEvent;

public class TestBackRes {
	
	private MyEventSource es=new MyEventSource();
	@Test
	public void testCreate() {
		
	}
	
	private Flux<MyEventSource.MyEvent> create(FluxSink.OverflowStrategy startegy){
		return Flux.create(sink ->es.register(new MyEventListener() {
			
			@Override
			public void onNewEvent(MyEvent event) {
				System.out.println("publish>>>"+event.getMessage());
				sink.next(event);
			}
			
			@Override
			public void onEventStopped() {
				sink.complete();
			}
		}),startegy); 
	}
}
