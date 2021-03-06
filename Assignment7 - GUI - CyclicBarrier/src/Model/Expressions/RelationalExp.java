package Model.Expressions;

import Exceptions.EvalException;
import Exceptions.TypeCheckException;
import Model.ADTs.IDict;
import Model.ADTs.IHeap;
import Model.Types.BoolType;
import Model.Types.IType;
import Model.Types.IntType;
import Model.Values.BoolValue;
import Model.Values.IValue;
import Model.Values.IntValue;

public class RelationalExp implements IExp{
    private IExp ex1,ex2;
    private String op;

    public RelationalExp(IExp e1,IExp e2,String o){
        ex1=e1;
        ex2=e2;
        op=o;
    }

    @Override
    public IValue eval(IDict<String, IValue> table, IHeap<IValue> heap) throws EvalException {
        IValue v1,v2;
        v1=ex1.eval(table,heap);
        if(v1.getType().equals(new IntType())){
            v2=ex2.eval(table,heap);
            if(v2.getType().equals(new IntType())){
                IntValue i1,i2;
                i1=(IntValue)v1;
                i2=(IntValue)v2;
                int n1,n2;
                n1=i1.getValue();
                n2= i2.getValue();
                if(op.equals("<")) return new BoolValue(n1<n2);
                if(op.equals("<=")) return new BoolValue(n1<=n2);
                if(op.equals("==")) return new BoolValue(n1==n2);
                if(op.equals("!=")) return new BoolValue(n1!=n2);
                if(op.equals(">")) return new BoolValue(n1>n2);
                if(op.equals(">=")) return new BoolValue(n1>=n2);
                throw new EvalException("Invalid operand");
            }
            else throw new EvalException("Second operand is not an integer");
        }
        else throw new EvalException("First operand is not an integer");
    }

    @Override
    public String toString() {
        return ex1.toString()+op+ex2.toString();
    }

    @Override
    public IType typecheck(IDict<String, IType> typeEnv) throws TypeCheckException {
        IType typ1, typ2;
        typ1=ex1.typecheck(typeEnv);
        typ2=ex2.typecheck(typeEnv);
        if (typ1.equals(new IntType())) {
            if (typ2.equals(new IntType())) {
                return new BoolType();
            } else
                throw new TypeCheckException("second operand is not an integer");
        }else
            throw new TypeCheckException("first operand is not an integer");
    }

}
