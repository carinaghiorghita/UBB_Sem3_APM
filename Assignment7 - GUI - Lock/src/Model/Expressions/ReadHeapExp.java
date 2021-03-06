package Model.Expressions;

import Exceptions.EvalException;
import Exceptions.TypeCheckException;
import Model.ADTs.IDict;
import Model.ADTs.IHeap;
import Model.Types.IType;
import Model.Types.RefType;
import Model.Values.IValue;
import Model.Values.RefValue;

public class ReadHeapExp implements IExp{
    private IExp exp;

    public ReadHeapExp(IExp e){
        exp=e;
    }

    @Override
    public IValue eval(IDict<String, IValue> table, IHeap<IValue> heap) throws EvalException {
        try{

            if(!(exp.eval(table,heap) instanceof RefValue))
                throw new EvalException("Invalid type");

            RefValue ref=(RefValue)exp.eval(table,heap);
            int addr=ref.getAddr();

            if(!(heap.containsKey(addr)))
                throw new EvalException("Key doesn't exist");

            while(heap.get(addr).getType() instanceof RefType) {
                RefValue reff=(RefValue)heap.get(addr);
                addr = reff.getAddr();

                if(!(heap.containsKey(addr)))
                    throw new EvalException("Key doesn't exist");
            }
            return heap.get(addr);

        }catch(EvalException e){
            throw e;
        }

    }

    @Override
    public String toString() {
        return "read("+exp.toString()+")";
    }

    @Override
    public IType typecheck(IDict<String, IType> typeEnv) throws TypeCheckException {
        IType typ=exp.typecheck(typeEnv);
        if (typ instanceof RefType) {
            RefType reft =(RefType) typ;
            return reft.getInner();
        } else
            throw new TypeCheckException("the rH argument is not a Ref Type");
    }
}
