package Model.Statements;

import Exceptions.EvalException;
import Exceptions.StmtException;
import Exceptions.TypeCheckException;
import Model.ADTs.IDict;
import Model.ADTs.IHeap;
import Model.Expressions.IExp;
import Model.ProgramState;
import Model.Types.IType;
import Model.Types.IntType;
import Model.Types.RefType;
import Model.Values.IValue;
import Model.Values.RefValue;

public class NewHeapStmt implements IStmt {
    private String var_name;
    private IExp exp;

    public NewHeapStmt (String var,IExp e){
        var_name=var;
        exp=e;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StmtException {
        IDict<String, IValue> table=state.getSymTable();
        IHeap<IValue> heap=state.getHeapTable();

        if(!(table.isDefined(var_name)))
            throw new StmtException("Undefined variable");

        if(!(table.lookup(var_name).getType() instanceof RefType))
            throw new StmtException("Type should be Ref");

        try {
            IValue v = exp.eval(table,heap);
            RefValue ref= (RefValue) table.lookup(var_name);

            if(!(v.getType().getClass().equals(ref.getLocType().getClass())))
                throw new StmtException("Types are not compatible");

            int newKey=heap.getFreeAddress();
            heap.put(newKey,v);
            table.update(var_name,new RefValue(newKey,ref.getLocType()));

        }catch(EvalException e){
            throw new StmtException(e.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return "new("+var_name+","+exp.toString()+")";
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws TypeCheckException {
        IType typevar = typeEnv.lookup(var_name);
        IType typexp = exp.typecheck(typeEnv);
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new TypeCheckException("NEW stmt: right hand side and left hand side have different types ");
    }
}
