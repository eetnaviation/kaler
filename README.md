# kaler
Kaler is a open-source java ip and port scanner.

# CLI Syntax
The command line arguments are:

firstIp maxIps maxPort debugArray

- firstIp -> The first IP to start scanning from
- maxIps -> The maximum amount of IPs (IPv4 Max is 255)
- maxPort -> Maximum port to scan
- debugArray -> Boolean: true/false, show the full list of IPs after ip scan?

So the command,

```java -jar kaler.jar 192.168.1.0 50 1500 false```

Would result in:

- All ips scanned starting from 192.168.1.0 up to 192.168.1.50
- Ports up to 1500 scanned for each ip
- Debug array would not be shown