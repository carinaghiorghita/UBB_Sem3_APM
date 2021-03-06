package Model.Statements;

import Exceptions.StmtException;
import Exceptions.TypeCheckException;
import Model.ADTs.IDict;
import Model.ProgramState;
import Model.Types.IType;

public class SleepStmt implements IStmt{
    private int nr;

    public SleepStmt(int n){nr = n;}

    @Override
    public ProgramState execute(ProgramState state) throws StmtException {
        if(nr!=0) state.getExeStack().push(new SleepStmt(nr-1));
        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws TypeCheckException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "sleep("+nr+")";
    }
}
