package io.github.rainblooding.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/io/github/rainblooding/gui/main.fxml"));

        Scene scene = new Scene(root, 900, 800);
        stage.setScene(scene);
        stage.setTitle("登录");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
