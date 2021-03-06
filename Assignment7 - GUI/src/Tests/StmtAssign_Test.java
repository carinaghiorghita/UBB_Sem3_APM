package Tests;

import Exceptions.StmtException;
import Model.ADTs.*;
import Model.Expressions.ValueExp;
import Model.ProgramState;
import Model.Statements.AssignStmt;
import Model.Statements.IStmt;
import Model.Statements.VarDeclStmt;
import Model.Types.IntType;
import Model.Values.IValue;
import Model.Values.IntValue;
import Model.Values.StringValue;

import java.io.BufferedReader;

public class StmtAssign_Test {
    public StmtAssign_Test(){}

    public void test(){
        IStack<IStmt> stack=new myStack<IStmt>();
        IDict<String, IValue> table=new myDict<String, IValue>();
        IDict<StringValue, BufferedReader>files=new myDict<StringValue, BufferedReader>();
        IList<IValue> out=new myList<IValue>();
        IStmt prog=new VarDeclStmt("v",new IntType());
        IHeap<IValue> heap=new myHeap<IValue>();

        ProgramState ps=new ProgramState(stack,table,files,heap,out,prog,1);

        IStmt decl=new VarDeclStmt("v",new IntType());
        try{
            ps=decl.execute(ps);
        }catch(StmtException e){
            assert false;
        }

        IStmt assign=new AssignStmt("v",new ValueExp(new IntValue(2)));
        try{
            //assert ps.getSymTable().isDefined("v");
            ps=assign.execute(ps);
            IValue v= ps.getSymTable().lookup("v");
            IntValue val=(IntValue)v;
            assert val.getValue()==2;
        }catch(StmtException e){
            assert false;
        }
    }
}
