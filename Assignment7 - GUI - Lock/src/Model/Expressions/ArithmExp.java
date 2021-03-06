package Model.Expressions;

import Exceptions.EvalException;
import Exceptions.TypeCheckException;
import Model.ADTs.IDict;
import Model.ADTs.IHeap;
import Model.Types.IType;
import Model.Types.IntType;
import Model.Values.IValue;
import Model.Values.IntValue;

public class ArithmExp implements IExp{
    private IExp exp1;
    private IExp exp2;
    private char op;

    public ArithmExp(char o,IExp e1,IExp e2){
        exp1=e1;
        exp2=e2;
        op=o;
    }

    @Override
    public IValue eval(IDict<String, IValue> table, IHeap<IValue> heap) throws EvalException {
        IValue v1,v2;
        v1=exp1.eval(table,heap);
        if(v1.getType().equals(new IntType())){
            v2=exp2.eval(table,heap);
            if(v1.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1,n2;
                n1=i1.getValue();
                n2=i2.getValue();
                if(op=='+')return new IntValue(n1+n2);
                if(op=='-')return new IntValue(n1-n2);
                if(op=='*')return new IntValue(n1*n2);
                if(op=='/') {
                    if (n2 == 0) throw new EvalException("Cannot divide by zero");
                    return new IntValue(n1 / n2);
                }
                throw new EvalException("Invalid operation");
            }
            throw new EvalException("Second operand is not an integer");
        }
        throw new EvalException("First operand is not an integer");
    }

    @Override
    public String toString() {
        String oper=String.valueOf(op);
        return exp1.toString()+oper+exp2.toString();
    }

    @Override
    public IType typecheck(IDict<String, IType> typeEnv) throws TypeCheckException {
        IType typ1, typ2;
        typ1=exp1.typecheck(typeEnv);
        typ2=exp2.typecheck(typeEnv);
        //if (typ1.equals(new IntType())) {
            if (typ2.equals(new IntType())) {
                return new IntType();
            } else
            throw new TypeCheckException("second operand is not an integer");
        //}else
        //throw new TypeCheckException("first operand is not an integer");
    }
}
