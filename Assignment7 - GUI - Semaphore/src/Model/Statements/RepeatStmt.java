package Model.Statements;

import Exceptions.StmtException;
import Exceptions.TypeCheckException;
import Model.ADTs.IDict;
import Model.Expressions.IExp;
import Model.Expressions.NotExp;
import Model.Expressions.RelationalExp;
import Model.ProgramState;
import Model.Types.BoolType;
import Model.Types.IType;

public class RepeatStmt implements IStmt{
    private IStmt stmt;
    private IExp exp;

    public RepeatStmt(IStmt s,IExp e){
        stmt=s;exp=e;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StmtException {

        IStmt newRepeat=new CompStmt(stmt,new WhileStmt(new NotExp(exp),stmt));
        state.getExeStack().push(newRepeat);

        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws TypeCheckException {
        IType typexp=exp.typecheck(typeEnv);
        if(typexp.equals(new BoolType())) {
            stmt.typecheck(typeEnv.clone());
            return typeEnv;
        }
        else
            throw new TypeCheckException("Expression must be bool");
    }

    @Override
    public String toString() {
        return "repeat "+stmt.toString()+" until "+exp.toString();
    }
}
