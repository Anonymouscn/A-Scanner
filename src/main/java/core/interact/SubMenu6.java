package core.interact;

import async.TaskPool;
import async.task.NetScanTask;
import config.Global;
import constant.Network;
import constant.lang.OutputFactory;
import util.NetUtil;
import java.util.InputMismatchException;

/**
 * 子菜单 6 -- 子域扫描 | sub menu 6 -- scan on a subnet
 *
 * @author anonymous
 */
public class SubMenu6 {

    /**
     * 处理方法 handle method
     */
    public static void handle() {
        System.out.print(OutputFactory.getInputSubnet(Global.RUNTIME_LANGUAGE));
        String ip = "";
        try {
            ip = InputScanner.getScanner().next();
        } catch (InputMismatchException e) {
            NetUtil.wrongIpv4(Global.RUNTIME_LANGUAGE);
        }
        if(!NetUtil.checkIPv4(ip)) {
            NetUtil.wrongIpv4(Global.RUNTIME_LANGUAGE);
            System.exit(0);
        }
        System.out.print(OutputFactory.getInputMask(Global.RUNTIME_LANGUAGE));
        String mask = "";
        try {
            mask = InputScanner.getScanner().next();
        } catch (InputMismatchException e) {
            NetUtil.wrongMask(Global.RUNTIME_LANGUAGE);
        }
        if(!NetUtil.checkMask(mask)) {
            NetUtil.wrongMask(Global.RUNTIME_LANGUAGE);
            System.exit(0);
        }
        System.out.println(OutputFactory.getScanType(Global.RUNTIME_LANGUAGE));
        System.out.print(OutputFactory.getSELECT(Global.RUNTIME_LANGUAGE));
        int select = 0;
        try {
            select = InputScanner.getScanner().nextInt();
        } catch (InputMismatchException e) {
            // 不处理
        }
        switch(select) {
            // 常见端口扫描
            case 1:
                System.out.println();
                NetScanTask.runSubnetSimpleScanTask(TaskPool.maxThreads, ip, mask);
                break;
            // 特定端口扫描
            case 2:
                System.out.print(OutputFactory.getInputPort(Global.RUNTIME_LANGUAGE));
                int port = -1;
                try {
                    port = InputScanner.getScanner().nextInt();
                    if(port < Network.PORT_START || port > Network.PORT_END) {
                        NetUtil.wrongPort(Global.RUNTIME_LANGUAGE);
                        System.exit(0);
                    }
                } catch (Exception e) {
                    NetUtil.wrongPort(Global.RUNTIME_LANGUAGE);
                }
                System.out.println();
                NetScanTask.runSubnetScanTask(TaskPool.maxThreads, port, ip, mask);
                break;
            // 全扫描
            case 3:
                System.out.println();
                NetScanTask.runSubnetFullScanTask(TaskPool.maxThreads, ip, mask);
                break;
            // 返回
            case 4:
                MainMenu.handle();
                // 任意其他按键退出
            default:
                System.out.println(OutputFactory.getGoodBye(Global.RUNTIME_LANGUAGE));
                System.exit(0);
        }
    }
}
