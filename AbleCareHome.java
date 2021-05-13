import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AbleCareHome {
    ArrayList<Doctor> doctors;
    ArrayList<Nurse> nurses;
    ArrayList<Ward> wards;
    ArrayList<Manager> managers;
    ArrayList<Patient> patients;
    Scanner scanner = new Scanner(System.in);

    AbleCareHome() throws SQLException {
        JdbcConnect connect = new JdbcConnect();
        connect.connect();
        doctors = new ArrayList<Doctor>();
        nurses = new ArrayList<Nurse>();
        wards = new ArrayList<Ward>();
        managers = new ArrayList<Manager>();
        patients = new ArrayList<Patient>();


        ResultSet resultSet = connect.resultSearch("select * from user where usertype='doctor'");
        if (resultSet != null) {
            while (resultSet.next()) {
                this.doctors.add(new Doctor(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getString("password")));
            }
        }

        resultSet = connect.resultSearch("select * from user where usertype='nurse'");
        if (resultSet != null) {
            while (resultSet.next()) {
                this.nurses.add(new Nurse(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getString("password")));
            }
        }

        resultSet = connect.resultSearch("select * from user where usertype='manager'");
        if (resultSet != null) {
            while (resultSet.next()) {
                this.managers.add(new Manager(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getString("password")));
            }
        }

        resultSet = connect.resultSearch("select * from patient");
        if (resultSet != null) {

            while (resultSet.next()) {
                Patient patient = new Patient();
                patient.ID = resultSet.getInt("id");
                patient.name = resultSet.getString("name");
                patient.sex = (resultSet.getString("sex") == "Male") ? Sex.Male : Sex.Female;
                patient.needIsolation = resultSet.getString("needIsolation") == "True" ? true : false;
                this.patients.add(patient);
            }
        }

        for (int i = 0; i < 2; ++i) {
            Ward ward = new Ward();
            for (int j = 0; j < 6; ++j) {
                Room room = new Room();
                for (int x = 0; x < 4; ++x) {
                    Bed bed = new Bed("" + i + j + x);
                    room.addBed(bed);
                }
                ward.addRoom(room);
            }
            this.wards.add(ward);
        }


        resultSet = connect.resultSearch("select * from Bed");

        if (resultSet != null) {
            while (resultSet.next()) {
                Bed tarbed = null;
                outer:
                for (Ward ward : this.wards)
                    for (Room room : ward.rooms)
                        for (Bed bed : room.Beds)
                            if (bed.ID.equals(resultSet.getString("ID"))) {
                                tarbed = bed;
                                int patientID = resultSet.getInt("patientID");
                                for (Patient patient : this.patients) {
                                    if (patient.ID == patientID)
                                        bed.patient = patient;
                                }
                                break outer;
                            }
            }
        }
        for (Ward ward : this.wards) {
            for (Room room : ward.rooms)
                for (Bed bed : room.Beds)
                    if (bed.patient != null) {
                        ResultSet prescriptionSet = connect.resultSearch("select * from Prescription where patientID=" + bed.patient.ID);
                        Prescription prescription = new Prescription();
                        if (prescriptionSet != null) {
                            while (prescriptionSet.next()) {
                                prescription.patientID = prescriptionSet.getInt("patientID");
                                prescription.workerID = prescriptionSet.getInt("workerID");
                                Medicine medicine = new Medicine();
                                medicine.name = prescriptionSet.getString("medicineName");
                                medicine.doseAmt = Integer.parseInt(prescriptionSet.getString("medicinedoseAmt"));
                                medicine.doseUnit = prescriptionSet.getString("medicinedoseUnit");
                                prescription.medicines.add(medicine);
                            }
                            bed.prescription = prescription;
                        }
                    }
        }
        for (Nurse nurse : this.nurses){
            resultSet = connect.resultSearch("select * from shifts where id="+nurse.ID);
            if (resultSet != null) {
                while (resultSet.next()) {
                    ShiftDay shiftDay = new ShiftDay();
                    shiftDay.startTime.set(0, 0, 0,Integer.parseInt(resultSet.getString("start_time").split(":")[0]),Integer.parseInt(resultSet.getString("start_time").split(":")[1]));
                    shiftDay.endTime.set(0, 0, 0,Integer.parseInt(resultSet.getString("end_time").split(":")[0]),Integer.parseInt(resultSet.getString("end_time").split(":")[1]));
                    shiftDay.day.set(Integer.parseInt(resultSet.getString("day").split("-")[0]),Integer.parseInt(resultSet.getString("day").split("-")[1]),Integer.parseInt(resultSet.getString("day").split("-")[2]));
                    nurse.shiftDay.add(shiftDay);
                }
            }
        }

        for (Doctor doctor : this.doctors){
            resultSet = connect.resultSearch("select * from shifts where id="+doctor.ID);
            if (resultSet != null) {
                while (resultSet.next()) {
                    ShiftDay shiftDay = new ShiftDay();
                    shiftDay.startTime.set(0, 0, 0,Integer.parseInt(resultSet.getString("start_time").split(":")[0]),Integer.parseInt(resultSet.getString("start_time").split(":")[1]));
                    shiftDay.endTime.set(0, 0, 0,Integer.parseInt(resultSet.getString("end_time").split(":")[0]),Integer.parseInt(resultSet.getString("end_time").split(":")[1]));
                    shiftDay.day.set(Integer.parseInt(resultSet.getString("day").split("-")[0]),Integer.parseInt(resultSet.getString("day").split("-")[1]),Integer.parseInt(resultSet.getString("day").split("-")[2]));
                    doctor.shiftDay.add(shiftDay);
                }
            }
        }
    }


    public Staff Login(String userType, String username, String password) {
        switch (userType) {
            case "Manager":
                for (Manager manager : managers) {
                    if (manager.Authentication(username, password))
                        return manager;
                }
                break;
            case "Doctor":
                for (Doctor doctor : doctors) {
                    if (doctor.Authentication(username, password))
                        return doctor;
                }
                break;
            case "Nurse":
                for (Nurse nurse : nurses) {
                    if (nurse.Authentication(username, password))
                        return nurse;
                }
                break;
        }
        return null;
    }

    public void addPatient(String name, Sex sex, boolean needIsolation) {
        Patient patient = new Patient();
        patient.name = name;
        patient.sex = sex;
        patient.needIsolation = needIsolation;
        String idString = patient.name;
        patient.ID = idString.hashCode();
        StageManager.connect.updateData("insert into patient values(" + patient.ID + ",'" + patient.name + "','" + (patient.sex == Sex.Male ? "Male" : "Female") + "','" + (patient.needIsolation ? "True" : "False") + "')");
        this.patients.add(patient);
    }

    public void manager(Manager manager1) {
        System.out.println("Choice Manager Method:");
        System.out.println("1.AddNurse");
        System.out.println("2.AddDoctor");
        System.out.println("3.ChangeNursePassword");
        System.out.println("4.ChangeDoctorPassword");
        System.out.println("5.allocateShift");
        System.out.println("6.updateShift");
        System.out.println("7.addPatient");
        int choice = this.scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.print("Input Nurse ID:");
                int id = this.scanner.nextInt();
                String username = this.scanner.nextLine();
                System.out.print("Input Nurse username:");
                username = this.scanner.nextLine();
                System.out.print("Input Nurse password:");
                String password = this.scanner.nextLine();
                Nurse nurse = new Nurse(id, username, password);
                manager1.addNurse(this.nurses, nurse);
                break;
            case 2:
                System.out.print("Input Doctor ID:");
                id = this.scanner.nextInt();
                username = this.scanner.nextLine();
                System.out.print("Input Doctor username:");
                username = this.scanner.nextLine();
                System.out.print("Input Doctor password:");
                password = this.scanner.nextLine();
                Doctor doctor = new Doctor(id, username, password);
                manager1.addDoctor(this.doctors, doctor);
                break;
            case 3:
                username = this.scanner.nextLine();
                System.out.print("Input Nurse username:");
                username = this.scanner.nextLine();
                System.out.print("Input Nurse old password:");
                password = this.scanner.nextLine();
                System.out.print("Input Nurse new password:");
                String newpassword = this.scanner.nextLine();
                manager1.changeNursePassword(this.nurses, username, password, newpassword);
                break;
            case 4:
                username = this.scanner.nextLine();
                System.out.print("Input Doctor username:");
                username = this.scanner.nextLine();
                System.out.print("Input Doctor old password:");
                password = this.scanner.nextLine();
                System.out.print("Input Doctor new password:");
                newpassword = this.scanner.nextLine();
                manager1.changeDoctorPassword(this.doctors, username, password, newpassword);
                break;
            case 5:
                int i = 0;
                for (Nurse nurse1 : this.nurses) {
                    System.out.println("0" + i + " " + nurse1.username);
                    ++i;
                }
                i = 0;
                for (Doctor doctor1 : this.doctors) {
                    System.out.println("1" + i + " " + doctor1.username);
                    ++i;
                }
                String choice_str = this.scanner.nextLine();
                System.out.print("Choice which woker:");
                choice_str = this.scanner.nextLine();
                System.out.print("Input Year:");
                int Year = this.scanner.nextInt();
                System.out.print("Input Month:");
                int Month = this.scanner.nextInt();
                System.out.print("Input Day:");
                int Day = this.scanner.nextInt();
                System.out.print("Input startHour:");
                int startHour = this.scanner.nextInt();
                System.out.print("Input startMin:");
                int startMin = this.scanner.nextInt();
                System.out.print("Input endHour:");
                int endHour = this.scanner.nextInt();
                System.out.print("Input endMin:");
                int endMin = this.scanner.nextInt();
                switch (choice_str.split("")[0]) {
                    case "0":
                        try {
                            manager1.allocateShift(this.nurses.get(Integer.parseInt(choice_str.split("")[1])), Year, Month, Day, startHour, startMin, endHour, endMin);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "1":
                        try {
                            manager1.allocateShift(this.doctors.get(Integer.parseInt(choice_str.split("")[1])), Year, Month, Day, startHour, startMin, endHour, endMin);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                }

                break;
            case 6:
                i = 0;
                for (Nurse nurse1 : this.nurses) {
                    System.out.println("0" + i + " " + nurse1.username);
                    ++i;
                }
                i = 0;
                for (Doctor doctor1 : this.doctors) {
                    System.out.println("1" + i + " " + doctor1.username);
                    ++i;
                }
                choice_str = this.scanner.nextLine();
                System.out.print("Choice which woker:");
                choice_str = this.scanner.nextLine();

                switch (choice_str.split("")[0]) {
                    case "0":
                        i = 0;
                        for (ShiftDay shiftDay : this.nurses.get(Integer.parseInt(choice_str.split("")[1])).shiftDay) {
                            System.out.println(i + " " + shiftDay.day.get(Calendar.YEAR) + "/" + shiftDay.day.get(Calendar.MONTH) + "/" + shiftDay.day.get(Calendar.DAY_OF_MONTH) + " " + shiftDay.startTime.get(Calendar.HOUR_OF_DAY) + ":" + shiftDay.startTime.get(Calendar.MINUTE) + "--->" + shiftDay.endTime.get(Calendar.HOUR_OF_DAY) + ":" + shiftDay.endTime.get(Calendar.MINUTE));
                            ++i;
                        }
                        System.out.print("choice shiftday:");
                        choice = this.scanner.nextInt();
                        System.out.print("Input Year:");
                        Year = this.scanner.nextInt();
                        System.out.print("Input Month:");
                        Month = this.scanner.nextInt();
                        System.out.print("Input Day:");
                        Day = this.scanner.nextInt();
                        System.out.print("Input startHour:");
                        startHour = this.scanner.nextInt();
                        System.out.print("Input startMin:");
                        startMin = this.scanner.nextInt();
                        System.out.print("Input endHour:");
                        endHour = this.scanner.nextInt();
                        System.out.print("Input endMin:");
                        endMin = this.scanner.nextInt();
                        ShiftDay tempShiftDay = this.nurses.get(Integer.parseInt(choice_str.split("")[1])).shiftDay.get(choice);
                        try {
                            manager1.updateShift(this.nurses.get(Integer.parseInt(choice_str.split("")[1])), tempShiftDay.day.get(Calendar.YEAR), tempShiftDay.day.get(Calendar.MONTH), tempShiftDay.day.get(Calendar.DAY_OF_MONTH), tempShiftDay.startTime.get(Calendar.HOUR_OF_DAY), tempShiftDay.startTime.get(Calendar.MINUTE), tempShiftDay.endTime.get(Calendar.HOUR_OF_DAY), tempShiftDay.endTime.get(Calendar.MINUTE), Year, Month, Day, startHour, startMin, endHour, endMin);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "1":
                        for (ShiftDay shiftDay : this.doctors.get(Integer.parseInt(choice_str.split("")[1])).shiftDay) {
                            System.out.println(i + " " + shiftDay.day.get(Calendar.YEAR) + "/" + shiftDay.day.get(Calendar.MONTH) + "/" + shiftDay.day.get(Calendar.DAY_OF_MONTH) + " " + shiftDay.startTime.get(Calendar.HOUR_OF_DAY) + ":" + shiftDay.startTime.get(Calendar.MINUTE) + "--->" + shiftDay.endTime.get(Calendar.HOUR_OF_DAY) + ":" + shiftDay.endTime.get(Calendar.MINUTE));
                            ++i;
                        }
                        System.out.print("choice shiftday:");
                        choice = this.scanner.nextInt();
                        System.out.print("Input Year:");
                        Year = this.scanner.nextInt();
                        System.out.print("Input Month:");
                        Month = this.scanner.nextInt();
                        System.out.print("Input Day:");
                        Day = this.scanner.nextInt();
                        System.out.print("Input startHour:");
                        startHour = this.scanner.nextInt();
                        System.out.print("Input startMin:");
                        startMin = this.scanner.nextInt();
                        System.out.print("Input endHour:");
                        endHour = this.scanner.nextInt();
                        System.out.print("Input endMin:");
                        endMin = this.scanner.nextInt();
                        tempShiftDay = this.nurses.get(Integer.parseInt(choice_str.split("")[1])).shiftDay.get(choice);
                        try {
                            manager1.updateShift(this.nurses.get(Integer.parseInt(choice_str.split("")[1])), tempShiftDay.day.get(Calendar.YEAR), tempShiftDay.day.get(Calendar.MONTH), tempShiftDay.day.get(Calendar.DAY_OF_MONTH), tempShiftDay.startTime.get(Calendar.HOUR_OF_DAY), tempShiftDay.startTime.get(Calendar.MINUTE), tempShiftDay.endTime.get(Calendar.HOUR_OF_DAY), tempShiftDay.endTime.get(Calendar.MINUTE), Year, Month, Day, startHour, startMin, endHour, endMin);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                }

                break;
            case 7:
                String name = this.scanner.nextLine();
                System.out.println("Input patient name:");
                name = this.scanner.nextLine();
                System.out.println("Input patient sex (M/F):");
                String sex_str = this.scanner.nextLine();
                Sex sex = sex_str.equals("M") ? Sex.Male : Sex.Female;
                System.out.println("Input patient needIsolation?(T/F):");
                choice_str = this.scanner.nextLine();
                boolean needIsolation = choice_str.equals("T") ? true : false;
                addPatient(name, sex, needIsolation);
                break;
        }
    }

    public void doctor(Doctor doctor1) {
        System.out.println("Choice Doctor Method:");
        System.out.println("1.CheckDetail");
        System.out.println("2.Attach new Prescription");
        System.out.println("3.remove Prescription");
        System.out.println("4.update Prescription");
        int choice = this.scanner.nextInt();
        switch (choice) {
            case 1:
                int i = 0;
                if (this.patients.size() == 0)
                    break;
                for (Patient patient : this.patients) {
                    System.out.println(i + " " + patient.ID + " " + patient.name);
                }
                System.out.print("Choice Patient:");
                choice = this.scanner.nextInt();
                doctor1.CheckDetail(this.wards, this.patients.get(choice).ID);
                break;
            case 2:
                for (int x = 0; x < this.wards.size(); ++x) {
                    for (int y = 0; y < this.wards.get(x).rooms.size(); ++y) {
                        for (int z = 0; z < this.wards.get(x).rooms.get(y).Beds.size(); ++z) {
                            System.out.println(String.valueOf(x) + String.valueOf(y) + String.valueOf(z) + " Bed:" + this.wards.get(x).rooms.get(y).Beds.get(z).ID + " Patient:" + (this.wards.get(x).rooms.get(y).Beds.get(z).patient == null ? "No patient" : this.wards.get(x).rooms.get(y).Beds.get(z).patient.name));
                        }
                    }
                }
                String choice_str = this.scanner.nextLine();
                System.out.println("Choice bed:");
                choice_str = this.scanner.nextLine();
                System.out.println("Input medicineName:");
                String medicineName = this.scanner.nextLine();
                System.out.println("Input doseAmt:");
                int doseAmt = this.scanner.nextInt();
                String doseUnit = this.scanner.nextLine();
                System.out.println("Input doseUnit:");
                doseUnit = this.scanner.nextLine();
                doctor1.attachPrescription(this.wards.get(Integer.parseInt(choice_str.split("")[0])).rooms.get(Integer.parseInt(choice_str.split("")[1])).Beds.get(Integer.parseInt(choice_str.split("")[2])), medicineName, doseAmt, doseUnit);
                break;
            case 3:
                for (int x = 0; x < this.wards.size(); ++x) {
                    for (int y = 0; y < this.wards.get(x).rooms.size(); ++y) {
                        for (int z = 0; z < this.wards.get(x).rooms.get(y).Beds.size(); ++z) {
                            System.out.println(String.valueOf(x) + String.valueOf(y) + String.valueOf(z) + " Bed:" + this.wards.get(x).rooms.get(y).Beds.get(z).ID + " Patient:" + (this.wards.get(x).rooms.get(y).Beds.get(z).patient == null ? "No patient" : this.wards.get(x).rooms.get(y).Beds.get(z).patient.name));
                        }
                    }
                }
//                choice_str = this.scanner.nextLine();
//                System.out.println("Input medicineName:");
                choice_str = this.scanner.nextLine();
                System.out.println("choice bed:");
                choice_str = this.scanner.nextLine();
                Bed bed = this.wards.get(Integer.parseInt(choice_str.split("")[0])).rooms.get(Integer.parseInt(choice_str.split("")[1])).Beds.get(Integer.parseInt(choice_str.split("")[2]));
                i = 0;
                for (Medicine medicine : bed.prescription.medicines) {
                    System.out.println(i + " " + medicine.name);
                    ++i;
                }
                choice = this.scanner.nextInt();
                doctor1.removePrescription(bed, bed.prescription.medicines.get(choice).name);
                break;
            case 4:
                for (int x = 0; x < this.wards.size(); ++x) {
                    for (int y = 0; y < this.wards.get(x).rooms.size(); ++y) {
                        for (int z = 0; z < this.wards.get(x).rooms.get(y).Beds.size(); ++z) {
                            System.out.println(String.valueOf(x) + String.valueOf(y) + String.valueOf(z) + " Bed:" + this.wards.get(x).rooms.get(y).Beds.get(z).ID + " Patient:" + (this.wards.get(x).rooms.get(y).Beds.get(z).patient == null ? "No patient" : this.wards.get(x).rooms.get(y).Beds.get(z).patient.name));
                        }
                    }
                }
                choice_str = this.scanner.nextLine();
                System.out.print("choice Bed:");
                choice_str = this.scanner.nextLine();
                int x = Integer.parseInt(choice_str.split("")[0]);
                int y = Integer.parseInt(choice_str.split("")[1]);
                int z = Integer.parseInt(choice_str.split("")[2]);

                i = 0;
                bed = this.wards.get(x).rooms.get(y).Beds.get(z);
                if (bed.prescription.medicines.size() == 0)
                    break;
                else {
                    for (Medicine medicine : bed.prescription.medicines) {
                        System.out.println(i + " medicineName:" + medicine.name + " doseAmt:" + medicine.doseAmt + " doseUnit:" + medicine.doseUnit);
                    }
                    System.out.print("choice medicine:");
                    choice = this.scanner.nextInt();
                    choice_str = this.scanner.nextLine();
                    System.out.print("Input doseAmt:");
                    doseAmt = this.scanner.nextInt();
                    choice_str = this.scanner.nextLine();
                    System.out.print("Input doseUnit:");
                    doseUnit = this.scanner.nextLine();
                    doctor1.updatePrescription(bed, bed.prescription.medicines.get(choice).name, doseAmt, doseUnit);
                }
                break;
        }
    }

    public void nurse(Nurse nurse1) {
        System.out.println("Choice Nurse Method:");
        System.out.println("1.change patient to another bed");
        System.out.println("2.add patient to bed");
        System.out.println("3.CheckDetail");
        System.out.println("which method:");
        int choice = this.scanner.nextInt();
        switch (choice) {
            case 1:
                int i = 0;
                for (Patient patient : this.patients) {
                    System.out.println(i + " " + patient.name);
                }
                System.out.print("choice patient:");
                choice = this.scanner.nextInt();
                String choice_str = this.scanner.nextLine();
                Bed srcbed = null;
                for (Ward ward : this.wards) {
                    for (Room room : ward.rooms)
                        for (Bed bed : room.Beds)
                            if (bed.patient != null)
                                if (bed.patient.name.equals(this.patients.get(choice).name))
                                    srcbed = bed;
                }

                for (int x = 0; x < this.wards.size(); ++x) {
                    for (int y = 0; y < this.wards.get(x).rooms.size(); ++y) {
                        for (int z = 0; z < this.wards.get(x).rooms.get(y).Beds.size(); ++z) {
                            System.out.println(String.valueOf(x) + String.valueOf(y) + String.valueOf(z) + " Bed:" + this.wards.get(x).rooms.get(y).Beds.get(z).ID + " Patient:" + (this.wards.get(x).rooms.get(y).Beds.get(z).patient == null ? "No patient" : this.wards.get(x).rooms.get(y).Beds.get(z).patient.name));
                        }
                    }
                }
                System.out.print("choice Bed:");
                choice_str = this.scanner.nextLine();
                int x = Integer.parseInt(choice_str.split("")[0]);
                int y = Integer.parseInt(choice_str.split("")[1]);
                int z = Integer.parseInt(choice_str.split("")[2]);
                nurse1.ChangeBed(this.patients.get(choice), srcbed, this.wards.get(x).rooms.get(y).Beds.get(z));
                break;
            case 2:
                i = 0;
                for (Patient patient : this.patients) {
                    System.out.println(i + " " + patient.ID + " " + patient.name);
                }
                System.out.println("choice patient:");
                choice = this.scanner.nextInt();

                choice_str = this.scanner.nextLine();
                for (x = 0; x < this.wards.size(); ++x) {
                    for (y = 0; y < this.wards.get(x).rooms.size(); ++y) {
                        for (z = 0; z < this.wards.get(x).rooms.get(y).Beds.size(); ++z) {
//                            System.out.println(x + y + z + " Bed:" + this.wards.get(x).rooms.get(y).Beds.get(z).ID + " Patient:" + this.wards.get(x).rooms.get(y).Beds.get(z).patient == null ? "" :this.wards.get(x).rooms.get(y).Beds.get(z).patient.name);
                            System.out.println(String.valueOf(x) + String.valueOf(y) + String.valueOf(z) + " Bed:" + this.wards.get(x).rooms.get(y).Beds.get(z).ID + " Patient:" + (this.wards.get(x).rooms.get(y).Beds.get(z).patient == null ? "No patient" : this.wards.get(x).rooms.get(y).Beds.get(z).patient.name));
                        }
                    }
                }
                System.out.print("choice Bed:");
                choice_str = this.scanner.nextLine();
                x = Integer.parseInt(choice_str.split("")[0]);
                y = Integer.parseInt(choice_str.split("")[1]);
                z = Integer.parseInt(choice_str.split("")[2]);

                Bed bed = this.wards.get(x).rooms.get(y).Beds.get(z);
                nurse1.AddToBed(this.patients.get(choice), bed);
                break;
            case 3:
                i = 0;
                if (this.patients.size() == 0)
                    break;
                for (Patient patient : this.patients) {
                    System.out.println(i + " " + patient.ID + " " + patient.name);
                }
                System.out.print("Choice Patient:");
                choice = this.scanner.nextInt();
                nurse1.CheckDetail(this.wards, this.patients.get(choice).ID);
                break;
        }
    }

    public static void main(String[] args) throws SQLException {
        JdbcConnect connect = new JdbcConnect();
        connect.connect();
        AbleCareHome ableCareHome = new AbleCareHome();
//        Manager manager = new Manager(3001, "test", "1111");
//        ableCareHome.managers.add(manager);
//        Nurse nurse1 = new Nurse(1001, "nurse1", "1111");
//        ableCareHome.nurses.add(nurse1);
//        Doctor doctor1 = new Doctor(2001, "doctor1", "1111");
//        ableCareHome.doctors.add(doctor1);
        int choice = 0;
        boolean flag = true;
//        ableCareHome.managers.get(0).allocateShift(ableCareHome.doctors.get(0),2021,4,5,8,30,16,30);
        Staff user;
        while (flag) {
            System.out.println("============================Able Care Home============================");
            System.out.println("choice User to Login:");
            System.out.println("1.Manager");
            System.out.println("2.Doctor");
            System.out.println("3.Nurse");
            System.out.println("4.Quit");
            choice = ableCareHome.scanner.nextInt();
            String username = ableCareHome.scanner.nextLine();
            System.out.print("Input user name:");
            username = ableCareHome.scanner.nextLine();
            System.out.print("Input password:");
            String password = ableCareHome.scanner.nextLine();
            switch (choice) {
                case 1:
                    Manager manager1 = (Manager) ableCareHome.Login("Manager", username, password);
                    if (manager1 != null)
                        ableCareHome.manager(manager1);
                    else
                        System.out.println("Login Failed");
                    break;
                case 2:
                    Doctor doctor = (Doctor) ableCareHome.Login("Doctor", username, password);
                    if (doctor != null) {
                        ableCareHome.doctor(doctor);
                    } else
                        System.out.println("Login Failed");
                    break;
                case 3:
                    Nurse nurse = (Nurse) ableCareHome.Login("Nurse", username, password);
                    if (nurse != null) {
                        ableCareHome.nurse(nurse);
                    } else
                        System.out.println("Login Failed");
                    break;
                case 4:
                    flag = false;
                    break;
            }
        }
    }
}
