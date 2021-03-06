package Model.Statements;

import Exceptions.StmtException;
import Exceptions.TypeCheckException;
import Model.ADTs.IBarrierTable;
import Model.ADTs.IDict;
import Model.ADTs.IHeap;
import Model.ProgramState;
import Model.Types.IType;
import Model.Types.IntType;
import Model.Values.IValue;
import Model.Values.IntValue;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AwaitStmt implements IStmt{
    private String var;

    public AwaitStmt(String v){var=v;}
    private static Lock lock=new ReentrantLock();

    @Override
    public ProgramState execute(ProgramState state) throws StmtException {
        IDict<String, IValue> table=state.getSymTable();
        IHeap<IValue> heap=state.getHeapTable();
        lock.lock();
        IBarrierTable barrier=state.getBarrierTable();
        lock.unlock();

        if(!table.isDefined(var))throw new StmtException("Var not defined");
        if(!table.lookup(var).getType().equals(new IntType())) throw new StmtException("Var is not int");
        IntValue f=(IntValue)table.lookup(var);
        int foundIndex=f.getValue();

        lock.lock();
        if(!barrier.containsKey(foundIndex)) throw new StmtException("Index not in BarrierTable");
        lock.unlock();

        lock.lock();
        Pair<Integer,List<Integer>> foundBarrier= barrier.get(foundIndex);
        lock.unlock();

        int NL=foundBarrier.getValue().size();
        int N1=foundBarrier.getKey();
        ArrayList<Integer> l=(ArrayList<Integer>) foundBarrier.getValue();

        if(N1>NL){
            if(l.contains(state.getCurrentID()))
                state.getExeStack().push(this);
            else {
                l.add(state.getCurrentID());
                state.getExeStack().push(this);
                //barrier.put(foundIndex, new Pair<>(N1, l));
            }
        }

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
