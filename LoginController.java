import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class LoginController {
    private JdbcConnect jdb;
    @FXML
    private ToggleGroup UserType;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;

    @FXML
    public void initialize(){
        this.jdb = new JdbcConnect();
        this.jdb.connect();
    }

    public void LoginClick(Event event){
        try {
//            Parent root = FXMLLoader.load(getClass().getResource("ErrorDialog.fxml"));
            System.out.println(usernameTextField.getText());
            System.out.println(passwordTextField.getText());
            System.out.println(UserType.selectedToggleProperty().getValue().getUserData());
            boolean isLogin = this.jdb.boolSearch("select * from user where username='"+usernameTextField.getText()+"' and password='"+passwordTextField.getText()+"' and usertype='"+UserType.selectedToggleProperty().getValue().getUserData()+"'");
            if (isLogin) {
                WardPage wardPage = new WardPage();
                Stage stage = (Stage)this.passwordTextField.getScene().getWindow();
                stage.close();

//                Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
//                Scene scene = new Scene(root, 600, 600);
//                ArrayList<String> arrayList = new ArrayList<String>();
//                arrayList.add("Admin1111");
//                scene.setUserData(arrayList);
//                Stage stage = new Stage();
//                stage.initModality(Modality.APPLICATION_MODAL);
//                stage.setScene(scene);
//                stage.show();
            }else {
                //Login Failed
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
