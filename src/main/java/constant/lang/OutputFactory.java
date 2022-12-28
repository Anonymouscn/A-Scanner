package constant.lang;

/**
 * 输出工厂 output factory
 *
 * @author anonymous
 */
public class OutputFactory {

    public static String getLanguageSelect() {
        return "\n[1] 中文\n[2] English\n\n[$] 选择 | select: ";
    }

    public static String getMainMenu(Language language) {
        return language == Language.CHINESE ? Chinese.MAIN_MENU : English.MAIN_MENU;
    }

    public static String getScanType(Language language) {
        return language == Language.CHINESE ? Chinese.SCAN_TYPE : English.SCAN_TYPE;
    }

    public static String getSELECT(Language language) {
        return language == Language.CHINESE ? Chinese.SELECT : English.SELECT;
    }

    public static String getInputIP(Language language) {
        return language == Language.CHINESE ? Chinese.INPUT_IP : English.INPUT_IP;
    }

    public static String getInputDomain(Language language) {
        return language == Language.CHINESE ? Chinese.INPUT_DOMAIN : English.INPUT_DOMAIN;
    }

    public static String getInputCIDR(Language language) {
        return language == Language.CHINESE ? Chinese.INPUT_CIDR : English.INPUT_CIDR;
    }

    public static String getInputPort(Language language) {
        return language == Language.CHINESE ? Chinese.INPUT_PORT : English.INPUT_PORT;
    }

    public static String getInputMask(Language language) {
        return language == Language.CHINESE ? Chinese.INPUT_MASK : English.INPUT_MASK;
    }

    public static String getInputSubnet(Language language) {
        return language == Language.CHINESE ? Chinese.INPUT_SUBNET : English.INPUT_SUBNET;
    }

    public static String getGoodBye(Language language) {
        return language == Language.CHINESE ? Chinese.GOODBYE : English.GOODBYE;
    }

    public static String getOpeningPort(Language language) {
        return  language == Language.CHINESE ? Chinese.OPENING_PORT : English.OPENING_PORT;
    }

    public static String getGuess(Language language) {
        return  language == Language.CHINESE ? Chinese.GUESS : English.GUESS;
    }

    public static String getWrongExpress(Language language) {
        return language == Language.CHINESE ? Chinese.WRONG_EXPRESS : English.WRONG_EXPRESS;
    }

    public static String getWrongPort(Language language) {
        return language == Language.CHINESE ? Chinese.WRONG_PORT : English.WRONG_PORT;
    }

    public static String getWrongIpv4(Language language) {
        return language == Language.CHINESE ? Chinese.WRONG_IPV4 : English.WRONG_IPV4;
    }

    public static String getWrongDomain(Language language) {
        return language == Language.CHINESE ? Chinese.WRONG_DOMAIN : English.WRONG_DOMAIN;
    }

    public static String getWrongCidr(Language language) {
        return language == Language.CHINESE ? Chinese.WRONG_CIDR : English.WRONG_CIDR;
    }

    public static String getWrongMask(Language language) {
        return language == Language.CHINESE ? Chinese.WRONG_MASK : English.WRONG_MASK;
    }
}
