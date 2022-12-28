package core.interact;

import java.util.Scanner;

/**
 * 用户输入 Scanner | inputScanner
 *
 * @author anonymous
 */
public class InputScanner {

    /** 全局 Scanner | global input scanner */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * 获取用户输入 Scanner | get global input scanner
     *
     * @return input scanner
     */
    public static Scanner getScanner() {
        return scanner;
    }
}
