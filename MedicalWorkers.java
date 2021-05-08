import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public abstract class MedicalWorkers extends Staff implements MedicalWorkerFunctions{
    ArrayList<ShiftDay> shiftDay;

    MedicalWorkers(){
        shiftDay = new ArrayList<ShiftDay>();
    }

    public boolean CheckCompliance() {
        try {
            if (this.shiftDay.size() > 14)
                throw new UnreasonableShiftException();
            Map<Calendar,Float> ShiftHours = new HashMap<Calendar,Float>();
            float hour = 0.0f;
            for (ShiftDay tempShiftDay : this.shiftDay){
                if (ShiftHours.containsKey(tempShiftDay.day)){
                    hour = hour + tempShiftDay.endTime.get(Calendar.HOUR_OF_DAY)-tempShiftDay.startTime.get(Calendar.HOUR_OF_DAY) + ((float)tempShiftDay.endTime.get(Calendar.MINUTE) - (float)tempShiftDay.startTime.get(Calendar.MINUTE))/60;
                    ShiftHours.put(tempShiftDay.day,ShiftHours.get(tempShiftDay.day) + hour);
                }
                else{
                    hour = tempShiftDay.endTime.get(Calendar.HOUR_OF_DAY)-tempShiftDay.startTime.get(Calendar.HOUR_OF_DAY) + ((float)tempShiftDay.endTime.get(Calendar.MINUTE) - (float)tempShiftDay.startTime.get(Calendar.MINUTE))/60;
                    ShiftHours.put(tempShiftDay.day,hour);
                }
            }
            for (Map.Entry<Calendar,Float> entry : ShiftHours.entrySet()){
                if (entry.getValue() > 8) {
                    throw new UnreasonableShiftException();
                }else
                    return true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Prescription CheckDetail(ArrayList<Ward> wards, int id) {
        Prescription prescription = null;
        try {
            if (!this.isAuthoized)
                throw new AccessDeniedException();
            outer:
            for (Ward ward : wards) {
                for (Room room : ward.rooms) {
                    for (Bed bed : room.Beds) {
                        Patient patient = bed.patient;
                        if(patient != null)
                            if (patient.ID == id) {
                                System.out.println("Patient ID:" + patient.ID);
                                System.out.println("Patient name:" + patient.name);
                                System.out.println("Patient sex:" + patient.sex);
                                System.out.println("Patient needIsolation:" + patient.needIsolation);
                                System.out.println("Patient Prescription:");
                                System.out.println("Patient ID:"+bed.prescription.patientID);
                                System.out.println("Worker ID:"+bed.prescription.workerID);
                                for (Medicine medicine : bed.prescription.medicines) {
                                    System.out.println(medicine.name + " " + medicine.doseAmt + " times a day " + "one time " + medicine.doseUnit);
                                }
                                Calendar cal = Calendar.getInstance();
                                LogAction("MedicalWorker: "+this.ID + " Check Patient:" + patient.name + " Detail",cal);
                                prescription = bed.prescription;
                                break outer;
                            }
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return prescription;
    }
}
