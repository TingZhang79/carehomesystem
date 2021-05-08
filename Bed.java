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
    }
    public void removePatient(){
        this.patient = null;
    }
}
