package ipScanner;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.*;

public class IpScanner {

    public static void main(String firstIp, Integer maxIps, Integer maxPort, Boolean debugArray) {
        ConcurrentSkipListSet networkIps = scan(firstIp, maxIps, debugArray);
        System.out.println("All devices detected! Starting port scan.");
        for (Object i : networkIps) {
            String ip = String.valueOf(i);
            try {
                runPortScan(ip, maxPort);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Port scan completed.");
    }

    /**
     * @param firstIpInTheNetwork e.g: 192.168.1.0
     * @param numOfIps            e.g: 254
     * @return
     */
    public static ConcurrentSkipListSet scan(String firstIpInTheNetwork, int numOfIps, Boolean arrayValue) {
        ArrayList<String> ar = new ArrayList<String>();
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        final String networkId = firstIpInTheNetwork.substring(0, firstIpInTheNetwork.length() - 1);
        ConcurrentSkipListSet ipsSet = new ConcurrentSkipListSet();

        AtomicInteger ips = new AtomicInteger(0);
        while (ips.get() <= numOfIps) {
            String ip = networkId + ips.getAndIncrement();
            executorService.submit(() -> {
                try {
                    InetAddress inAddress = InetAddress.getByName(ip);
                    if (inAddress.isReachable(500)) {
                        System.out.println("found ip: " + ip);
                        ipsSet.add(ip);
                        ar.add(ip);
                    }
                } catch (IOException e) {

                }
            });
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        if (arrayValue == true) { System.out.println(ar); };
        return ipsSet;
    }

    public static void runPortScan(String ip, int nbrPortMaxToScan) throws IOException {
        ConcurrentLinkedQueue openPorts = new ConcurrentLinkedQueue<>();
        int poolSize = 1;
        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        AtomicInteger port = new AtomicInteger(0);
        while (port.get() < nbrPortMaxToScan) {
            final int currentPort = port.getAndIncrement();
            executorService.submit(() -> {
                try {
                    Socket socket = new Socket();
                    int timeOut = 30;
                    socket.connect(new InetSocketAddress(ip, currentPort), timeOut);
                    socket.close();
                    openPorts.add(currentPort);
                    System.out.println(ip + " ,port open: " + currentPort);
                } catch (IOException e) {
                    //System.in.println(e);
                }
            });
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List openPortList = new ArrayList<>();
        //System.out.println("openPortsQueue: " + openPorts.size());
        while (!openPorts.isEmpty()) {
            openPortList.add(openPorts.poll());
        }
        //openPortList.forEach(p -> System.out.println("port " + p + " is open"));
    }

}