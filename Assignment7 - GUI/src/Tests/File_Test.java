package Tests;

import Exceptions.StmtException;
import Model.ADTs.*;
import Model.Expressions.IExp;
import Model.Expressions.ValueExp;
import Model.ProgramState;
import Model.Statements.*;
import Model.Types.IntType;
import Model.Types.StringType;
import Model.Values.IValue;
import Model.Values.IntValue;
import Model.Values.StringValue;

import java.io.BufferedReader;

public class File_Test {
    public File_Test(){}

    public void test(){
        IStack<IStmt> stack=new myStack<IStmt>();
        IDict<String, IValue> table=new myDict<String, IValue>();
        IList<IValue> out=new myList<IValue>();
        IDict<StringValue, BufferedReader>files=new myDict<StringValue, BufferedReader>();
        IHeap<IValue> heap=new myHeap<IValue>();

        IStmt prog=new VarDeclStmt("v",new StringType());

        ProgramState ps=new ProgramState(stack,table,files,heap,out,prog,1);

        IExp exfile=new ValueExp(new StringValue("test.in"));
        IStmt of=new OpenFileStmt(exfile);
        try {
            of.execute(ps);
        }catch (StmtException e){
            assert false;
        }

        IStmt vardec=new VarDeclStmt("varc",new IntType());
        try{
            vardec.execute(ps);
        }catch (StmtException e){
            assert false;
        }

        IStmt rf=new ReadFileStmt(exfile,"varc");
        try{
            rf.execute(ps);
        }catch (StmtException e){
            assert false;
        }

        IValue val=(IntValue)table.lookup("varc");
        assert(val.equals(new IntValue(15)));

        IStmt rf2=new ReadFileStmt(exfile,"varc");
        try{
            rf2.execute(ps);
        }catch (StmtException e){
            assert false;
        }

        val=(IntValue)table.lookup("varc");
        assert(val.equals(new IntValue(50)));

        IStmt cf=new CloseFileStmt(exfile);
        try{
            cf.execute(ps);
        }catch (StmtException e){
            assert false;
        }

    }
}
