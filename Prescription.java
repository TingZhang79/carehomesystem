import java.util.ArrayList;

public class Prescription {
    int patientID;
    int workerID;
    ArrayList<Medicine> medicines;
    Prescription(){
        this.medicines = new ArrayList<Medicine>();
    }

}
