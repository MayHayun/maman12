package q1;

// class for an elevator alarm
public class Elevator extends Alarm{
    protected int floor;

    public Elevator(String address, int floor){
        super(address);
        this.floor = floor;
    }
    @Override
    public void action() {
        System.out.println("this is an elevator kind alarm\n" + "\nthe address: " + this.address
                +"\nin floor: " + floor + "\nthe action time: " + this.actionTime.toString().substring(9,15));
    }

    public void reset(){
        this.floor = 0;
    }
}