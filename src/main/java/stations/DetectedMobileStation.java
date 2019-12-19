package stations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.sql.Timestamp;

public class DetectedMobileStation {
    //DMS POJO
    UUID id;
    float lastKnownX, lastKnownY;
    double dis;
    Timestamp timeStamp;
    boolean triangulated;
    List<UUID> detectedBy = new ArrayList<>();

    public DetectedMobileStation(UUID id, Timestamp timeStamp, double dis, boolean triangulated){
        this.id = id;
        this.timeStamp = timeStamp;
        this.dis = dis;
        this.triangulated = triangulated;
    }


    public UUID getID(){
        return id;
    }
    public float getLastKnownX(){
        return lastKnownX;
    }
    public float getLastKnownY(){
        return lastKnownY;
    }
    public List<UUID> getDetectedBy(){
        return detectedBy;
    }
    public Timestamp getTimeStamp(){
        return timeStamp;
    }

    public boolean getTriangulated(){
        return triangulated;
    }

    public double getDis(){
        return dis;
    }

    public void setID(UUID id){
        this.id = id;
    }
    public void setLastKnownX(float x){
        this.lastKnownX = x;
    }
    public void setLastKnownY(float y){
        this.lastKnownY = y;
    }
    public void setTimeStamp(Timestamp timeStamp){
        this.timeStamp = timeStamp;
    }

    public void setTriangulated(boolean triangulated){
        this.triangulated = triangulated;
    }

    public void setDis(double dis){
        this.dis = dis;
    }
}
