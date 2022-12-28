package core.interact;

import async.TaskPool;
import async.task.NetScanTask;
import config.Global;
import constant.Network;
import constant.lang.OutputFactory;
import util.NetUtil;
import java.util.InputMismatchException;

/**
 * 子菜单 4 -- 目标域名扫描 | sub menu 4 -- scan on a domain
 *
 * @author anonymous
 */
public class SubMenu4 {

    /**
     * 处理方法 handle method
     */
    public static void handle() {
        System.out.print(OutputFactory.getInputDomain(Global.RUNTIME_LANGUAGE));
        String domain = "";
        try {
            domain = InputScanner.getScanner().next();
        } catch (InputMismatchException e) {
            NetUtil.wrongDomain(Global.RUNTIME_LANGUAGE);
        }
        if(!NetUtil.checkDomain(domain)) {
            NetUtil.wrongDomain(Global.RUNTIME_LANGUAGE);
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
                NetScanTask.runDomainSimpleScanTask(TaskPool.maxThreads, domain);
                break;
            // 特定端口扫描
            case 2:
                System.out.print(OutputFactory.getInputPort(Global.RUNTIME_LANGUAGE));
                int port = -1;
                try {
                    port = InputScanner.getScanner().nextInt();
                    if(port < 0 || port > Network.PORT_END) {
                        NetUtil.wrongPort(Global.RUNTIME_LANGUAGE);
                        System.exit(0);
                    }
                } catch (Exception e) {
                    NetUtil.wrongPort(Global.RUNTIME_LANGUAGE);
                }
                System.out.println();
                NetScanTask.runDomainScanTask(TaskPool.maxThreads, port, domain);
                break;
            // 全扫描
            case 3:
                System.out.println();
                NetScanTask.runDomainFullScanTask(TaskPool.maxThreads, domain);
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
