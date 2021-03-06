package Model.Statements;

import Exceptions.StackException;
import Exceptions.StmtException;
import Exceptions.TypeCheckException;
import Model.ADTs.IDict;
import Model.ProgramState;
import Model.Types.IType;

public class ReturnStmt implements IStmt {

    @Override
    public ProgramState execute(ProgramState state) throws StmtException {
        try{
            state.getSymTable().pop();
        }catch(StackException e){
            throw new StmtException(e.getMessage());
        }

        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws TypeCheckException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "return";
    }
}
