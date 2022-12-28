package config;

import constant.lang.Language;

/** 全局配置 global config */
public class Global {

    /** 版本号 version */
    public static final String VERSION = "v1.0.0";

    /** 作者 author */
    public static final String AUTHOR = "anonymous";

    /** github */
    public static final String GITHUB = "https://github.com/Anonymouscn/A-Scanner";

    /** 默认语言 default language */
    public static final Language DEFAULT_LANGUAGE = Language.ENGLISH;

    /** 运行语言 runtime language */
    public static Language RUNTIME_LANGUAGE = null;

    /** 中文帮助文档路径 path of Chinese HELP */
    public static final String HELP_ZH_PATH = "/help-zh.txt";

    /** 英文帮助文档路径 path of English HELP */
    public static final String HELP_EN_PATH = "/help-en.txt";
}
