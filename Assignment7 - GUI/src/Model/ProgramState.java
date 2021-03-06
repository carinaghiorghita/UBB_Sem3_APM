package Model;

import Exceptions.OneStepException;
import Exceptions.StackException;
import Exceptions.StmtException;
import Model.ADTs.*;
import Model.Statements.IStmt;
import Model.Values.IValue;
import Model.Values.StringValue;

import java.io.BufferedReader;

public class ProgramState {
    private IStack<IStmt> exeStack;
    private IDict<String, IValue> symTable;
    private IDict<StringValue, BufferedReader> fileTable;
    private IHeap<IValue> heapTable;
    private IList<IValue> output;
    private IStmt originalProgram;
    private int id;
    //private int lastid;
    static int count=1;

    public ProgramState(IStack<IStmt> stk, IDict<String,IValue> symtbl,IDict<StringValue, BufferedReader> file,
                        IHeap<IValue> heap, IList<IValue> out, IStmt prg,int newID){
        exeStack=stk;
        symTable=symtbl;
        fileTable=file;
        heapTable=heap;
        output = out;
        originalProgram=prg;
        exeStack.push(prg);
        id=newID;
        //lastid=newID;

    }

    @Override
    public String toString() {
        StringBuilder s= new StringBuilder();
        s.append("\nThread ID:"+String.valueOf(id));
        s.append("\nExecution Stack:"+exeStack.toString());
        s.append("\nSymbol Table:"+symTable.toString());
        s.append("\nFile Table:"+fileTable.toStringFiles());
        s.append("\nHeap Table:"+heapTable.toString());
        s.append("\nOutput:"+output.toString()+"\n");
        s.append("\n--------------------------------\n");
        return s.toString();
    }

    public boolean isNotCompleted(){
        return !exeStack.isEmpty();
    }

    public ProgramState oneStep() throws OneStepException {
        if(exeStack.isEmpty()) return null;
        try {
            IStmt crtStmt = exeStack.pop();
            return crtStmt.execute(this);
        }catch(StackException | StmtException e){
            System.out.println(e.getMessage());
        }
        return this;
    }

    public IStack<IStmt> getExeStack() {
        return exeStack;
    }

    public IList<IValue> getOutput() {
        return output;
    }

    public IDict<String, IValue> getSymTable() {
        return symTable;
    }

    public IDict<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public IHeap<IValue> getHeapTable() {
        return heapTable;
    }

    public int getCurrentID(){
        return id;
    }

    public int getNewid(){return ++count;}

//    public void setID(int id) {
//        this.lastid = id;
//    }

    //    public String getExString() {
//        return originalProgram.toString();
//    }
}
