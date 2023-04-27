package q1;

import java.util.Date;

public class Fire extends Smoke {

    protected boolean active;

    public Fire(String address, String operatorName) throws BadAlarm {
        super(address, operatorName);
        this.active = true;
    }
    @Override
    public void action() {
        System.out.println("this is a fire kind alarm\n" + "the operator name is: " + this.operatorName +
                "\nthe address: " + this.address + "\nthe action time: " + this.actionTime.toString().substring(10,16));
        active = false;
    }
}
