package com.example.school_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class view implements Initializable {

    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ca209db","root","");
    Statement st = con.createStatement();
    @FXML
    private TableColumn<student, Integer> columnId;

    @FXML
    private TableColumn<student, String> columnName;

    @FXML
    private TableView<student> tblView;

    ObservableList list= FXCollections.observableArrayList();


    @FXML
    void bHndlr(MouseEvent event) throws IOException {
        Pane root= FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(root);
        Stage loginStage= new Stage();
        loginStage.setScene(scene);
        loginStage.setTitle("Main Window");
        loginStage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    void  BindTableView() throws SQLException {
        tblView.getItems().clear();
        tblView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        columnId.setCellValueFactory(new PropertyValueFactory<student,Integer>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<student, String>("name"));

        ResultSet rs = st.executeQuery("select * from students ");

        while (rs.next()){
            list.addAll(new student(rs.getInt("id"),rs.getString("name")));
        }
        tblView.setItems(list);}

    public view() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            BindTableView();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
