package Model.Statements;

import Exceptions.StmtException;
import Exceptions.TypeCheckException;
import Model.ADTs.IDict;
import Model.ADTs.IHeap;
import Model.ADTs.ILatchTable;
import Model.ProgramState;
import Model.Types.IType;
import Model.Types.IntType;
import Model.Values.IValue;
import Model.Values.IntValue;

public class AwaitStmt implements IStmt{
    private String var;

    public AwaitStmt(String v){var=v;}

    @Override
    public ProgramState execute(ProgramState state) throws StmtException {

        IDict<String, IValue> table=state.getSymTable();
        ILatchTable latch = state.getLatchTable();

        if(!table.isDefined(var))throw new StmtException("Variable not defined");

        IntValue fi = (IntValue)table.lookup(var);
        int foundIndex=fi.getValue();

        if(!latch.containsKey(foundIndex)) throw new StmtException("Index not in LatchTable");
        if(latch.get(foundIndex)!=0)
            state.getExeStack().push(this);

        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws TypeCheckException {
        if(typeEnv.lookup(var).equals(new IntType()))
            return typeEnv;
        else throw new TypeCheckException("Var is not int");
    }

    @Override
    public String toString() {
        return "await("+var+")";
    }
}
