package Tests;

import Exceptions.StmtException;
import Model.ADTs.*;
import Model.Expressions.IExp;
import Model.Expressions.LogicExp;
import Model.Expressions.ValueExp;
import Model.ProgramState;
import Model.Statements.IStmt;
import Model.Statements.IfStmt;
import Model.Statements.PrintStmt;
import Model.Statements.VarDeclStmt;
import Model.Types.IntType;
import Model.Values.BoolValue;
import Model.Values.IValue;
import Model.Values.IntValue;
import Model.Values.StringValue;

import java.io.BufferedReader;

public class StmtPrint_Test {
    public StmtPrint_Test(){}

    public void test(){
        IStack<IStmt> stack=new myStack<IStmt>();
        IDict<String, IValue> table=new myDict<String, IValue>();
        IDict<StringValue, BufferedReader>files=new myDict<StringValue, BufferedReader>();
        IList<IValue> out=new myList<IValue>();
        IStmt prog=new VarDeclStmt("v",new IntType());
        IHeap<IValue> heap=new myHeap<IValue>();

        ProgramState ps=new ProgramState(stack,table,files,heap,out,prog,1);

        IStmt pr=new PrintStmt(new ValueExp(new IntValue(3)));

        try{
            ps=pr.execute(ps);
            assert ps.getOutput().getSize()==1;
        }catch(StmtException e){
            assert false;
        }
    }
}
