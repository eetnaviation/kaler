import ipScanner.*;
import gui.pointRenderer;

public class Main {
    public static void main(String[] args) {
        //pointRenderer.main();
        IpScanner.main("192.168.1.0", 255, 1500, true);
    }
}