package core.interact;

import async.TaskPool;
import async.task.NetScanTask;
import config.Global;
import constant.Network;
import constant.lang.OutputFactory;
import util.NetUtil;
import java.util.InputMismatchException;

/**
 * 子菜单 3 -- CIDR 网域扫描 | sub menu 3 -- scan on a CIDR network segment
 *
 * @author anonymous
 */
public class SubMenu3 {

    /**
     * 处理方法 handle method
     */
    public static void handle() {
        System.out.print(OutputFactory.getInputCIDR(Global.RUNTIME_LANGUAGE));
        String cidr = "";
        try {
            cidr = InputScanner.getScanner().next();
        } catch (Exception e) {
            NetUtil.wrongCidr(Global.RUNTIME_LANGUAGE);
        }
        if(!NetUtil.checkCidr(cidr)) {
            NetUtil.wrongCidr(Global.RUNTIME_LANGUAGE);
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
            case 1:
                System.out.println();
                NetScanTask.runCidrSimpleScanTask(TaskPool.maxThreads, cidr);
                break;
            case 2:
                System.out.println();
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
                NetScanTask.runCidrScanTask(TaskPool.maxThreads, port, cidr);
                break;
            case 3:
                System.out.println();
                NetScanTask.runCidrFullScanTask(TaskPool.maxThreads, cidr);
                break;
            case 4:
                MainMenu.handle();
            default:
                System.out.println(OutputFactory.getGoodBye(Global.RUNTIME_LANGUAGE));
                System.exit(0);
        }
    }
}
