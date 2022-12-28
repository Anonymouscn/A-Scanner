package core.interact;

import config.Global;
import constant.lang.Language;
import util.IOUtil;

/**
 * 帮助 help
 *
 * @author anonymous
 */
public class Help {

    /**
     * 打印帮助文档 print help doc
     *
     * @param language 输出语言 output language
     */
    public static void print(Language language) {
        String path = language == Language.CHINESE ? Global.HELP_ZH_PATH : Global.HELP_EN_PATH;
        IOUtil.printContents(path);
    }
}
