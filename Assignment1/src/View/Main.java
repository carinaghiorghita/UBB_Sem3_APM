package View;

import java.util.Scanner;
import Controller.Controller;
import Model.Cube;
import Model.Cylinder;
import Model.IObject;
import Model.Sphere;
import Repository.Repository;
import Repository.MyException;

public class Main {

    public static void printMenu(){
        System.out.println("1.Add object\n"+
                "2.Delete object\n"+
                "3.Print all objects\n"+
                "4.Filter objects by volume (volume greater than 25 cm3)\n"+
                "0.Exit\n"+
                "Your option:");
    }

    public static void main(String[] args) {
        Repository repo = new Repository(10);
        Controller ctrl = new Controller(repo);

        Scanner in = new Scanner(System.in);

        printMenu();
        int opt = in.nextInt();

        while (opt != 0) {
            if (opt == 1) {
                Scanner inShape = new Scanner(System.in);
                System.out.println("Enter shape:");
                String shape = inShape.nextLine();
                System.out.println("Enter volume:");
                double vol = inShape.nextDouble();
                try {
                    if (shape.equals("cube")) {
                        Cube c = new Cube(vol);
                        ctrl.add(c);
                    } else if (shape.equals("sphere")) {
                        Sphere s = new Sphere(vol);
                        ctrl.add(s);
                    } else if (shape.equals("cylinder")) {
                        Cylinder c = new Cylinder(vol);
                        ctrl.add(c);
                    } else throw new MyException("Invalid shape");
                } catch (MyException ex) {
                    System.out.println("Error:" + ex + "\n");
                }
            } else if (opt == 2) {
                Scanner inIndex = new Scanner(System.in);
                System.out.println("Enter index of object to be deleted:");
                int index = inIndex.nextInt();
                try {
                    ctrl.remove(index);
                } catch (MyException ex) {
                    System.out.println("Error:" + ex + "\n");
                }

            } else if (opt == 3) {
                try{
                    IObject[] obj= ctrl.getAll();
                    for(int i=0;i< ctrl.getSize();++i) {
                        System.out.println(obj[i].getString());
                    }
                }catch (MyException ex) {
                    System.out.println("Error:" + ex + "\n");
                }

            } else if (opt == 4) {
                try {
                    IObject[] filteredObjects = ctrl.filter();

                    for (int i = 0; i < filteredObjects.length; ++i) {
                        System.out.println(filteredObjects[i].getString());
                    }
                }catch (MyException ex){
                    System.out.println("Error:" + ex + "\n");
                }

            } else {
                System.out.println("Invalid option");
            }
            printMenu();
            opt = in.nextInt();
        }

/*
        Cube o1 = new Cube(30);
        Cube o2 = new Cube(20.5);
        Sphere o3 = new Sphere(26.3);
        Sphere o4 = new Sphere(15);
        Cylinder o5 = new Cylinder(27.2);
        Cylinder o6 = new Cylinder(2.4);

        try {
            ctrl.add(o1);
            ctrl.add(o2);
            ctrl.add(o3);
            ctrl.add(o4);
            ctrl.add(o5);
            ctrl.add(o6);
        }
        catch (MyException ex) {
            System.out.println("Error:" + ex + "\n");
        }

        IObject[] filteredObjects= ctrl.filter();
        for(int i=0;i< filteredObjects.length;++i){
            System.out.println(filteredObjects[i].getString());
        }

*/
    }
}
