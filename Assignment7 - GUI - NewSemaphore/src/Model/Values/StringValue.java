package Model.Values;

import Model.Types.IType;
import Model.Types.IntType;
import Model.Types.StringType;

public class StringValue implements IValue{
    private String val;

    @Override
    public boolean equals(Object another) {
        if(another instanceof StringValue && ((StringValue) another).getValue().equals(this.getValue()))
            return true;
        return false;

    }

    public StringValue(String v){
        val=v;
    }

    public String getValue(){
        return val;
    }

    @Override
    public String toString() {
        return val;
    }

    @Override
    public IType getType() {return new StringType();}
}
