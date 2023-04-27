package q1;

import java.util.Date;

public abstract class  Alarm {
    protected Date actionTime;
    protected String address;

    public Alarm(String address) {
        this.address = address;
        this.actionTime = new Date();
    }

    public abstract void action();

}
