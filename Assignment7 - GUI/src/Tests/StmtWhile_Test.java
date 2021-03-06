package Tests;

import Exceptions.StackException;
import Exceptions.StmtException;
import Model.ADTs.*;
import Model.Expressions.ArithmExp;
import Model.Expressions.RelationalExp;
import Model.Expressions.ValueExp;
import Model.Expressions.VarExp;
import Model.ProgramState;
import Model.Statements.AssignStmt;
import Model.Statements.IStmt;
import Model.Statements.VarDeclStmt;
import Model.Statements.WhileStmt;
import Model.Types.IntType;
import Model.Values.IValue;
import Model.Values.IntValue;
import Model.Values.StringValue;

import java.io.BufferedReader;

public class StmtWhile_Test {
    public StmtWhile_Test() {
    }

    public void test() {
        IStack<IStmt> stack = new myStack<IStmt>();
        IDict<String, IValue> table = new myDict<String, IValue>();
        IDict<StringValue, BufferedReader> files = new myDict<StringValue, BufferedReader>();
        IList<IValue> out = new myList<IValue>();
        IStmt prog = new VarDeclStmt("v", new IntType());
        IHeap<IValue> heap = new myHeap<IValue>();

        ProgramState ps = new ProgramState(stack, table, files, heap, out, prog,1);

        IStmt vardec=new VarDeclStmt("v",new IntType());
        try{
            ps= vardec.execute(ps);
        }catch(StmtException e){
            assert false;
        }

        IStmt varasgn=new AssignStmt("v",new ValueExp(new IntValue(3)));
        try{
            ps= varasgn.execute(ps);
        }catch(StmtException e){
            assert false;
        }

        assert(table.lookup("v").equals(new IntValue(3)));

        IStmt w = new WhileStmt(new RelationalExp(new VarExp("v"),new ValueExp(new IntValue(0)),">"),new AssignStmt("v",new ArithmExp('-',new VarExp("v"),new ValueExp(new IntValue(1)))));
        stack.push(w);
        while(!(stack.isEmpty())) {
            try {
                ps = w.execute(ps);
                if(!(stack.isEmpty())) w=stack.pop();
            } catch (StmtException| StackException e) {
                assert false;
            }
        }

        assert(table.lookup("v").equals(new IntValue(0)));
        assert (stack.isEmpty());
    }
}