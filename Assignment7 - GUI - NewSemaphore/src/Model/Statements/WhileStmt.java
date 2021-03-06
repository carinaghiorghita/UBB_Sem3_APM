package Model.Statements;

import Exceptions.EvalException;
import Exceptions.StmtException;
import Exceptions.TypeCheckException;
import Model.ADTs.IDict;
import Model.ADTs.IHeap;
import Model.ADTs.IStack;
import Model.Expressions.IExp;
import Model.ProgramState;
import Model.Types.BoolType;
import Model.Types.IType;
import Model.Values.BoolValue;
import Model.Values.IValue;

public class WhileStmt implements IStmt{
    private IStmt stmt;
    private IExp exp;

    public WhileStmt(IExp e,IStmt s){
        stmt=s;
        exp=e;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StmtException {
        IStack<IStmt> stack= state.getExeStack();
        IDict<String, IValue> table=state.getSymTable();
        IHeap<IValue> heap=state.getHeapTable();

        try {
            if(!(exp.eval(table,heap).getType() instanceof BoolType))
                throw new StmtException("Invalid expression type");
            if (exp.eval(table, heap).equals(new BoolValue(true))){
                stack.push(this);
                stack.push(stmt);
            }
        }catch(EvalException e){
            throw new StmtException(e.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return "while("+exp.toString()+") {"+stmt.toString()+"}";
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws TypeCheckException {
        IType typexp=exp.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            stmt.typecheck(typeEnv.clone());
            return typeEnv;
        }
        else
            throw new TypeCheckException("The condition of WHILE has not the type bool");

    }
}
