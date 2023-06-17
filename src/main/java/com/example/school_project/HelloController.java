package com.example.school_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    void registerHandler(ActionEvent event) throws IOException {
        Pane root= FXMLLoader.load(getClass().getResource("registration.fxml"));
        Scene scene = new Scene(root);
        Stage loginStage= new Stage();
        loginStage.setScene(scene);
        loginStage.setTitle("Registration Window");
        loginStage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void logOutHandler(ActionEvent event) throws IOException {
        Pane root= FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        Stage loginStage= new Stage();
        loginStage.setScene(scene);
        loginStage.setTitle("Login Window");
        loginStage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void viewHandler(ActionEvent event) throws IOException {
        Pane root= FXMLLoader.load(getClass().getResource("view.fxml"));
        Scene scene = new Scene(root);
        Stage loginStage= new Stage();
        loginStage.setScene(scene);
        loginStage.setTitle("View All Students");
        loginStage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void searchHandler(ActionEvent event) throws IOException {
        Pane root= FXMLLoader.load(getClass().getResource("search.fxml"));
        Scene scene = new Scene(root);
        Stage loginStage= new Stage();
        loginStage.setScene(scene);
        loginStage.setTitle("Search Window");
        loginStage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

}
