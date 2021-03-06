package Model.Values;

import Model.Types.IType;
import Model.Types.IntType;

import java.util.Objects;

public class IntValue implements IValue{
    private int val;

    @Override
    public boolean equals(Object another) {
        if(another instanceof IntValue && ((IntValue) another).getValue()==this.getValue())
                return true;
        return false;

    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }

    public IntValue(int v){
        val=v;
    }

    public int getValue(){
        return val;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }

    @Override
    public IType getType() {
        return new IntType();
    }
}
