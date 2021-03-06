package Model;

public class Cylinder implements IObject{
    double volume;
    private final String TYPE = "cylinder";

    public Cylinder(double volume){this.volume=volume;}

    public double getVolume(){return this.volume;}

    public String getString(){
        String s=new String();
        s += ("Type: " + this.TYPE + "\n" + "Volume: " + Double.toString(this.getVolume())+"\n");
        return s;
    }

}
