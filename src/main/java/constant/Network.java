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

    /** socket 超时时间 | socket timeout */
    public static final int SOCKET_TIMEOUT = 200;

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

    /** 回环地址 | loopback IP address */
    public static final String LOOPBACK = "127.0.0.1";

    /** 常用开放端口 ｜ commonly used open ports */
    public static CommonPort[] commonPorts = new CommonPort[]{
            new CommonPort(21, "ftp/tftp/vsftpd"),
            new CommonPort(22, "ssh"),
            new CommonPort(23, "telnet"),
            new CommonPort(25, "smtp"),
            new CommonPort(53, "dns"),
            new CommonPort(67, "dhcp"),
            new CommonPort(68, "dhcp"),
            new CommonPort(80, "http"),
            new CommonPort(110, "pop3"),
            new CommonPort(139, "Samba"),
            new CommonPort(143, "imap"),
            new CommonPort(161, "snmp"),
            new CommonPort(389, "ldap"),
            new CommonPort(443, "https"),
            new CommonPort(445, "smb"),
            new CommonPort(512, "Linux Rexec"),
            new CommonPort(513, "Linux Rexec"),
            new CommonPort(514, "Linux Rexec"),
            new CommonPort(873, "rsync"),
            new CommonPort(1080, "socket"),
            new CommonPort(1352, "lotus domino"),
            new CommonPort(1433, "mssql"),
            new CommonPort(1521, "oracle"),
            new CommonPort(2049, "nfs"),
            new CommonPort(2181, "zookeeper"),
            new CommonPort(2375, "docker remote api"),
            new CommonPort(3306, "mysql"),
            new CommonPort(3389, "rdp"),
            new CommonPort(4848, "glassfish console"),
            new CommonPort(5000, "sybase/DB2"),
            new CommonPort(5432, "postgresql"),
            new CommonPort(5632, "pcanywhere"),
            new CommonPort(5900, "vnc"),
            new CommonPort(6379, "redis"),
            new CommonPort(7001, "weblogic"),
            new CommonPort(7002, "weblogic"),
            new CommonPort(8069, "zabbix"),
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
            new CommonPort(9090, "Websphere console"),
            new CommonPort(9200, "elasticsearch"),
            new CommonPort(9300, "elasticsearch"),
            new CommonPort(11211, "memcached"),
            new CommonPort(27017, "mongodb"),
            new CommonPort(27018, "mongodb")
    };

    /**
     * 常用端口类定义 define of port object
     */
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class CommonPort {
        // 端口 port
        public int port;
        // 信息 info
        public String info;
    }
}
