package core.interact;

import async.TaskPool;
import async.task.NetScanTask;
import config.Global;
import constant.Network;
import constant.lang.OutputFactory;
import util.NetUtil;
import java.util.InputMismatchException;

/**
 * 子菜单 1 -- 扫描全网 | sub menu 1 -- scan on Internet
 *
 * @author anonymous
 */
public class SubMenu1 {

    /**
     * 处理方法 handle method
     */
    public static void handle() {
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
                NetScanTask.runInternetSimpleScanTask(TaskPool.maxThreads);
                break;
            // 特定端口扫描
            case 2:
                System.out.println();
                System.out.print(OutputFactory.getInputPort(Global.RUNTIME_LANGUAGE));
                int port = -1;
                try {
                    port = InputScanner.getScanner().nextInt();
                } catch (InputMismatchException e) {
                    NetUtil.wrongPort(Global.RUNTIME_LANGUAGE);
                }
                System.out.println();
                if(port < Network.PORT_START || port > Network.PORT_END) {
                    NetUtil.wrongPort(Global.RUNTIME_LANGUAGE);
                    System.exit(0);
                }
                NetScanTask.runInternetScanTask(TaskPool.maxThreads, port);
                break;
                // 全扫描
            case 3:
                System.out.println();
                NetScanTask.runInternetFullScanTask(TaskPool.maxThreads);
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
