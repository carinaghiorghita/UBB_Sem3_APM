package Model.Statements;

import Exceptions.EvalException;
import Exceptions.StmtException;
import Exceptions.TypeCheckException;
import Model.ADTs.IDict;
import Model.ADTs.IHeap;
import Model.Expressions.IExp;
import Model.ProgramState;
import Model.Types.BoolType;
import Model.Types.IType;
import Model.Values.BoolValue;
import Model.Values.IValue;

public class IfStmt implements IStmt{
    private IExp exp;
    private IStmt thenst,elsest;

    public IfStmt(IExp e, IStmt t, IStmt el) {
        exp=e;
        thenst=t;
        elsest=el;
    }

    @Override
    public String toString() {
        return "(if("+ exp.toString()+") then(" +thenst.toString()
                +") else("+elsest.toString()+"))";
    }

    @Override
    public ProgramState execute(ProgramState state) throws StmtException {

        IDict<String,IValue> table=state.getSymTable();
        IHeap<IValue> heap=state.getHeapTable();

        try{
            IValue cond= exp.eval(table,heap);
            //if(!(cond.getType().equals(new BoolType())))throw new StmtException("Condition is not boolean");
            BoolValue c=(BoolValue)cond;
            if(c.getValue()==true)
                state.getExeStack().push(thenst);
            else
                state.getExeStack().push(elsest);
        }catch(EvalException e){
            throw new StmtException(e.getMessage());
        }
        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws TypeCheckException {
        IType typexp=exp.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            thenst.typecheck(typeEnv.clone());
            elsest.typecheck(typeEnv.clone());
            return typeEnv;
        }
        else
            throw new TypeCheckException("The condition of IF has not the type bool");
    }
}
