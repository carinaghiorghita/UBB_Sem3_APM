package Model.Statements;

import Exceptions.EvalException;
import Exceptions.StmtException;
import Exceptions.TypeCheckException;
import Model.ADTs.IDict;
import Model.ADTs.IHeap;
import Model.Expressions.IExp;
import Model.ProgramState;
import Model.Types.IType;
import Model.Types.RefType;
import Model.Values.IValue;
import Model.Values.RefValue;

public class WriteHeapStmt implements IStmt{
    private String var_name;
    private IExp exp;

    public WriteHeapStmt(String v,IExp e){
        var_name=v;
        exp=e;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StmtException {
        IDict<String, IValue> table=state.getSymTable();
        IHeap<IValue> heap=state.getHeapTable();

        if(!(table.isDefined(var_name)))
            throw new StmtException("Variable is undefined");

        if(!(table.lookup(var_name).getType() instanceof RefType))
            throw new StmtException("Address is not a reference");

        RefValue ref=(RefValue)table.lookup(var_name);
        int addr=ref.getAddr();

        if(!(heap.containsKey(addr)))
            throw new StmtException("Address does not exist in the heap");

        try{
            if(!(exp.eval(table,heap).getType().equals(ref.getLocType())))
                throw new StmtException("Types do not correspond");

            heap.update(addr,exp.eval(table,heap));
        }catch(EvalException e){
            throw new StmtException(e.getMessage());
        }

        return null;
    }

    @Override
    public String toString() {
        return "write("+var_name+","+exp.toString()+")";
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws TypeCheckException {
        IType typevar = typeEnv.lookup(var_name);
        IType typexp = exp.typecheck(typeEnv);
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new TypeCheckException("WRITE stmt: right hand side and left hand side have different types ");
    }
}
