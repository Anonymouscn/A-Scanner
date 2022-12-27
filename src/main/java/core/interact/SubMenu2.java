package core.interact;

import async.TaskPool;
import async.task.PortScanTask;
import core.scanner.PortScanner;
import java.util.InputMismatchException;

/**
 * 子菜单 2 -- 目标扫描 | sub menu 2 -- scan a target
 *
 * @author anonymous
 */
public class SubMenu2 {

    /**
     * 处理方法
     */
    public static void handle() {
        System.out.print("\n[*] 目标ip地址: ");
        String ip = "";
        try {
            ip = InputScanner.getScanner().next();
        } catch (Exception e) {
            System.out.println("ip地址读取失败");
        }
        System.out.println("\n准备扫描 [*] target " + ip);
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
            // 常用端口扫描
            case 1:
                PortScanTask.runSingleTargetSimpleScanTask(TaskPool.maxThreads, ip);
                break;
                // 特定端口扫描
            case 2:
                System.out.print("[*] 端口: ");
                int port = 0;
                try {
                    port = InputScanner.getScanner().nextInt();
                } catch (Exception e) {
                    System.out.println("非法端口");
                }
                System.out.println();
                new PortScanner(ip).scan(port);
                break;
                // 全扫描
            case 3:
                PortScanTask.runSingleTargetScanTask(TaskPool.maxThreads, ip);
                break;
                // 返回
            case 4: MainMenu.handle();
            // 任意其他按键退出
            default:
                System.out.println("Goodbye!");
                System.exit(0);
        }
    }
}
