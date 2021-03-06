package Model.Statements;

import Exceptions.StmtException;
import Exceptions.TypeCheckException;
import Model.ADTs.IDict;
import Model.ProgramState;
import Model.Types.IType;

public class NopStmt implements IStmt{
    @Override
    public ProgramState execute(ProgramState state) throws StmtException {
        return null;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws TypeCheckException {
        return typeEnv;
    }
}
