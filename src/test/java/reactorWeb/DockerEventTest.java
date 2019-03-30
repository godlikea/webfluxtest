package reactorWeb;

import org.junit.Test;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Event;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.command.EventsResultCallback;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

public class DockerEventTest {
	
	@Test
	public void dockerEventToFlux(){
		collectDockerEvents().subscribe(System.out::println);
	}
	
	public Flux<Event> collectDockerEvents(){
		DockerClient docker=DockerClientBuilder.getInstance().build();
		return Flux.create((FluxSink<Event> sink)->{
			EventsResultCallback callBack=new EventsResultCallback() {
				 
	                public void onNext(Event event) {
	                    sink.next(event);
	                }
			};
			 docker.eventsCmd().exec(callBack);
		});
	}
}
