package Model.Types;

import Model.Values.IValue;
import Model.Values.IntValue;

public class IntType implements IType{
    @Override
    public boolean equals(Object another){
        if(another instanceof IntType)
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "int";
    }

    @Override
    public IValue defaultValue() {
        IValue defVal=new IntValue(0);
        return defVal;
    }
}
