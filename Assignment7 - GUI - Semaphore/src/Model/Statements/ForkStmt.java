package Model.Statements;

import Exceptions.StmtException;
import Exceptions.TypeCheckException;
import Model.ADTs.IDict;
import Model.ADTs.myStack;
import Model.ProgramState;
import Model.Types.BoolType;
import Model.Types.IType;

public class ForkStmt implements IStmt{
    private IStmt stmt;

    public ForkStmt(IStmt s){
        stmt=s;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StmtException {
        return new ProgramState(new myStack<IStmt>(),
                state.getSymTable().clone(),
                state.getFileTable(),
                state.getHeapTable(),
                state.getOutput(),
                stmt,
                state.getNextID(),
                state.getSemaphoreTable());
    }

    @Override
    public String toString() {
        return "fork("+ stmt.toString()+")";
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws TypeCheckException {
        stmt.typecheck(typeEnv.clone());
        return typeEnv;

    }
}
