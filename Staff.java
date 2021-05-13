import java.io.FileWriter;
import java.util.Calendar;

public abstract class Staff implements StaffFunctions{
    int ID;
    protected String username;
    protected String password;
    boolean isAuthoized = false;

    public void LogAction(String actionString,Calendar time){

        try{
            if (time == null)
                throw new MissingDayTimeException();
            FileWriter fileWriter = new FileWriter("action_log.txt",true);
            fileWriter.write("[" + time.get(Calendar.YEAR) + "/" + time.get(Calendar.MONTH) + "/" + time.get(Calendar.DAY_OF_MONTH) + " " + time.get(Calendar.HOUR_OF_DAY) + ":" + time.get(Calendar.MINUTE) + ":" + time.get(Calendar.SECOND) + "]" + "\t" + ID + " " + actionString+"\n");
            StageManager.connect.updateData("insert into Log values('"+time.get(Calendar.YEAR)+"-"+time.get(Calendar.MONTH)+"-"+time.get(Calendar.DAY_OF_MONTH)+" "+time.get(Calendar.HOUR_OF_DAY)+":"+time.get(Calendar.MINUTE)+"','"+actionString+"')");
            System.out.println("[+] Action Log Success");
            fileWriter.close();
        }catch (Exception e){
            System.out.println("[-] Action Log Failed");
            System.out.println(e.getMessage());
        }
    }

    public boolean Authentication(String username,String password) {
        if (this.username.equals(username) && this.password.equals(password)){
            this.isAuthoized = true;
        }
        else{
            this.isAuthoized = false;
        }
        return this.isAuthoized;
    }
}
