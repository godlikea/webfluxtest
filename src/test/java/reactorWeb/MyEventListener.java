package reactorWeb;

public interface MyEventListener {
	
	void onNewEvent(MyEventSource.MyEvent event);
	void onEventStopped();
}
