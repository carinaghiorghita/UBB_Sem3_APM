package Model.Statements;

import Exceptions.StmtException;
import Exceptions.TypeCheckException;
import Model.ADTs.IDict;
import Model.Expressions.IExp;
import Model.Expressions.RelationalExp;
import Model.Expressions.ValueExp;
import Model.Expressions.VarExp;
import Model.ProgramState;
import Model.Types.BoolType;
import Model.Types.IType;
import Model.Types.IntType;

public class ForStmt implements IStmt{
    private String v;
    private IExp exp1;
    private IExp exp2;
    private IExp exp3;
    private IStmt stmt;

    public ForStmt(String val,IExp e1,IExp e2,IExp e3,IStmt s){
        v=val;exp1=e1;exp2=e2;exp3=e3;stmt=s;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StmtException {
        IStmt newFor=new CompStmt(new VarDeclStmt(v,new IntType()),
                new CompStmt(new AssignStmt(v,exp1),new WhileStmt(new RelationalExp(new VarExp(v),exp2,"<"),
                                new CompStmt(stmt,new AssignStmt("v",exp3)))));
        state.getExeStack().push(newFor);

        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws TypeCheckException {
        typeEnv.add(v,new IntType());
        IType typv=typeEnv.lookup(v);
        IType typexp1=exp1.typecheck(typeEnv);
        IType typexp2=exp2.typecheck(typeEnv);
        IType typexp3=exp3.typecheck(typeEnv);

        if(typv.equals(new IntType())&&typexp1.equals(new IntType()) && typexp2.equals(new IntType()) && typexp3.equals(new IntType())) return typeEnv;
        else throw new TypeCheckException("The for is invalid");
    }

    @Override
    public String toString() {
        return "for("+v+"="+exp1.toString()+";"+v+"<"+exp2.toString()+";"+v+"="+exp3.toString()+"){"+stmt.toString()+"}";
    }
}
