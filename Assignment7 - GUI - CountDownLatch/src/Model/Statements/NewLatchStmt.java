package Model.Statements;

import Exceptions.EvalException;
import Exceptions.StmtException;
import Exceptions.TypeCheckException;
import Model.ADTs.IDict;
import Model.ADTs.IHeap;
import Model.ADTs.ILatchTable;
import Model.Expressions.IExp;
import Model.ProgramState;
import Model.Types.IType;
import Model.Types.IntType;
import Model.Values.IValue;
import Model.Values.IntValue;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewLatchStmt implements IStmt{
    private String var;
    private IExp exp;
    private static Lock lock=new ReentrantLock();

    public NewLatchStmt(String v,IExp e){var=v;exp=e;}

    @Override
    public ProgramState execute(ProgramState state) throws StmtException {
        lock.lock();
        try{
        IDict<String, IValue> table=state.getSymTable();
        IHeap<IValue> heap=state.getHeapTable();
        ILatchTable latch = state.getLatchTable();

        IntValue nr = (IntValue)(exp.eval(table, heap));
        int number = nr.getValue();

        int freeLoc= latch.getFreeAddress();
        latch.put(freeLoc,number);

        if(table.isDefined(var))
            table.update(var,new IntValue(freeLoc));
        else
            table.add(var,new IntValue(freeLoc));

        } catch(EvalException e){
            throw new StmtException(e.getMessage());
        }
        lock.unlock();
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
        return "newLatch("+var+","+exp.toString()+")";
    }
}
