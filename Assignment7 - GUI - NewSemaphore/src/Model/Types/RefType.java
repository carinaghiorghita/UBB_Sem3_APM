package Model.Types;

import Model.Values.IValue;
import Model.Values.RefValue;

public class RefType implements IType{
    private IType inner;

    public RefType(IType in) {
        inner=in;
    }

    public IType getInner() {
        return inner;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RefType) {
            return ((RefType) obj).getInner().equals(this.inner);
        }

        return false;
    }

    @Override
    public String toString() { return "Ref(" +inner.toString()+")";}

    @Override
    public IValue defaultValue() { return new RefValue(0,inner);}

}
