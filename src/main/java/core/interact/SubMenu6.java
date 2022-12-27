package core.interact;

import async.TaskPool;
import async.task.NetScanTask;
import java.util.InputMismatchException;

/**
 * 子菜单 6 -- 子域扫描 | sub menu 6 -- scan on a subnet
 *
 * @author anonymous
 */
public class SubMenu6 {

    /**
     * 处理方法
     */
    public static void handle() {
        System.out.print("[*] 子域其一 ip : ");
        String ip = "";
        try {
            ip = InputScanner.getScanner().next();
        } catch (InputMismatchException e) {
            System.out.println("ip 读取失败");
        }
        System.out.print("[*] 子网掩码 : ");
        String mask = "";
        try {
            mask = InputScanner.getScanner().next();
        } catch (InputMismatchException e) {
            System.out.println("子网掩码读取失败");
        }
        System.out.println("\n[1] 常用端口扫描\n[2] 特定端口扫描\n[3] 全扫描\n[4] 返回\n[*] 退出\n");
        System.out.print("选择: ");
        int select = 0;
        try {
            select = InputScanner.getScanner().nextInt();
        } catch (InputMismatchException e) {
            // 不处理
        }
        switch(select) {
            // 常见端口扫描
            case 1: NetScanTask.runSubnetSimpleScanTask(TaskPool.maxThreads, ip, mask); break;
            // 特定端口扫描
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
                NetScanTask.runSubnetScanTask(TaskPool.maxThreads, port, ip, mask); break;
            // 全扫描
            case 3: NetScanTask.runSubnetFullScanTask(TaskPool.maxThreads, ip, mask); break;
            // 返回
            case 4: MainMenu.handle();
                // 任意其他按键退出
            default:
                System.out.println("\nGoodbye!");
                System.exit(0);
        }
    }
}
