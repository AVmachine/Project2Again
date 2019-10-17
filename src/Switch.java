import java.io.*;
import java.util.*;



public class Switch {

    private Port P1 = new Port("P1");
    private Port P2 = new Port("P2");
    private Port P3 = new Port("P3");
    private Port P4 = new Port("P4");
    private Port P5 = new Port("P5");
    private Port P6 = new Port("P6");
    private Port P7 = new Port("P7");
    private Port P8 = new Port("P8");

    private List<Frame> frameCollection = new ArrayList();
    private List<String> srcList = new ArrayList();
    private List<Port> portList = new ArrayList()
    {
        {
            add(P1);
            add(P2);
            add(P3);
            add(P4);
            add(P5);
            add(P6);
            add(P7);
            add(P8);
        }
    };



    Switch()
    {

    }

    void readFrames()
    {
        String directory = System.getProperty("user.dir");      //Used to get path inside the project folder
        String fileName = "src/in.txt";                         //gets inside src folder where in.txt is

        String inPath = directory + File.separator + fileName;

        System.out.println(directory);

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(inPath))) {
            String line = bufferedReader.readLine();
            int j = 0;
            while(line != null) {
                if(line.isBlank() == false) {
                    String[] singleFrameArr = line.split("\\s+");
                    frameCollection.add(new Frame(singleFrameArr));
                    addSrcToList(j);
                    checkForDestAddr(j);
                    j++;
                }
                line = bufferedReader.readLine();

            }
        } catch (FileNotFoundException e) {
            System.out.println("Exception at FileNotFound");
        } catch (IOException e) {
            System.out.println("Exception at IO");
        }
    }

    void addSrcToList(int j)
    {
            String src_addr = frameCollection.get(j).getSrcAddr();
            String port_num = frameCollection.get(j).getArrivalPort();
            srcList.add(src_addr);
            switch(port_num)
            {
                case "P1":
                    P1.attachAddrToPort(src_addr);
                    break;
                case "P2":
                    P2.attachAddrToPort(src_addr);
                    break;
                case "P3":
                    P3.attachAddrToPort(src_addr);
                    break;
                case "P4":
                    P4.attachAddrToPort(src_addr);
                    break;
                case "P5":
                    P5.attachAddrToPort(src_addr);
                    break;
                case "P6":
                    P6.attachAddrToPort(src_addr);
                    break;
                case "P7":
                    P7.attachAddrToPort(src_addr);
                    break;
                case "P8":
                    P8.attachAddrToPort(src_addr);
                    break;
            }

    }

    void showSrcList()
    {
        for( int i = 0; i < srcList.size(); i++)
        {
            System.out.print(srcList.get(i)+" ");
        }
    }

    void checkForDestAddr(int j)
    {
        String dest_addr = frameCollection.get(j).getDestAddr();
        String on_port = frameCollection.get(j).getArrivalPort();
        boolean found = false;
        for(int i = 0; i<portList.size();i++)
        {
            if(portList.get(i).getConnectedAddr() != null &&portList.get(i).getConnectedAddr().equals(dest_addr)) {
                String found_port = portList.get(i).getPortNum();
                sendFrameToDest(j, found_port);
                found = true;
                break;
            }
        }
        if(found == false && !(dest_addr.equals("X"))) // &&
        {
            broadcastToAllOthers(on_port, j);
        }
        else if(dest_addr.equals("X"))
        {
            broadcastToAll(j);
        }


    }

    void broadcastToAllOthers(String on_port, int j)
    {
        String frame_id = frameCollection.get(j).getFrameID();
        for(int i = 0; i<portList.size(); i++)
        {
            if(portList.get(i).getPortNum() != null && on_port.equals(portList.get(i).getPortNum()))
            {
                continue;
            }
            else
            {
                portList.get(i).addToHistory(frame_id);
            }
        }

    }

    void broadcastToAll(int j)
    {
        String frame_id = frameCollection.get(j).getFrameID();
        for(int i = 0; i<portList.size(); i++)
        {
                portList.get(i).addToHistory(frame_id);

        }

    }

    void sendFrameToDest(int j, String port_num)
    {
        String frame_id =frameCollection.get(j).getFrameID();
        for(int i=0;i<portList.size();i++)
        {
            if(portList.get(i).getPortNum()!= null && port_num.equals(portList.get(i).getPortNum()))
            {
                portList.get(i).addToHistory(frame_id);
            }
        }


    }


    void printPortHistory()
    {
        for(int i=0; i < portList.size(); i++)
        {
           portList.get(i).showPortHistory();
        }
    }

    void writePortHistory()
    {
        for(int i=0; i < portList.size(); i++)
        {
            portList.get(i).showPortHistory();
        }
    }

    void showPortNames()
    {
        P1.showPortNum();
        P2.showPortNum();
        P3.showPortNum();
        P4.showPortNum();
        P5.showPortNum();
        P6.showPortNum();
        P7.showPortNum();
        P8.showPortNum();
    }

    void showAllFrames()
    {
        for( int i = 0; i < frameCollection.size(); i++)
        {
            frameCollection.get(i).showFrameDetails();
        }
    }

    void writeToFile()
    {


        String directory = System.getProperty("user.dir");
        String fileName = "src/out.txt";
        String outPath = directory + File.separator + fileName;

        File file = new File(outPath);
        try {
            file = new File("out.txt");
            if (file.createNewFile()) {
                System.out.println("txt File Created in Project root directory");
            } else System.out.println("txt already exists in the project root directory");
        }
        catch(IOException e)
        {
            System.out.println("IOException in file creation");
        }


        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outPath))) {

            for(int i=0; i< portList.size(); i++ ) {
                String portLine = portList.get(i).writeLineToFile();
                bufferedWriter.write(portLine);
                bufferedWriter .write("\n");
            }


        } catch (IOException e) {
            System.out.println("IO Exception trying to write");
        }
    }

}
