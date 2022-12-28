package util;

import config.Global;
import constant.Network;
import constant.lang.Language;
import constant.lang.OutputFactory;
import java.util.regex.Pattern;

/**
 * 网络工具类 network utils
 *
 * @author anonymous
 */
public class NetUtil {

    /**
     * 数字转 IP 字符串 convert number to IP string
     *
     * @param number 数字 number
     * @return IP 字符串 | IP string
     */
    public static String numberToIp(Long number) {
        String s1 = String.valueOf((number & 4278190080L) / 16777216L);
        String s2 = String.valueOf((number & 16711680L) / 65536L);
        String s3 = String.valueOf((number & 65280L) / 256L);
        String s4 = String.valueOf(number & 255L);
        return s1 + "." + s2 + "." + s3 + "." + s4;
    }

    /**
     * IP 字符串转数字 convert IP string to number
     *
     * @param ip IP 字符串 | IP string
     * @return 数字 number
     */
    public static Long ipToNumber(String ip) {
        long number = 0L;
        String tmp = ip;
        for(int i = 0; i < 3; i++) {
            number = number * 256 + Long.parseLong(tmp.substring(0, tmp.indexOf(".")));
            tmp = tmp.substring(tmp.indexOf(".") + 1);
        }
        number = number * 256 + Long.parseLong(tmp);
        return number;
    }

    /**
     * 校验 IPv4 是否合法
     *
     * @param ipv4 IPv4 地址
     * @return 是否合法
     */
    public static boolean checkIPv4(String ipv4) {
        String regex = "^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$";
        return Pattern.matches(regex, ipv4);
    }

    /**
     * 是否是边界地址 is it a boundary address
     *
     * @param ip IP
     * @return 是否是边界地址 is it a boundary address
     */
    public static boolean isBorder(String ip) {
        StringBuilder builder = new StringBuilder(ip);
        String reverse = builder.reverse().toString();
        return "0".equals(reverse.substring(0, 1)) || "552".equals(reverse.substring(0, 3));
    }

    /**
     * 是否是回环地址 is it a loopback address
     *
     * @param ip IP
     * @return 是否是回环地址 is it a loopback address
     */
    public static boolean isLoopback(String ip) {
        return Network.LOOPBACK.equals(ip);
    }

    /**
     * 校验是否是合法的域名 check if it is a valid domain name
     *
     * @param domain 域名 domain
     * @return 是否是合法的域名 is it a valid domain name
     */
    public static boolean checkDomain(String domain) {
        final String regex = "^((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}$";
        return Pattern.matches(regex, domain);
    }

    /**
     * 校验是否是合法的 CIDR 网段 check whether it is a legal CIDR network segment
     *
     * @param cidr CIDR 网段 CIDR
     * @return 是否合法 is it legal
     */
    public static boolean checkCidr(String cidr) {
        final String regex =  "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])(\\/([0-9]|[1-2][0-9]|3[0-2]))?$";
        return Pattern.matches(regex, cidr);
    }

    /**
     * 校验是否是合法的子网掩码 check if it is a legal subnet mask
     *
     * @param mask 子网掩码 mask
     * @return is it legal
     */
    public static boolean checkMask(String mask) {
        String regex = "^((254|252|248|240|224|192|128)\\.0\\.0\\.0|255\\.(254|252|248|240|224|192|128|0)\\.0\\.0|255\\.255\\.(254|252|248|240|224|192|128|0)\\.0|255\\.255\\.255\\.(254|252|248|240|224|192|128|0))$";
        return Pattern.matches(regex, mask);
    }

    /** 无法解析的命令行表达式 wrong expression */
    public static void wrongExpress(Language language) {
        language = language == null ? Global.DEFAULT_LANGUAGE : language;
        System.out.println(OutputFactory.getWrongExpress(language));
    }

    /** 非法端口值 wrong port */
    public static void wrongPort(Language language) {
        language = language == null ? Global.DEFAULT_LANGUAGE : language;
        System.out.println(OutputFactory.getWrongPort(language));
    }

    /** 非法 IPv4 地址 wrong IPv4 address */
    public static void wrongIpv4(Language language) {
        language = language == null ? Global.DEFAULT_LANGUAGE : language;
        System.out.println(OutputFactory.getWrongIpv4(language));
    }

    /** 非法域名 wrong domain */
    public static void wrongDomain(Language language) {
        language = language == null ? Global.DEFAULT_LANGUAGE : language;
        System.out.println(OutputFactory.getWrongDomain(language));
    }

    /** 非法 CIDR 网段 wrong CIDR */
    public static void wrongCidr(Language language) {
        language = language == null ? Global.DEFAULT_LANGUAGE : language;
        System.out.println(OutputFactory.getWrongCidr(language));
    }

    /** 非法子网掩码 wrong mask */
    public static void wrongMask(Language language) {
        language = language == null ? Global.DEFAULT_LANGUAGE : language;
        System.out.println(OutputFactory.getWrongMask(language));
    }
}
