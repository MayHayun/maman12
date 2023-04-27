package q1;

import java.util.ArrayList;

public class TestAlarms {

    // process method to process the different kinds of alarms and use the action method
    public static void process(ArrayList<Alarm> alarms) {
        int smokeCount = 0;
        for ( int i = 0; i < alarms.size(); i++ ) {
            Alarm a = alarms.get(i);
            if (a instanceof Smoke) {
                smokeCount++;
                Smoke smoke = (Smoke) a;
                smoke.action();
            }
            else if (a instanceof Elevator) {
                Elevator elevator = (Elevator) a;
                elevator.action();
                elevator.reset();
            }
            else if (a instanceof Fire) {
                Fire fire = (Fire) a;
                fire.action();
            }
        }
        System.out.println("Number of smoke alarms: " + smokeCount);
    }

    public static void main(String[] args) {
        // making an array list of alarms and going through them with the required actions
        ArrayList<Alarm> alarms = new ArrayList<Alarm>();
        try {
            alarms.add(new Smoke("Greg", "Tlv"));
            alarms.add(new Smoke("George", "Tlv"));
            alarms.add(new Smoke("Roger", "Tlv"));
            alarms.add(new Elevator("Peter", 1));
            alarms.add(new Elevator("ronnie", 2));
            alarms.add(new Fire("Ben", "Tlv"));
            alarms.add(new Fire("Dean", "Tlv"));
            alarms.add(new Fire("Dana", "Tlv"));

            process(alarms);
        }
        catch (BadAlarm e) {
            System.out.println(e.getMessage());
        }
    }
}