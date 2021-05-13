public class Bed {
    String ID;
    Patient patient;
    Prescription prescription;

    Bed(String id){
        this.ID = id;
        this.patient = null;
        this.prescription = new Prescription();
    }
    public void addPatient(Patient patient){
        this.patient = patient;
        StageManager.connect.updateData("insert into Bed values('"+this.ID+"',"+patient.ID+")");
    }
    public void removePatient(){
        StageManager.connect.updateData("delete from Bed where patientID="+this.patient.ID+" and ID='"+this.ID+"'");
        this.patient = null;
    }
}
