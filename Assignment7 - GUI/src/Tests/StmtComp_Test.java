package Tests;

import Exceptions.StmtException;
import Model.ADTs.*;
import Model.Expressions.VarExp;
import Model.ProgramState;
import Model.Statements.CompStmt;
import Model.Statements.IStmt;
import Model.Statements.PrintStmt;
import Model.Statements.VarDeclStmt;
import Model.Types.IntType;
import Model.Values.IValue;
import Model.Values.StringValue;

import java.io.BufferedReader;

public class StmtComp_Test {
    public StmtComp_Test(){}

    public void test(){
        IStack<IStmt> stack=new myStack<IStmt>();
        IDict<String, IValue> table=new myDict<String, IValue>();
        IDict<StringValue, BufferedReader>files=new myDict<StringValue, BufferedReader>();
        IList<IValue> out=new myList<IValue>();
        IStmt prog=new VarDeclStmt("v",new IntType());
        IHeap<IValue> heap=new myHeap<IValue>();

        ProgramState ps=new ProgramState(stack,table,files,heap,out,prog,1);

        IStmt comp=new CompStmt(new VarDeclStmt("v",new IntType()),new PrintStmt(new VarExp("v")));

        try{
            ps=comp.execute(ps);
            assert ps.getExeStack().size()==3;
        }catch(StmtException e){
            assert false;
        }
    }
}
