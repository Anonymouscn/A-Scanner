package core.scanner;

import config.Global;
import constant.Network;
import constant.lang.OutputFactory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import util.NetUtil;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * 网络扫描器 network scanner
 *
 * @author anonymous
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NetScanner {

    /** IP 开始范围 IP start range */
    private Long start;

    /** IP 结束范围 IP end range */
    private Long end;

    /** 端口扫描器 port scanner */
    private PortScanner scanner = new PortScanner();

    public NetScanner(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    /** 指定端口扫描 scan a port */
    public void scan(int port) {
        LongStream.range(start, end + 1).forEach(i -> {
            try {
                Socket s = new Socket();
                String ip = NetUtil.numberToIp(i);
                if(!NetUtil.isBorder(ip) && !NetUtil.isLoopback(ip)) {
                    s.connect(new InetSocketAddress(ip, port), Network.SOCKET_TIMEOUT);
                    System.out.println("[+] " + ip + ":" + port + OutputFactory.getOpeningPort(Global.RUNTIME_LANGUAGE));
                    s.close();
                }
            } catch (IOException e) {
                // 不做处理
            }
        });
    }

    /** 全端口扫描 scan all ports */
    public void fullScan() {
        LongStream.range(start, end + 1).forEach(i -> {
            String ip = NetUtil.numberToIp(i);
            if(!NetUtil.isBorder(ip) && !NetUtil.isLoopback(ip)) {
                scanner.setTarget(ip);
                scanner.setStart(Network.PORT_START);
                scanner.setEnd(Network.PORT_END);
                scanner.fullScan();
            }
        });
    }

    /** 常用端口扫描 scan ports which are used frequently */
    public void simpleScan() {
        LongStream.range(start, end + 1).forEach(i -> {
            String ip = NetUtil.numberToIp(i);
            if(!NetUtil.isBorder(ip) && !NetUtil.isLoopback(ip)) {
                scanner.setTarget(ip);
                scanner.simpleScan();
            }
        });
    }

    /**
     * 目标集合指定端口扫描 scan a port in range
     *
     * @param addresses 目标集合 target host collection
     * @param start 开始索引 start index
     * @param end 结束索引 end index
     * @param port 端口 which port will be scanned
     */
    public void collectionScan(InetAddress[] addresses, int start, int end, int port) {
        IntStream.range(start, end + 1).forEach(i -> {
            String ip = addresses[i].getHostAddress();
            if(!NetUtil.isBorder(ip) && !NetUtil.isLoopback(ip)) {
                scanner.setTarget(NetUtil.numberToIp(NetUtil.ipToNumber(ip)));
                scanner.scan(port);
            }
        });
    }

    /**
     * 目标集合常用端口扫描 scan ports which are used frequently in range
     *
     * @param addresses 目标集合 target host collection
     * @param start 开始索引 start index
     * @param end 结束索引 end index
     */
    public void collectionSimpleScan(InetAddress[] addresses, int start, int end) {
        IntStream.range(start, end + 1).forEach( i -> {
            String ip = addresses[i].getHostAddress();
            if(!NetUtil.isBorder(ip) && !NetUtil.isLoopback(ip)) {
                scanner.setTarget(NetUtil.numberToIp(NetUtil.ipToNumber(ip)));
                scanner.simpleScan();
            }
        });
    }
}
