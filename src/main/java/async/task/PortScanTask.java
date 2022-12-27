package async.task;

import async.TaskPool;
import constant.Network;
import core.scanner.PortScanner;

/**
 * 端口扫描任务
 * Port scan task
 *
 * @author anonymous
 */
public class PortScanTask  {

    /**
     * 扫描目标主机全部端口 scan all ports on a target host
     *
     * @param threads 线程数 number of threads
     * @param target 目标主机 IP | an IP address of the target
     */
    public static void runSingleTargetScanTask(int threads, String target) {
        int pieceSize = Network.PORT_END / threads;
        for(int i = 0, s = Network.PORT_START, e = pieceSize - 1; i < threads; i++) {
            int fs = s;
            int fe = e;
            TaskPool.pool.submit(() -> new PortScanner(target, fs, fe).fullScan());
            s += pieceSize;
            e = i + 1 < threads ? e + pieceSize : Network.PORT_END;
        }
    }

    /**
     * 扫描目标主机常用端口 scan ports which are used frequently on a target host
     *
     * @param threads 线程数 number of threads
     * @param target 目标主机 IP | an IP address of the target
     */
    public static void runSingleTargetSimpleScanTask(int threads, String target) {
        int pieceSize = Network.commonPorts.length / threads + 1;
        for(int i = 0, s = 0, e = pieceSize - 1; i < threads; i++) {
            int fs = s;
            int fe = e;
            TaskPool.pool.submit(() -> new PortScanner(target).simpleScan(fs, fe));
            s += pieceSize;
            e = i + 1 < threads ? e + pieceSize : Network.PORT_END;
        }
    }
}
