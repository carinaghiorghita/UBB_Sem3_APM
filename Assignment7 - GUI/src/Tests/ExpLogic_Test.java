package Tests;

import Exceptions.EvalException;
import Model.ADTs.IDict;
import Model.ADTs.IHeap;
import Model.ADTs.myDict;
import Model.ADTs.myHeap;
import Model.Expressions.IExp;
import Model.Expressions.LogicExp;
import Model.Expressions.ValueExp;
import Model.Values.BoolValue;
import Model.Values.IValue;
import Model.Values.IntValue;

public class ExpLogic_Test {
    public ExpLogic_Test(){}
    public void test(){
        IDict<String, IValue> table=new myDict<String,IValue>();
        IHeap<IValue> heap=new myHeap<IValue>();
        IExp ex=new LogicExp(new ValueExp(new BoolValue(true)),new ValueExp(new BoolValue(false)),"and");
        try{
            IValue ev=ex.eval(table,heap);
            String s=ev.toString();
            assert s=="false";
        }catch(EvalException e){
            assert false;
        }
    }
}
