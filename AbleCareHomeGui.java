import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AbleCareHomeGui extends Application {

    JdbcConnect jdb;
    private Manager manager;
    private Nurse nurse;
    private Doctor doctor;

    public static void main(String[] args) {
        launch(args);
    }

    public void ManagerPage(){
        Stage stage = new Stage();
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(30);
        gridPane.setHgap(30);
        Button addNursebtn = new Button("Add Nurse");
        Button addDoctorBtn = new Button("Add Doctor");
        Button dayShiftBtn = new Button("Day shift");
        Button logout = new Button("Logout");
        gridPane.add(addNursebtn,0,0);
        gridPane.add(addDoctorBtn,0,1);
        gridPane.add(dayShiftBtn,0,2);
        addNursebtn.setOnMouseClicked(event -> {
            AddNursePage();
        });
        addDoctorBtn.setOnMouseClicked(event -> {
            AddDoctorPage();
        });
        dayShiftBtn.setOnMouseClicked(event -> {
            dayShiftPage();
        });
        Scene scene = new Scene(gridPane,500,800);
        stage.setScene(scene);
        stage.show();
    }

    public void AddNursePage(){
        Stage stage = new Stage();
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30,30,30,30));
        root.setVgap(30);
        root.setHgap(30);

        Label idLabel = new Label("ID");
        Label usernameLabel = new Label("Username");
        Label passwordLabel = new Label("Password");
        TextField idText = new TextField();
        TextField usernameText = new TextField();
        PasswordField passwordText = new PasswordField();
        Button addBtn = new Button("Add Nurse");

        addBtn.setOnMouseClicked(event -> {
            Nurse nurse = new Nurse(Integer.parseInt(idText.getText()),usernameText.getText(),passwordText.getText());
            if (StageManager.ableCareHome.nurses.contains(nurse)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Notice");
                alert.setHeaderText("Error");
                alert.setContentText("The Nurse Account has existed");
                alert.show();
            }else {
                this.manager.addNurse(StageManager.ableCareHome.nurses,nurse);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notice");
                alert.setHeaderText("Success");
                alert.setContentText("The Nurse Account add success");
                alert.show();
                stage.close();
            }
        });
        root.setHalignment(idLabel,HPos.CENTER);
        root.setValignment(idLabel,VPos.CENTER);
        root.setHalignment(usernameLabel,HPos.CENTER);
        root.setValignment(usernameLabel,VPos.CENTER);
        root.setHalignment(passwordLabel,HPos.CENTER);
        root.setValignment(passwordLabel,VPos.CENTER);
        root.setHalignment(addBtn,HPos.CENTER);
        root.setValignment(addBtn,VPos.CENTER);

        root.add(idLabel,0,0);
        root.add(usernameLabel,0,1);
        root.add(passwordLabel,0,2);
        root.add(idText,1,0);
        root.add(usernameText,1,1);
        root.add(passwordText,1,2);
        root.add(addBtn,1,3);
        Scene scene = new Scene(root,500,500);
        stage.setScene(scene);
        stage.show();
    }

    public void AddDoctorPage(){
        Stage stage = new Stage();
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30,30,30,30));
        root.setVgap(30);
        root.setHgap(30);

        Label idLabel = new Label("ID");
        Label usernameLabel = new Label("Username");
        Label passwordLabel = new Label("Password");
        TextField idText = new TextField();
        TextField usernameText = new TextField();
        PasswordField passwordText = new PasswordField();
        Button addBtn = new Button("Add Doctor");

        addBtn.setOnMouseClicked(event -> {
            Doctor doctor = new Doctor(Integer.parseInt(idText.getText()),usernameText.getText(),passwordText.getText());
            if (StageManager.ableCareHome.doctors.contains(doctor)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Notice");
                alert.setHeaderText("Error");
                alert.setContentText("The Doctor Account has existed");
                alert.show();
            }else {
                this.manager.addDoctor(StageManager.ableCareHome.doctors,doctor);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notice");
                alert.setHeaderText("Success");
                alert.setContentText("The Doctor Account add success");
                alert.show();
                stage.close();
            }
        });
        root.setHalignment(idLabel,HPos.CENTER);
        root.setValignment(idLabel,VPos.CENTER);
        root.setHalignment(usernameLabel,HPos.CENTER);
        root.setValignment(usernameLabel,VPos.CENTER);
        root.setHalignment(passwordLabel,HPos.CENTER);
        root.setValignment(passwordLabel,VPos.CENTER);
        root.setHalignment(addBtn,HPos.CENTER);
        root.setValignment(addBtn,VPos.CENTER);

        root.add(idLabel,0,0);
        root.add(usernameLabel,0,1);
        root.add(passwordLabel,0,2);
        root.add(idText,1,0);
        root.add(usernameText,1,1);
        root.add(passwordText,1,2);
        root.add(addBtn,1,3);
        Scene scene = new Scene(root,500,500);
        stage.setScene(scene);
        stage.show();
    }

    public void dayShiftPage(){
        Stage stage = new Stage();
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(30);
        gridPane.setHgap(30);
        DatePicker datePicker = new DatePicker();
        gridPane.add(datePicker,0,0);
        Scene scene = new Scene(gridPane,500,500);
        stage.setScene(scene);
        stage.show();
    }
    public void Login(Stage primaryStage) throws IOException{
        this.jdb = new JdbcConnect();
        this.jdb.connect();
//        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30,30,30,30));
        root.setVgap(30);
        root.setHgap(30);
        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.setUserData("UserType");
        RadioButton radioButton1 = new RadioButton("Manager");
        radioButton1.setSelected(true);
        radioButton1.setUserData("Manager");
        radioButton1.setToggleGroup(toggleGroup);
        RadioButton radioButton2 = new RadioButton("Doctor");
        radioButton2.setUserData("Doctor");
        radioButton2.setToggleGroup(toggleGroup);
        RadioButton radioButton3 = new RadioButton("Nurse");
        radioButton3.setUserData("Nurse");
        radioButton3.setToggleGroup(toggleGroup);
        root.setHalignment(radioButton1, HPos.CENTER);
        root.setValignment(radioButton1, VPos.CENTER);
        root.setHalignment(radioButton2, HPos.CENTER);
        root.setValignment(radioButton2, VPos.CENTER);
        root.setHalignment(radioButton3, HPos.CENTER);
        root.setValignment(radioButton3, VPos.CENTER);
        root.add(radioButton1,0,0);
        root.add(radioButton2,1,0);
        root.add(radioButton3,2,0);

        Label username = new Label("UserName");
        Label password = new Label("Password");
        TextField usernameText = new TextField();
        TextField passwordText = new PasswordField();
        Button LoginBtn = new Button("Login");
        root.setHalignment(LoginBtn, HPos.CENTER);
        root.setValignment(LoginBtn, VPos.CENTER);

        root.add(username,0,1);
        root.add(password,0,2);
        root.add(usernameText,1,1);
        root.add(passwordText,1,2);
        root.add(LoginBtn,1,3);
        LoginBtn.setOnMouseClicked(event -> {
            String userType = toggleGroup.selectedToggleProperty().getValue().getUserData().toString();
            System.out.println(userType);
//            boolean isLogin = this.jdb.boolSearch("select * from user where username='"+usernameText.getText()+"' and password='"+passwordText.getText()+"' and usertype='"+userType+"'");
            StageManager.ableCareHome.managers.add(new Manager("manager1","12345"));
            StageManager.ableCareHome.nurses.add(new Nurse(1001,"nurse1","12345"));
            Staff staff = StageManager.ableCareHome.Login(userType,usernameText.getText(),passwordText.getText());
            if (staff != null){
                primaryStage.close();
                if (userType.equals("Nurse") || userType.equals("Doctor")){
                    WardPage();
                }
                else
                    ManagerPage();
                switch (userType){
                    case "Nurse":
                        this.nurse = (Nurse)staff;
                        break;
                    case "Doctor":
                        this.doctor = (Doctor)staff;
                        break;
                    case "Manager":
                        this.manager = (Manager)staff;
                        break;
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Notice");
                alert.setHeaderText("Error");
                alert.setContentText("Login Failed");
                alert.show();
            }

        });
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Able Care Home Login");
        primaryStage.show();
    }
    public void WardPage(){
        Stage primaryStage = new Stage();
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setVgap(50);
        root.setHgap(30);
        for (int temp=0; temp<2; ++temp) {
            GridPane rooms = new GridPane();
            rooms.setStyle("-fx-border-color:#3498db;-fx-border-width:3px");
            rooms.setPadding(new Insets(30,30,30,30));
            rooms.setAlignment(Pos.CENTER);
            rooms.setVgap(50);
            rooms.setHgap(30);
            for (int i = 0,roomIndex=0; i < 2; ++i)
                for (int j = 0; j < 3; ++j) {
                    GridPane gridPane = new GridPane();
                    gridPane.setStyle("-fx-border-color:#3498db;-fx-border-width:3px");
                    gridPane.setPadding(new Insets(30, 30, 30, 30));
                    gridPane.setHgap(50);
                    gridPane.setVgap(30);
                    for (int x = 0,bedIndex=0; x < 2; ++x) {
                        for (int y = 0; y < 2; ++y) {
                            GridPane gridPane1 = new GridPane();
                            gridPane1.setStyle("-fx-background-color: white;-fx-border-color:#3498db;-fx-border-width:3px");
                            gridPane1.setMinWidth(50);
                            gridPane1.setMinHeight(50);
                            gridPane1.setHgap(20);
                            gridPane1.setVgap(15);
                            Bed finalBed = StageManager.ableCareHome.wards.get(temp).rooms.get(roomIndex).Beds.get(bedIndex);
                            gridPane1.setOnMouseClicked(event -> {
                                Stage stage = new Stage();
                                GridPane gridPane2 = new GridPane();
                                gridPane2.add(new Label(finalBed.ID), 1, 1);
                                gridPane2.add(new Label("test"), 1, 2);
                                gridPane2.setAlignment(Pos.CENTER);
                                Scene scene = new Scene(gridPane2, 800, 500);
                                stage.setScene(scene);
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.show();
                            });
                            bedIndex++;
                            gridPane.add(gridPane1, y, x);
                        }
                    }
                    roomIndex++;
                    rooms.add(gridPane, i, j);
                }
            root.add(rooms,temp,1);
            Label label1 = new Label("Ward "+temp);
            label1.setFont(Font.font(30));
            root.add(label1,temp,0);
        }
        Scene scene = new Scene(root,1200,1080);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    @Override
    public void start(Stage primaryStage) {
        try {
//            BorderPane root = new BorderPane(label);
            Login(primaryStage);

//            GridPane root = new GridPane();
//            root.setAlignment(Pos.CENTER);
//            root.setVgap(50);
//            root.setHgap(30);
//            for (int temp=0; temp<2; ++temp) {
//                GridPane rooms = new GridPane();
//                rooms.setStyle("-fx-border-color:#3498db;-fx-border-width:3px");
//                rooms.setPadding(new Insets(30,30,30,30));
//                rooms.setAlignment(Pos.CENTER);
//                rooms.setVgap(50);
//                rooms.setHgap(30);
//                for (int i = 0; i < 2; ++i)
//                    for (int j = 0; j < 3; ++j) {
//                        GridPane gridPane = new GridPane();
//                        gridPane.setStyle("-fx-border-color:#3498db;-fx-border-width:3px");
//                        gridPane.setPadding(new Insets(30, 30, 30, 30));
//                        gridPane.setHgap(50);
//                        gridPane.setVgap(30);
//                        for (int x = 0; x < 2; ++x) {
//                            for (int y = 0; y < 2; ++y) {
//                                GridPane gridPane1 = new GridPane();
//                                gridPane1.setStyle("-fx-background-color: white;-fx-border-color:#3498db;-fx-border-width:3px");
//                                gridPane1.setMinWidth(50);
//                                gridPane1.setMinHeight(50);
//                                gridPane1.setHgap(20);
//                                gridPane1.setVgap(15);
//                                gridPane1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
//                                    @Override
//                                    public void handle(MouseEvent event) {
//                                        Stage stage = new Stage();
//                                        GridPane gridPane2 = new GridPane();
//                                        gridPane2.add(new Label("test"), 1, 1);
//                                        gridPane2.add(new Label("test"), 1, 2);
//                                        gridPane2.setAlignment(Pos.CENTER);
//                                        Scene scene = new Scene(gridPane2, 800, 500);
//                                        stage.setScene(scene);
//                                        stage.initModality(Modality.APPLICATION_MODAL);
//                                        stage.show();
//                                    }
//                                });
//                                gridPane.add(gridPane1, x, y);
//                            }
//                        }
//                        rooms.add(gridPane, i, j);
//                    }
//                root.add(rooms,temp,1);
//                Label label1 = new Label("Ward "+temp);
//                label1.setFont(Font.font(30));
//                root.add(label1,temp,0);
//            }

//            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());


//            Stage stage  = new Stage();
//            Label label2 = new Label("test");
//            BorderPane root2 = new BorderPane(label2);
//            Scene scene2 = new Scene(root2,800,400);
//            stage.setScene(scene2);
//            stage.initModality(Modality.APPLICATION_MODAL);
//            stage.setTitle("window2");
//            stage.initStyle(StageStyle.UNIFIED);
//            stage.initOwner(primaryStage);
//            Parent root3 = FXMLLoader.load(getClass().getResource("main.fxml"));
//            Scene scene3 = new Scene(root3, 500, 400);
//            Label label1 = (Label) scene3.lookup("Label").lookup("#Label1");
//            label1.setText("test11111");
//            TreeView treeView = (TreeView) scene3.lookup("TreeView");
//            TreeItem item1 = new TreeItem("test");
//            treeView.setRoot(item1);
//            item1.getChildren().add(new TreeItem("2222"));
//            Stage stage3 = new Stage();
//            stage3.setTitle("FXML Welcome");
//            stage3.setScene(scene3);

//            stage.show();
//            stage3.show();
        } catch(Exception e) {
            e.printStackTrace();

        }
    }
}
