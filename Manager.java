import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Manager extends Staff{

    Manager(String username,String password){
        this.username = username;
        this.password = password;
    }

    public void addNurse(ArrayList<Nurse> nurses,Nurse nurse){
        try {
            if (this.isAuthoized){
                nurses.add(nurse);
                Calendar cal = Calendar.getInstance();
                LogAction("add Nurse ID:" + nurse.ID + " Username:"+username,cal);
            }
            else
                throw new AccessDeniedException();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void addDoctor(ArrayList<Doctor> doctors,Doctor doctor){
        try {
            if (this.isAuthoized){
                doctors.add(doctor);
                Calendar cal = Calendar.getInstance();
                LogAction("add Doctor ID:" + doctor.ID + " Username:"+username,cal);
            }
            else
                throw new AccessDeniedException();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public void changeManagerPassword(ArrayList<Manager> managers,String username,String oldPassword,String newPassword){
        try {
            if(this.isAuthoized) {
                for (Manager manager : managers){
                    if (manager.username.equals(username) && manager.password.equals(oldPassword)) {
                        manager.password = newPassword;
                        Calendar cal = Calendar.getInstance();
                        LogAction("change Manager Password ID:"+manager.ID+" username:"+username,cal);
                    }
                }
            }
            else
                throw new AccessDeniedException();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void changeNursePassword(ArrayList<Nurse> nurses,String username,String oldPassword,String newPassword){
        try {
            if(this.isAuthoized) {
                for (Nurse nurse : nurses){
                    if (nurse.username.equals(username) && nurse.password.equals(oldPassword)) {
                        nurse.password = newPassword;
                        Calendar cal = Calendar.getInstance();
                        LogAction("change Nurse Password ID:"+nurse.ID+" username:"+username,cal);
                    }
                }
            }
            else
                throw new AccessDeniedException();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void changeDoctorPassword(ArrayList<Doctor> doctors,String username,String oldPassword,String newPassword){
        try {
            if(this.isAuthoized) {
                for (Doctor doctor : doctors){
                    if (doctor.username.equals(username) && doctor.password.equals(oldPassword)) {
                        doctor.password = newPassword;
                        Calendar cal = Calendar.getInstance();
                        LogAction("change Doctor Password ID:"+doctor.ID+" username:"+username,cal);
                    }
                }
            }
            else
                throw new AccessDeniedException();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void allocateShift(MedicalWorkers worker,int Year,int Month,int Day,int startHour,int startMin,int endHour,int endMin){
        try {
            if (!this.isAuthoized)
                throw new AccessDeniedException();
            ShiftDay shiftDay = new ShiftDay();
            shiftDay.day.set(Year, Month, Day);
            shiftDay.startTime.set(0, 0, 0, startHour, startMin);
            shiftDay.endTime.set(0, 0, 0, endHour, endMin);
            worker.shiftDay.add(shiftDay);
            if(!worker.CheckCompliance())
                worker.shiftDay.remove(shiftDay);
            Calendar cal = Calendar.getInstance();
            LogAction("add Shift for worker:"+worker.ID+" shift:"+Year+"/"+Month+"/"+Day+" "+startHour+":"+startMin + "  --->  "+endHour+":"+endMin,cal);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void CheckCompliance(MedicalWorkers worker) {
        try {
            if (worker.shiftDay.size() > 14)
                throw new UnreasonableShiftException();
            Map<Calendar,Float> ShiftHours = new HashMap<Calendar,Float>();
            float hour = 0.0f;
            for (ShiftDay tempShiftDay : worker.shiftDay){
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
                if (entry.getValue() > 8)
                    throw new UnreasonableShiftException();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void updateShift(MedicalWorkers worker,int oldYear, int oldMonth,int oldDay,int oldStartHour,int oldStartMin,int oldEndHour,int oldEndMin,int Year,int Month,int Day,int startHour,int startMin,int endHour,int endMin){
        try {
            if (!this.isAuthoized)
                throw new AccessDeniedException();
            outer:
            for (ShiftDay tempShiftDay : worker.shiftDay){
                if (tempShiftDay.day.get(Calendar.YEAR) == oldYear)
                    if(tempShiftDay.day.get(Calendar.MONTH) == oldMonth)
                        if(tempShiftDay.day.get(Calendar.DAY_OF_MONTH) == oldDay)
                            if(tempShiftDay.startTime.get(Calendar.HOUR_OF_DAY) == oldStartHour)
                                if(tempShiftDay.startTime.get(Calendar.MINUTE) == oldStartMin)
                                    if(tempShiftDay.endTime.get(Calendar.HOUR_OF_DAY) == oldEndHour)
                                        if(tempShiftDay.endTime.get(Calendar.MINUTE) == oldEndMin) {
                                            tempShiftDay.day.set(Year,Month,Day);
                                            tempShiftDay.startTime.set(0,0,0,startHour,startMin);
                                            tempShiftDay.endTime.set(0,0,0,endHour,endMin);
                                            break outer;
                                        }
            }
            Calendar cal = Calendar.getInstance();
            LogAction("update Shift for worker:"+worker.ID+" shift:"+Year+"/"+Month+"/"+Day+" "+startHour+":"+startMin + "  --->  "+endHour+":"+endMin,cal);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
