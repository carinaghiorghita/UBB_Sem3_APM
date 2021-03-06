package Model.Expressions;

import Exceptions.EvalException;
import Exceptions.TypeCheckException;
import Model.ADTs.IDict;
import Model.ADTs.IHeap;
import Model.Types.IType;
import Model.Values.IValue;

public class VarExp implements IExp{
    private String id;

    public VarExp(String i){id=i;}

    @Override
    public IValue eval(IDict<String, IValue> table, IHeap<IValue> heap) throws EvalException {
        if(!table.isDefined(id)) throw new EvalException("Undefined variable");
        return table.lookup(id);
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public IType typecheck(IDict<String, IType> typeEnv) throws TypeCheckException {
        return typeEnv.lookup(id);
    }
}
