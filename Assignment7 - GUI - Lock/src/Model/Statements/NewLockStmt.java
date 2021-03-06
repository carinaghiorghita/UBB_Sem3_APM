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

public class NewLockStmt implements IStmt{
    private String var;
    private static Lock lock=new ReentrantLock();

    public NewLockStmt(String v){var=v;}

    @Override
    public ProgramState execute(ProgramState state) throws StmtException {
        lock.lock();

        ILockTable lockT=state.getLockTable();
        IDict<String, IValue> table=state.getSymTable();
        IHeap<IValue> heap=state.getHeapTable();

        int f=lockT.getFreeAddress();

        lockT.put(f,-1);

        if(table.isDefined(var)&&table.lookup(var).getType().equals(new IntType()))
            table.update(var,new IntValue(f));
        else throw new StmtException("Variable not declared");

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
        return "newLock("+var+")";
    }
}
