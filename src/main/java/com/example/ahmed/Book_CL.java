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

public class Book_CL implements Initializable {
    @FXML
    private TextField txttitle;
    @FXML
    private TextField txtauthor;
    @FXML
    private TextField txtyear;
    @FXML
    private TextField txtisbn;
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
    private TableColumn isbn;
    @FXML
    private TableColumn no;
    int isbn1;



    Statement state;

    public void insert(Books b) throws SQLException {

        state = DB.open_connection().createStatement();
        state.executeUpdate("INSERT INTO `books`(`Title`, `Author`, `Year`, `ISBN`, `Numbers`) VALUES ('" + b.getTitle() + "','" + b.getAuthor() + "','" + b.getYear() + "','" + b.getISBN() + "','" + b.getNumbers() + "')");
        DB.close_connection();
    }

    public  void delete(int isbn) throws SQLException {
        state=DB.open_connection().createStatement();
        state.executeUpdate("DELETE FROM `books` WHERE ISBN ='"+isbn+"'");

        DB.close_connection();
        table.setItems(this.getallbks());
    }

    public  void update(Books b) throws SQLException {
        state=DB.open_connection().createStatement();
        state.executeUpdate("UPDATE `books` SET Title='"+b.getTitle()+"',Author='"+b.getAuthor()+"',Year='"+b.getYear()+"',ISBN='"+b.getISBN()+"',Numbers='"+b.getNumbers()+"' WHERE ISBN ='"+isbn1+"'");
        DB.close_connection();

    }

    public ObservableList<Books> getallbks() throws SQLException {
        ObservableList std = FXCollections.observableArrayList();
        state=DB.open_connection().createStatement();
        ResultSet result = state.executeQuery("SELECT * FROM `books`");
        while (result.next())
        {
            Books obj = new Books();
            obj.setTitle(result.getString(1));
            obj.setAuthor(result.getString(2));
            obj.setYear(result.getInt(3));
            obj.setISBN(result.getInt(4));
            obj.setNumbers(result.getInt(5));
            std.add(obj);
        }
        DB.close_connection();

        return std;
    }


    public void back (Event e) throws IOException {

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
            isbn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
            no.setCellValueFactory(new PropertyValueFactory<>("Numbers"));

            table.setItems(this.getallbks());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public  void  Add (Event e) throws SQLException {
        Books st=new Books();
        st.setTitle(txttitle.getText());
        st.setAuthor(txtauthor.getText());
        st.setYear(Integer.parseInt(txtyear.getText()));
        st.setISBN(Integer.parseInt(txtisbn.getText()));
        st.setNumbers(Integer.parseInt(txtno.getText()));

        this.insert(st);
        table.setItems(this.getallbks());

        txttitle.setText("");
        txtauthor.setText("");
        txtyear.setText("");
        txtisbn.setText("");
        txtno.setText("");
    }

    public void Update (Event e) throws SQLException {

        Books st=new Books();
        st.setTitle(txttitle.getText());
        st.setAuthor(txtauthor.getText());
        st.setYear(Integer.parseInt(txtyear.getText()));
        st.setISBN(Integer.parseInt(txtisbn.getText()));
        st.setNumbers(Integer.parseInt(txtno.getText()));

        this.update(st);

        table.setItems(this.getallbks());

        txttitle.setText("");
        txtauthor.setText("");
        txtyear.setText("");
        txtisbn.setText("");
        txtno.setText("");
    }

    public void Delete (Event e) throws SQLException {

        this.delete(isbn1);
        txttitle.setText("");
        txtauthor.setText("");
        txtyear.setText("");
        txtisbn.setText("");
        txtno.setText("");

    }

    public void Click_Table(Event e){
        Books st = (Books) table.getSelectionModel().getSelectedItem();

        txttitle.setText(st.getTitle());
        txtauthor.setText(st.getAuthor());
        txtyear.setText(st.getYear()+"");
        txtisbn.setText(st.getISBN()+"");
        txtno.setText(st.getNumbers()+"");


        isbn1=st.getISBN();


    }



}
