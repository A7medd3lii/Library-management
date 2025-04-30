package com.example.ahmed;

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

public class Magazines_CL implements Initializable {
    @FXML
    private TextField txttitle;
    @FXML
    private TextField txtauthor;
    @FXML
    private TextField txtyear;
    @FXML
    private TextField txtiss;
    @FXML
    private TextField txtno;


    @FXML
    private TableView table;
    @FXML
    private TableColumn title;
    @FXML
    private TableColumn author;
    @FXML
    private TableColumn year;
    @FXML
    private TableColumn issu;
    @FXML
    private TableColumn no;
    int iss1;



    Statement state;

    public void insert(Magazines ma) throws SQLException {

        state = DB.open_connection().createStatement();
        state.executeUpdate("INSERT INTO `magazines`(`Title`, `Author`, `Year`, `issno`, `Numbers`) VALUES ('" + ma.getTitle() + "','" + ma.getAuthor() + "','" + ma.getYear() + "','" + ma.getISS() + "','" + ma.getNumbers() + "')");
        DB.close_connection();
    }

    public  void delete(int issu) throws SQLException {
        state=DB.open_connection().createStatement();
        state.executeUpdate("DELETE FROM `magazines` WHERE issno ='"+issu+"'");

        DB.close_connection();
        table.setItems(this.getallbks());
    }

    public  void update(Magazines ma) throws SQLException {
        state=DB.open_connection().createStatement();
        state.executeUpdate("UPDATE `magazines` SET Title='"+ma.getTitle()+"',Author='"+ma.getAuthor()+"',Year='"+ma.getYear()+"',issno='"+ma.getISS()+"',Numbers='"+ma.getNumbers()+"' WHERE issno ='"+iss1+"'");
        DB.close_connection();

    }

    public ObservableList<Books> getallbks() throws SQLException {
        ObservableList std = FXCollections.observableArrayList();
        state=DB.open_connection().createStatement();
        ResultSet result = state.executeQuery("SELECT * FROM `magazines`");
        while (result.next())
        {
            Magazines obj = new Magazines();
            obj.setTitle(result.getString(1));
            obj.setAuthor(result.getString(2));
            obj.setYear(result.getInt(3));
            obj.setISS(result.getInt(4));
            obj.setNumbers(result.getInt(5));
            std.add(obj);
        }
        DB.close_connection();

        return std;
    }


    public void back2 (Event e) throws IOException {

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
            title.setCellValueFactory(new PropertyValueFactory<>("Title"));
            author.setCellValueFactory(new PropertyValueFactory<>("Author"));
            year.setCellValueFactory(new PropertyValueFactory<>("Year"));
            issu.setCellValueFactory(new PropertyValueFactory<>("issno"));
            no.setCellValueFactory(new PropertyValueFactory<>("Numbers"));

            table.setItems(this.getallbks());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public  void  Add2 (Event e) throws SQLException {
        Magazines st=new Magazines();
        st.setTitle(txttitle.getText());
        st.setAuthor(txtauthor.getText());
        st.setYear(Integer.parseInt(txtyear.getText()));
        st.setISS(Integer.parseInt(txtiss.getText()));
        st.setNumbers(Integer.parseInt(txtno.getText()));

        this.insert(st);
        table.setItems(this.getallbks());

        txttitle.setText("");
        txtauthor.setText("");
        txtyear.setText("");
        txtiss.setText("");
        txtno.setText("");
    }

    public void Update2 (Event e) throws SQLException {

        Magazines st=new Magazines();
        st.setTitle(txttitle.getText());
        st.setAuthor(txtauthor.getText());
        st.setYear(Integer.parseInt(txtyear.getText()));
        st.setISS(Integer.parseInt(txtiss.getText()));
        st.setNumbers(Integer.parseInt(txtno.getText()));

        this.update(st);

        table.setItems(this.getallbks());

        txttitle.setText("");
        txtauthor.setText("");
        txtyear.setText("");
        txtiss.setText("");
        txtno.setText("");
    }

    public void Delete2 (Event e) throws SQLException {

        this.delete(iss1);
        txttitle.setText("");
        txtauthor.setText("");
        txtyear.setText("");
        txtiss.setText("");
        txtno.setText("");

    }

    public void Click_Table2 (Event e){
        Magazines ma = (Magazines) table.getSelectionModel().getSelectedItem();

        txttitle.setText(ma.getTitle());
        txtauthor.setText(ma.getAuthor());
        txtyear.setText(ma.getYear()+"");
        txtiss.setText(ma.getISS()+"");
        txtno.setText(ma.getNumbers()+"");


        iss1=ma.getISS();


    }



}
