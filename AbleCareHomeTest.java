import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

public class AbleCareHomeTest {
    AbleCareHome ableCareHome = new AbleCareHome();
    @Before
    public void before(){
        Manager manager = new Manager("manager1","1111");
        this.ableCareHome.managers.add(manager);

        ableCareHome.addPatient("patient1",Sex.Male,false);
        ableCareHome.addPatient("patient2",Sex.Female,false);
        ableCareHome.addPatient("patient3",Sex.Male,false);
        ableCareHome.addPatient("patient4",Sex.Female,false);
        ableCareHome.addPatient("patient5",Sex.Male,false);

        Manager manager1 = (Manager) ableCareHome.Login("Manager","manager1","1111");
        manager1.addDoctor(ableCareHome.doctors,new Doctor(1001,"doctor1","1111"));
        manager1.addNurse(ableCareHome.nurses,new Nurse(2001,"nurse1","1111"));
    }

    @Test
    public void addPatientToBed(){
        Nurse nurse = (Nurse) ableCareHome.Login("Nurse","nurse1","1111");
        nurse.AddToBed(this.ableCareHome.patients.get(0),this.ableCareHome.wards.get(0).rooms.get(0).Beds.get(0));
        assertEquals(this.ableCareHome.patients.get(0).ID,this.ableCareHome.wards.get(0).rooms.get(0).Beds.get(0).patient.ID);
    }
    @Test
    public void addNurse(){
        Manager manager1 = (Manager) ableCareHome.Login("Manager","manager1","1111");
        Nurse nurse = new Nurse(2002,"nurse2","1111");
        manager1.addNurse(ableCareHome.nurses,nurse);
        assertTrue(this.ableCareHome.nurses.contains(nurse));
    }
    @Test
    public void addDoctor(){
        Manager manager1 = (Manager) ableCareHome.Login("Manager","manager1","1111");
        Doctor doctor= new Doctor(1002,"doctor2","1111");
        manager1.addDoctor(ableCareHome.doctors,doctor);
        assertTrue(this.ableCareHome.doctors.contains(doctor));
    }

