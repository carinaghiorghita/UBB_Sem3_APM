package Model.Statements;

import Exceptions.EvalException;
import Exceptions.StackException;
import Exceptions.StmtException;
import Exceptions.TypeCheckException;
import Model.ADTs.IDict;
import Model.ADTs.IHeap;
import Model.ADTs.IList;
import Model.ADTs.myList;
import Model.Expressions.IExp;
import Model.ProgramState;
import Model.Types.IType;
import Model.Values.IValue;

public class PrintStmt implements IStmt{
    private IExp exp;

    public PrintStmt(IExp e){exp=e;}

    @Override
    public String toString() {
        return "print("+exp.toString()+")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws StmtException {
        try{IList<IValue> out=state.getOutput();
        IDict<String,IValue> table=state.getCurrentSymTable();
        IHeap<IValue> heap=state.getHeapTable();

        try{
            out.add(exp.eval(table,heap));
        }catch(EvalException e){
            throw new StmtException(e.getMessage());
        }        state.getSymTable().push(table);

        }catch (StackException e){throw new StmtException(e.getMessage());}
        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws TypeCheckException {
        exp.typecheck(typeEnv);
        return typeEnv;
    }
}
