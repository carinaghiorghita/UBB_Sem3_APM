package Model.Expressions;

import Exceptions.EvalException;
import Exceptions.StmtException;
import Exceptions.TypeCheckException;
import Model.ADTs.IDict;
import Model.ADTs.IHeap;
import Model.Expressions.ArithmExp;
import Model.Expressions.IExp;
import Model.ProgramState;
import Model.Types.IType;
import Model.Types.IntType;
import Model.Values.IValue;

public class MULExp implements IExp{
    //String v;
    private IExp exp1;
    private IExp exp2;

    public MULExp(IExp e1,IExp e2){exp1=e1;exp2=e2;}

    @Override
    public IType typecheck(IDict<String, IType> typeEnv) throws TypeCheckException {
        IType typexp1=exp1.typecheck(typeEnv);
        IType typexp2=exp2.typecheck(typeEnv);

        if(typexp1.equals(new IntType()) && typexp2.equals(new IntType()))
            return new IntType();
        else
            throw new TypeCheckException("Expressions should be int");
    }

    @Override
    public IValue eval(IDict<String, IValue> table, IHeap<IValue> heap) throws EvalException {
        return new ArithmExp('-',new ArithmExp('*',exp1,exp2),new ArithmExp('+',exp1,exp2)).eval(table,heap);
    }

    @Override
    public String toString() {
        return "MUL("+exp1.toString()+","+exp2+")";
    }
}
