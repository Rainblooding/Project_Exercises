package io.github.rainblooding.fx.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TableView<?> tview;

    @FXML
    private TableColumn<?, ?> colbusymaketime;

    @FXML
    private Label timeclock;

    @FXML
    private TableColumn<?, ?> colTasktitle;

    @FXML
    private TableColumn<?, ?> colBusyTaskinfo;

    @FXML
    private TableColumn<?, ?> colTaskinfo;

    @FXML
    private CheckBox checkBoxTop;

    @FXML
    private TableColumn<?, ?> colmaketime;

    @FXML
    private TableView<?> busytview;

    @FXML
    private TableColumn<?, ?> colBusyTasktitle;

    @FXML
    void changeUpTop(ActionEvent event) {

    }

    @FXML
    void clockRun(ActionEvent event) {

    }

    @FXML
    void timeStop(ActionEvent event) {

    }

    @FXML
    void sleepRun(ActionEvent event) {

    }

    @FXML
    void addTask(ActionEvent event) {

    }

    @FXML
    void addbusyTask(ActionEvent event) {

    }

    @FXML
    void queryTask(ActionEvent event) {

    }

    @FXML
    void clearCache(ActionEvent event) {

    }

    @FXML
    void ClickTwo(ActionEvent event) {

    }

    private void showList() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
