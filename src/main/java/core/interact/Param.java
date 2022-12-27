package core.interact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 参数实体
 *
 * @author anonymous
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Param {

    /** 目标主机 */
    public String target;

    /** 域名 */
    public String CIDR;

    /** 域名 */
    public String domain;

    /** 子网 ip */
    public String subnet;

    /** 子网掩码 */
    public String mask;

    /** 端口 */
    public String port;

    /** 模式 */
    public String mode;

    /** 输出帮助 */
    public boolean help;

    /** 输出版本 */
    public boolean version;
}
