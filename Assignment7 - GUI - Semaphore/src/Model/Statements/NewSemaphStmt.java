package Model.Statements;

import Exceptions.EvalException;
import Exceptions.StmtException;
import Exceptions.TypeCheckException;
import Model.ADTs.*;
import Model.Expressions.IExp;
import Model.Expressions.VarExp;
import Model.ProgramState;
import Model.Types.IType;
import Model.Types.IntType;
import Model.Values.IValue;
import Model.Values.IntValue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewSemaphStmt implements IStmt{
    private static Lock lock=new ReentrantLock();
    private String var;
    private IExp exp1;
    private IExp exp2;

    public NewSemaphStmt(String v,IExp e1,IExp e2){var=v;exp1=e1;exp2=e2;}

    @Override
    public ProgramState execute(ProgramState state) throws StmtException {
        lock.lock();

        IDict<String, IValue> table = state.getSymTable();
        IHeap<IValue> heap = state.getHeapTable();
        ISemaphoreTable semTab=state.getSemaphoreTable();

        try {
            IntValue nr1 = (IntValue)(exp1.eval(table, heap));
            IntValue nr2 = (IntValue)(exp2.eval(table, heap));

            int n1=(nr1).getValue();
            int n2=(nr2).getValue();
            int f=semTab.getFreeAddress();

            semTab.put(f, new myTuple<>(n1,new ArrayList<>(),n2));


            if(table.isDefined(var) && table.lookup(var).getType().equals(new IntType()))
                table.update(var,new IntValue(f));
            else throw new StmtException("var does not exist in SymTable");

        } catch(EvalException e){
            throw new StmtException(e.getMessage());
        }

        lock.unlock();

        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws TypeCheckException {
        //TODO
        return typeEnv;
    }

    @Override
    public String toString() {
        return "newSemaphore("+var+","+exp1.toString()+","+exp2.toString()+")";
    }
}
