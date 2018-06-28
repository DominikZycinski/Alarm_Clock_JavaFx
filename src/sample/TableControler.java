package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TableControler implements Initializable {

    //tutaj te fxml
    @FXML
    private TableView<ModelTable> table;
    @FXML
    private TableColumn<ModelTable,String> col_id;
    @FXML
    private TableColumn<ModelTable,String> col_name;
    @FXML
    private TableColumn<ModelTable,String> col_email;
    @FXML
    private TableColumn<ModelTable,String> col_prog;

    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Connection con = SqliteConnection.Connector();

        try {
            ResultSet rs = con.createStatement().executeQuery("select * from data");

            while(rs.next()){
                oblist.add(new ModelTable(rs.getString("id"),rs.getString("name"),
                        rs.getString("email"),rs.getString("prog")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_prog.setCellValueFactory(new PropertyValueFactory<>("prog"));

        table.setItems(oblist);
    }

}
