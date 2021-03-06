package Model.Statements;

import Exceptions.StmtException;
import Exceptions.TypeCheckException;
import Model.ADTs.IDict;
import Model.ADTs.IHeap;
import Model.ADTs.ILockTable;
import Model.ProgramState;
import Model.Types.IType;
import Model.Types.IntType;
import Model.Values.IValue;
import Model.Values.IntValue;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockStmt implements IStmt{
    private String var;
    private static Lock lock=new ReentrantLock();

    public LockStmt(String v){var=v;}

    @Override
    public ProgramState execute(ProgramState state) throws StmtException {
        lock.lock();
        IDict<String, IValue> table=state.getSymTable();
        ILockTable lockT = state.getLockTable();

        if(!table.isDefined(var))throw new StmtException("Variable not defined");
        if(!table.lookup(var).getType().equals(new IntType()))throw new StmtException("Var is not int");

        IntValue fi = (IntValue)table.lookup(var);
        int foundIndex=fi.getValue();

        if(!lockT.containsKey(foundIndex)) throw new StmtException("Index not in LockTable");

        if(lockT.get(foundIndex)==-1)
            lockT.update(foundIndex,state.getCurrentID());
        else
            state.getExeStack().push(this);
        lock.unlock();
        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws TypeCheckException {
        if(typeEnv.lookup(var).equals(new IntType()))
            return typeEnv;
        throw new TypeCheckException("Var is not int");
    }

    @Override
    public String toString() {
        return "lock("+var+")";
    }
}
