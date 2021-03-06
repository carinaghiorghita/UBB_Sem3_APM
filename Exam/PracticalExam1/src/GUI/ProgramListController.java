package GUI;

import Controller.Controller;
import Exceptions.TypeCheckException;
import Model.ADTs.*;
import Model.Expressions.ArithmExp;
import Model.Expressions.RelationalExp;
import Model.Expressions.ValueExp;
import Model.Expressions.VarExp;
import Model.ProgramState;
import Model.Types.IType;
import Model.Types.IntType;
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

import Model.Statements.*;
import Model.Values.*;
import javafx.stage.Stage;

public class ProgramListController implements Initializable {

    public ProgramListController(){}

    private List<Controller> programs= new ArrayList<>();

    @FXML
    private ListView<String> programListView=new ListView<>();

    public static IStmt parseStmts(IStmt... stmtList){
        if(stmtList.length==0)return new NopStmt();
        return new CompStmt(stmtList[0], parseStmts(Arrays.copyOfRange(stmtList,1,stmtList.length)));
    }

    public void setListView(){

//        int v; int x; int y; v=0;
//        (repeat (fork(print(v);v=v-1);v=v+1) until v==3);
//        x=1;nop;y=3;nop;
//        print(v*10)

        IStmt ex=parseStmts(new VarDeclStmt("v",new IntType()),new VarDeclStmt("x",new IntType()),new VarDeclStmt("y",new IntType()),
                new AssignStmt("v",new ValueExp(new IntValue(0))),
                new RepeatStmt(parseStmts(new ForkStmt(parseStmts(new PrintStmt(new VarExp("v")),
                        new AssignStmt("v",new ArithmExp('-',new VarExp("v"),new ValueExp(new IntValue(1)))))),
                        new AssignStmt("v",new ArithmExp('+',new VarExp("v"),new ValueExp(new IntValue(1))))),
                        new RelationalExp(new VarExp("v"),new ValueExp(new IntValue(3)),"==")),
                new AssignStmt("x",new ValueExp(new IntValue(1))),new NopStmt(),
                new AssignStmt("y",new ValueExp(new IntValue(3))),new NopStmt(),
                new PrintStmt(new ArithmExp('*',new VarExp("v"),new ValueExp(new IntValue(10)))));

        try{
            IStack<IStmt> exeStack=new myStack<IStmt>();
            IDict<String, IValue> symTable=new myDict<String, IValue>();
            IDict<StringValue, BufferedReader>fileTable=new myDict<StringValue, BufferedReader>();
            IHeap<IValue> heap=new myHeap<IValue>();
            IList<IValue> output=new myList<IValue>();

            IDict<String, IType> typeenv=new myDict<String, IType>();
            ex.typecheck(typeenv);

            ProgramState prg = new ProgramState(exeStack, symTable, fileTable, heap, output, ex, 1);
            List<ProgramState> l = new ArrayList<ProgramState>();
            l.add(prg);
            IRepository repo = new Repository(l, "log.txt");
            Controller ctr = new Controller(repo,"Example Repeat");

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
