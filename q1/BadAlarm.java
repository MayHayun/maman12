package q1;
// class for a bad alarm exception in that the info that was provided is not valid
public class BadAlarm extends Exception {
    public BadAlarm(String message) {
        super(message);
    }
}