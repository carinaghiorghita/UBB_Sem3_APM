package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader mainLoader = new FXMLLoader();
        mainLoader.setLocation(getClass().getResource("ProgramListLayout.fxml"));
        Parent mainWindow = mainLoader.load();

        ProgramListController controller = mainLoader.getController();
        controller.setListView();

        primaryStage.setTitle("Select Program");
        primaryStage.setScene(new Scene(mainWindow));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
