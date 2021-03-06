package Model.Expressions;

import Exceptions.EvalException;
import Exceptions.TypeCheckException;
import Model.ADTs.IDict;
import Model.ADTs.IHeap;
import Model.Types.IType;
import Model.Values.IValue;

public interface IExp {
    IValue eval(IDict<String,IValue> table, IHeap<IValue> heap) throws EvalException;
    String toString();
    IType typecheck(IDict<String,IType> typeEnv) throws TypeCheckException;
}
