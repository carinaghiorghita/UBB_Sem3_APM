package Model.Statements;

import Exceptions.StmtException;
import Exceptions.TypeCheckException;
import Model.ADTs.IDict;
import Model.Expressions.IExp;
import Model.Expressions.VarExp;
import Model.ProgramState;
import Model.Types.BoolType;
import Model.Types.IType;
import Model.Types.IntType;
import Model.Values.StringValue;

public class ConditionalStmt implements IStmt{
    private String v;
    private IExp exp1;
    private IExp exp2;
    private IExp exp3;

    public ConditionalStmt(String val,IExp e1,IExp e2,IExp e3){
        v=val;exp1=e1;exp2=e2;exp3=e3;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StmtException {
        IStmt newIf=new IfStmt(exp1,new AssignStmt(v,exp2),new AssignStmt(v,exp3));
        state.getExeStack().push(newIf);
        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws TypeCheckException {
        IType typev=new VarExp(v).typecheck(typeEnv);
        IType typexp1=exp1.typecheck(typeEnv);
        IType typexp2=exp2.typecheck(typeEnv);
        IType typexp3=exp3.typecheck(typeEnv);

        if(typexp1.equals(new BoolType()) && typexp2.equals(typev) && typexp3.equals(typev)) return typeEnv;
        else throw new TypeCheckException("The conditional assignment is invalid");

    }

    @Override
    public String toString() {
        return v+"=("+exp1.toString()+")?"+exp2.toString()+":"+exp3.toString();
    }
}
