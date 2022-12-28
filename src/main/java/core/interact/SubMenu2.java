package core.interact;

import async.TaskPool;
import async.task.PortScanTask;
import config.Global;
import constant.Network;
import constant.lang.OutputFactory;
import core.scanner.PortScanner;
import util.NetUtil;
import java.util.InputMismatchException;

/**
 * 子菜单 2 -- 目标扫描 | sub menu 2 -- scan a target
 *
 * @author anonymous
 */
public class SubMenu2 {

    /**
     * 处理方法 handle method
     */
    public static void handle() {
        System.out.print(OutputFactory.getInputIP(Global.RUNTIME_LANGUAGE));
        String ip = "";
        try {
            ip = InputScanner.getScanner().next();
        } catch (Exception e) {
            NetUtil.wrongIpv4(Global.RUNTIME_LANGUAGE);
        }
        if(!NetUtil.checkIPv4(ip)) {
            NetUtil.wrongIpv4(Global.RUNTIME_LANGUAGE);
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
            // 常用端口扫描
            case 1:
                System.out.println();
                PortScanTask.runSingleTargetSimpleScanTask(TaskPool.maxThreads, ip);
                break;
                // 特定端口扫描
            case 2:
                System.out.println();
                System.out.print(OutputFactory.getInputPort(Global.RUNTIME_LANGUAGE));
                int port = 0;
                try {
                    port = InputScanner.getScanner().nextInt();
                } catch (Exception e) {
                    NetUtil.wrongPort(Global.RUNTIME_LANGUAGE);
                }
                if(port > Network.PORT_START && port < Network.PORT_END) {
                    System.out.println();
                    new PortScanner(ip).scan(port);
                } else {
                    NetUtil.wrongPort(Global.RUNTIME_LANGUAGE);
                    System.exit(0);
                }
                break;
                // 全扫描
            case 3:
                System.out.println();
                PortScanTask.runSingleTargetScanTask(TaskPool.maxThreads, ip);
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
