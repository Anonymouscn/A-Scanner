package constant.lang;

import config.Global;

/**
 * 英文输出 output with English
 *
 * @author anonymous
 */
public class English {

    public static final String MAIN_MENU = "\n[1] scan on the Internet\n[2] scan a target IPv4\n[3] scan a CIDR network segment\n[4] scan a domain\n[5] search domain whois info\n[6] scan a subnet\n[*] exit\n";

    public static final String SCAN_TYPE = "\n[1] scan ports which are used frequently\n[2] scan a target port\n[3] scan all ports\n[4] back\n[*] exit\n";

    public static final String SELECT = "[$] select: ";

    public static final String INPUT_IP = "\n[*] target IP: ";

    public static final String INPUT_DOMAIN = "\n[*] target domain: ";

    public static final String INPUT_CIDR = "\n[*] CIDR: ";

    public static final String INPUT_PORT = "[*] port: ";

    public static final String INPUT_MASK = "[*] mask : ";

    public static final String INPUT_SUBNET = "\n[*] an IP of target subnet: ";

    public static final String GOODBYE = "\nGoodbye";

    public static final String OPENING_PORT = " is open ";

    public static final String GUESS = " guess => ";

    public static final String WRONG_EXPRESS = "Unpassable command line expression, see help for details: $ java -jar A-Scanner-" + Global.VERSION + ".jar -h";

    public static final String WRONG_PORT = "Illegal port value, see help for details: $ java -jar A-Scanner-" + Global.VERSION + ".jar -h";

    public static final String WRONG_IPV4 = "Illegal IPv4 address, see help for details: $ java -jar A-Scanner-" + Global.VERSION + ".jar -h";

    public static final String WRONG_DOMAIN = "Illegal domain, see help for details: $ java -jar A-Scanner-" + Global.VERSION + ".jar -h";

    public static final String WRONG_CIDR = "Illegal CIDR network segment, see help for details: $ java -jar A-Scanner-" + Global.VERSION + ".jar -h";

    public static final String WRONG_MASK = "Illegal subnet mask, see help for details: $ java -jar A-Scanner-" + Global.VERSION + ".jar -h";
}
