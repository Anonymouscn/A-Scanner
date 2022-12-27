package core.interact;

import async.TaskPool;
import async.task.NetScanTask;
import java.util.InputMismatchException;

/**
 * 子菜单 1 -- 扫描全网 | sub menu 1 -- scan on Internet
 *
 * @author anonymous
 */
public class SubMenu1 {

    /**
     * 处理方法
     */
    public static void handle() {
        System.out.println("\n[1] 常用端口扫描\n[2] 特定端口扫描\n[3] 全扫描 (效率低，非必要建议使用常用扫描)\n[4] 返回\n[*] 退出\n");
        System.out.print("选择: ");
        int select = 0;
        try {
            select = InputScanner.getScanner().nextInt();
        } catch (InputMismatchException e) {
            // 不处理
        }
        switch(select) {
            // 常用端口扫描
            case 1: NetScanTask.runInternetSimpleScanTask(TaskPool.maxThreads); break;
            // 特定端口扫描
            case 2:
                System.out.print("\n[*] 端口: ");
                int port = -1;
                try {
                    port = InputScanner.getScanner().nextInt();
                } catch (InputMismatchException e) {
                    // 不处理
                }
                System.out.println();
                if(port > 0) {
                    NetScanTask.runInternetScanTask(TaskPool.maxThreads, port);
                } else {
                    System.out.println("错误或非法端口号!");
                }
                break;
                // 全扫描
            case 3: NetScanTask.runInternetFullScanTask(TaskPool.maxThreads); break;
            // 返回
            case 4: MainMenu.handle();
            // 任意其他按键退出
            default:
                System.out.println("\nGoodbye!");
                System.exit(0);
        }
    }
}
