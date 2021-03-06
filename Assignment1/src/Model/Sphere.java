package Model;

public class Sphere implements IObject{
    double volume;
    private final String TYPE = "sphere";

    public Sphere(double volume){this.volume=volume;}

    public double getVolume(){return this.volume;}

    public String getString(){
        String s=new String();
        s += ("Type: " + this.TYPE + "\n" + "Volume: " + Double.toString(this.getVolume())+"\n");
        return s;
    }

}
