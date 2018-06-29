package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import java.awt.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class TableControler  implements Initializable {

    //tutaj te fxml
    @FXML
    private TableView<ModelTable> table;
    @FXML
    private TableColumn<ModelTable,String> col_id;
    @FXML
    private javafx.scene.control.TextField tf_delete;
    @FXML
    private TableColumn<ModelTable,String> col_name;
    @FXML
    private javafx.scene.control.TextField tf_alarm;


    @FXML
    public Label label_wakeUp;


//    @FXML
//    public TextField textField_alarm;

    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Connection con = SqliteConnection.Connector();

        try {
            ResultSet rs = con.createStatement().executeQuery("select * from data");

            while(rs.next()){
                oblist.add(new ModelTable(rs.getString("id"),rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));

        table.setItems(oblist);


        pressAlarm();


    }

    public void loadTable(){
        Connection con = SqliteConnection.Connector();

        try {
            ResultSet rs = con.createStatement().executeQuery("select * from data");
            int x;
            while(rs.next()){
                oblist.add(new ModelTable(rs.getString("id"),rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));

        table.setItems(oblist);


    }

    public static void pressAlarm(){

        Connection con = SqliteConnection.Connector();



        try {
            ResultSet rs = con.createStatement().executeQuery("select * from data");

            while(rs.next()){
                System.out.println(rs.getString("name"));
                Global.timeTable.add(rs.getString("name"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAlarm() throws SQLException {
        Connection con = SqliteConnection.Connector();


        PreparedStatement preparedStatement = con.prepareStatement("insert into data ('name') values (?)");
        preparedStatement.setString(1,tf_alarm.getText());
        preparedStatement.execute();

        oblist.clear();
        loadTable();
        showAlert();
        pressAlarm();


    }

    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Dodałeś alarm!");
        alert.showAndWait();
    }

    public void deleteAlarm() throws SQLException {
        Connection con = SqliteConnection.Connector();
        int id;
        ObservableList<ModelTable> allAlarms;
        allAlarms =table.getItems();
        id= table.getSelectionModel().getSelectedIndex();

        PreparedStatement preparedStatement = con.prepareStatement("delete from data where id = (?) ");
        preparedStatement.setInt(1, table.getSelectionModel().getSelectedIndex());
        preparedStatement.execute();


        oblist.clear();
        loadTable();
        System.out.println(id);
    }




}
