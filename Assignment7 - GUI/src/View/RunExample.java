package View;

import Controller.Controller;

import java.io.IOException;

public class RunExample extends Command {
    private Controller ctrl;

    public RunExample(String key, String desc,Controller c){
        super(key, desc);
        this.ctrl=c;
    }

    @Override
    public void execute() {
        try {
            ctrl.allStep();
        }catch (Exception e){
            System.out.print(e.getMessage());
        }
    }
}