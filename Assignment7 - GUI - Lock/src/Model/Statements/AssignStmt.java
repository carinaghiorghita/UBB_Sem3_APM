package Model.Statements;

import Exceptions.EvalException;
import Exceptions.StmtException;
import Exceptions.TypeCheckException;
import Model.ADTs.IDict;
import Model.ADTs.IHeap;
import Model.ADTs.IStack;
import Model.Expressions.IExp;
import Model.ProgramState;
import Model.Types.IType;
import Model.Values.IValue;

public class AssignStmt implements IStmt{
    private String id;
    private IExp exp;

    public AssignStmt(String i,IExp e){
        id=i;
        exp=e;
    }

    @Override
    public String toString() {
        return id+"="+ exp.toString();
    }

    @Override
    public ProgramState execute(ProgramState state) throws StmtException {
        IDict<String, IValue> table= state.getSymTable();
        IHeap<IValue> heap=state.getHeapTable();

        if(table.isDefined(id)){
            try{
                IValue val= exp.eval(table,heap);

                IType typeid=(table.lookup(id)).getType();
                if(val.getType().equals(typeid))
                    table.update(id,val);
                else throw new StmtException("Type of "+id+" and type of expression do not match");

                return null;
            } catch(EvalException e){
                throw new StmtException(e.getMessage());
            }
        }
        else {
            throw new StmtException("Variable " + id + " was never used before");
        }
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws TypeCheckException {
        IType typevar = typeEnv.lookup(id);
        IType typexp = exp.typecheck(typeEnv);
        if (typevar.equals(typexp))
            return typeEnv;
        else
            throw new TypeCheckException("Assignment: right hand side and left hand side have different types ");
    }
}
