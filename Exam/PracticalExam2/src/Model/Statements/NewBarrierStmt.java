package Model.Statements;

import Exceptions.EvalException;
import Exceptions.StmtException;
import Exceptions.TypeCheckException;
import Model.ADTs.IBarrierTable;
import Model.ADTs.IDict;
import Model.ADTs.IHeap;
import Model.Expressions.IExp;
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

public class NewBarrierStmt implements IStmt{
    private String var;
    private IExp exp;
    private static Lock lock=new ReentrantLock();

    public NewBarrierStmt(String v, IExp e){var=v;exp=e;}

    @Override
    public ProgramState execute(ProgramState state) throws StmtException {
        IDict<String, IValue> table=state.getSymTable();
        IHeap<IValue> heap=state.getHeapTable();
        lock.lock();
        IBarrierTable barrier=state.getBarrierTable();
        lock.unlock();

        try{
            IntValue nr = (IntValue)(exp.eval(table, heap));
            int number=nr.getValue();

            lock.lock();
            int f=barrier.getFreeAddress();
            lock.unlock();

            lock.lock();
            barrier.put(f, new Pair<>(number,new ArrayList<>()));
            lock.unlock();

            if(table.isDefined(var))
                if(table.lookup(var).getType().equals(new IntType()))
                    table.update(var,new IntValue(f));
                else throw new StmtException("Var does not have the type int");
            else throw new StmtException("Var is not defined");


        }catch(EvalException e){
            throw new StmtException(e.getMessage());
        }

        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws TypeCheckException {
        if(typeEnv.lookup(var).equals(new IntType()))
            if(exp.typecheck(typeEnv).equals(new IntType()))
                return typeEnv;
            else throw new TypeCheckException("Exp is not int");
        else throw new TypeCheckException("Var is not int");
    }

    @Override
    public String toString() {
        return "newBarrier("+var+","+exp.toString()+")";
    }
}
