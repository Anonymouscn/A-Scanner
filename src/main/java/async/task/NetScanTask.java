package async.task;

import async.TaskPool;
import constant.Network;
import core.scanner.NetScanner;
import core.scanner.PortScanner;
import org.apache.commons.net.util.SubnetUtils;
import org.apache.commons.net.whois.WhoisClient;
import util.NetUtil;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * 网络扫描任务
 * Network scan task
 *
 * @author anonymous
 */
public class NetScanTask {

    /**
     * 指定 IP 范围扫描特定端口
     * scan a port in this IP range
     *
     * @param threads 线程数 number of threads
     * @param start ip 开始范围 ip start range
     * @param end ip 结束范围 ip end range
     * @param port 端口号 which port will be scanned
     */
    public static void runNetScanTask(int threads, Long start, Long end, int port) {
        long pieceSize = end - start > threads ? (end - start) / threads : 1;
        threads = end - start > threads ? threads : (int)(end - start);
        for(long i = 0, s = start, e = start + pieceSize - 1; i < threads; i++) {
            long fs = s;
            long fe = e;
            TaskPool.pool.submit(() -> new NetScanner(fs, fe).scan(port));
            s += pieceSize;
            e = i + 1 < threads ? e + pieceSize : end;
        }
    }

    /**
     * 指定 ip 范围扫描常用端口 scan ports which are used frequently in this ip range
     *
     * @param threads 线程数 number of threads
     * @param start ip 开始范围 ip start range
     * @param end ip 结束范围 ip end range
     */
    public static void runNetSimpleScanTask(int threads, Long start, Long end) {
        long pieceSize = end - start > threads ? (end - start) / threads : 1;
        threads = end - start > threads ? threads : (int)(end - start);
        for(long i = 0, s = start, e = start + pieceSize - 1; i < threads; i++) {
            long fs = s;
            long fe = e;
            TaskPool.pool.submit(() -> new NetScanner(fs, fe).simpleScan());
            s += pieceSize;
            e = i + 1 < threads ? e + pieceSize : end;
        }
    }

    /**
     * 指定 ip 范围扫描所有端口 scan all ports in this ip range
     *
     * @param threads 线程数 number of threads
     * @param start ip 开始范围 ip start range
     * @param end ip 结束范围 ip end range
     */
    public static void runNetFullScanTask(int threads, Long start, Long end) {
        long pieceSize = end - start > threads ? (end - start) / threads : 1;
        threads = end - start > threads ? threads : (int)(end - start);
        for(long i = 0, s = start, e = start + pieceSize - 1; i < threads; i++) {
            long fs = s;
            long fe = e;
            TaskPool.pool.submit(() -> new NetScanner(fs, fe).fullScan());
            s += pieceSize;
            e = i + 1 < threads ? e + pieceSize : end;
        }
    }

    /**
     * CIDR 网段指定端口扫描 scan a port on the CIDR network segment
     *
     * @param threads 线程数 number of threads
     * @param port 端口 which port will be scanned
     * @param cidr CIDR 网段 CIDR network segment
     */
    public static void runCidrScanTask(int threads, int port, String cidr) {
        SubnetUtils net = new SubnetUtils(cidr);
        runNetScanTask(threads, NetUtil.ipToNumber(net.getInfo().getLowAddress()), NetUtil.ipToNumber(net.getInfo().getHighAddress()), port);
    }

    /**
     * CIDR 网段常用端口扫描 scan ports which are used frequently on the CIDR network segment
     *
     * @param threads 线程数 number of threads
     * @param cidr CIDR 网段 CIDR network segment
     */
    public static void runCidrSimpleScanTask(int threads, String cidr) {
        SubnetUtils net = new SubnetUtils(cidr);
        runNetSimpleScanTask(threads, NetUtil.ipToNumber(net.getInfo().getLowAddress()), NetUtil.ipToNumber(net.getInfo().getHighAddress()));
    }

    /**
     * CIDR 网段全端口扫描
     *
     * @param threads 线程数 number of threads
     * @param cidr Cidr 网段 CIDR network segment
     */
    public static void runCidrFullScanTask(int threads, String cidr) {
        SubnetUtils net = new SubnetUtils(cidr);
        long start = NetUtil.ipToNumber(net.getInfo().getLowAddress());
        long end = NetUtil.ipToNumber(net.getInfo().getHighAddress());
        threads = threads / (int) (end - start) + 1;
        int pieceSize = Network.PORT_END / threads;
        int portStart = Network.PORT_START;
        for(long i = start; i <= end; i++) {
            long fi = i;
            for(int x = 0, s = portStart, e = portStart + pieceSize - 1; x < threads; x++) {
                int fs = s;
                int fe = e;
                TaskPool.pool.submit(() -> new PortScanner(NetUtil.numberToIp(fi), fs, fe).fullScan());
                s += pieceSize;
                e = x + 1 < threads ? e + pieceSize : Network.PORT_END;
            }
        }
    }

