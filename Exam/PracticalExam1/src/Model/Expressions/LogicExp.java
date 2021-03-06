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

public class LogicExp implements IExp{
    private IExp e1,e2;
    private String op;

    public LogicExp(IExp b1,IExp b2,String o){
        e1=b1;
        e2=b2;
        op=o;
    }

    @Override
    public IValue eval(IDict<String, IValue> table, IHeap<IValue> heap) throws EvalException {
        IValue v1,v2;
        v1=e1.eval(table,heap);

        if(v1.getType().equals(new BoolType())) {
            v2 = e2.eval(table,heap);
            if(v2.getType().equals(new BoolType())){
                BoolValue i1=(BoolValue)v1;
                BoolValue i2=(BoolValue)v2;

                boolean b1,b2;
                b1=i1.getValue();
                b2=i2.getValue();
                if(op=="and")return new BoolValue(b1&&b2);
                if(op=="or") return new BoolValue(b1||b2);
                throw new EvalException("Undefined operation");
            }
            throw new EvalException("Second operand is not a bool");
        }
        throw new EvalException("First operand is not a bool");
    }

    @Override
    public String toString() {
        return e1.toString()+op+e2.toString();
    }

    @Override
    public IType typecheck(IDict<String, IType> typeEnv) throws TypeCheckException {
        IType typ1, typ2;
        typ1=e1.typecheck(typeEnv);
        typ2=e2.typecheck(typeEnv);
        if (typ1.equals(new BoolType())) {
            if (typ2.equals(new BoolType())) {
                return new BoolType();
            } else
                throw new TypeCheckException("second operand is not a bool");
        }else
            throw new TypeCheckException("first operand is not a bool");
    }
}
