package Model.Statements;

import Exceptions.StmtException;
import Exceptions.TypeCheckException;
import Model.ADTs.IDict;
import Model.Expressions.IExp;
import Model.Expressions.NotExp;
import Model.ProgramState;
import Model.Types.BoolType;
import Model.Types.IType;

public class DoWhileStmt implements IStmt{
    private IStmt stmt;
    private IExp exp;

    public DoWhileStmt(IStmt s,IExp e){stmt=s;exp=e;}

    @Override
    public ProgramState execute(ProgramState state) throws StmtException {
        IStmt newDoWhile=new CompStmt(stmt,new WhileStmt(exp,stmt));
        state.getExeStack().push(newDoWhile);

        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws TypeCheckException {
        IType typexp=exp.typecheck(typeEnv);
        if(typexp.equals(new BoolType())){
            stmt.typecheck(typeEnv.clone());
            return typeEnv;
        }
        else throw new TypeCheckException("Condition should be bool");
    }

    @Override
    public String toString() {
        return "do "+stmt.toString()+" while "+exp.toString();
    }
}
