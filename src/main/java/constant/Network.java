package constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 网络常量 constant of Network
 *
 * @author anonymous
 */
public class Network {

    /** socket 超时时间 */
    public static final int SOCKET_TIMEOUT = 3000;

    /** 开始端口 start port*/
    public static final int PORT_START = 0;

    /** 截止端口 end port */
    public static final int PORT_END = 65535;

    /** 开始 | start IP -> 默认 | default 0.0.0.1 */
    public static final Long IP_START = 1L;

    /** 截止ip -> 默认 255.255.255.254 */
    public static final Long IP_END = 4294967294L;

    /** 私有 A 类地址开始范围 -> 10.0.0.0 */
    public static final Long PRIVATE_A_START = 167772161L;

    /** 私有 A 类地址结束范围 -> 10.255.255.255 */
    public static final Long PRIVATE_A_END = 184549375L;

    /** 私有 B 类地址开始范围 -> 172.16.0.0 */
    public static final Long PRIVATE_B_START = 2886729728L;

    /** 私有 B 类地址结束范围 -> 172.31.255.255 */
    public static final Long PRIVATE_B_END = 2887778303L;

    /** 私有 C 类地址开始范围 -> 192.168.0.0 */
    public static final Long PRIVATE_C_START = 3232235520L;

    /** 私有 C 类地址结束范围 -> 192.168.255.255 */
    public static final Long PRIVATE_C_END = 3232301055L;

    /** 本地地址 local loopback IP address */
    public static final Long LOCALHOST = 2130706433L;

    /** 常用开放端口 ｜ commonly used open ports */
    public static CommonPort[] commonPorts = new CommonPort[]{
            new CommonPort(21, "ftp/tftp/vsftpd 文件传输协议"),
            new CommonPort(22, "ssh 远程连接"),
            new CommonPort(23, "telnet 远程连接"),
            new CommonPort(25, "smtp 邮件服务"),
            new CommonPort(53, "dns 域名解析服务"),
            new CommonPort(67, "dhcp 服务"),
            new CommonPort(68, "dhcp 服务"),
            new CommonPort(80, "http web 服务"),
            new CommonPort(110, "pop3"),
            new CommonPort(139, "Samba服务"),
            new CommonPort(143, "imap"),
            new CommonPort(161, "snmp协议"),
            new CommonPort(389, "ldap目录访问协议"),
            new CommonPort(443, "https web服务"),
            new CommonPort(445, "smb服务"),
            new CommonPort(512, "Linux Rexec 服务"),
            new CommonPort(513, "Linux Rexec 服务"),
            new CommonPort(514, "Linux Rexec 服务"),
            new CommonPort(873, "rsync 服务"),
            new CommonPort(1080, "socket"),
            new CommonPort(1352, "lotus domino 邮件服务"),
            new CommonPort(1433, "mssql"),
            new CommonPort(1521, "oracle"),
            new CommonPort(2049, "nfs 服务"),
            new CommonPort(2181, "zookeeper 服务"),
            new CommonPort(2375, "docker remote api"),
            new CommonPort(3306, "mysql"),
            new CommonPort(3389, "rdp 远程桌面连接"),
            new CommonPort(4848, "glassfish 控制台"),
            new CommonPort(5000, "sybase/DB2 数据库"),
            new CommonPort(5432, "postgresql 数据库"),
            new CommonPort(5632, "pcanywhere 服务"),
            new CommonPort(5900, "vnc"),
            new CommonPort(6379, "redis 数据库"),
            new CommonPort(7001, "weblogic"),
            new CommonPort(7002, "weblogic"),
            new CommonPort(8069, "zabbix 服务"),
            new CommonPort(8161, "activemq"),
            new CommonPort(8080, "Jboss/Tomcat/Resin/socket"),
            new CommonPort(8081, "Jboss/Tomcat/Resin/socket"),
            new CommonPort(8082, "Jboss/Tomcat/Resin/socket"),
            new CommonPort(8083, "Jboss/Tomcat/Resin/socket/influxDB"),
            new CommonPort(8084, "Jboss/Tomcat/Resin/socket"),
            new CommonPort(8085, "Jboss/Tomcat/Resin/socket"),
            new CommonPort(8086, "Jboss/Tomcat/Resin/socket/influxDB"),
            new CommonPort(8087, "Jboss/Tomcat/Resin/socket"),
            new CommonPort(8088, "Jboss/Tomcat/Resin/socket"),
            new CommonPort(8089, "Jboss/Tomcat/Resin/socket"),
            new CommonPort(8090, "Jboss/Tomcat/Resin/socket"),
            new CommonPort(8443, "web"),
            new CommonPort(8888, "web"),
            new CommonPort(9000, "fastcgi"),
            new CommonPort(9090, "Websphere 控制台"),
            new CommonPort(9200, "elasticsearch"),
            new CommonPort(9300, "elasticsearch"),
            new CommonPort(11211, "memcached"),
            new CommonPort(27017, "mongodb"),
            new CommonPort(27018, "mongodb")
    };

    /**
     * 常用端口类定义
     */
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class CommonPort {
        // 端口
        public int port;
        // 信息
        public String info;
    }
}
