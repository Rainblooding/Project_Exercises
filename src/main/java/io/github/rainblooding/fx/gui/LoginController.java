package io.github.rainblooding.fx.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {


    @FXML
    private Label labelPassword;

    @FXML
    private Button buttonLogin;

    @FXML
    private Label labelUsername;

    @FXML
    private TextField inputUsername;

    @FXML
    private Button buttonRegister;

    @FXML
    private TextField inputPassword;

    @FXML
    void loginBtnReleased() {
        String username = inputUsername.getText();
        String password = inputPassword.getText();

        if (username.equals("admin") && password.equals("admin")) {//判断用户名密码是否为某个值
            //弹出一个对话框
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            alert.setHeaderText("登录成功");
            alert.setContentText("登录成功");
            alert.showAndWait();
        } else {
            //弹出一个对话框
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("提示");
            alert.setHeaderText("登录失败");
            alert.setContentText("登录失败");
            alert.showAndWait();
        }
    }

}
