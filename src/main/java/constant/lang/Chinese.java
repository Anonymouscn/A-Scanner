package constant.lang;

import config.Global;

/**
 * 中文输出 output with Chinese
 *
 * @author anonymous
 */
public class Chinese {

    public static final String MAIN_MENU = "\n[1] 全网扫描\n[2] 目标 IPv4 扫描\n[3] CIDR 网段扫描\n[4] 目标域名扫描\n[5] whois 域名信息查询\n[6] IP 子域扫描\n[*] 退出\n";

    public static final String SCAN_TYPE = "\n[1] 常用端口扫描\n[2] 特定端口扫描\n[3] 全扫描\n[4] 返回\n[*] 退出\n";

    public static final String SELECT = "[$] 选择: ";

    public static final String INPUT_IP = "\n[*] 目标ip地址: ";

    public static final String INPUT_DOMAIN = "\n[*] 域名: ";

    public static final String INPUT_CIDR = "\n[*] 目标网域: ";

    public static final String INPUT_PORT = "[*] 端口: ";

    public static final String INPUT_MASK = "[*] 子网掩码 : ";

    public static final String INPUT_SUBNET = "\n[*] 子域其一 ip : ";

    public static final String GOODBYE = "\n后会有期！";

    public static final String OPENING_PORT = " 端口开放 ";

    public static final String GUESS = " 可能是 => ";

    public static final String WRONG_EXPRESS = "无法解析的命令行表达式，详细查看帮助: $ java -jar A-Scanner-" + Global.VERSION + ".jar -h";

    public static final String WRONG_PORT = "非法端口值，详细查看帮助: $ java -jar A-Scanner-" + Global.VERSION + ".jar -h";

    public static final String WRONG_IPV4 = "非法 IPv4 地址，详细查看帮助: $ java -jar A-Scanner-" + Global.VERSION + ".jar -h";

    public static final String WRONG_DOMAIN = "非法域名，详细查看帮助: $ java -jar A-Scanner-" + Global.VERSION + ".jar -h";

    public static final String WRONG_CIDR = "非法 CIDR 网段，详细查看帮助: $ java -jar A-Scanner-" + Global.VERSION + ".jar -h";

    public static final String WRONG_MASK = "非法子网掩码，详细查看帮助: $ java -jar A-Scanner-" + Global.VERSION + ".jar -h";
}
