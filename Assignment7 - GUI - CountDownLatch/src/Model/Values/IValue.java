package Model.Values;

import Model.Types.IType;

public interface IValue {

    boolean equals(Object another);
    IType getType();
    String toString();
}
