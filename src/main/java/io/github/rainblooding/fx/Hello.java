package io.github.rainblooding.fx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Hello extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // 按钮
        Button b1 = new Button("Say, Hello World");
        // 按钮事件
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("hello world");
            }
        });

        // 布局
        StackPane root = new StackPane();
        root.getChildren().add(b1);

        // 场景
        Scene scene = new Scene(root, 600, 400);

        // 舞台
        stage.setScene(scene);
        stage.setTitle("First JavaFX Application");
        stage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
