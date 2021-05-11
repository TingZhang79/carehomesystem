import java.util.ArrayList;
import java.util.Calendar;

public class Manager extends Staff {

    Manager(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void addNurse(ArrayList<Nurse> nurses, Nurse nurse) {
        try {
            if (this.isAuthoized) {
                nurses.add(nurse);
                Calendar cal = Calendar.getInstance();
                LogAction("add Nurse ID:" + nurse.ID + " Username:" + username, cal);
            } else
                throw new AccessDeniedException();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addDoctor(ArrayList<Doctor> doctors, Doctor doctor) {
        try {
            if (this.isAuthoized) {
                doctors.add(doctor);
                Calendar cal = Calendar.getInstance();
                LogAction("add Doctor ID:" + doctor.ID + " Username:" + username, cal);
            } else
                throw new AccessDeniedException();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void changeManagerPassword(ArrayList<Manager> managers, String username, String oldPassword, String newPassword) {
        try {
            if (this.isAuthoized) {
                for (Manager manager : managers) {
                    if (manager.username.equals(username) && manager.password.equals(oldPassword)) {
                        manager.password = newPassword;
                        Calendar cal = Calendar.getInstance();
                        LogAction("change Manager Password ID:" + manager.ID + " username:" + username, cal);
                    }
                }
            } else
                throw new AccessDeniedException();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void changeNursePassword(ArrayList<Nurse> nurses, String username, String oldPassword, String newPassword) {
        try {
            if (this.isAuthoized) {
                for (Nurse nurse : nurses) {
                    if (nurse.username.equals(username) && nurse.password.equals(oldPassword)) {
                        nurse.password = newPassword;
                        Calendar cal = Calendar.getInstance();
                        LogAction("change Nurse Password ID:" + nurse.ID + " username:" + username, cal);
                    }
                }
            } else
                throw new AccessDeniedException();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void changeDoctorPassword(ArrayList<Doctor> doctors, String username, String oldPassword, String newPassword) {
        try {
            if (this.isAuthoized) {
                for (Doctor doctor : doctors) {
                    if (doctor.username.equals(username) && doctor.password.equals(oldPassword)) {
                        doctor.password = newPassword;
                        Calendar cal = Calendar.getInstance();
                        LogAction("change Doctor Password ID:" + doctor.ID + " username:" + username, cal);
                    }
                }
            } else
                throw new AccessDeniedException();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void allocateShift(MedicalWorkers worker, int Year, int Month, int Day, int startHour, int startMin, int endHour, int endMin) throws AccessDeniedException, UnreasonableShiftException {
        if (!this.isAuthoized)
            throw new AccessDeniedException();
        ShiftDay shiftDay = new ShiftDay();
        shiftDay.day.set(Year, Month, Day);
        shiftDay.startTime.set(0, 0, 0, startHour, startMin);
        shiftDay.endTime.set(0, 0, 0, endHour, endMin);
        worker.shiftDay.add(shiftDay);
        if (!worker.CheckCompliance()) {
            worker.shiftDay.remove(shiftDay);
            throw new UnreasonableShiftException();
        } else {
            Calendar cal = Calendar.getInstance();
            LogAction("add Shift for worker:" + worker.ID + " shift:" + Year + "/" + Month + "/" + Day + " " + startHour + ":" + startMin + "  --->  " + endHour + ":" + endMin, cal);
        }
    }

    public void deleteShift(MedicalWorkers worker, int Year, int Month, int Day, int startHour, int startMin, int endHour, int endMin) throws AccessDeniedException{
        if (!this.isAuthoized)
            throw new AccessDeniedException();
        outer:
        for (ShiftDay tempShiftDay : worker.shiftDay){
            if (tempShiftDay.day.get(Calendar.YEAR) == Year)
                if(tempShiftDay.day.get(Calendar.MONTH) == Month)
                    if (tempShiftDay.day.get(Calendar.DAY_OF_MONTH) == Day)
                        if (tempShiftDay.startTime.get(Calendar.HOUR_OF_DAY) == startHour)
                            if (tempShiftDay.startTime.get(Calendar.MINUTE) == startMin)
                                if (tempShiftDay.endTime.get(Calendar.HOUR_OF_DAY) == endHour)
                                    if (tempShiftDay.endTime.get(Calendar.MINUTE) == endMin) {
                                        worker.shiftDay.remove(tempShiftDay);
                                        break outer;
                                    }
        }
        Calendar cal = Calendar.getInstance();
        LogAction("delete Shift from worker:" + worker.ID + " shift:" + Year + "/" + Month + "/" + Day + " " + startHour + ":" + startMin + "  --->  " + endHour + ":" + endMin, cal);

    }

    public void updateShift(MedicalWorkers worker, int oldYear, int oldMonth, int oldDay, int oldStartHour, int oldStartMin, int oldEndHour, int oldEndMin, int Year, int Month, int Day, int startHour, int startMin, int endHour, int endMin) throws AccessDeniedException,UnreasonableShiftException{
        ShiftDay day = null;
        if (!this.isAuthoized)
            throw new AccessDeniedException();
        outer:
        for (ShiftDay tempShiftDay : worker.shiftDay) {
            if (tempShiftDay.day.get(Calendar.YEAR) == oldYear)
                if (tempShiftDay.day.get(Calendar.MONTH) == oldMonth)
                    if (tempShiftDay.day.get(Calendar.DAY_OF_MONTH) == oldDay)
                        if (tempShiftDay.startTime.get(Calendar.HOUR_OF_DAY) == oldStartHour)
                            if (tempShiftDay.startTime.get(Calendar.MINUTE) == oldStartMin)
                                if (tempShiftDay.endTime.get(Calendar.HOUR_OF_DAY) == oldEndHour)
                                    if (tempShiftDay.endTime.get(Calendar.MINUTE) == oldEndMin) {
                                        day = tempShiftDay;
                                        break outer;
                                    }
        }
        if(!worker.CheckCompliance()){
            throw new UnreasonableShiftException();
        }
        if (day!=null) {
            day.day.set(Year, Month, Day);
            day.startTime.set(0, 0, 0, startHour, startMin);
            day.endTime.set(0, 0, 0, endHour, endMin);
            Calendar cal = Calendar.getInstance();
            LogAction("update Shift for worker:" + worker.ID + " shift:" + Year + "/" + Month + "/" + Day + " " + startHour + ":" + startMin + "  --->  " + endHour + ":" + endMin, cal);
        }
    }
}
