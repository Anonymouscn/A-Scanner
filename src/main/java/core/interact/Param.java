package core.interact;

import constant.lang.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 参数实体 param object
 *
 * @author anonymous
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Param {

    /** 目标主机IPv4 | target host IPv4 */
    public String target;

    /** CIDR 网段 | CIDR */
    public String CIDR;

    /** 域名 domain */
    public String domain;

    /** 子网 IP | an IP of subnet */
    public String subnet;

    /** 子网掩码 mask */
    public String mask;

    /** 端口 port */
    public String port;

    /** 模式 mode */
    public String mode;

    /** 输出帮助 print help doc */
    public boolean help;

    /** 输出版本 print version */
    public boolean version;

    /** 语言 set language */
    public Language language;
}