    @Test
    public void changeNursePassword(){
        Manager manager1 = (Manager) ableCareHome.Login("Manager","manager1","1111");
        Nurse nurse = new Nurse(2001,"nurse2","1111");
        manager1.addNurse(ableCareHome.nurses,nurse);
        manager1.changeNursePassword(ableCareHome.nurses,"nurse2","1111","2222");
        for (Nurse nurse1 : ableCareHome.nurses)
            if (nurse1.username.equals("nurse2"))
                assertEquals(nurse1.password,"2222");
    }
    @Test
    public void changeDoctorPassword(){
        Manager manager1 = (Manager) ableCareHome.Login("Manager","manager1","1111");
        Doctor doctor = new Doctor(1001,"doctor2","1111");
        manager1.addDoctor(ableCareHome.doctors,doctor);
        manager1.changeDoctorPassword(ableCareHome.doctors,"doctor2","1111","2222");
        for (Doctor doctor1 : ableCareHome.doctors)
            if (doctor1.username.equals("doctor2"))
                assertEquals(doctor1.password,"2222");
    }
    @Test
    public void attachShiftTest(){
        try {
            Manager manager1 = (Manager) ableCareHome.Login("Manager", "manager1", "1111");
            manager1.allocateShift(ableCareHome.nurses.get(0), 2021, 5, 1, 8, 0, 16, 0);
            assertEquals(ableCareHome.nurses.get(0).shiftDay.get(0).endTime.get(Calendar.HOUR_OF_DAY), 16);
            assertEquals(ableCareHome.nurses.get(0).shiftDay.get(0).endTime.get(Calendar.MINUTE), 0);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void checkShiftTest(){
        try {
            Manager manager1 = (Manager) ableCareHome.Login("Manager", "manager1", "1111");
            manager1.allocateShift(ableCareHome.nurses.get(0), 2021, 5, 1, 8, 0, 13, 0);
            manager1.allocateShift(ableCareHome.nurses.get(0), 2021, 5, 1, 13, 0, 16, 30);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void updateShiftTest(){
        try {
            Manager manager1 = (Manager) ableCareHome.Login("Manager","manager1","1111");
            manager1.allocateShift(ableCareHome.nurses.get(0),2021,5,1,8,0,16,0);
            manager1.updateShift(ableCareHome.nurses.get(0),2021,5,1,8,0,16,0,2021,5,1,8,0,15,0);
            assertEquals(ableCareHome.nurses.get(0).shiftDay.get(0).endTime.get(Calendar.MINUTE),0);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void checkDetailTest(){
        Nurse nurse = (Nurse) ableCareHome.Login("Nurse","nurse1","1111");
        nurse.AddToBed(this.ableCareHome.patients.get(0),this.ableCareHome.wards.get(0).rooms.get(0).Beds.get(0));
        nurse.CheckDetail(ableCareHome.wards,this.ableCareHome.patients.get(0).ID);
    }
    @Test
    public void attachNewPrescriptionTest(){
        Nurse nurse = (Nurse) ableCareHome.Login("Nurse","nurse1","1111");
        nurse.AddToBed(this.ableCareHome.patients.get(0),this.ableCareHome.wards.get(0).rooms.get(0).Beds.get(0));
        Doctor doctor = (Doctor) ableCareHome.Login("Doctor","doctor1","1111");
        doctor.attachPrescription(this.ableCareHome.wards.get(0).rooms.get(0).Beds.get(0),"testmedicine",3,"4g");
    }

    @Test
    public void removePrescriptionTest(){
        Nurse nurse = (Nurse) ableCareHome.Login("Nurse","nurse1","1111");
        nurse.AddToBed(this.ableCareHome.patients.get(0),this.ableCareHome.wards.get(0).rooms.get(0).Beds.get(0));
        Doctor doctor = (Doctor) ableCareHome.Login("Doctor","doctor1","1111");
        doctor.attachPrescription(this.ableCareHome.wards.get(0).rooms.get(0).Beds.get(0),"testmedicine",3,"4g");
        doctor.removePrescription(this.ableCareHome.wards.get(0).rooms.get(0).Beds.get(0),"testmedicine");
        assertEquals(this.ableCareHome.wards.get(0).rooms.get(0).Beds.get(0).prescription.medicines.size(),0);
    }

    @Test
    public void updatePrescription(){
        Nurse nurse = (Nurse) ableCareHome.Login("Nurse","nurse1","1111");
        nurse.AddToBed(this.ableCareHome.patients.get(0),this.ableCareHome.wards.get(0).rooms.get(0).Beds.get(0));
        Doctor doctor = (Doctor) ableCareHome.Login("Doctor","doctor1","1111");
        doctor.attachPrescription(this.ableCareHome.wards.get(0).rooms.get(0).Beds.get(0),"testmedicine",3,"4g");
        doctor.updatePrescription(this.ableCareHome.wards.get(0).rooms.get(0).Beds.get(0),"testmedicine",4,"2g");
        assertEquals(this.ableCareHome.wards.get(0).rooms.get(0).Beds.get(0).prescription.medicines.get(0).doseAmt,4);
        assertEquals(this.ableCareHome.wards.get(0).rooms.get(0).Beds.get(0).prescription.medicines.get(0).doseUnit,"2g");
    }

    @Test
    public void administerPrescription(){
        try {
            Nurse nurse = (Nurse) ableCareHome.Login("Nurse", "nurse1", "1111");
            nurse.AddToBed(this.ableCareHome.patients.get(0), this.ableCareHome.wards.get(0).rooms.get(0).Beds.get(0));
            Doctor doctor = (Doctor) ableCareHome.Login("Doctor", "doctor1", "1111");
            doctor.attachPrescription(this.ableCareHome.wards.get(0).rooms.get(0).Beds.get(0), "testmedicine", 3, "4g");
            Prescription prescription = nurse.AdministerPrescritpion(this.ableCareHome.wards, this.ableCareHome.patients.get(0).ID);
            assertEquals(this.ableCareHome.wards.get(0).rooms.get(0).Beds.get(0).prescription.medicines.get(0).name, prescription.medicines.get(0).name);
            assertEquals(this.ableCareHome.wards.get(0).rooms.get(0).Beds.get(0).prescription.medicines.get(0).doseAmt, prescription.medicines.get(0).doseAmt);
            assertEquals(this.ableCareHome.wards.get(0).rooms.get(0).Beds.get(0).prescription.medicines.get(0).doseUnit, prescription.medicines.get(0).doseUnit);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void logTest(){
        Nurse nurse = (Nurse) ableCareHome.Login("Nurse","nurse1","1111");
        nurse.LogAction("testlog",null);
    }
}