    /**
     * 获取域名信息 get info of domain
     *
     * @param domain 域名 domain
     */
    public static void getDomainInfo(String domain) {
        WhoisClient client = new WhoisClient();
        try {
            client.connect(WhoisClient.DEFAULT_HOST);
            System.out.println("域名信息: ");
            System.out.println(client.query(domain));
        } catch (IOException e) {
            System.out.println("连接不上 whois 服务器");
        }
    }

    /**
     * 域名指定端口扫描 scan a port on a domain
     *
     * @param threads 线程数 number of threads
     * @param port 端口 which port will be scanned
     * @param domain 域名 which domain will be scanned
     */
    public static void runDomainScanTask(int threads, int port, String domain) {
        InetAddress[] addresses = null;
        try {
            addresses = InetAddress.getAllByName(domain);
        } catch (UnknownHostException e) {
            System.out.println("未知域名或主机");
        }
        if(addresses != null && addresses.length > 0) {
            long num = Arrays.stream(addresses).count();
            threads = num > threads ? threads : (int)num;
            int start = 0;
            int pieceSize = num > threads ? (int)(num / threads) : 1;
            for(int i = 0, s = start, e = start + pieceSize - 1; i < threads; i++) {
                InetAddress[] addr = addresses;
                int fs = s;
                int fe = e;
                TaskPool.pool.submit(() -> new NetScanner().collectionScan(addr, fs, fe, port));
                s += pieceSize;
                e = i + 1 < threads ? e + pieceSize : addresses.length - 1;
            }
        }
    }

    /**
     * 域名常用端口扫描 scan ports which are used frequently on a domain
     *
     * @param threads 线程数 number of threads
     * @param domain 域名 which domain will be scanned
     */
    public static void runDomainSimpleScanTask(int threads, String domain) {
        InetAddress[] addresses = null;
        try {
            addresses = InetAddress.getAllByName(domain);
        } catch (UnknownHostException e) {
            System.out.println("未知域名或主机");
        }
        if(addresses != null && addresses.length > 0) {
            long num = Arrays.stream(addresses).count();
            threads = num > threads ? threads : (int)num;
            int start = 0;
            int pieceSize = num > threads ? (int)(num / threads) : 1;
            for(int i = 0, s = start, e = start + pieceSize - 1; i < threads; i++) {
                InetAddress[] addr = addresses;
                int fs = s;
                int fe = e;
                TaskPool.pool.submit(() -> new NetScanner().collectionSimpleScan(addr, fs, fe));
                s += pieceSize;
                e = i + 1 < threads ? e + pieceSize : addresses.length - 1;
            }
        }
    }

    /**
     * 域名全端口扫描 scan all ports on a domain
     *
     * @param threads 线程数 number of threads
     * @param domain 域名 which domain will be scanned
     */
    public static void runDomainFullScanTask(int threads, String domain) {
        InetAddress[] addresses = null;
        try {
            addresses = InetAddress.getAllByName(domain);
        } catch (UnknownHostException e) {
            System.out.println("未知域名或主机");
        }
        if(addresses != null && addresses.length > 0) {
            int start = 0;
            threads = threads > addresses.length ? threads / addresses.length : 1;
            int pieceSize = Network.PORT_END / threads;
            for(int i = 0; i < addresses.length; i++) {
                    int fi = i;
                for(int x = 0, s = start, e = start + pieceSize - 1; x < threads; x++) {
                    int fs = s;
                    int fe = e;
                    InetAddress[] addrs = addresses;
                    TaskPool.pool.submit(() -> new PortScanner(addrs[fi].getHostAddress(), fs, fe).fullScan());
                    s += pieceSize;
                    e = x + 1 < threads ? e + pieceSize : Network.PORT_END;
                }
            }
        }
    }

    /**
     * 子网指定端口扫描 scan a port on a subnet
     *
     * @param threads 线程数 number of threads
     * @param port 端口 which port will be scanned
     * @param ip 子网其一 IP | an IP of this subnet
     * @param mask 子网掩码 subnet mask
     */
    public static void runSubnetScanTask(int threads, int port, String ip, String mask) {
        SubnetUtils net = new SubnetUtils(ip, mask);
        runNetScanTask(threads, NetUtil.ipToNumber(net.getInfo().getLowAddress()), NetUtil.ipToNumber(net.getInfo().getHighAddress()), port);
    }

    /**
     * 子网常用端口扫描 scan ports which are used frequently on a subnet
     *
     * @param threads 线程数 number of threads
     * @param ip 子网其一 IP | an IP of this subnet
     * @param mask 子网掩码 subnet mask
     */
    public static void runSubnetSimpleScanTask(int threads, String ip, String mask) {
        SubnetUtils net = new SubnetUtils(ip, mask);
        runNetSimpleScanTask(threads, NetUtil.ipToNumber(net.getInfo().getLowAddress()), NetUtil.ipToNumber(net.getInfo().getHighAddress()));
    }

