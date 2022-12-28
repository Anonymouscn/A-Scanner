package core.interact;

import async.task.NetScanTask;
import config.Global;
import constant.lang.Language;
import constant.lang.OutputFactory;
import util.NetUtil;
import java.util.InputMismatchException;

/**
 * 主菜单 main menu
 *
 * @author anonymous
 */
public class MainMenu {

    /**
     * 处理方法 handle method
     */
    public static void handle() {
        int select = 0;
        if(Global.RUNTIME_LANGUAGE == null) {
            System.out.print(OutputFactory.getLanguageSelect());
            try {
                select = InputScanner.getScanner().nextInt();
            } catch (InputMismatchException e) {
                System.out.println(OutputFactory.getGoodBye(Global.DEFAULT_LANGUAGE));
                System.exit(0);
            }
            switch (select) {
                case 1: Global.RUNTIME_LANGUAGE = Language.CHINESE; break;
                case 2: Global.RUNTIME_LANGUAGE = Language.ENGLISH; break;
                default: Global.RUNTIME_LANGUAGE = Global.DEFAULT_LANGUAGE;
            }
        }
        System.out.println(OutputFactory.getMainMenu(Global.RUNTIME_LANGUAGE));
        System.out.print(OutputFactory.getSELECT(Global.RUNTIME_LANGUAGE));
        try {
            select = InputScanner.getScanner().nextInt();
        } catch (InputMismatchException e) {
            select = -1;
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
                System.out.print(OutputFactory.getInputDomain(Global.RUNTIME_LANGUAGE));
                String domain = "";
                try {
                    domain = InputScanner.getScanner().next();
                } catch (InputMismatchException e) {
                    // 不处理
                }
                if(!NetUtil.checkDomain(domain)) {
                    NetUtil.wrongDomain(Global.RUNTIME_LANGUAGE);
                    System.exit(0);
                }
                System.out.println();
                NetScanTask.getDomainInfo(domain); break;
            // IP 子域扫描
            case 6: SubMenu6.handle(); break;
            // 任意其他按键退出
            default:
                System.out.println(OutputFactory.getGoodBye(Global.RUNTIME_LANGUAGE));
                System.exit(0);
        }
    }
}
