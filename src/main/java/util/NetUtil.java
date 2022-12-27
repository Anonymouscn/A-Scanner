package util;

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

    public static boolean checkIP(String ip) {
        return false;
    }

    public static boolean checkDomain(String domain) {
        return false;
    }

    public static boolean checkCidr(String cidr) {
        return false;
    }

    public static void main(String[] args) {
        System.out.println(ipToNumber("127.0.0.1"));
    }
}
