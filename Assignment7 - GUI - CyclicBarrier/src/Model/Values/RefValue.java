package Model.Values;

import Model.Types.IType;
import Model.Types.RefType;

import java.util.Objects;

public class RefValue implements IValue{
    private int address;
    private IType locationType;

    public RefValue(int addr,IType loc){
        address=addr;
        locationType=loc;
    }

    public RefValue(RefValue v){
        address=v.getAddr();
        locationType=v.getLocType();
    }


    public int getAddr() {
        return address;
    }

    @Override
    public IType getType() {
        return new RefType(locationType);
    }

    @Override
    public boolean equals(Object another) {
        if(another instanceof RefValue) {
            RefType t= (RefType)((RefValue) another).getType();
            if(t.getInner().equals(locationType))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "("+String.valueOf(address)+','+locationType.toString()+")";
    }

    public IType getLocType(){
        return locationType;
    }
}
