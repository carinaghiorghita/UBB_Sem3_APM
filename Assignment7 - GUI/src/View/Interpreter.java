package View;

import Controller.Controller;
import Exceptions.TypeCheckException;
import Model.ADTs.*;
import Model.Expressions.*;
import Model.ProgramState;
import Model.Statements.*;
import Model.Types.BoolType;
import Model.Types.IType;
import Model.Types.IntType;
import Model.Types.RefType;
import Model.Values.*;
import Repository.IRepository;
import Repository.Repository;
import Tests.*;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

class Interpreter {
    /*
    public static void tests(){
        ExpArithm_Test test1=new ExpArithm_Test();
        ExpLogic_Test test2=new ExpLogic_Test();
        ExpValue_Test test3=new ExpValue_Test();
        ExpVar_Test test4=new ExpVar_Test();
        StmtVarDecl_Test test5=new StmtVarDecl_Test();
        StmtAssign_Test test6=new StmtAssign_Test();
        StmtComp_Test test7=new StmtComp_Test();
        StmtIf_Test test8=new StmtIf_Test();
        StmtPrint_Test test9=new StmtPrint_Test();

        test1.test();
        test2.test();
        test3.test();
        test4.test();
        test5.test();
        test6.test();
        test7.test();
        test8.test();
        test9.test();

        File_Test newTest1=new File_Test();
        ExpRelation_Test newTest2=new ExpRelation_Test();

        newTest1.test();
        newTest2.test();

        Heap_Test newNewTest1=new Heap_Test();
        StmtWhile_Test newNewTest2=new StmtWhile_Test();

        newNewTest1.test();
        newNewTest2.test();

        System.out.print("All tests are passing!\n");
    }
    */

