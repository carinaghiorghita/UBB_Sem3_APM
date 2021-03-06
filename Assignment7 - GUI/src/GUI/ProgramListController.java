package GUI;

import Controller.Controller;
import Exceptions.TypeCheckException;
import Model.ADTs.*;
import Model.ProgramState;
import Model.Types.IType;
import Repository.IRepository;
import Repository.Repository;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;

import java.io.BufferedReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import Model.Expressions.*;
import Model.Statements.*;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.RefType;
import Model.Values.*;
import javafx.stage.Stage;

public class ProgramListController implements Initializable {

    public ProgramListController(){}

    private List<Controller> programs= new ArrayList<>();

    @FXML
    private ListView<String> programListView=new ListView<>();


    public void setListView(){

        IStmt ex1= new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))));

        try{
            IStack<IStmt> exeStack1=new myStack<IStmt>();
            IDict<String, IValue> symTable1=new myDict<String, IValue>();
            IDict<StringValue, BufferedReader>fileTable1=new myDict<StringValue, BufferedReader>();
            IHeap<IValue> heap1=new myHeap<IValue>();
            IList<IValue> output1=new myList<IValue>();

            IDict<String, IType> typeenv=new myDict<String, IType>();
            ex1.typecheck(typeenv);

            ProgramState prg1 = new ProgramState(exeStack1, symTable1, fileTable1, heap1, output1, ex1, 1);
            List<ProgramState> l1 = new ArrayList<ProgramState>();
            l1.add(prg1);
            IRepository repo1 = new Repository(l1, "log1.txt");
            Controller ctr1 = new Controller(repo1,"Example 1");

            programs.add(ctr1);
        }catch(TypeCheckException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Typecheck error");
            alert.setContentText("Example 1 did not pass the typecheck: "+e.getMessage());

            alert.showAndWait();
        }


        IStmt ex2 = new CompStmt( new VarDeclStmt("a",new IntType()),
                new CompStmt(new VarDeclStmt("b",new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithmExp('+',new ValueExp(new IntValue(2)),
                                new ArithmExp('*',new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b",new ArithmExp('+',new VarExp("a"),
                                        new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));

        try{
            IStack<IStmt> exeStack2=new myStack<IStmt>();
            IDict<String, IValue> symTable2=new myDict<String, IValue>();
            IDict<StringValue, BufferedReader>fileTable2=new myDict<StringValue, BufferedReader>();
            IHeap<IValue> heap2=new myHeap<IValue>();
            IList<IValue> output2=new myList<IValue>();

            IDict<String, IType> typeenv=new myDict<String, IType>();
            ex2.typecheck(typeenv);

            ProgramState prg2 = new ProgramState(exeStack2,symTable2,fileTable2,heap2,output2,ex2,1);
            List<ProgramState> l2=new ArrayList<ProgramState>();
            l2.add(prg2);
            IRepository repo2 = new Repository(l2,"log2.txt");
            Controller ctr2 = new Controller(repo2,"Example 2");

            programs.add(ctr2);
        }catch(TypeCheckException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Typecheck error");
            alert.setContentText("Example 2 did not pass the typecheck: "+e.getMessage());

            alert.showAndWait();
        }


        IStmt ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new IntValue(2))),
                                        new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));
        try{
            IStack<IStmt> exeStack3=new myStack<IStmt>();
            IDict<String, IValue> symTable3=new myDict<String, IValue>();
            IDict<StringValue, BufferedReader>fileTable3=new myDict<StringValue, BufferedReader>();
            IHeap<IValue> heap3=new myHeap<IValue>();
            IList<IValue> output3=new myList<IValue>();

            IDict<String, IType> typeenv=new myDict<String, IType>();
            ex3.typecheck(typeenv);

            ProgramState prg3 = new ProgramState(exeStack3, symTable3, fileTable3, heap3, output3, ex3, 1);

            List<ProgramState> l3 = new ArrayList<ProgramState>();
            l3.add(prg3);
            IRepository repo3 = new Repository(l3, "log3.txt");
            Controller ctr3 = new Controller(repo3,"Example 3");

            programs.add(ctr3);
        }catch(TypeCheckException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Typecheck error");
            alert.setContentText("Example 3 did not pass the typecheck: "+e.getMessage());

            alert.showAndWait();
        }

        IExp filename4=new ValueExp(new StringValue("ex4.in"));
        IStmt ex4 = new CompStmt(new OpenFileStmt(filename4),
                new CompStmt(new VarDeclStmt("v",new IntType()),
                        new CompStmt(new ReadFileStmt(filename4, "v"),
                                new CompStmt(new PrintStmt(new VarExp("v")),
                                        new CompStmt(new IfStmt(new RelationalExp(new VarExp("v"),new ValueExp(new IntValue(2)),">="),
                                                new CompStmt(new ReadFileStmt(filename4, "v"), new PrintStmt(new VarExp("v"))),
                                                new PrintStmt(new ValueExp(new IntValue(-1)))), new CloseFileStmt(filename4))))));

        try{
            IStack<IStmt> exeStack4=new myStack<IStmt>();
            IDict<String, IValue> symTable4=new myDict<String, IValue>();
            IDict<StringValue, BufferedReader>fileTable4=new myDict<StringValue, BufferedReader>();
            IHeap<IValue> heap4=new myHeap<IValue>();
            IList<IValue> output4=new myList<IValue>();

            IDict<String, IType> typeenv=new myDict<String, IType>();
            ex4.typecheck(typeenv);

            ProgramState prg4 = new ProgramState(exeStack4, symTable4, fileTable4, heap4, output4, ex4, 1);
            List<ProgramState> l4 = new ArrayList<ProgramState>();
            l4.add(prg4);
            IRepository repo4 = new Repository(l4, "log4.txt");
            Controller ctr4 = new Controller(repo4,"Example 4");

            programs.add(ctr4);
        }catch(TypeCheckException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Typecheck error");
            alert.setContentText("Example 4 did not pass the typecheck: "+e.getMessage());

            alert.showAndWait();
        }


        IStmt ex5 = new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(6))),
                        new CompStmt(new WhileStmt(new RelationalExp(new VarExp("v"),new ValueExp(new IntValue(4)),">"),
                                new CompStmt(new PrintStmt( new VarExp("v")),new AssignStmt("v",
                                        new ArithmExp('-',new VarExp("v"),new ValueExp(new IntValue(1)))))),
                                new PrintStmt(new VarExp("v")))));

        try{
            IStack<IStmt> exeStack5=new myStack<IStmt>();
            IDict<String, IValue> symTable5=new myDict<String, IValue>();
            IDict<StringValue, BufferedReader>fileTable5=new myDict<StringValue, BufferedReader>();
            IHeap<IValue> heap5=new myHeap<IValue>();
            IList<IValue> output5=new myList<IValue>();

            IDict<String, IType> typeenv=new myDict<String, IType>();
            ex5.typecheck(typeenv);

            ProgramState prg5 = new ProgramState(exeStack5,symTable5,fileTable5,heap5,output5,ex5,1);
            List<ProgramState> l5=new ArrayList<ProgramState>();
            l5.add(prg5);
            IRepository repo5 = new Repository(l5,"log5.txt");
            Controller ctr5 = new Controller(repo5,"Example 5");

            programs.add(ctr5);
        }catch(TypeCheckException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Typecheck error");
            alert.setContentText("Example 5 did not pass the typecheck: "+e.getMessage());

            alert.showAndWait();
        }


        IStmt ex6 = new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),
                new CompStmt(new VarDeclStmt("a",new RefType(new RefType(new IntType()))),
                        new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(20))),
                                new CompStmt(new NewHeapStmt("a",new VarExp("v")),
                                        new CompStmt(new WriteHeapStmt("v", new ValueExp(new IntValue(30))),
                                                new PrintStmt(new ReadHeapExp(new VarExp("a"))))))));

        try{
            IStack<IStmt> exeStack6=new myStack<IStmt>();
            IDict<String, IValue> symTable6=new myDict<String, IValue>();
            IDict<StringValue, BufferedReader>fileTable6=new myDict<StringValue, BufferedReader>();
            IHeap<IValue> heap6=new myHeap<IValue>();
            IList<IValue> output6=new myList<IValue>();

            IDict<String, IType> typeenv=new myDict<String, IType>();
            ex6.typecheck(typeenv);

            ProgramState prg6 = new ProgramState(exeStack6, symTable6, fileTable6, heap6, output6, ex6, 1);
            List<ProgramState> l6 = new ArrayList<ProgramState>();
            l6.add(prg6);
            IRepository repo6 = new Repository(l6, "log6.txt");
            Controller ctr6 = new Controller(repo6,"Example 6");

            programs.add(ctr6);
        }catch(TypeCheckException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Typecheck error");
            alert.setContentText("Example 6 did not pass the typecheck: "+e.getMessage());

            alert.showAndWait();
        }


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

        try{
            IStack<IStmt> exeStack7=new myStack<IStmt>();
            IDict<String, IValue> symTable7=new myDict<String, IValue>();
            IDict<StringValue, BufferedReader>fileTable7=new myDict<StringValue, BufferedReader>();
            IHeap<IValue> heap7=new myHeap<IValue>();
            IList<IValue> output7=new myList<IValue>();

            IDict<String, IType> typeenv=new myDict<String, IType>();
            ex7.typecheck(typeenv);

            ProgramState prg7 = new ProgramState(exeStack7, symTable7, fileTable7, heap7, output7, ex7, 1);
            List<ProgramState> l7 = new ArrayList<ProgramState>();
            l7.add(prg7);
            IRepository repo7 = new Repository(l7, "log7.txt");
            Controller ctr7 = new Controller(repo7,"Example 7");

            programs.add(ctr7);
        }catch(TypeCheckException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Typecheck error");
            alert.setContentText("Example 7 did not pass the typecheck: "+e.getMessage());

            alert.showAndWait();
        }


        IStmt ex8 = new CompStmt(new VarDeclStmt("a",new IntType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(7))),
                                new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new IntValue(2))),
                                        new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));

        try{
            IStack<IStmt> exeStack8=new myStack<IStmt>();
            IDict<String, IValue> symTable8=new myDict<String, IValue>();
            IDict<StringValue, BufferedReader>fileTable8=new myDict<StringValue, BufferedReader>();
            IHeap<IValue> heap8=new myHeap<IValue>();
            IList<IValue> output8=new myList<IValue>();

            IDict<String, IType> typeenv=new myDict<String, IType>();
            ex8.typecheck(typeenv);

            ProgramState prg8 = new ProgramState(exeStack8, symTable8, fileTable8, heap8, output8, ex8, 1);
            List<ProgramState> l8 = new ArrayList<ProgramState>();
            l8.add(prg8);
            IRepository repo8 = new Repository(l8, "log8.txt");
            Controller ctr8 = new Controller(repo8,"Example 8");

            programs.add(ctr8);
        }catch(TypeCheckException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Typecheck error");
            alert.setContentText("Example 8 did not pass the typecheck: "+e.getMessage());

            alert.showAndWait();
        }

        IStmt ex9 = new CompStmt(new VarDeclStmt("b",new BoolType()),
                new CompStmt(new VarDeclStmt("c",new IntType()),
                        new CompStmt(new AssignStmt("b",new ValueExp(new BoolValue(true))),
                                new CompStmt(new ConditionalStmt("c",new VarExp("b"),new ValueExp(new IntValue(100)),new ValueExp(new IntValue(200))),
                                        new CompStmt(new PrintStmt(new VarExp("c")),
                                                new CompStmt(new ConditionalStmt("c",new ValueExp(new BoolValue(false)),new ValueExp(new IntValue(100)),new ValueExp(new IntValue(200))),
                                                        new PrintStmt(new VarExp("c"))))))));

        try{
            IStack<IStmt> exeStack9=new myStack<IStmt>();
            IDict<String, IValue> symTable9=new myDict<String, IValue>();
            IDict<StringValue, BufferedReader>fileTable9=new myDict<StringValue, BufferedReader>();
            IHeap<IValue> heap9=new myHeap<IValue>();
            IList<IValue> output9=new myList<IValue>();

            IDict<String, IType> typeenv=new myDict<String, IType>();
            ex9.typecheck(typeenv);

            ProgramState prg9 = new ProgramState(exeStack9, symTable9, fileTable9, heap9, output9, ex9, 1);
            List<ProgramState> l9 = new ArrayList<ProgramState>();
            l9.add(prg9);
            IRepository repo9 = new Repository(l9, "log9.txt");
            Controller ctr9 = new Controller(repo9,"Example 9");

            programs.add(ctr9);
        }catch(TypeCheckException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Typecheck error");
            alert.setContentText("Example 9 did not pass the typecheck: "+e.getMessage());

            alert.showAndWait();
        }
        //TODO typecheck for for + modify typecheck for arithmexp back
        IStmt ex10 = new CompStmt(new VarDeclStmt("a",new RefType(new IntType())),
                new CompStmt(new NewHeapStmt("a",new ValueExp(new IntValue(20))),
                        new CompStmt(new ForStmt("v",new ValueExp(new IntValue(0)),new ValueExp(new IntValue(3)),new ArithmExp('+',new VarExp("v"),new ValueExp(new IntValue(1))),
                                new ForkStmt(new CompStmt(new PrintStmt(new VarExp("v")),new AssignStmt("v",new ArithmExp('*',new VarExp("v"),new ReadHeapExp(new VarExp("a"))))))),
                                new PrintStmt(new ReadHeapExp(new VarExp("a"))))));

        try{
            IStack<IStmt> exeStack10=new myStack<IStmt>();
            IDict<String, IValue> symTable10=new myDict<String, IValue>();
            IDict<StringValue, BufferedReader>fileTable10=new myDict<StringValue, BufferedReader>();
            IHeap<IValue> heap10=new myHeap<IValue>();
            IList<IValue> output10=new myList<IValue>();

            IDict<String, IType> typeenv=new myDict<String, IType>();
            ex10.typecheck(typeenv);

            ProgramState prg10 = new ProgramState(exeStack10, symTable10, fileTable10, heap10, output10, ex10, 1);
            List<ProgramState> l10 = new ArrayList<ProgramState>();
            l10.add(prg10);
            IRepository repo10 = new Repository(l10, "log10.txt");
            Controller ctr10 = new Controller(repo10,"Example 10");

            programs.add(ctr10);
        }catch(TypeCheckException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Typecheck error");
            alert.setContentText("Example 10 did not pass the typecheck: "+e.getMessage());

            alert.showAndWait();
        }

        IStmt ex11 = new CompStmt(new VarDeclStmt("a",new IntType()),
                new CompStmt(new VarDeclStmt("b",new IntType()),
                        new CompStmt(new VarDeclStmt("c",new IntType()),
                                new CompStmt(new AssignStmt("a",new ValueExp(new IntValue(1))),
                                        new CompStmt(new AssignStmt("b",new ValueExp(new IntValue(2))),
                                                new CompStmt(new AssignStmt("c",new ValueExp(new IntValue(5))),
                                                        new CompStmt(new SwitchStmt(new ArithmExp('*',new VarExp("a"),new ValueExp(new IntValue(10))),
                                                                new ArithmExp('*',new VarExp("b"),new VarExp("c")),
                                                                new ValueExp(new IntValue(10)),
                                                                new CompStmt(new PrintStmt(new VarExp("a")),new PrintStmt(new VarExp("b"))),
                                                                new CompStmt(new PrintStmt(new ValueExp(new IntValue(100))),new PrintStmt(new ValueExp(new IntValue(200)))),
                                                                new PrintStmt(new ValueExp(new IntValue(300)))),
                                                                new PrintStmt(new ValueExp(new IntValue(300))))))))));

        try{
            IStack<IStmt> exeStack11=new myStack<IStmt>();
            IDict<String, IValue> symTable11=new myDict<String, IValue>();
            IDict<StringValue, BufferedReader>fileTable11=new myDict<StringValue, BufferedReader>();
            IHeap<IValue> heap11=new myHeap<IValue>();
            IList<IValue> output11=new myList<IValue>();

            IDict<String, IType> typeenv=new myDict<String, IType>();
            ex11.typecheck(typeenv);

            ProgramState prg11 = new ProgramState(exeStack11, symTable11, fileTable11, heap11, output11, ex11, 1);
            List<ProgramState> l11 = new ArrayList<ProgramState>();
            l11.add(prg11);
            IRepository repo11 = new Repository(l11, "log11.txt");
            Controller ctr11 = new Controller(repo11,"Example 11");

            programs.add(ctr11);
        }catch(TypeCheckException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Typecheck error");
            alert.setContentText("Example 11 did not pass the typecheck: "+e.getMessage());

            alert.showAndWait();
        }


        IStmt ex12 = new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(0))),
                        new CompStmt(new RepeatStmt(new CompStmt(new ForkStmt(new CompStmt(new PrintStmt(new VarExp("v")),
                                new AssignStmt("v",new ArithmExp('-',new VarExp("v"),new ValueExp(new IntValue(1)))))),
                                new AssignStmt("v",new ArithmExp('+',new VarExp("v"),new ValueExp(new IntValue(1))))),
                                new RelationalExp(new VarExp("v"),new ValueExp(new IntValue(3)),"==")),
                                new PrintStmt(new ArithmExp('*',new VarExp("v"),new ValueExp(new IntValue(10)))))));

        try{
            IStack<IStmt> exeStack12=new myStack<IStmt>();
            IDict<String, IValue> symTable12=new myDict<String, IValue>();
            IDict<StringValue, BufferedReader>fileTable12=new myDict<StringValue, BufferedReader>();
            IHeap<IValue> heap12=new myHeap<IValue>();
            IList<IValue> output12=new myList<IValue>();

            IDict<String, IType> typeenv=new myDict<String, IType>();
            ex12.typecheck(typeenv);

            ProgramState prg12 = new ProgramState(exeStack12, symTable12, fileTable12, heap12, output12, ex12, 1);
            List<ProgramState> l12 = new ArrayList<ProgramState>();
            l12.add(prg12);
            IRepository repo12 = new Repository(l12, "log12.txt");
            Controller ctr12 = new Controller(repo12,"Example 12");

            programs.add(ctr12);
        }catch(TypeCheckException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Typecheck error");
            alert.setContentText("Example 12 did not pass the typecheck: "+e.getMessage());

            alert.showAndWait();
        }

        IStmt ex13 = new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(10))),
                        new CompStmt(new ForkStmt(new CompStmt(new AssignStmt("v",new ArithmExp('-',new VarExp("v"),new ValueExp(new IntValue(1)))),
                                new CompStmt(new AssignStmt("v",new ArithmExp('-',new VarExp("v"),new ValueExp(new IntValue(1)))),
                                        new PrintStmt(new VarExp("v"))))),
                                new CompStmt(new SleepStmt(10),new PrintStmt(new ArithmExp('*',new VarExp("v"),new ValueExp(new IntValue(10))))))));

        try{
            IStack<IStmt> exeStack13=new myStack<IStmt>();
            IDict<String, IValue> symTable13=new myDict<String, IValue>();
            IDict<StringValue, BufferedReader>fileTable13=new myDict<StringValue, BufferedReader>();
            IHeap<IValue> heap13=new myHeap<IValue>();
            IList<IValue> output13=new myList<IValue>();

            IDict<String, IType> typeenv=new myDict<String, IType>();
            ex13.typecheck(typeenv);

            ProgramState prg13 = new ProgramState(exeStack13, symTable13, fileTable13, heap13, output13, ex13, 1);
            List<ProgramState> l13 = new ArrayList<ProgramState>();
            l13.add(prg13);
            IRepository repo13 = new Repository(l13, "log13.txt");
            Controller ctr13 = new Controller(repo13,"Example 13");

            programs.add(ctr13);
        }catch(TypeCheckException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Typecheck error");
            alert.setContentText("Example 13 did not pass the typecheck: "+e.getMessage());

            alert.showAndWait();
        }


        IStmt ex14 = new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(20))),
                        new CompStmt(new WaitStmt(10),new PrintStmt(new ArithmExp('*',new VarExp("v"),new ValueExp(new IntValue(10)))))));

        try{
            IStack<IStmt> exeStack14=new myStack<IStmt>();
            IDict<String, IValue> symTable14=new myDict<String, IValue>();
            IDict<StringValue, BufferedReader>fileTable14=new myDict<StringValue, BufferedReader>();
            IHeap<IValue> heap14=new myHeap<IValue>();
            IList<IValue> output14=new myList<IValue>();

            IDict<String, IType> typeenv=new myDict<String, IType>();
            ex14.typecheck(typeenv);

            ProgramState prg14 = new ProgramState(exeStack14, symTable14, fileTable14, heap14, output14, ex14, 1);
            List<ProgramState> l14 = new ArrayList<ProgramState>();
            l14.add(prg14);
            IRepository repo14 = new Repository(l14, "log14.txt");
            Controller ctr14 = new Controller(repo14,"Example 14");

            programs.add(ctr14);
        }catch(TypeCheckException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Typecheck error");
            alert.setContentText("Example 14 did not pass the typecheck: "+e.getMessage());

            alert.showAndWait();
        }

        IStmt ex15 = new CompStmt(new VarDeclStmt("v1",new IntType()),
                new CompStmt(new VarDeclStmt("v2",new IntType()),
                        new CompStmt(new AssignStmt("v1",new ValueExp(new IntValue(2))),
                                new CompStmt(new AssignStmt("v2",new ValueExp(new IntValue(3))),
                                        new IfStmt(new RelationalExp(new VarExp("v1"),new ValueExp(new IntValue(0)),">"),
                                                new PrintStmt(new MULExp(new VarExp("v1"),new VarExp("v2"))),new PrintStmt(new VarExp("v1")))))));

        try{
            IStack<IStmt> exeStack15=new myStack<IStmt>();
            IDict<String, IValue> symTable15=new myDict<String, IValue>();
            IDict<StringValue, BufferedReader>fileTable15=new myDict<StringValue, BufferedReader>();
            IHeap<IValue> heap15=new myHeap<IValue>();
            IList<IValue> output15=new myList<IValue>();

            IDict<String, IType> typeenv=new myDict<String, IType>();
            ex15.typecheck(typeenv);

            ProgramState prg15 = new ProgramState(exeStack15, symTable15, fileTable15, heap15, output15, ex15, 1);
            List<ProgramState> l15 = new ArrayList<ProgramState>();
            l15.add(prg15);
            IRepository repo15 = new Repository(l15, "log15.txt");
            Controller ctr15 = new Controller(repo15,"Example 15");

            programs.add(ctr15);
        }catch(TypeCheckException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Typecheck error");
            alert.setContentText("Example 15 did not pass the typecheck: "+e.getMessage());

            alert.showAndWait();
        }


        IStmt ex16 = new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(0))),
                        new CompStmt(new DoWhileStmt(new CompStmt(new ForkStmt(new CompStmt(new PrintStmt(new VarExp("v")),
                                new AssignStmt("v",new ArithmExp('-',new VarExp("v"),new ValueExp(new IntValue(1)))))),
                                new AssignStmt("v",new ArithmExp('+',new VarExp("v"),new ValueExp(new IntValue(1))))),
                                new RelationalExp(new VarExp("v"),new ValueExp(new IntValue(3)),"<")),
                                new PrintStmt(new ArithmExp('*',new VarExp("v"),new ValueExp(new IntValue(10)))))));

        try{
            IStack<IStmt> exeStack16=new myStack<IStmt>();
            IDict<String, IValue> symTable16=new myDict<String, IValue>();
            IDict<StringValue, BufferedReader>fileTable16=new myDict<StringValue, BufferedReader>();
            IHeap<IValue> heap16=new myHeap<IValue>();
            IList<IValue> output16=new myList<IValue>();

            IDict<String, IType> typeenv=new myDict<String, IType>();
            ex16.typecheck(typeenv);

            ProgramState prg16 = new ProgramState(exeStack16, symTable16, fileTable16, heap16, output16, ex16, 1);
            List<ProgramState> l16 = new ArrayList<ProgramState>();
            l16.add(prg16);
            IRepository repo16 = new Repository(l16, "log16.txt");
            Controller ctr16 = new Controller(repo16,"Example 16");

            programs.add(ctr16);
        }catch(TypeCheckException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Typecheck error");
            alert.setContentText("Example 16 did not pass the typecheck: "+e.getMessage());

            alert.showAndWait();
        }

        programListView.setItems(FXCollections.observableArrayList(
                programs.stream().map(Controller::getProgramName).collect(Collectors.toList())
        ));

    }

    @FXML
    private void onRunButtonPressed() {
        if (programListView.getSelectionModel().getSelectedItem() != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProgramRunLayout.fxml"));
                Parent interpreterView = loader.load();
                ProgramRunController controller = loader.getController();
                controller.setController(programs.get(programListView.getSelectionModel().getSelectedIndex()));
                Stage stage = new Stage();
                stage.setTitle("Main Window");
                stage.setScene(new Scene(interpreterView));
                stage.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
