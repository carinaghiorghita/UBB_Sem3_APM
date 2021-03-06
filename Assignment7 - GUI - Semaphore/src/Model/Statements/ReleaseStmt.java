package Model.Statements;

import Exceptions.StmtException;
import Exceptions.TypeCheckException;
import Model.ADTs.*;
import Model.ProgramState;
import Model.Types.IType;
import Model.Types.IntType;
import Model.Values.IValue;
import Model.Values.IntValue;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReleaseStmt implements IStmt{
    private String var;
    private static Lock lock=new ReentrantLock();

    public ReleaseStmt (String v){var=v;}

    @Override
    public ProgramState execute(ProgramState state) throws StmtException {
        lock.lock();

        IDict<String, IValue> table = state.getSymTable();
        IHeap<IValue> heap = state.getHeapTable();
        ISemaphoreTable semTab=state.getSemaphoreTable();
        try {
            if(!table.isDefined(var))throw new StmtException("Index not in SymTable");
            if(!table.lookup(var).getType().equals(new IntType()))throw new StmtException("Index should be int");
            IntValue f=(IntValue)table.lookup(var);
            int foundIndex=f.getValue();

            if(!semTab.lookup(foundIndex)) throw new StmtException("Index not in SemaphoreTable");

            ITuple<Integer, List<Integer>, Integer> foundSem=semTab.get(foundIndex);

            if(foundSem.getSecond().contains(state.getCurrentID()))
                foundSem.getSecond().remove((Integer)state.getCurrentID());
            semTab.update(foundIndex, new myTuple<>(foundSem.getFirst(),foundSem.getSecond(),foundSem.getThird()));

        }catch(Exception e){
            throw new StmtException(e.getMessage());
        }finally {
            lock.unlock();
        }

        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws TypeCheckException {
        //TODO
        return typeEnv;
    }

    @Override
    public String toString() {
        return "release("+var+")";
    }
}
