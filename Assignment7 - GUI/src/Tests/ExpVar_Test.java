package Tests;

import Exceptions.EvalException;
import Model.ADTs.IDict;
import Model.ADTs.IHeap;
import Model.ADTs.myDict;
import Model.ADTs.myHeap;
import Model.Expressions.IExp;
import Model.Expressions.VarExp;
import Model.Values.IValue;

public class ExpVar_Test {
    public ExpVar_Test(){}
    public void test(){
        IDict<String, IValue> table=new myDict<String,IValue>();
        IHeap<IValue> heap=new myHeap<IValue>();
        IExp ex=new VarExp("v");
        try {
            ex.eval(table,heap);
            assert false;
        }catch(EvalException e){
            assert true;
        }
    }
}
