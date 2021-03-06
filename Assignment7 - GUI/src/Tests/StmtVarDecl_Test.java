package Tests;

import Exceptions.StmtException;
import Model.ADTs.*;
import Model.ProgramState;
import Model.Statements.AssignStmt;
import Model.Statements.CompStmt;
import Model.Statements.IStmt;
import Model.Statements.VarDeclStmt;
import Model.Types.IntType;
import Model.Values.IValue;
import Model.Values.StringValue;

import java.io.BufferedReader;

public class StmtVarDecl_Test {
    public StmtVarDecl_Test(){}
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
            assert ps.getSymTable().isDefined("v");
        }catch(StmtException e){
            assert false;
        }
    }
}
