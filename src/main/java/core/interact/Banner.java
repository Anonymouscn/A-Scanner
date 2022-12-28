package core.interact;

import config.Global;

/**
 * logo
 *
 * @author anonymous
 */
public class Banner {

    /** logo 打印 | print logo */
    public static void show() {
        System.out.println("\033[31m");
        System.out.println("    _        ____\n" +
                "   / \\      / ___|  ___ __ _ _ __  _ __   ___ _ __\n" +
                "  / _ \\ ____\\___ \\ / __/ _` | '_ \\| '_ \\ / _ \\ '__|\n" +
                " / ___ \\_____|__) | (_| (_| | | | | | | |  __/ |\n" +
                "/_/   \\_\\   |____/ \\___\\__,_|_| |_|_| |_|\\___|_|\n");
        System.out.println("\033[33m" + "@version "+ Global.VERSION +
                "\n@author " + Global.AUTHOR +
                "\ndetails on github: " + Global.GITHUB);
        System.out.println("\033[0m");
    }
}
