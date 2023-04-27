package q1;

// class for a smoke alarm
public class Smoke extends Alarm {

    protected String operatorName;
    public Smoke(String  operatorName, String address) throws BadAlarm{
        super(address);
        if(address == null || address.isEmpty()) //check if address input is invalid
            throw new BadAlarm("this address is not valid");
        this.operatorName = operatorName;
    }
    @Override
    public void action() {
        System.out.println("this is a smoke kind alarm\n" + "the operator name is: " + this.operatorName +
                "\nthe address: " + this.address + "\nthe action time: " + this.actionTime.toString().substring(9,15));
    }
}