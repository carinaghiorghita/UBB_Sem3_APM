package Model;

public class Cube implements IObject{

    double volume;
    private final String TYPE = "cube";

    public Cube(double volume){this.volume=volume;}

    public double getVolume(){return this.volume;}

    public String getString(){
        String s=new String();
        s += ("Type: " + this.TYPE + "\n" + "Volume: " + Double.toString(this.getVolume())+"\n");
        return s;
    }
}
