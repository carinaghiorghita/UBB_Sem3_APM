package Tests;

import Exceptions.EvalException;
import Model.ADTs.IDict;
import Model.ADTs.IHeap;
import Model.ADTs.myDict;
import Model.ADTs.myHeap;
import Model.Expressions.IExp;
import Model.Expressions.RelationalExp;
import Model.Expressions.ValueExp;
import Model.Values.IValue;
import Model.Values.IntValue;

public class ExpRelation_Test {
    public ExpRelation_Test(){}
    public void test(){
        IDict<String, IValue> table=new myDict<String,IValue>();
        IHeap<IValue> heap=new myHeap<IValue>();
        IExp ex=new RelationalExp(new ValueExp(new IntValue(3)),new ValueExp(new IntValue(2)),">");
        try {
            IValue ev = ex.eval(table,heap);
            String s=ev.toString();
            assert s.equals("true");
        }catch(EvalException e){
            assert false;
        }
    }
}
