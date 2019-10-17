import java.util.*;
import java.io.*;

public class Port
{

    private String portNum;
    private List<String> history = new ArrayList();
    private String connectedAddr;

    Port(String port_num){
        portNum = port_num;
    }


    void addToHistory(String srcAddr)
    {
        history.add(srcAddr);
    }

    void attachAddrToPort(String src_addr)
    {
        connectedAddr = src_addr;
    }

    String getConnectedAddr()
    {
        return connectedAddr;
    }

    String getPortNum()
    {
        return portNum;
    }

    void showPortHistory()
    {
        System.out.print(portNum+": ");
        for(int i = 0; i<history.size();i++)
        {
            System.out.print(history.get(i)+" ");
        }
        System.out.println();
    }

    String writeLineToFile()
    {
        String fileLine = portNum +":";
        for(int i = 0; i < history.size();i++)
        {
            fileLine += " ";
            fileLine += history.get(i);
        }

       return fileLine;
    }


    void showPortNum()
    {
        System.out.println(portNum);
    }


}
