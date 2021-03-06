package Model.Expressions;

import Exceptions.EvalException;
import Exceptions.TypeCheckException;
import Model.ADTs.IDict;
import Model.ADTs.IHeap;
import Model.Types.IType;
import Model.Values.BoolValue;
import Model.Values.IValue;

public class NotExp implements IExp{
    private IExp exp;

    public NotExp(IExp e){exp=e;}

    @Override
    public IValue eval(IDict<String, IValue> table, IHeap<IValue> heap) throws EvalException {
        BoolValue b=(BoolValue)exp.eval(table, heap);
        return b.getValue() == false ? new BoolValue(true) : new BoolValue(false);
    }

    @Override
    public IType typecheck(IDict<String, IType> typeEnv) throws TypeCheckException {
        return exp.typecheck(typeEnv);
    }

    @Override
    public String toString() {
        return "!("+exp.toString()+")";
    }
}
