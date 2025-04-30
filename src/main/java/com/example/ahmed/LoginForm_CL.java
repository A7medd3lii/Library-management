package com.example.ahmed;
import javafx.event.Event;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.io.IOException;
import java.sql.SQLException;

public class LoginForm_CL {

    @FXML
    TextField txtuser;
    @FXML
    PasswordField txtid;
    @FXML
    Label lblmsg;
    @FXML
    Button btn;

    Admins m = new Admins();
    login_control lg = new login_control();

    public void Click(Event event) throws SQLException, IOException {
        m.setM_Name(txtuser.getText());
        m.setM_ID(txtid.getText());


        if(lg.islogin(m))
        {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else {
            lblmsg.setText("User Name or ID invalid");
        }


    }


}
