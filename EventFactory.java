//Factory Method for Event Creation

public class EventFactory {

    public static Event createEvent(String eventType) {
        switch (eventType.toLowerCase()) {
            case "invasion":
                return new InvasionEvent();
            case "famine":
                return new FamineEvent(); // Ensure this case exists
            case "plague":
                return new PlagueEvent();
            default:
                throw new IllegalArgumentException("Unknown event type: " + eventType);
        }
    }
}