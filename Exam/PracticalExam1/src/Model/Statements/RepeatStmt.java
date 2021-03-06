package Model.Statements;

import Exceptions.StmtException;
import Exceptions.TypeCheckException;
import Model.ADTs.IDict;
import Model.Expressions.IExp;
import Model.Expressions.NotExp;
import Model.ProgramState;
import Model.Types.BoolType;
import Model.Types.IType;

public class RepeatStmt implements IStmt{
    private IStmt stmt1;
    private IExp exp2;

    public RepeatStmt(IStmt s,IExp e){
        stmt1 =s;
        exp2 =e;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StmtException {

        IStmt newRepeat=new CompStmt(stmt1,new WhileStmt(new NotExp(exp2), stmt1));
        state.getExeStack().push(newRepeat);

        return null;
    }
//    The typecheck method of repeat statement verifies if exp2 has the type bool and also typecheck the statement stmt1.
    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws TypeCheckException {
        IType typexp= exp2.typecheck(typeEnv);
        if(typexp.equals(new BoolType())) {
            stmt1.typecheck(typeEnv.clone());
            return typeEnv;
        }
        else
            throw new TypeCheckException("Expression must be bool");
    }

    @Override
    public String toString() {
        return "repeat "+ stmt1.toString()+" until "+ exp2.toString();
    }
}
