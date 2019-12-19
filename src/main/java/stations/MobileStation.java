package stations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MobileStation {
    //MS POJO
    UUID id;
    float lastKownX, lastKownY;
    List<DetectedByBaseStation> detectedBy = new ArrayList<>();

    public MobileStation (float x, float y){
        id = UUID.randomUUID();
        this.lastKownX = x;
        this.lastKownY = y;
    }

    public UUID getID(){
        return id;
    }
    public float getlastKownX(){
        return lastKownX;
    }
    public float getlastKownY(){
        return lastKownY;
    }
    public List<DetectedByBaseStation> getDetectedBy() {
        return detectedBy;
    }
}
