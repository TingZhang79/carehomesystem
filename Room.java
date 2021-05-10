import java.util.ArrayList;

public class Room {
    ArrayList<Bed> Beds;
    Room(){
        Beds = new ArrayList<>();
    }
    public void addBed(Bed bed){
        this.Beds.add(bed);
    }
}
