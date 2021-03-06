package Model.Values;

import Model.Types.BoolType;
import Model.Types.IType;

public class BoolValue implements IValue{
    private boolean bool;

    @Override
    public boolean equals(Object another) {
        if(another instanceof BoolValue && ((BoolValue) another).getValue()==this.getValue())
            return true;
        return false;

    }

    public BoolValue(boolean b){
        bool=b;
    }

    public boolean getValue(){
        return bool;
    }

    @Override
    public String toString() {
        return String.valueOf(bool);
    }

    @Override
    public IType getType() {
        return new BoolType();
    }
}