    /**
     * 子网全端口扫描 scan all ports on a subnet
     *
     * @param threads 线程数 number of threads
     * @param ip 子网其一 IP | an IP of this subnet
     * @param mask 子网掩码 subnet mask
     */
    public static void runSubnetFullScanTask(int threads, String ip, String mask) {
        SubnetUtils net = new SubnetUtils(ip, mask);
        runCidrFullScanTask(threads, net.getInfo().getCidrSignature());
    }

    /**
     * 因特网全网指定端口扫描 scan a port on the Internet
     *
     * @param threads 线程数 number of threads
     * @param port 端口 which port will be scanned
     */
    public static void runInternetScanTask(int threads, int port) {
        // 0.0.0.1 ~ 9.255.255.255
        runNetScanTask((int) ((((double) Network.PRIVATE_A_START - (double) Network.IP_START) / (double) Network.IP_END) * threads + 1), Network.IP_START, Network.PRIVATE_A_START - 1, port);
        // 11.0.0.0 ~ 171.15.255.255
        runNetScanTask((int) ((((double) Network.PRIVATE_B_START - (double) Network.PRIVATE_A_END - 1) / (double) Network.IP_END) * threads + 1), Network.PRIVATE_A_END + 1, Network.PRIVATE_B_START - 1, port);
        // 172.32.0.0 ~ 192.167.255.255
        runNetScanTask((int) ((((double) Network.PRIVATE_C_START - (double) Network.PRIVATE_B_END) / (double) Network.IP_END) * threads + 1), Network.PRIVATE_B_END + 1, Network.PRIVATE_C_START - 1, port);
        // 192.169.0.0 ~ 255.255.255.254
        runNetScanTask((int) ((((double) Network.IP_END - (double) Network.PRIVATE_C_END) / (double) Network.IP_END) * threads + 1), Network.PRIVATE_C_END - 1, Network.IP_END, port);
    }

    /**
     * 因特网全网常用端口扫描 scan ports which are used frequently on the Internet
     *
     * @param threads 线程数 number of threads
     */
    public static void runInternetSimpleScanTask(int threads) {
        // 0.0.0.1 ~ 9.255.255.255
        runNetSimpleScanTask((int) ((((double) Network.PRIVATE_A_START - (double) Network.IP_START) / (double) Network.IP_END) * threads + 1), Network.IP_START, Network.PRIVATE_A_START - 1);
        // 11.0.0.0 ~ 171.15.255.255
        runNetSimpleScanTask((int) ((((double) Network.PRIVATE_B_START - (double) Network.PRIVATE_A_END - 1) / (double) Network.IP_END) * threads + 1), Network.PRIVATE_A_END + 1, Network.PRIVATE_B_START - 1);
        // 172.32.0.0 ~ 192.167.255.255
        runNetSimpleScanTask((int) ((((double) Network.PRIVATE_C_START - (double) Network.PRIVATE_B_END) / (double) Network.IP_END) * threads + 1), Network.PRIVATE_B_END + 1, Network.PRIVATE_C_START - 1);
        // 192.169.0.0 ~ 255.255.255.254
        runNetSimpleScanTask((int) ((((double) Network.IP_END - (double) Network.PRIVATE_C_END) / (double) Network.IP_END) * threads + 1), Network.PRIVATE_C_END - 1, Network.IP_END);
    }

    /**
     * 因特网全网全端口扫描 scan all ports on the Internet
     *
     * @param threads 线程数 number of threads
     */
    public static void runInternetFullScanTask(int threads) {
        // 0.0.0.1 ~ 9.255.255.255
        runNetFullScanTask((int) ((((double) Network.PRIVATE_A_START - (double) Network.IP_START) / (double) Network.IP_END) * threads + 1), Network.IP_START, Network.PRIVATE_A_START - 1);
        // 11.0.0.0 ~ 171.15.255.255
        runNetFullScanTask((int) ((((double) Network.PRIVATE_B_START - (double) Network.PRIVATE_A_END - 1) / (double) Network.IP_END) * threads + 1), Network.PRIVATE_A_END + 1, Network.PRIVATE_B_START - 1);
        // 172.32.0.0 ~ 192.167.255.255
        runNetFullScanTask((int) ((((double) Network.PRIVATE_C_START - (double) Network.PRIVATE_B_END) / (double) Network.IP_END) * threads + 1), Network.PRIVATE_B_END + 1, Network.PRIVATE_C_START - 1);
        // 192.169.0.0 ~ 255.255.255.254
        runNetFullScanTask((int) ((((double) Network.IP_END - (double) Network.PRIVATE_C_END) / (double) Network.IP_END) * threads + 1), Network.PRIVATE_C_END - 1, Network.IP_END);
    }
}
