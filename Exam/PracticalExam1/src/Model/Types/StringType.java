package Model.Types;

import Model.Values.IValue;
import Model.Values.IntValue;
import Model.Values.StringValue;

public class StringType implements IType{

    @Override
    public boolean equals(Object another){
        if(another instanceof StringType)
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "string";
    }

    @Override
    public IValue defaultValue() {
        IValue defVal= new StringValue("");
        return defVal;
    }
}
