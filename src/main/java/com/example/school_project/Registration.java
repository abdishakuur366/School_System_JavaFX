package com.example.school_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Registration implements Initializable {

    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ca209db","root","");
    Statement st = con.createStatement();


    @FXML
    private Button updateBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button clearBtn;


    @FXML
    private TableColumn<student, Integer> columnId;

    @FXML
    private TableColumn<student, String> columnName;

    @FXML
    private TableView<student> tblView;

    ObservableList<student> list = FXCollections.observableArrayList();

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSearchId;


    int IDNO=0;
    @FXML
    void getTableData(MouseEvent event) {
        student st= tblView.getSelectionModel().getSelectedItem();
        txtId.setText(String.valueOf( st.getId()));
        txtName.setText(st.getName());
        IDNO=st.getId();

        updateBtn.setDisable(false);
        deleteBtn.setDisable(false);
        saveBtn.setDisable(true);
    }

    public Registration() throws SQLException {
    }

    @FXML
    void UpdateHandler(ActionEvent event) throws SQLException {
        String NAME= txtName.getText();
        st.executeUpdate("update students set name= '"+NAME+"' where id='"+IDNO+"'");
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("This Row Has Been Updated!!");
        alert.show();
        txtName.clear();
        txtId.clear();
        BindTableView();
    }

    @FXML
    void backHandler(ActionEvent event) throws IOException {
        Pane root= FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(root);
        Stage loginStage= new Stage();
        loginStage.setScene(scene);
        loginStage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void clearHandler(ActionEvent event) {
        txtName.clear();
        txtId.clear();
        txtSearchId.clear();
    }

    @FXML
    void deleteHandler(ActionEvent event) throws SQLException {
        st.executeUpdate("delete from students where id= '"+IDNO+"'");
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("This Student Has Been Removed!!");
        alert.show();
        txtName.clear();
        txtId.clear();
        BindTableView();
    }

    @FXML
    void logoutHandler(ActionEvent event) throws IOException {
        Pane root= FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        Stage loginStage= new Stage();
        loginStage.setScene(scene);
        loginStage.setTitle("Login Window");
        loginStage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();

    }

    @FXML
    void refreshHandler(ActionEvent event) throws SQLException {
        BindTableView();
    }

    @FXML
    void saveHandler(ActionEvent event) throws SQLException {
        String name= txtName.getText();
        int id = Integer.parseInt(txtId.getText());

        st.executeUpdate("insert into students values('"+id+"', '"+name+"')");

        Alert al=new Alert(Alert.AlertType.INFORMATION);
        al.setContentText("Saved New Student");
        al.show();
        txtName.clear();
        txtId.clear();
        BindTableView();
    }

    @FXML
    void searchHandler(ActionEvent event) throws SQLException {
        tblView.getItems().clear();
        tblView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        columnId.setCellValueFactory(new PropertyValueFactory<student,Integer>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<student, String>("name"));
        int searchId= Integer.parseInt(txtSearchId.getText());
        ResultSet rs = st.executeQuery("select * from students where id='"+searchId+"'");

        while (rs.next()){
            list.addAll(new student(rs.getInt("id"),rs.getString("name")));
        }
        tblView.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            BindTableView();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        updateBtn.setDisable(true);
        deleteBtn.setDisable(true);


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
        tblView.setItems(list);

        saveBtn.setDisable(false);
        deleteBtn.setDisable(true);
        updateBtn.setDisable(true);
    }
}
