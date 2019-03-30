package reactorWeb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class MyEventSource {
	private List<MyEventListener> listener;
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class MyEvent{
		private Date timeStemp;
		private String message;
	}
	
	public MyEventSource() {
		this.listener=new ArrayList<>();
	}
	/***
	 * 注册监听
	 * @author ggk
	 * @data 2019年3月29日上午11:58:27
	 * @param listener
	 */
	public void register(MyEventListener listener) {
		this.listener.add(listener);
	}
	
	public void newEvent(MyEvent event) {
		for(MyEventListener listener:listener) {
			listener.onNewEvent(event);
		}
	}
	
	public void eventStopped() {
		for(MyEventListener listener:listener) {
			listener.onEventStopped();
		}
	}
}
