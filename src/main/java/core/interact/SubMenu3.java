package core.interact;

import async.TaskPool;
import async.task.NetScanTask;
import java.util.InputMismatchException;

/**
 * 子菜单 3 -- CIDR 网域扫描 | sub menu 3 -- scan on a CIDR network segment
 *
 * @author anonymous
 */
public class SubMenu3 {

    /**
     * 处理方法
     */
    public static void handle() {
        System.out.print("\n[*] 目标网域: ");
        String cidr = "";
        try {
            cidr = InputScanner.getScanner().next();
        } catch (Exception e) {
            System.out.println("子网读取失败");
        }
        System.out.println("\n[1] 常用端口扫描\n[2] 特定端口扫描\n[3] 全扫描\n[4] 返回\n[*] 退出\n");
        System.out.print("选择: ");
        int select = 0;
        try {
            select = InputScanner.getScanner().nextInt();
        } catch (InputMismatchException e) {
            // 不处理
        }
        System.out.println();
        switch(select) {
            case 1:
                NetScanTask.runCidrSimpleScanTask(TaskPool.maxThreads, cidr); break;
            case 2:
                System.out.print("[*] 端口: ");
                int port = -1;
                try {
                    port = InputScanner.getScanner().nextInt();
                    if(port < 0) {
                        throw new IllegalArgumentException();
                    }
                } catch (Exception e) {
                    System.out.println("端口号读取失败");
                }
                System.out.println();
                NetScanTask.runCidrScanTask(TaskPool.maxThreads, port, cidr); break;
            case 3: NetScanTask.runCidrFullScanTask(TaskPool.maxThreads, cidr); break;
            case 4: MainMenu.handle();
            default:
                System.out.println("\nGoodbye!");
                System.exit(0);
        }
    }
}
