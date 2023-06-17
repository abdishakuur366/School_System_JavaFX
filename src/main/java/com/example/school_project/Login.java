package com.example.school_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Login {

    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/ca209db","root","");
    Statement st=con.createStatement();
    @FXML
    private Label lblOutput;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    public Login() throws SQLException {
    }

    @FXML
    void loginHandler(ActionEvent event) throws SQLException, IOException {
        if (txtUsername.getText().equals("") || txtPassword.getText().equals("")){
            lblOutput.setText("Plz Fill The Blanks!");
        }
        else {
            String user=txtUsername.getText();
            String password=txtPassword.getText();

            ResultSet rs= st.executeQuery("select * from users where username='"+user+"' and password='"+password+"'");

            if (rs.next()){
                Pane root= FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                Scene scene = new Scene(root);
                Stage loginStage= new Stage();
                loginStage.setScene(scene);
                loginStage.setTitle("Main Window");
                loginStage.show();
                ((Node)(event.getSource())).getScene().getWindow().hide();
            }
            else {
                lblOutput.setText("Invalid Username or Password");
            }
        }
    }

}
