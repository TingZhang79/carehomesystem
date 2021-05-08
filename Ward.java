import java.util.ArrayList;

public class Ward {
    ArrayList<Room> rooms;

    Ward(){
        this.rooms = new ArrayList<>();
    }
    public void addRoom(Room room){
        this.rooms.add(room);
    }
}
