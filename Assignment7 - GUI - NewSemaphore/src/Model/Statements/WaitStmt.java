package Model.Statements;

import Exceptions.StmtException;
import Exceptions.TypeCheckException;
import Model.ADTs.IDict;
import Model.Expressions.ValueExp;
import Model.ProgramState;
import Model.Types.IType;
import Model.Values.IntValue;

public class WaitStmt implements IStmt{
    private int nr;

    public WaitStmt(int n){nr=n;}

    @Override
    public ProgramState execute(ProgramState state) throws StmtException {
        if(nr!=0) state.getExeStack().push(new CompStmt(new PrintStmt(new ValueExp(new IntValue(nr))),new WaitStmt(nr-1)));
        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws TypeCheckException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "wait("+nr+")";
    }
}
