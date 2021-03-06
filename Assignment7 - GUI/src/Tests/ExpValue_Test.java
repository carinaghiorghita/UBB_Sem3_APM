package Tests;

import Exceptions.EvalException;
import Model.ADTs.IDict;
import Model.ADTs.IHeap;
import Model.ADTs.myDict;
import Model.ADTs.myHeap;
import Model.Expressions.IExp;
import Model.Expressions.ValueExp;
import Model.Values.IValue;
import Model.Values.IntValue;

public class ExpValue_Test {
    public ExpValue_Test(){}
    public void test(){
        IDict<String, IValue> table=new myDict<String,IValue>();
        IHeap<IValue> heap=new myHeap<IValue>();
        IExp ex=new ValueExp(new IntValue(5));
        try {
            IValue ev = ex.eval(table,heap);
            String s=ev.toString();
            assert s.equals("5");
        }catch(EvalException e){
            assert false;
        }
    }
}
