package com.example.ahmed;

import com.example.ahmed.DB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Member_CL implements Initializable {
    @FXML
    private TextField txtn;
    @FXML
    private TextField txtid;
    @FXML
    private TextField txtad;

    @FXML
    private TableView table;
    @FXML
    private TableColumn name;
    @FXML
    private TableColumn id;
    @FXML
    private TableColumn ad;
    @FXML

    int id1;



    Statement state;

    public void insert(Member m) throws SQLException {

        state = DB.open_connection().createStatement();
        state.executeUpdate("INSERT INTO `members`(`Name`, `ID`, `Address`) VALUES ('" + m.getName() + "','" + m.getID() + "','" + m.getAddress() + "')");
        DB.close_connection();
    }

    public  void delete(int id) throws SQLException {
        state=DB.open_connection().createStatement();
        state.executeUpdate("DELETE FROM `members` WHERE ID ='"+id+"'");

        DB.close_connection();
        table.setItems(this.getallbks());
    }

    public  void update(Member m ) throws SQLException {
        state=DB.open_connection().createStatement();
        state.executeUpdate("UPDATE `members` SET Name='"+m.getName()+"',ID='"+m.getID()+"',Address='"+m.getAddress()+"' WHERE ID ='"+id1+"'");
        DB.close_connection();

    }

    public ObservableList<Member> getallbks() throws SQLException {
        ObservableList std = FXCollections.observableArrayList();
        state=DB.open_connection().createStatement();
        ResultSet result = state.executeQuery("SELECT * FROM `members`");
        while (result.next())
        {
           Member obj = new Member();
            obj.setName(result.getString(1));
            obj.setID(result.getInt(2));
            obj.setAddress(result.getString(3));


            std.add(obj);
        }
        DB.close_connection();

        return std;
    }


    public void back1 (Event e) throws IOException {

        Node node = (Node)  e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        Parent root= FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene scene=new Scene(root);
        stage.setTitle("Library");
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            name.setCellValueFactory(new PropertyValueFactory<>("Name"));
            id.setCellValueFactory(new PropertyValueFactory<>("ID"));
            ad.setCellValueFactory(new PropertyValueFactory<>("Address"));


            table.setItems(this.getallbks());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public  void  Add1 (Event e) throws SQLException {
        Member st=new Member();
        st.setName(txtn.getText());
        st.setID(Integer.parseInt(txtid.getText()));
        st.setAddress(txtad.getText());


        this.insert(st);
        table.setItems(this.getallbks());

        txtn.setText("");
        txtid.setText("");
        txtad.setText("");

    }

    public void Update1 (Event e) throws SQLException {

        Member st=new Member();
        st.setName(txtn.getText());
        st.setID(Integer.parseInt(txtid.getText()));
        st.setAddress(txtad.getText());


        this.update(st);

        table.setItems(this.getallbks());

        txtn.setText("");
        txtid.setText("");
        txtad.setText("");
    }

    public void Delete1 (Event e) throws SQLException {

        this.delete(id1);
        txtn.setText("");
        txtid.setText("");
        txtad.setText("");

    }

    public void Click_Table1(Event e){
        Member st = (Member) table.getSelectionModel().getSelectedItem();

        txtn.setText(st.getName());
        txtid.setText(st.getID()+"");
        txtad.setText(st.getAddress());


        id1=st.getID();


    }



}
