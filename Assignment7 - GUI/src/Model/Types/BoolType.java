package Model.Types;

import Model.Values.BoolValue;
import Model.Values.IValue;
import Model.Values.IntValue;

public class BoolType implements IType{
    @Override
    public boolean equals(Object another){
        if(another instanceof BoolType)
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "bool";
    }

    @Override
    public IValue defaultValue() {
        IValue defVal=new BoolValue(false);
        return defVal;
    }
}
