import java.util.Calendar;

public class Doctor extends MedicalWorkers{
    Doctor(int id,String username,String password){
        this.username = username;
        this.password = password;
        this.ID = id;
    }
    public void attachPrescription(Bed bed,String name,int doseAmt,String doseUnit){
        try {
            if (this.isAuthoized) {
                Medicine medicine = new Medicine();
                medicine.name = name;
                medicine.doseUnit = doseUnit;
                medicine.doseAmt = doseAmt;
                bed.prescription.medicines.add(medicine);
                bed.prescription.patientID = bed.patient.ID;
                bed.prescription.workerID = this.ID;
                StageManager.connect.updateData("insert into Prescription values("+bed.patient.ID+","+this.ID+",'"+name+"'"+",'"+doseAmt+"'"+",'"+doseUnit+"')");
                Calendar cal = Calendar.getInstance();
                LogAction("Doctor: "+ this.ID +" "+ this.username + " attach prescription:"+medicine.name + " to " + bed.ID + "->Patient:"+bed.patient.name,cal);
            }else
                throw new AccessDeniedException();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void removePrescription(Bed bed,String medicineName){
        try {
            if (this.isAuthoized) {
                for (Medicine medicine : bed.prescription.medicines){
                    if (medicine.name.equals(medicineName)) {
                        bed.prescription.medicines.remove(medicine);
                        StageManager.connect.updateData("delete from Prescription where patientID="+bed.patient.ID+" and medicineName='"+medicineName+"'");
                        Calendar cal = Calendar.getInstance();
                        LogAction("Doctor: "+ this.ID +" "+ this.username + " remove medicine from prescription:"+medicine.name + " to " + bed.ID + "->Patient:"+bed.patient.name,cal);
                        break;
                    }
                }
            }else
                throw new AccessDeniedException();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void updatePrescription(Bed bed,String tarMedicineName,int doseAmt,String doseUnit){
        try {
            if (!isAuthoized)
                throw new AccessDeniedException();
            for (Medicine medicine : bed.prescription.medicines) {
                if (medicine.name.equals(tarMedicineName)) {
                    medicine.doseAmt = doseAmt;
                    medicine.doseUnit = doseUnit;
                    StageManager.connect.updateData("update Prescription set medicinedoseAmt='"+doseAmt+"',"+"medicinedoseUnit='"+doseUnit+"' where patientID="+bed.patient.ID+" and medicineName='"+tarMedicineName+"'");
                    Calendar cal = Calendar.getInstance();
                    LogAction("Nurse: "+this.ID + " update bed "+bed.ID+" Prescription  medicine name:"+tarMedicineName +" doseAmt:"+doseAmt+ " doseUnit:"+doseUnit,cal);
                    break;
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
