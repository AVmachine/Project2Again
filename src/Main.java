import java.io.*;

public class Main {

    public static void main(String[] args) {

        Switch mySwitch = new Switch();

        mySwitch.readFrames();

        mySwitch.printPortHistory();

        mySwitch.writeToFile();


    }
}
