package Model.Statements;

import Exceptions.StmtException;
import Exceptions.TypeCheckException;
import Model.ADTs.IDict;
import Model.Expressions.IExp;
import Model.Expressions.RelationalExp;
import Model.ProgramState;
import Model.Types.IType;

public class SwitchStmt implements IStmt{
    private IExp exp;
    private IExp exp1;
    private IExp exp2;
    private IStmt stmt1;
    private IStmt stmt2;
    private IStmt stmt3;

    public SwitchStmt(IExp e,IExp e1,IExp e2,IStmt s1,IStmt s2,IStmt s3){
        exp=e;exp1=e1;exp2=e2;stmt1=s1;stmt2=s2;stmt3=s3;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StmtException {
        IStmt newSwitch=new IfStmt(new RelationalExp(exp,exp1,"=="),stmt1,
                new IfStmt(new RelationalExp(exp,exp2,"=="),stmt2,stmt3));
        state.getExeStack().push(newSwitch);

        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws TypeCheckException {
        IType typexp=exp.typecheck(typeEnv);
        IType typexp1=exp1.typecheck(typeEnv);
        IType typexp2=exp2.typecheck(typeEnv);

        if(typexp.equals(typexp1) && typexp.equals(typexp2)){
            stmt1.typecheck(typeEnv.clone());
            stmt2.typecheck(typeEnv.clone());
            stmt3.typecheck(typeEnv.clone());
            return typeEnv;
        }
        else
            throw new TypeCheckException("The expression types don't match");
    }

    @Override
    public String toString() {
        return "switch("+exp.toString()+") (case "+exp1.toString()+": "+stmt1.toString()+")(case "+exp2.toString()+": "+stmt2.toString()+")(default: "+stmt3.toString()+")";
    }
}
