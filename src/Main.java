import ipScanner.*;
import gui.pointRenderer;

public class Main {
    public static void main(String[] args) {
        //pointRenderer.main();
        System.out.println("kaler v1.0 by eetnaviation");
        IpScanner.main(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), false);
    }
}