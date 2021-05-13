import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Calendar;

public class AbleCareHomeGui extends Application {

    JdbcConnect jdb;
    private Manager manager;
    private Nurse nurse;
    private Doctor doctor;

    public static void main(String[] args) {
        launch(args);
    }

    public void ManagerPage() {
        Stage stage = new Stage();
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(30);
        gridPane.setHgap(30);
        Button addNursebtn = new Button("Add Nurse");
        Button addDoctorBtn = new Button("Add Doctor");
        Button dayShiftBtn = new Button("Day shift");
        Button changeNursePassBtn = new Button("Change Nurse Password");
        Button changeDoctorPassBtn = new Button("Change Doctor Password");
        Button addPatientBtn = new Button("Add Patient");
        Button logout = new Button("Logout");
        gridPane.add(addNursebtn, 0, 0);
        gridPane.add(addDoctorBtn, 0, 1);
        gridPane.add(dayShiftBtn, 0, 2);
        gridPane.add(addPatientBtn, 0, 3);
        gridPane.add(changeNursePassBtn, 0, 4);
        gridPane.add(changeDoctorPassBtn, 0, 5);
        gridPane.add(logout, 0, 6);
        addNursebtn.setOnMouseClicked(event -> {
            AddNursePage();
        });
        addDoctorBtn.setOnMouseClicked(event -> {
            AddDoctorPage();
        });
        dayShiftBtn.setOnMouseClicked(event -> {
            dayShiftPage();
        });
        changeNursePassBtn.setOnMouseClicked(event -> {
            changeNursePasswordPage();
        });
        changeDoctorPassBtn.setOnMouseClicked(event -> {
            changeDoctorPasswordPage();
        });
        addPatientBtn.setOnMouseClicked(event -> {
            addPatient();
        });
        logout.setOnMouseClicked(event -> {
            stage.close();
            Logout(this.manager);
            this.manager = null;
        });
        Scene scene = new Scene(gridPane, 500, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void AddNursePage() {
        Stage stage = new Stage();
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30, 30, 30, 30));
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
            Nurse nurse = new Nurse(Integer.parseInt(idText.getText()), usernameText.getText(), passwordText.getText());
            if (StageManager.ableCareHome.nurses.contains(nurse)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Notice");
                alert.setHeaderText("Error");
                alert.setContentText("The Nurse Account has existed");
                alert.show();
            } else {
                this.manager.addNurse(StageManager.ableCareHome.nurses, nurse);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notice");
                alert.setHeaderText("Success");
                alert.setContentText("The Nurse Account add success");
                alert.show();
                stage.close();
            }
        });
        root.setHalignment(idLabel, HPos.CENTER);
        root.setValignment(idLabel, VPos.CENTER);
        root.setHalignment(usernameLabel, HPos.CENTER);
        root.setValignment(usernameLabel, VPos.CENTER);
        root.setHalignment(passwordLabel, HPos.CENTER);
        root.setValignment(passwordLabel, VPos.CENTER);
        root.setHalignment(addBtn, HPos.CENTER);
        root.setValignment(addBtn, VPos.CENTER);

        root.add(idLabel, 0, 0);
        root.add(usernameLabel, 0, 1);
        root.add(passwordLabel, 0, 2);
        root.add(idText, 1, 0);
        root.add(usernameText, 1, 1);
        root.add(passwordText, 1, 2);
        root.add(addBtn, 1, 3);
        Scene scene = new Scene(root, 500, 500);
        stage.setScene(scene);
        stage.show();
    }

    public void AddDoctorPage() {
        Stage stage = new Stage();
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30, 30, 30, 30));
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
            Doctor doctor = new Doctor(Integer.parseInt(idText.getText()), usernameText.getText(), passwordText.getText());
            if (StageManager.ableCareHome.doctors.contains(doctor)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Notice");
                alert.setHeaderText("Error");
                alert.setContentText("The Doctor Account has existed");
                alert.show();
            } else {
                this.manager.addDoctor(StageManager.ableCareHome.doctors, doctor);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notice");
                alert.setHeaderText("Success");
                alert.setContentText("The Doctor Account add success");
                alert.show();
                stage.close();
            }
        });
        root.setHalignment(idLabel, HPos.CENTER);
        root.setValignment(idLabel, VPos.CENTER);
        root.setHalignment(usernameLabel, HPos.CENTER);
        root.setValignment(usernameLabel, VPos.CENTER);
        root.setHalignment(passwordLabel, HPos.CENTER);
        root.setValignment(passwordLabel, VPos.CENTER);
        root.setHalignment(addBtn, HPos.CENTER);
        root.setValignment(addBtn, VPos.CENTER);

        root.add(idLabel, 0, 0);
        root.add(usernameLabel, 0, 1);
        root.add(passwordLabel, 0, 2);
        root.add(idText, 1, 0);
        root.add(usernameText, 1, 1);
        root.add(passwordText, 1, 2);
        root.add(addBtn, 1, 3);
        Scene scene = new Scene(root, 500, 500);
        stage.setScene(scene);
        stage.show();
    }

    public void changeNursePasswordPage() {
        Stage stage = new Stage();
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(30);
        gridPane.setHgap(30);
        Label idLabel = new Label("ID");
        Label usernameLabel = new Label("UserName");
        Label passwordLabel = new Label("Password");

        TextField userNameText = new TextField();
        userNameText.setEditable(false);
        PasswordField passWordText = new PasswordField();
        Button changeButton = new Button("Change");

        ComboBox comboBox = new ComboBox();
        for (Nurse nurse : StageManager.ableCareHome.nurses)
            comboBox.getItems().add(nurse.ID);
        comboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            for (Nurse nurse : StageManager.ableCareHome.nurses)
                if (newValue.equals(nurse.ID)) {
                    userNameText.setText(nurse.username);
                    passWordText.setText(nurse.password);
                }
        });
        changeButton.setOnMouseClicked(event -> {
            for (Nurse nurse : StageManager.ableCareHome.nurses)
                if (comboBox.valueProperty().getValue().equals(nurse.ID)) {
                    if (nurse.password.equals(passWordText.getText())) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Notice");
                        alert.setHeaderText("Error");
                        alert.setContentText("The new password is as same as before");
                        alert.show();
                        break;
                    }
                    this.manager.changeNursePassword(StageManager.ableCareHome.nurses, nurse.username, nurse.password, passWordText.getText());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Notice");
                    alert.setHeaderText("Notice");
                    alert.setContentText("The password change success");
                    alert.show();
                }
        });

        gridPane.add(idLabel, 0, 0);
        gridPane.add(usernameLabel, 0, 1);
        gridPane.add(passwordLabel, 0, 2);
        gridPane.add(comboBox, 1, 0);
        gridPane.add(userNameText, 1, 1);
        gridPane.add(passWordText, 1, 2);
        gridPane.add(changeButton, 1, 3);
        stage.setScene(new Scene(gridPane, 500, 500));
        stage.show();
    }

    public void changeDoctorPasswordPage() {
        Stage stage = new Stage();
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(30);
        gridPane.setHgap(30);
        Label idLabel = new Label("ID");
        Label usernameLabel = new Label("UserName");
        Label passwordLabel = new Label("Password");

        TextField userNameText = new TextField();
        userNameText.setEditable(false);
        PasswordField passWordText = new PasswordField();
        Button changeButton = new Button("Change");

        ComboBox comboBox = new ComboBox();
        for (Doctor doctor : StageManager.ableCareHome.doctors)
            comboBox.getItems().add(doctor.ID);
        comboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            for (Doctor doctor : StageManager.ableCareHome.doctors)
                if (newValue.equals(doctor.ID)) {
                    userNameText.setText(doctor.username);
                    passWordText.setText(doctor.password);
                }
        });
        changeButton.setOnMouseClicked(event -> {
            for (Doctor doctor : StageManager.ableCareHome.doctors)
                if (comboBox.valueProperty().getValue().equals(doctor.ID)) {
                    if (doctor.password.equals(passWordText.getText())) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Notice");
                        alert.setHeaderText("Error");
                        alert.setContentText("The new password is as same as before");
                        alert.show();
                        break;
                    }
                    this.manager.changeDoctorPassword(StageManager.ableCareHome.doctors, doctor.username, doctor.password, passWordText.getText());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Notice");
                    alert.setHeaderText("Notice");
                    alert.setContentText("The password change success");
                    alert.show();
                }
        });

        gridPane.add(idLabel, 0, 0);
        gridPane.add(usernameLabel, 0, 1);
        gridPane.add(passwordLabel, 0, 2);
        gridPane.add(comboBox, 1, 0);
        gridPane.add(userNameText, 1, 1);
        gridPane.add(passWordText, 1, 2);
        gridPane.add(changeButton, 1, 3);
        stage.setScene(new Scene(gridPane, 500, 500));
        stage.show();
    }

    public void dayShiftPage() {
        Stage stage = new Stage();
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(30);
        gridPane.setHgap(30);
        DatePicker datePicker = new DatePicker();
        ComboBox comboBox = new ComboBox();
        for (Nurse nurse : StageManager.ableCareHome.nurses)
            comboBox.getItems().add(nurse.ID + " " + nurse.username);

        for (Doctor doctor : StageManager.ableCareHome.doctors)
            comboBox.getItems().add(doctor.ID + " " + doctor.username);

        ListView dayshiftView = new ListView();
        ObservableList<String> list = FXCollections.observableArrayList();
        comboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            list.clear();
            for (Nurse nurse : StageManager.ableCareHome.nurses)
                if ((nurse.ID + " " + nurse.username).equals(newValue)) {
                    for (ShiftDay shiftDay : nurse.shiftDay)
                        list.add(shiftDay.day.get(Calendar.YEAR) + "/" + shiftDay.day.get(Calendar.MONTH) + "/" + shiftDay.day.get(Calendar.DAY_OF_MONTH) + " " + shiftDay.startTime.get(Calendar.HOUR_OF_DAY) + ":" + shiftDay.startTime.get(Calendar.MINUTE) + "  --->  " + shiftDay.endTime.get(Calendar.HOUR_OF_DAY) + ":" + shiftDay.endTime.get(Calendar.MINUTE));
                }
            for (Doctor doctor : StageManager.ableCareHome.doctors)
                if ((doctor.ID + " " + doctor.username).equals(newValue)) {
                    for (ShiftDay shiftDay : doctor.shiftDay)
                        list.add(shiftDay.day.get(Calendar.YEAR) + "/" + shiftDay.day.get(Calendar.MONTH) + "/" + shiftDay.day.get(Calendar.DAY_OF_MONTH) + " " + shiftDay.startTime.get(Calendar.HOUR_OF_DAY) + ":" + shiftDay.startTime.get(Calendar.MINUTE) + "  --->  " + shiftDay.endTime.get(Calendar.HOUR_OF_DAY) + ":" + shiftDay.endTime.get(Calendar.MINUTE));
                }
        });
        dayshiftView.setItems(list);
        dayshiftView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("selected item:" + newValue);
        });

        TextField startTime = new TextField();
        startTime.setPromptText("xx:yy");
        TextField endTime = new TextField();
        endTime.setPromptText("xx:yy");
        gridPane.add(dayshiftView, 0, 0);
        Button addShiftBtn = new Button("addShift");
        Button removeShiftBtn = new Button("removeShift");
        Button updateShiftBtn = new Button("updateShift");


        addShiftBtn.setOnMouseClicked(event -> {
            MedicalWorkers worker = null;
            for (Nurse nurse : StageManager.ableCareHome.nurses)
                if ((nurse.ID + " " + nurse.username).equals(comboBox.valueProperty().getValue()))
                    worker = nurse;

            for (Doctor doctor : StageManager.ableCareHome.doctors)
                if ((doctor.ID + " " + doctor.username).equals(comboBox.valueProperty().getValue()))
                    worker = doctor;
            int Year = datePicker.valueProperty().getValue().getYear();
            int Month = datePicker.valueProperty().getValue().getMonthValue();
            int Day = datePicker.valueProperty().getValue().getDayOfMonth();
            int startHour = Integer.parseInt(startTime.getText().split(":")[0]);
            int startMin = Integer.parseInt(startTime.getText().split(":")[1]);
            int endHour = Integer.parseInt(endTime.getText().split(":")[0]);
            int endMin = Integer.parseInt(endTime.getText().split(":")[1]);
            System.out.println(startHour + " " + startMin + " " + endHour + " " + endMin);
            try {
                manager.allocateShift(worker, Year, Month, Day, startHour, startMin, endHour, endMin);
                list.add(Year + "/" + Month + "/" + Day + " " + startTime.getText() + "  --->  " + endTime.getText());
                System.out.println();
                System.out.println(datePicker.valueProperty().getValue());
                System.out.println(startTime.getText());
                System.out.println(endTime.getText());
            } catch (UnreasonableShiftException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Notice");
                alert.setHeaderText("Error");
                alert.setContentText("The day shift is Unreasonable");
                alert.show();
            } catch (AccessDeniedException e) {
                System.out.println(e.getMessage());
            }
        });

        removeShiftBtn.setOnMouseClicked(event -> {
            MedicalWorkers worker = null;
            boolean flag = true;
            for (Nurse nurse : StageManager.ableCareHome.nurses)
                if ((nurse.ID + " " + nurse.username).equals(comboBox.valueProperty().getValue()))
                    worker = nurse;

            for (Doctor doctor : StageManager.ableCareHome.doctors)
                if ((doctor.ID + " " + doctor.username).equals(comboBox.valueProperty().getValue()))
                    worker = doctor;
            String dateStr = dayshiftView.getSelectionModel().getSelectedItem().toString().split(" ")[0];
            String startTimeStr = dayshiftView.getSelectionModel().getSelectedItem().toString().split(" ")[1];
            String endTimeStr = dayshiftView.getSelectionModel().getSelectedItem().toString().split(" ")[5];
            System.out.println(dateStr);
            System.out.println(startTimeStr);
            System.out.println(endTimeStr);

            int Year = Integer.parseInt(dateStr.split("/")[0]);
            int Month = Integer.parseInt(dateStr.split("/")[1]);
            int Day = Integer.parseInt(dateStr.split("/")[2]);

            int startHour = Integer.parseInt(startTimeStr.split(":")[0]);
            int startMin = Integer.parseInt(startTimeStr.split(":")[1]);
            int endHour = Integer.parseInt(endTimeStr.split(":")[0]);
            int endMin = Integer.parseInt(endTimeStr.split(":")[1]);

            try {
                this.manager.deleteShift(worker, Year, Month, Day, startHour, startMin, endHour, endMin);
                list.remove(dayshiftView.getSelectionModel().getSelectedItem().toString());
            } catch (AccessDeniedException e) {
                System.out.println(e.getMessage());
            }
        });

        updateShiftBtn.setOnMouseClicked(event -> {
            MedicalWorkers worker = null;
            boolean flag = true;
            for (Nurse nurse : StageManager.ableCareHome.nurses)
                if ((nurse.ID + " " + nurse.username).equals(comboBox.valueProperty().getValue()))
                    worker = nurse;

            for (Doctor doctor : StageManager.ableCareHome.doctors)
                if ((doctor.ID + " " + doctor.username).equals(comboBox.valueProperty().getValue()))
                    worker = doctor;

            String dateStr = dayshiftView.getSelectionModel().getSelectedItem().toString().split(" ")[0];
            String startTimeStr = dayshiftView.getSelectionModel().getSelectedItem().toString().split(" ")[1];
            String endTimeStr = dayshiftView.getSelectionModel().getSelectedItem().toString().split(" ")[5];
            System.out.println(dateStr);
            System.out.println(startTimeStr);
            System.out.println(endTimeStr);

            int oldYear = Integer.parseInt(dateStr.split("/")[0]);
            int oldMonth = Integer.parseInt(dateStr.split("/")[1]);
            int oldDay = Integer.parseInt(dateStr.split("/")[2]);

            int oldStartHour = Integer.parseInt(startTimeStr.split(":")[0]);
            int oldStartMin = Integer.parseInt(startTimeStr.split(":")[1]);
            int oldEndHour = Integer.parseInt(endTimeStr.split(":")[0]);
            int oldEndMin = Integer.parseInt(endTimeStr.split(":")[1]);

            int newYear = datePicker.valueProperty().getValue().getYear();
            int newMonth = datePicker.valueProperty().getValue().getMonthValue();
            int newDay = datePicker.valueProperty().getValue().getDayOfMonth();

            int newStartHour = Integer.parseInt(startTime.getText().split(":")[0]);
            int newStartMin = Integer.parseInt(startTime.getText().split(":")[1]);
            int newEndHour = Integer.parseInt(endTime.getText().split(":")[0]);
            int newEndMin = Integer.parseInt(endTime.getText().split(":")[1]);
            try {
                this.manager.updateShift(worker, oldYear, oldMonth, oldDay, oldStartHour, oldStartMin, oldEndHour, oldEndMin, newYear, newMonth, newDay, newStartHour, newStartMin, newEndHour, newEndMin);
                list.set(dayshiftView.getSelectionModel().getSelectedIndex(), newYear + "/" + newMonth + "/" + newDay + " " + startTime.getText() + "  --->  " + endTime.getText());
            } catch (UnreasonableShiftException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Notice");
                alert.setHeaderText("Error");
                alert.setContentText("The day shift is Unreasonable");
                alert.show();
            } catch (AccessDeniedException e) {
                System.out.println(e.getMessage());
            }
        });
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.TOP_LEFT);
        vBox.setSpacing(30);
        vBox.getChildren().add(comboBox);
        vBox.getChildren().add(datePicker);
        vBox.getChildren().add(startTime);
        vBox.getChildren().add(endTime);
        vBox.getChildren().add(addShiftBtn);
        vBox.getChildren().add(removeShiftBtn);
        vBox.getChildren().add(updateShiftBtn);
        gridPane.add(vBox, 1, 0);
