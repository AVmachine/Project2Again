import java.util.*;
import java.io.*;

public class Frame
{
    private String frameID;
    private String arrivalPort;
    private String srcAddr;
    private String destAddr;

    Frame(String[] arr)
    {
        frameID = arr[0];
        arrivalPort = arr[1];
        String[] travelArr = arr[2].split("--");
        srcAddr = travelArr[0];
        destAddr = travelArr[1];

    }

    void showFrameDetails(){
        System.out.println("Frame ID: " + frameID + " | Arrival Port: " + arrivalPort + " | Src Addr: " + srcAddr + " | Dest Addr: " + destAddr);
    }

    String getFrameID()
    {
        return frameID;
    }

    String getArrivalPort()
    {
        return arrivalPort;
    }

    String getSrcAddr()
    {
        return srcAddr;
    }

    String getDestAddr()
    {
        return destAddr;
    }

}
