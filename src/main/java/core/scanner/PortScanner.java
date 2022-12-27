package core.scanner;

import constant.Network;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 端口扫描器 port scanner
 *
 * @author anonymous
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortScanner {

    /** 目标主机 target host */
    private String target;

    /** 端口开始范围 port start range */
    private int start;

    /** 端口结束范围 port end range */
    private int end;

    public PortScanner(String target) {
        this();
        this.target = target;
    }

    /** 指定端口扫描 scan a port */
    public void scan(int port) {
        try {
            Socket s = new Socket();
            s.connect(new InetSocketAddress(target, port), Network.SOCKET_TIMEOUT);
            System.out.println("[+] " + target + ":" + port + " 端口开放");
            s.close();
        } catch (IOException e) {
            // 不做处理 nothing to do
        }
    }

    /** 全端口扫描 scan all ports */
    public void fullScan() {
        IntStream.range(start, end + 1).forEach(i -> {
            try {
                Socket s = new Socket();
                s.connect(new InetSocketAddress(target, i), Network.SOCKET_TIMEOUT);
                System.out.println("[+] " + target + ":" + i + " 端口开放");
                s.close();
            } catch (IOException e) {
                // 不做处理 nothing to do
            }
        });
    }

    /**
     * 常用端口扫描 scan ports which are used frequently in a range
     *
     * @param start 开始索引 start index
     * @param end 结束索引 end index
     */
    public void simpleScan(int start, int end) {

        IntStream.range(start, end + 1).forEach(i -> {
            try {
                Socket s = new Socket();
                s.connect(new InetSocketAddress(target, Network.commonPorts[i].port), Network.SOCKET_TIMEOUT);
                System.out.println("[+] " + target + ":" + Network.commonPorts[i].port + " 端口开放" + " guess: " + Network.commonPorts[i].info);
                s.close();
            } catch (IOException e) {
                // 不做处理 nothing to do
            }
        });


    }

    /** 常用端口扫描 常用端口扫描 scan ports which are used frequently */
    public void simpleScan() {
        Arrays.stream(Network.commonPorts).forEach(p -> {
            try {
                Socket s = new Socket();
                s.connect(new InetSocketAddress(target, p.port), Network.SOCKET_TIMEOUT);
                System.out.println("[+] " + target + ":" + p.port + " 端口开放" + " guess: " + p.info);
                s.close();
            } catch (IOException e) {
                // 不做处理 nothing to do
            }
        });
    }
}
