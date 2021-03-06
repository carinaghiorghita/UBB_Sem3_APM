package Model.Statements;

import Exceptions.StmtException;
import Exceptions.TypeCheckException;
import Model.ADTs.IDict;
import Model.ProgramState;
import Model.Types.IType;

public interface IStmt {
    ProgramState execute(ProgramState state) throws StmtException;
    String toString();
    IDict<String, IType> typecheck(IDict<String,IType> typeEnv) throws TypeCheckException;
}
