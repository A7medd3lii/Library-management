package com.example.ahmed;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.Event;

import java.io.IOException;

public class Home_CL {

    @FXML
    Button btnb, btnm, btni , btnu ;
    public void open_book (Event e) throws IOException {

        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("Books.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void open_magazines (Event e) throws IOException {

        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("Magazines.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void open_members (Event e) throws IOException {

        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("Members.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }



}
