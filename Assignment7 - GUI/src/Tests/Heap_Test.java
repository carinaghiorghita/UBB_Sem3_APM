package Tests;

import Exceptions.EvalException;
import Exceptions.StmtException;
import Model.ADTs.*;
import Model.Expressions.ArithmExp;
import Model.Expressions.ReadHeapExp;
import Model.Expressions.ValueExp;
import Model.Expressions.VarExp;
import Model.ProgramState;
import Model.Statements.*;
import Model.Types.IntType;
import Model.Types.RefType;
import Model.Values.IValue;
import Model.Values.IntValue;
import Model.Values.RefValue;
import Model.Values.StringValue;

import java.io.BufferedReader;

public class Heap_Test {
    public Heap_Test(){}
    public void test(){
        IStack<IStmt> stack=new myStack<IStmt>();
        IDict<String, IValue> table=new myDict<String, IValue>();
        IDict<StringValue, BufferedReader>files=new myDict<StringValue, BufferedReader>();
        IList<IValue> out=new myList<IValue>();
        IStmt prog=new VarDeclStmt("v",new IntType());
        IHeap<IValue> heap=new myHeap<IValue>();

        ProgramState ps=new ProgramState(stack,table,files,heap,out,prog,1);

        IStmt vardec=new VarDeclStmt("v",new RefType(new IntType()));
        try{
            vardec.execute(ps);
        }catch (StmtException e){
            assert false;
        }

        IStmt nH=new NewHeapStmt("v",new ValueExp(new IntValue(20)));
        try{
            ps=nH.execute(ps);
        }catch(StmtException e){
            assert false;
        }
        //System.out.print(heap.toString());

        IStmt vardec2=new VarDeclStmt("a",new RefType(new RefType(new IntType())));
        try{
            vardec2.execute(ps);
        }catch (StmtException e){
            assert false;
        }

        IStmt nH2=new NewHeapStmt("a",new VarExp("v"));
        try{
            ps=nH2.execute(ps);
        }catch(StmtException e){
            System.out.print(e.getMessage());
            assert false;
        }

        IStmt pr=new PrintStmt(new ValueExp(table.lookup("v")));

        try{
            ps=pr.execute(ps);
        }catch(StmtException e){
            assert false;
        }

        IStmt pr2=new PrintStmt(new ValueExp(table.lookup("a")));

        try{
            ps=pr2.execute(ps);
        }catch(StmtException e){
            assert false;
        }

        IStmt pr3=new PrintStmt(new ReadHeapExp(new VarExp("v")));
        try{
            ps=pr3.execute(ps);
        }catch(StmtException e){
            assert false;
        }

        IStmt pr4=new PrintStmt(new ArithmExp('+',new ReadHeapExp(new VarExp("a")),new ValueExp(new IntValue(5))));

        try{

            ps=pr4.execute(ps);
        }catch(StmtException e){
            System.out.print(e.getMessage());
            assert false;
        }

        IStmt wH=new WriteHeapStmt("v",new ValueExp(new IntValue(30)));
        try{

            ps=wH.execute(ps);
        }catch(StmtException e){
            System.out.print(e.getMessage());
            assert false;
        }

        IStmt pr5=new PrintStmt(new ArithmExp('+',new ReadHeapExp(new VarExp("v")),new ValueExp(new IntValue(5))));

        try{

            ps=pr5.execute(ps);
        }catch(StmtException e){
            System.out.print(e.getMessage());
            assert false;
        }


//        System.out.print(heap.toString());
//        System.out.print(table.toString());
//        System.out.print(out.toString());


    }

}
