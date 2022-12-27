package core.interact;

import async.task.NetScanTask;
import java.util.InputMismatchException;

/**
 * 主菜单 main menu
 *
 * @author anonymous
 */
public class MainMenu {

    /**
     * 处理方法
     */
    public static void handle() {
        System.out.println("\n[1] 全网扫描\n[2] 目标 IP 扫描\n[3] CIDR 网域扫描\n[4] 目标域名扫描\n[5] 域名信息查询\n[6] IP 子域扫描\n[*] 退出\n");
        System.out.print("选择: ");
        int select = 0;
        try {
            select = InputScanner.getScanner().nextInt();
        } catch (InputMismatchException e) {
            // 不处理, 直接退出
        }
        switch(select) {
            // 全网扫描
            case 1: SubMenu1.handle(); break;
            // 目标 IP 扫描
            case 2: SubMenu2.handle(); break;
            // CIDR 网域扫描
            case 3: SubMenu3.handle(); break;
            // 目标域名扫描
            case 4: SubMenu4.handle(); break;
            // 域名信息查询
            case 5:
                System.out.print("\n[*] 域名: ");
                String domain = "";
                try {
                    domain = InputScanner.getScanner().next();
                } catch (InputMismatchException e) {
                    // 不处理, 直接退出
                }
                System.out.println();
                NetScanTask.getDomainInfo(domain); break;
            // IP 子域扫描
            case 6: SubMenu6.handle(); break;
            // 任意其他按键退出
            default:
                System.out.println("\nGoodbye!");
                System.exit(0);
        }
    }
}
