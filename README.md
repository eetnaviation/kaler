# kaler
Kaler is a open-source java ip and port scanner.

# CLI Syntax
The command line arguments are:

firstIp maxIps maxPort debugArray

- firstIp -> The first IP to start scanning from
- maxIps -> The maximum amount of IPs (IPv4 Max is 255)
- maxPort -> Maximum port to scan

So the command,

```java -jar kaler.jar 192.168.1.0 50 1500```

Would result in:

- All ips scanned starting from 192.168.1.0 up to 192.168.1.50
- Ports up to 1500 scanned for each ip

and the command,

```java -jar kaler.jar 192.168.1.0 255 80```

Would result in:

- All ips scanned starting from 192.168.1.0 up to 192.168.1.255
- Ports up to 80 scanned for each ip