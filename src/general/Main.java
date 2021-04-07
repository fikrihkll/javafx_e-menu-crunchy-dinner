package general;

import general.creation.StackController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {

    double x,y = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println(getClass().getClassLoader().getResource(""));
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ui/home/home.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("E-Menu Crunchy Dinner");

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        StackController.addToStack(scene);
        primaryStage.show();
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);
        });


    }


    public static void main(String[] args) {
        launch(args);
    }
}
