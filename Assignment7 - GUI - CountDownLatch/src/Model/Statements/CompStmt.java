package Model.Statements;

import Exceptions.StmtException;
import Exceptions.TypeCheckException;
import Model.ADTs.IDict;
import Model.ADTs.IStack;
import Model.ProgramState;
import Model.Types.IType;

public class CompStmt implements IStmt{
    private IStmt first;
    private IStmt second;

    public CompStmt(IStmt frst, IStmt scnd){
        first=frst;
        second=scnd;
    }
    @Override
    public String toString() {
        return "("+first.toString() + ";" + second.toString()+")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws StmtException {
        IStack<IStmt> stack=state.getExeStack();
        stack.push(second);
        stack.push(first);
        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws TypeCheckException {
        return second.typecheck(first.typecheck(typeEnv));
    }
}


