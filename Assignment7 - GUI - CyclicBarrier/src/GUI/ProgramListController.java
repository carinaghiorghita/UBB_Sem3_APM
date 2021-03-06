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
import java.util.Arrays;
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
import javafx.util.Pair;

public class ProgramListController implements Initializable {

    public ProgramListController(){}

    private List<Controller> programs= new ArrayList<>();

    @FXML
    private ListView<String> programListView=new ListView<>();

    public static IStmt makeIt(IStmt... stmtList){
        if(stmtList.length==0)return new NopStmt();
        return new CompStmt(stmtList[0],makeIt(Arrays.copyOfRange(stmtList,1,stmtList.length)));
    }


    public void setListView(){

        IStmt ex=makeIt(new VarDeclStmt("v1",new RefType(new IntType())),new VarDeclStmt("v2",new RefType(new IntType())),new VarDeclStmt("v3",new RefType(new IntType())),new VarDeclStmt("cnt",new IntType()),
                new NewHeapStmt("v1",new ValueExp(new IntValue(2))),new NewHeapStmt("v2",new ValueExp(new IntValue(3))),new NewHeapStmt("v3",new ValueExp(new IntValue(4))),
                new NewBarrierStmt("cnt",new ReadHeapExp(new VarExp("v2"))),
                new ForkStmt(makeIt(new AwaitStmt("cnt"),
                        new WriteHeapStmt("v1",new ArithmExp('*',new ReadHeapExp(new VarExp("v1")),new ValueExp(new IntValue(10)))),
                        new PrintStmt(new ReadHeapExp(new VarExp("v1"))))),
                new ForkStmt(makeIt(new AwaitStmt("cnt"),
                        new WriteHeapStmt("v2",new ArithmExp('*',new ReadHeapExp(new VarExp("v2")),new ValueExp(new IntValue(10)))),
                        new WriteHeapStmt("v2",new ArithmExp('*',new ReadHeapExp(new VarExp("v2")),new ValueExp(new IntValue(10)))),
                        new PrintStmt(new ReadHeapExp(new VarExp("v2"))))),
                new AwaitStmt("cnt"),new PrintStmt(new ReadHeapExp(new VarExp("v3"))));

        try{
            IStack<IStmt> exeStack=new myStack<IStmt>();
            IDict<String, IValue> symTable=new myDict<String, IValue>();
            IDict<StringValue, BufferedReader>fileTable=new myDict<StringValue, BufferedReader>();
            IHeap<IValue> heap=new myHeap<IValue>();
            IList<IValue> output=new myList<IValue>();
            IBarrierTable barrierTable=new myBarrierTable();

            IDict<String, IType> typeenv=new myDict<String, IType>();
            ex.typecheck(typeenv);

            ProgramState prg = new ProgramState(exeStack, symTable, fileTable, heap, output, ex, 1,barrierTable);
            List<ProgramState> l = new ArrayList<ProgramState>();
            l.add(prg);
            IRepository repo = new Repository(l, "log.txt");
            Controller ctr = new Controller(repo,"Example CyclicBarrier");

            programs.add(ctr);
        }catch(TypeCheckException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Typecheck error");
            alert.setContentText("Example did not pass the typecheck: "+e.getMessage());

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
