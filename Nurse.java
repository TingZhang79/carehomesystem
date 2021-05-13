import java.util.ArrayList;
import java.util.Calendar;

public class Nurse extends MedicalWorkers{
    JdbcConnect connect = new JdbcConnect();
    Nurse(int id,String username,String password){
        connect.connect();
        this.username = username;
        this.password = password;
        this.ID = id;
    }
    public Prescription AdministerPrescritpion(ArrayList<Ward> wards,int id) throws AccessDeniedException {
        Bed tarbed = null;
        if (!this.isAuthoized)
            throw new AccessDeniedException();
        outer:
        for (Ward ward:wards){
            for (Room room : ward.rooms)
                for (Bed bed : room.Beds)
                    if (bed.patient != null)
                        if (bed.patient.ID == id) {
                            tarbed = bed;
                            break outer;
                        }
        }
        if (tarbed != null)
            return tarbed.prescription;
        else
            return null;
    }

    public void ChangeBed(Patient patient,Bed srcbed,Bed tarbed) {
        try {
            if (!isAuthoized)
                throw new AccessDeniedException();
            srcbed.removePatient();
            tarbed.prescription = srcbed.prescription;
            tarbed.addPatient(patient);

            Calendar cal = Calendar.getInstance();
            LogAction("Nurse:" +this.ID+ " Change patient:" + patient.name+ " from bed:" + srcbed.ID+" to "+tarbed.ID,cal);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void AddToBed(Patient patient, Bed bed) {
        try {
            if (!isAuthoized)
                throw new AccessDeniedException();
            bed.addPatient(patient);
            Calendar cal = Calendar.getInstance();
            LogAction("Nurse:" +this.ID+ " Add patient:" + patient.name+ " to bed:" + bed.ID,cal);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
