package Tests;

import Exceptions.EvalException;
import Model.ADTs.IDict;
import Model.ADTs.IHeap;
import Model.ADTs.myDict;
import Model.ADTs.myHeap;
import Model.Expressions.ArithmExp;
import Model.Expressions.IExp;
import Model.Expressions.ValueExp;
import Model.Values.IValue;
import Model.Values.IntValue;

public class ExpArithm_Test {
    public ExpArithm_Test(){}
    public void test(){
        IDict<String,IValue>table=new myDict<String,IValue>();
        IHeap<IValue> heap=new myHeap<IValue>();
        IExp ex=new ArithmExp('+',new ValueExp(new IntValue(3)),new ValueExp(new IntValue(2)));
        try {
            IValue ev = ex.eval(table,heap);
            String s=ev.toString();
            assert s.equals("5");
        }catch(EvalException e){
            assert false;
        }
    }
}