    public static void main(String[] args) {
        //tests();


        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));


        IStack<IStmt> exeStack1=new myStack<IStmt>();
        IDict<String, IValue> symTable1=new myDict<String, IValue>();
        IDict<StringValue, BufferedReader>fileTable1=new myDict<StringValue, BufferedReader>();
        IHeap<IValue> heap1=new myHeap<IValue>();
        IList<IValue> output1=new myList<IValue>();
        IStmt ex1=new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))));
        try {
            IDict<String, IType> typeenv=new myDict<String, IType>();
            ex1.typecheck(typeenv);
            ProgramState prg1 = new ProgramState(exeStack1, symTable1, fileTable1, heap1, output1, ex1, 1);
            List<ProgramState> l1 = new ArrayList<ProgramState>();
            l1.add(prg1);
            IRepository repo1 = new Repository(l1, "log1.txt");
            Controller ctr1 = new Controller(repo1,"Example 1");

            menu.addCommand(new RunExample("1",ex1.toString(),ctr1));
        }catch(TypeCheckException e){
            System.out.print("Example 1:"+e.getMessage()+'\n');
        }

        IStack<IStmt> exeStack2=new myStack<IStmt>();
        IDict<String, IValue> symTable2=new myDict<String, IValue>();
        IDict<StringValue, BufferedReader>fileTable2=new myDict<StringValue, BufferedReader>();
        IHeap<IValue> heap2=new myHeap<IValue>();
        IList<IValue> output2=new myList<IValue>();
        IStmt ex2 = new CompStmt( new VarDeclStmt("a",new IntType()),
                new CompStmt(new VarDeclStmt("b",new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithmExp('+',new ValueExp(new IntValue(2)),
                                new ArithmExp('*',new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b",new ArithmExp('+',new VarExp("a"),
                                        new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        try{
            IDict<String, IType> typeenv=new myDict<String, IType>();
            ex2.typecheck(typeenv);
            ProgramState prg2 = new ProgramState(exeStack2,symTable2,fileTable2,heap2,output2,ex2,1);
            List<ProgramState> l2=new ArrayList<ProgramState>();
            l2.add(prg2);
            IRepository repo2 = new Repository(l2,"log2.txt");
            Controller ctr2 = new Controller(repo2,"Example 2");
            menu.addCommand(new RunExample("2",ex2.toString(),ctr2));

        }catch(TypeCheckException e){
            System.out.print("Example 2:"+e.getMessage()+'\n');
        }


        IStack<IStmt> exeStack3=new myStack<IStmt>();
        IDict<String, IValue> symTable3=new myDict<String, IValue>();
        IDict<StringValue, BufferedReader>fileTable3=new myDict<StringValue, BufferedReader>();
        IHeap<IValue> heap3=new myHeap<IValue>();
        IList<IValue> output3=new myList<IValue>();
        IStmt ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new IntValue(2))),
                                        new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));
        try {
            IDict<String, IType> typeenv=new myDict<String, IType>();
            ex3.typecheck(typeenv);

            ProgramState prg3 = new ProgramState(exeStack3, symTable3, fileTable3, heap3, output3, ex3, 1);

            List<ProgramState> l3 = new ArrayList<ProgramState>();
            l3.add(prg3);
            IRepository repo3 = new Repository(l3, "log3.txt");
            Controller ctr3 = new Controller(repo3,"Example 3");
            menu.addCommand(new RunExample("3",ex3.toString(),ctr3));

        }catch(TypeCheckException e){
            System.out.print("Example 3:"+e.getMessage()+'\n');
        }

        IStack<IStmt> exeStack4=new myStack<IStmt>();
        IDict<String, IValue> symTable4=new myDict<String, IValue>();
        IDict<StringValue, BufferedReader>fileTable4=new myDict<StringValue, BufferedReader>();
        IHeap<IValue> heap4=new myHeap<IValue>();
        IList<IValue> output4=new myList<IValue>();
        IExp filename4=new ValueExp(new StringValue("ex4.in"));
        IStmt ex4 = new CompStmt(new OpenFileStmt(filename4),
                new CompStmt(new VarDeclStmt("v",new IntType()),
                        new CompStmt(new ReadFileStmt(filename4, "v"),
                            new CompStmt(new PrintStmt(new VarExp("v")),
                                new CompStmt(new IfStmt(new RelationalExp(new VarExp("v"),new ValueExp(new IntValue(2)),">="),
                                        new CompStmt(new ReadFileStmt(filename4, "v"), new PrintStmt(new VarExp("v"))),
                                        new PrintStmt(new ValueExp(new IntValue(-1)))), new CloseFileStmt(filename4))))));
        try {
            IDict<String, IType> typeenv=new myDict<String, IType>();
            ex4.typecheck(typeenv);

            ProgramState prg4 = new ProgramState(exeStack4, symTable4, fileTable4, heap4, output4, ex4, 1);
            List<ProgramState> l4 = new ArrayList<ProgramState>();
            l4.add(prg4);
            IRepository repo4 = new Repository(l4, "log4.txt");
            Controller ctr4 = new Controller(repo4,"Example 4");
            menu.addCommand(new RunExample("4",ex4.toString(),ctr4));

        }catch(TypeCheckException e){
            System.out.print("Example 4:"+e.getMessage()+'\n');
        }

        IStack<IStmt> exeStack5=new myStack<IStmt>();
        IDict<String, IValue> symTable5=new myDict<String, IValue>();
        IDict<StringValue, BufferedReader>fileTable5=new myDict<StringValue, BufferedReader>();
        IHeap<IValue> heap5=new myHeap<IValue>();
        IList<IValue> output5=new myList<IValue>();
        IStmt ex5 = new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(6))),
                    new CompStmt(new WhileStmt(new RelationalExp(new VarExp("v"),new ValueExp(new IntValue(4)),">"),
                            new CompStmt(new PrintStmt( new VarExp("v")),new AssignStmt("v",
                                    new ArithmExp('-',new VarExp("v"),new ValueExp(new IntValue(1)))))),
                                                new PrintStmt(new VarExp("v")))));
        try{

            IDict<String, IType> typeenv=new myDict<String, IType>();
            ex5.typecheck(typeenv);

            ProgramState prg5 = new ProgramState(exeStack5,symTable5,fileTable5,heap5,output5,ex5,1);
            List<ProgramState> l5=new ArrayList<ProgramState>();
            l5.add(prg5);
            IRepository repo5 = new Repository(l5,"log5.txt");
            Controller ctr5 = new Controller(repo5,"Example 5");
            menu.addCommand(new RunExample("5",ex5.toString(),ctr5));

        }catch(TypeCheckException e){
            System.out.print("Example 5:"+e.getMessage()+'\n');
        }

        IStack<IStmt> exeStack6=new myStack<IStmt>();
        IDict<String, IValue> symTable6=new myDict<String, IValue>();
        IDict<StringValue, BufferedReader>fileTable6=new myDict<StringValue, BufferedReader>();
        IHeap<IValue> heap6=new myHeap<IValue>();
        IList<IValue> output6=new myList<IValue>();
        IStmt ex6 = new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),
                new CompStmt(new VarDeclStmt("a",new RefType(new RefType(new IntType()))),
                    new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new NewHeapStmt("a",new VarExp("v")),
                            new CompStmt(new WriteHeapStmt("v", new ValueExp(new IntValue(30))),
                                new PrintStmt(new ReadHeapExp(new VarExp("a"))))))));

        try {
            IDict<String, IType> typeenv=new myDict<String, IType>();
            ex6.typecheck(typeenv);

            ProgramState prg6 = new ProgramState(exeStack6, symTable6, fileTable6, heap6, output6, ex6, 1);

            List<ProgramState> l6 = new ArrayList<ProgramState>();
            l6.add(prg6);
            IRepository repo6 = new Repository(l6, "log6.txt");
            Controller ctr6 = new Controller(repo6,"Example 6");
            menu.addCommand(new RunExample("6",ex6.toString(),ctr6));

        }catch(TypeCheckException e){
            System.out.print("Example 6:"+e.getMessage()+'\n');
        }

        IStack<IStmt> exeStack7=new myStack<IStmt>();
        IDict<String, IValue> symTable7=new myDict<String, IValue>();
        IDict<StringValue, BufferedReader>fileTable7=new myDict<StringValue, BufferedReader>();
        IHeap<IValue> heap7=new myHeap<IValue>();
        IList<IValue> output7=new myList<IValue>();
        IStmt ex7 = new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new VarDeclStmt("a",new RefType(new IntType())),
                        new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(10))),
                                new CompStmt(new NewHeapStmt("a",new ValueExp(new IntValue(22))),
                                        new CompStmt(
                                            new ForkStmt(
                                                new CompStmt(new WriteHeapStmt("a",new ValueExp(new IntValue(30))),
                                                        new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(32))),
                                                                new CompStmt(new PrintStmt(new VarExp("v")),
                                                                        new PrintStmt(new ReadHeapExp(new VarExp("a"))))))
                                            ),
                                                new CompStmt(new PrintStmt(new VarExp("v")),
                                                        new PrintStmt(new ReadHeapExp(new VarExp("a")))))))));
        try {
            IDict<String, IType> typeenv=new myDict<String, IType>();
            ex7.typecheck(typeenv);

            ProgramState prg7 = new ProgramState(exeStack7, symTable7, fileTable7, heap7, output7, ex7, 1);
            List<ProgramState> l7 = new ArrayList<ProgramState>();
            l7.add(prg7);
            IRepository repo7 = new Repository(l7, "log7.txt");
            Controller ctr7 = new Controller(repo7,"Example 7");
            menu.addCommand(new RunExample("7",ex7.toString(),ctr7));

        }catch(TypeCheckException e){
            System.out.print("Example 7:"+e.getMessage()+'\n');
        }

        IStack<IStmt> exeStack8=new myStack<IStmt>();
        IDict<String, IValue> symTable8=new myDict<String, IValue>();
        IDict<StringValue, BufferedReader>fileTable8=new myDict<StringValue, BufferedReader>();
        IHeap<IValue> heap8=new myHeap<IValue>();
        IList<IValue> output8=new myList<IValue>();
        IStmt ex8 = new CompStmt(new VarDeclStmt("a",new IntType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(7))),
                                new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new IntValue(2))),
                                        new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));
        try {
            IDict<String, IType> typeenv=new myDict<String, IType>();
            ex8.typecheck(typeenv);

            ProgramState prg8 = new ProgramState(exeStack8, symTable8, fileTable8, heap8, output8, ex8, 1);

            List<ProgramState> l8 = new ArrayList<ProgramState>();
            l8.add(prg8);
            IRepository repo8 = new Repository(l8, "log8.txt");
            Controller ctr8 = new Controller(repo8,"Example 8");
            menu.addCommand(new RunExample("8",ex8.toString(),ctr8));

        }catch(TypeCheckException e){
            System.out.print("Example 8:"+e.getMessage()+'\n');
        }

        menu.show();

    }
}