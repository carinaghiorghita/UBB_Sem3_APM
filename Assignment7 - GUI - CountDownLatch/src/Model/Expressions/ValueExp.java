package Model.Expressions;

import Exceptions.EvalException;
import Exceptions.TypeCheckException;
import Model.ADTs.IDict;
import Model.ADTs.IHeap;
import Model.Types.IType;
import Model.Values.IValue;

public class ValueExp implements IExp{
    private IValue e;

    public ValueExp(IValue v){e=v;}

    @Override
    public IValue eval(IDict<String, IValue> table, IHeap<IValue> heap) throws EvalException {
        return e;
    }

    @Override
    public String toString() {
        return e.toString();
    }

    @Override
    public IType typecheck(IDict<String, IType> typeEnv) throws TypeCheckException {
        return e.getType();
    }
}
