package stations;

import java.util.UUID;

public class DetectedByBaseStation {
    UUID id;
    float x, y;
    double dis;


    public DetectedByBaseStation (UUID id, float x, float y, double dis){
        this.id = id;
        this.x = x;
        this.y = y;
        this.dis = dis;
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
    public double getDis(){
        return dis;
    }
}