//       gridPane.add(dayshiftView,0,0);

        Scene scene = new Scene(gridPane, 800, 500);
        stage.setScene(scene);
        stage.show();
    }

    public void addPatient() {
        Stage stage = new Stage();
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(30);
        gridPane.setHgap(30);
        Label nameLabel = new Label("Patient Name");
        Label genderLabel = new Label("Patient gender");
        Label isolationLabel = new Label("Patient isloation");
        TextField patientNameText = new TextField();
        ComboBox sexCombo = new ComboBox();
        sexCombo.getItems().add("Male");
        sexCombo.getItems().add("FeMale");

        ComboBox needIsolation = new ComboBox();
        needIsolation.getItems().add("True");
        needIsolation.getItems().add("False");

        Button addBtn = new Button("Add Patient");
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(genderLabel, 0, 1);
        gridPane.add(isolationLabel, 0, 2);
        gridPane.add(patientNameText, 1, 0);
        gridPane.add(sexCombo, 1, 1);
        gridPane.add(needIsolation, 1, 2);
        gridPane.add(addBtn, 1, 3);

        addBtn.setOnMouseClicked(event -> {
            String patientName = patientNameText.getText();
            boolean patientExist = false;
            for (Patient patient : StageManager.ableCareHome.patients) {
                if (patient.name.equals(patientName)) {
                    patientExist = true;
                    break;
                }
            }
            if (patientExist) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Notice");
                alert.setHeaderText("Error");
                alert.setContentText("The patient is already existed");
                alert.show();
            } else {
                Sex sex = sexCombo.getSelectionModel().getSelectedItem().equals("Male") ? Sex.Male : Sex.Female;
                boolean Isolation = needIsolation.getSelectionModel().getSelectedItem().equals("True") ? true : false;
                StageManager.ableCareHome.addPatient(patientName, sex, Isolation);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notice");
                alert.setHeaderText("Notice");
                alert.setContentText("The patient " + patientName + "add success");
                alert.show();
            }
        });
        Scene scene = new Scene(gridPane, 500, 500);
        stage.setScene(scene);
        stage.show();
    }

    public void Logout(Staff staff) {
        Stage stage = new Stage();
        staff.isAuthoized = false;
        try {
            Login(stage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void Login(Stage primaryStage){
        this.jdb = new JdbcConnect();
        this.jdb.connect();
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30, 30, 30, 30));
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
        root.add(radioButton1, 0, 0);
        root.add(radioButton2, 1, 0);
        root.add(radioButton3, 2, 0);

        Label username = new Label("UserName");
        Label password = new Label("Password");
        TextField usernameText = new TextField();
        TextField passwordText = new PasswordField();
        Button LoginBtn = new Button("Login");
        root.setHalignment(LoginBtn, HPos.CENTER);
        root.setValignment(LoginBtn, VPos.CENTER);

        root.add(username, 0, 1);
        root.add(password, 0, 2);
        root.add(usernameText, 1, 1);
        root.add(passwordText, 1, 2);
        root.add(LoginBtn, 1, 3);
        LoginBtn.setOnMouseClicked(event -> {
            String userType = toggleGroup.selectedToggleProperty().getValue().getUserData().toString();
            System.out.println(userType);
//            boolean isLogin = this.jdb.boolSearch("select * from user where username='"+usernameText.getText()+"' and password='"+passwordText.getText()+"' and usertype='"+userType+"'");
//            StageManager.ableCareHome.managers.add(new Manager(3001,"manager1", "12345"));
//            StageManager.ableCareHome.nurses.add(new Nurse(1001, "nurse1", "12345"));
//            StageManager.ableCareHome.nurses.add(new Nurse(1002, "nurse2", "12345"));
            Staff staff = StageManager.ableCareHome.Login(userType, usernameText.getText(), passwordText.getText());
            if (staff != null) {
                primaryStage.close();
                switch (userType) {
                    case "Nurse":
                        this.nurse = (Nurse) staff;
                        break;
                    case "Doctor":
                        this.doctor = (Doctor) staff;
                        break;
                    case "Manager":
                        this.manager = (Manager) staff;
                        break;
                }
                try {
//                    this.manager.allocateShift(StageManager.ableCareHome.nurses.get(0), 2021, 5, 11, 8, 30, 16, 30);
//                    this.manager.allocateShift(StageManager.ableCareHome.nurses.get(0), 2021, 6, 11, 8, 30, 16, 30);
//                    this.manager.allocateShift(StageManager.ableCareHome.nurses.get(1), 2021, 5, 11, 11, 30, 15, 30);
//                    this.manager.allocateShift(StageManager.ableCareHome.nurses.get(1), 2021, 6, 11, 8, 30, 14, 30);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                if (userType.equals("Nurse") || userType.equals("Doctor")) {
                    WardPage();
                } else
                    ManagerPage();
            } else {
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

    public void WardPage() {
        Stage primaryStage = new Stage();
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setVgap(50);
        root.setHgap(30);
        for (int temp = 0; temp < 2; ++temp) {
            GridPane rooms = new GridPane();
            rooms.setStyle("-fx-border-color:#3498db;-fx-border-width:3px");
            rooms.setPadding(new Insets(30, 30, 30, 30));
            rooms.setAlignment(Pos.CENTER);
            rooms.setVgap(50);
            rooms.setHgap(30);
            for (int i = 0, roomIndex = 0; i < 2; ++i)
                for (int j = 0; j < 3; ++j) {
                    GridPane gridPane = new GridPane();
                    gridPane.setStyle("-fx-border-color:#3498db;-fx-border-width:3px");
                    gridPane.setPadding(new Insets(30, 30, 30, 30));
                    gridPane.setHgap(50);
                    gridPane.setVgap(30);
                    for (int x = 0, bedIndex = 0; x < 2; ++x) {
                        for (int y = 0; y < 2; ++y) {
                            GridPane gridPane1 = new GridPane();
                            Bed finalBed = StageManager.ableCareHome.wards.get(temp).rooms.get(roomIndex).Beds.get(bedIndex);
                            if (finalBed.patient != null)
                                if (finalBed.patient.sex == Sex.Male)
                                    gridPane1.setStyle("-fx-background-color: blue;-fx-border-color:#3498db;-fx-border-width:3px");
                                else {
                                    if (finalBed.patient.sex == Sex.Female) {
                                        gridPane1.setStyle("-fx-background-color: red;-fx-border-color:#3498db;-fx-border-width:3px");
                                    }
                                }
                            else {
                                gridPane1.setStyle("-fx-background-color: white;-fx-border-color:#3498db;-fx-border-width:3px");
                            }
                            gridPane1.setMinWidth(50);
                            gridPane1.setMinHeight(50);
                            gridPane1.setHgap(20);
                            gridPane1.setVgap(15);
                            int finalTemp = temp;
                            int finalRoomIndex = roomIndex;
                            int finalBedIndex = bedIndex;
                            gridPane1.setOnMouseClicked(event -> {
                                //Nurse Page
                                if (this.nurse != null) {
                                    Stage stage = new Stage();
                                    GridPane gridPane2 = new GridPane();
                                    gridPane2.setAlignment(Pos.CENTER);
                                    gridPane2.setVgap(30);
                                    gridPane2.setHgap(30);

                                    ComboBox patientCombo = new ComboBox();
                                    for (Patient patient : StageManager.ableCareHome.patients)
                                        patientCombo.getItems().add(patient.ID + " " + patient.name);

                                    //When bed don't have patient button will show
                                    Button button = new Button("Add patient to Bed");
                                    button.setOnMouseClicked(event1 -> {
                                        outer:
                                        for (Patient patient : StageManager.ableCareHome.patients)
                                            if ((patient.ID + " " + patient.name).equals(patientCombo.getSelectionModel().getSelectedItem())) {
                                                for (Ward ward : StageManager.ableCareHome.wards)
                                                    for (Room room : ward.rooms)
                                                        for (Bed bed : room.Beds)
                                                            if (bed.patient != null)
                                                                if (bed.patient.equals(patient)) {
                                                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                                                    alert.setTitle("Notice");
                                                                    alert.setHeaderText("Error");
                                                                    alert.setContentText("The patient is already has bed");
                                                                    alert.show();
                                                                    break outer;
                                                                }
                                                this.nurse.AddToBed(patient, StageManager.ableCareHome.wards.get(finalTemp).rooms.get(finalRoomIndex).Beds.get(finalBedIndex));
                                                stage.close();
                                                primaryStage.close();
                                                WardPage();
                                            }
                                    });

                                    if (finalBed.patient == null && this.nurse != null) {
                                        gridPane2.add(patientCombo, 0, 2);
                                        gridPane2.add(button, 1, 2);
                                    } else {
                                        gridPane2.add(new Label(finalBed.ID), 0, 0);
                                        gridPane2.add(new Label("Patient: " + finalBed.patient.ID + " " + finalBed.patient.name + " " + ((finalBed.patient.sex == Sex.Male) ? "Male" : "FeMale")), 0, 1);
                                        gridPane2.add(new Label("Need Isolation: " +(finalBed.patient.needIsolation ? "True" : "False")), 0, 2);
                                        gridPane2.add(new Label("Prescription Worker ID: " + finalBed.prescription.workerID), 0, 3);
                                        Button administerPreBtn = new Button("Administer Prescription");
                                        ListView medicines = new ListView();
                                        ObservableList<String> list = FXCollections.observableArrayList();


                                        gridPane2.add(medicines,0,4);
                                        administerPreBtn.setOnMouseClicked(event1 -> {
                                            try {
                                                list.clear();
                                                Prescription prescription = this.nurse.AdministerPrescritpion(StageManager.ableCareHome.wards, finalBed.patient.ID);
                                                for (Medicine medicine : prescription.medicines)
                                                    list.add(medicine.name + " " + medicine.doseAmt + " times a day " + "one time " + medicine.doseUnit);
                                            }catch (AccessDeniedException e){
                                                System.out.println(e.getMessage());
                                            }
                                        });
                                        medicines.setItems(list);
                                        gridPane2.add(administerPreBtn,1,0);

                                        VBox vBox = new VBox();
                                        vBox.setSpacing(30);
                                        vBox.setAlignment(Pos.CENTER);
                                        ComboBox comboBox = new ComboBox();
                                        for (Ward ward : StageManager.ableCareHome.wards)
                                            for (Room room : ward.rooms)
                                                for (Bed bed : room.Beds)
                                                    comboBox.getItems().add(bed.ID);
                                        vBox.getChildren().add(comboBox);
                                        Button changeBed = new Button("Change Bed");
                                        changeBed.setOnMouseClicked(event1 -> {
                                            outer:
                                            for (Ward ward : StageManager.ableCareHome.wards)
                                                for (Room room : ward.rooms)
                                                    for (Bed bed : room.Beds) {
                                                        if (bed.ID.equals(comboBox.getSelectionModel().getSelectedItem())) {
                                                            if (bed.patient == null) {
                                                                this.nurse.ChangeBed(finalBed.patient, finalBed, bed);
                                                                stage.close();
                                                                primaryStage.close();
                                                                WardPage();
                                                                break outer;
                                                            }
                                                        }
                                                    }
                                        });
                                        vBox.getChildren().add(changeBed);
                                        gridPane2.add(vBox,1,1);
                                    }

                                    Scene scene = new Scene(gridPane2, 1000, 800);
                                    stage.setScene(scene);
                                    stage.initModality(Modality.APPLICATION_MODAL);
                                    stage.show();
                                }else
                                    //Doctor Page
                                    if (this.doctor != null){
                                        Stage stage = new Stage();
                                        GridPane doctorGrid = new GridPane();
                                        doctorGrid.setAlignment(Pos.CENTER);
                                        doctorGrid.setVgap(30);
                                        doctorGrid.setHgap(20);
                                        if (finalBed.patient != null) {
                                            doctorGrid.add(new Label("Patient: " + finalBed.patient.ID + " " + finalBed.patient.name + " " + ((finalBed.patient.sex == Sex.Male) ? "Male" : "FeMale")), 0, 1);
                                            doctorGrid.add(new Label("Need Isolation: " + (finalBed.patient.needIsolation ? "True" : "False")), 0, 2);
                                            doctorGrid.add(new Label("Prescription Worker ID: " + finalBed.prescription.workerID), 0, 3);

                                            ListView medicines = new ListView();
                                            ObservableList<String> list = FXCollections.observableArrayList();
                                            Button checkDetailBtn = new Button("Check Detail");
                                            checkDetailBtn.setOnMouseClicked(event1 -> {
                                                list.clear();
                                                Prescription prescription = this.doctor.CheckDetail(StageManager.ableCareHome.wards, finalBed.patient.ID);
                                                for (Medicine medicine : prescription.medicines)
                                                    list.add(medicine.name + " " + medicine.doseAmt + " times a day " + "one time " + medicine.doseUnit);
                                            });
                                            medicines.setItems(list);
                                            doctorGrid.add(medicines,0,4);


                                            TextField medicineNameText = new TextField();
                                            medicineNameText.setPromptText("medicine name ");
                                            TextField doseAmtText = new TextField();
                                            doseAmtText.setPromptText("medicine doseAmt");
                                            TextField doseUnitText = new TextField();
                                            doseUnitText.setPromptText("medicine doseUnit");
                                            VBox vBox = new VBox();
                                            vBox.setAlignment(Pos.TOP_LEFT);
                                            vBox.setSpacing(30);

                                            vBox.getChildren().add(medicineNameText);
                                            vBox.getChildren().add(doseAmtText);
                                            vBox.getChildren().add(doseUnitText);
                                            vBox.getChildren().add(checkDetailBtn);

                                            Button attachPreBtn = new Button("attach Prescription");
                                            attachPreBtn.setOnMouseClicked(event1 -> {
                                                this.doctor.attachPrescription(finalBed,medicineNameText.getText(),Integer.parseInt(doseAmtText.getText()),doseUnitText.getText());
                                                list.add(medicineNameText.getText() + " " + doseAmtText.getText() + " times a day " + "one time " + doseUnitText.getText());
                                            });
                                            vBox.getChildren().add(attachPreBtn);

                                            Button removePreBtn = new Button("remove Prescription");
                                            removePreBtn.setOnMouseClicked(event1 -> {
                                                String medicineName = medicines.getSelectionModel().getSelectedItem().toString().split(" ")[0];
                                                System.out.println(medicineName);
                                                this.doctor.removePrescription(finalBed,medicineName);
                                                list.remove(medicines.getSelectionModel().getSelectedItem());
                                            });
                                            vBox.getChildren().add(removePreBtn);

                                            Button updatePreBtn = new Button("update Prescription");
                                            updatePreBtn.setOnMouseClicked(event1 -> {
                                                String medicineName = medicines.getSelectionModel().getSelectedItem().toString().split(" ")[0];
                                                this.doctor.updatePrescription(finalBed,medicineName,Integer.parseInt(doseAmtText.getText()),doseUnitText.getText());
                                                list.set(medicines.getSelectionModel().getSelectedIndex(),medicineName + " " + doseAmtText.getText() + " times a day " + "one time " + doseUnitText.getText());
                                            });
                                            vBox.getChildren().add(updatePreBtn);
                                            doctorGrid.add(vBox,1,4);
                                        }
//                                        doctorGrid.add(new Label("test"),0,0);
                                        Scene scene = new Scene(doctorGrid,800,700);
                                        stage.setScene(scene);
                                        stage.show();
                                    }
                            });
                            bedIndex++;
                            gridPane.add(gridPane1, y, x);
                        }
                    }
                    roomIndex++;
                    rooms.add(gridPane, i, j);
                }
            root.add(rooms, temp, 1);
            Label label1 = new Label("Ward " + temp);
            label1.setFont(Font.font(30));
            root.add(label1, temp, 0);
        }
        Button logoutBtn = new Button("Logout");
        logoutBtn.setOnMouseClicked(event -> {
            primaryStage.close();
            try {
                this.nurse = null;
                this.doctor = null;
                this.manager = null;
                Login(primaryStage);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        root.add(logoutBtn, 1, 2);
        Scene scene = new Scene(root, 1500, 1080);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Login(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
