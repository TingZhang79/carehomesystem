import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class Main{
    @FXML
    private Label Label1;

    @FXML
    public void testclick(Event e){
        System.out.println("Cliecked");
        ArrayList<String> list = (ArrayList<String>) this.Label1.getScene().getUserData();
        this.Label1.setText(list.get(0));
    }

    @FXML
    public void initialize(){
    }

}
