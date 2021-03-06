package Model.Statements;

import Exceptions.StackException;
import Exceptions.StmtException;
import Exceptions.TypeCheckException;
import Model.ADTs.IDict;
import Model.ADTs.IHeap;
import Model.ADTs.IProcedureTable;
import Model.ADTs.myDict;
import Model.Expressions.IExp;
import Model.ProgramState;
import Model.Types.IType;
import Model.Values.IValue;
import javafx.util.Pair;

import java.util.List;

public class CallProcStmt implements IStmt{
    String fname;
    List<IExp>exps;

    public CallProcStmt(String f,List<IExp> e){fname=f;exps=e;}

    @Override
    public ProgramState execute(ProgramState state) throws StmtException {
        try {
            IDict<String, IValue> table = state.getCurrentSymTable();
            IHeap<IValue> heap=state.getHeapTable();
            IProcedureTable procTable= state.getProcTable();

            if(!procTable.containsKey(fname))throw new StmtException("Procedure not in ProcTable");

            List<String> variables=procTable.get(fname).getKey();
            IStmt stmt=procTable.get(fname).getValue();

            IDict<String, IValue> newSymTable=new myDict<>();
            for(String v:variables) {
                int ind= variables.indexOf(v);
                newSymTable.add(v, exps.get(ind).eval(table,heap));
            }
            state.getSymTable().push(table);
            state.getSymTable().push(newSymTable);
            state.getExeStack().push(new ReturnStmt());
            state.getExeStack().push(stmt);


        }catch (Exception e){throw new StmtException(e.getMessage());}
        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws TypeCheckException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "call "+fname+"()";
    }
}
