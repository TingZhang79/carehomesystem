import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AbleCareHomeGui extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Label label = new Label("test");
//            BorderPane root = new BorderPane(label);
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(root,600,400);

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

//            Scene scene = new Scene(root,1200,1080);
//            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Able Care Home Login");


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

            primaryStage.show();
//            stage.show();
//            stage3.show();
        } catch(Exception e) {
            e.printStackTrace();

        }
    }
}
