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

        //    private IDict<String, Pair<List<String>,IStmt>> procTable;

        IStmt procSum=makeIt(new VarDeclStmt("v",new IntType()),
                new AssignStmt("v",new ArithmExp('+',new VarExp("a"),new VarExp("b"))),
                new PrintStmt(new VarExp("v")));

        IStmt procProd=makeIt(new VarDeclStmt("v",new IntType()),
                new AssignStmt("v",new ArithmExp('*',new VarExp("a"),new VarExp("b"))),
                new PrintStmt(new VarExp("v")));


        IStmt ex = makeIt(new VarDeclStmt("v",new IntType()),new VarDeclStmt("w",new IntType()),
                new AssignStmt("v",new ValueExp(new IntValue(2))),new AssignStmt("w",new ValueExp(new IntValue(5))),
                new CallProcStmt("sum",new ArrayList<>(Arrays.asList(new ArithmExp('*',new VarExp("v"),new ValueExp(new IntValue(10))),new VarExp("w")))),
                new PrintStmt(new VarExp("v")),
                new ForkStmt(makeIt(new CallProcStmt("product",new ArrayList<>(Arrays.asList(new VarExp("v"),new VarExp("w")))),
                        new ForkStmt(new CallProcStmt("sum",new ArrayList<>(Arrays.asList(new VarExp("v"),new VarExp("w"))))))));

        try{
            IStack<IStmt> exeStack=new myStack<IStmt>();
            IDict<String, IValue> sT=new myDict<>();
            IStack<IDict<String, IValue>> symTable=new myStack<>();
            symTable.push(sT);
            IDict<StringValue, BufferedReader>fileTable=new myDict<StringValue, BufferedReader>();
            IHeap<IValue> heap=new myHeap<IValue>();
            IList<IValue> output=new myList<IValue>();

            IProcedureTable procTable=new myProcedureTable();
            List<String> var=new ArrayList<>();
            var.add("a");var.add("b");
            procTable.put("sum",new Pair<>(var,procSum));
            List<String> var2=new ArrayList<>();
            var2.add("a");var2.add("b");
            procTable.put("product",new Pair<>(var2,procProd));

            IDict<String, IType> typeenv=new myDict<String, IType>();
            ex.typecheck(typeenv);

            ProgramState prg = new ProgramState(exeStack, symTable, fileTable, heap, output, ex, 1,procTable);
            List<ProgramState> l = new ArrayList<ProgramState>();
            l.add(prg);
            IRepository repo = new Repository(l, "log.txt");
            Controller ctr = new Controller(repo,"Example Procedures");

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
