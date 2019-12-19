package service;

import stations.BaseStation;
import stations.DetectedMobileStation;
import stations.MobileStation;
import com.example.demo.SetUp;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.UUID;


public class Calculate {

    public double radiusDetection(BaseStation bs,
                                  MobileStation ms){
        double dis;
        //Let us imagine that this calculates distance based on signal.
        dis = Math.sqrt((bs.getX() - ms.getlastKownX())*(bs.getX() - ms.getlastKownX()) + (bs.getY()-ms.getlastKownY())*(bs.getY()-ms.getlastKownY()));
        return dis;
    }

    public void triangulatePosition(UUID msID, float point1X, float point2X, float point3X, float point1Y,
                                    float point2Y, float point3Y, double dist1, double dist2, double dist3, List<BaseStation> baseStations){

        float S, T, D, xnum, xdem, ynum, ydem, y, x;
        System.out.println("Calculating");

        S = (float) (Math.pow(point1X, 2.0) + Math.pow(point1Y, 2.0) - Math.pow(dist1, 2.0));
        T = (float) (Math.pow(point2X, 2.0) + Math.pow(point2Y, 2.0) - Math.pow(dist2, 2.0));
        D = (float) (Math.pow(point3X, 2.0) + Math.pow(point3Y, 2.0) - Math.pow(dist3, 2.0));

        xnum = (S*(point3Y-point2Y) + T*(point1Y-point3Y)+D*(point2Y-point1Y));
        xdem = (2*(point1X*(point3Y-point2Y)+point2X*(point1Y-point3Y)+point3X*(point2Y-point1Y)));

        ynum = (S*(point3X-point2X) + T*(point1X-point3X)+D*(point2X-point1X));
        ydem = (2*(point1Y*(point3X-point2X)+point2Y*(point1X-point3X)+point3Y*(point2X-point1X)));

        x = xnum/xdem;
        y = ynum/ydem;
        System.out.println( "got X" + x + "got Y" + y);

        for (BaseStation bs: baseStations
             ) {
            for (DetectedMobileStation dMS: bs.getMsDetected()) {
                if(dMS.getID() == msID){
                    dMS.setLastKnownX(x);
                    dMS.setLastKnownY(y);
                    dMS.setTriangulated(true);
                }
            }
        }
        System.out.println( "Mobile Station ID: " + msID);
        System.out.println( "Location detected, X: " + x + " Y: "+y);
    }
}
