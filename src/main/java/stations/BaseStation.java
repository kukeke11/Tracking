package stations;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

public class BaseStation {
    //BS POJO
    UUID id;
    float x, y;
    float detectionRadiusInMeters;
    List<DetectedMobileStation> msDetected = new ArrayList<>();


    public BaseStation (float x, float y, float dRIM){
        id = UUID.randomUUID();
        this.x = x;
        this.y = y;
        this.detectionRadiusInMeters = dRIM;
    }

    public UUID getID(){
        return id;
    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }

    public List<DetectedMobileStation> getMsDetected() {
        return msDetected;
    }

    public void setDetectionRadiusInMeters(float dRIM){
        this.detectionRadiusInMeters = dRIM;
    }

    public void setID(UUID id){
        this.id = id;
    }
    public void setX(float x){
        this.x = x;
    }
    public void setY(float y){
        this.y = y;
    }


    public float getDetectionRadiusInMeters(){
        return detectionRadiusInMeters;
    }
}
