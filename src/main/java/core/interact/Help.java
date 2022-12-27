package core.interact;

import config.Global;

/**
 * 帮助 help
 *
 * @author anonymous
 */
public class Help {

    public static void print() {
        System.out.println(" arguments(参数):");
        System.out.println(" -t / --target 目标主机ip");
        System.out.println(" -c / --cidr cidr域");
        System.out.println(" -d / --domain 域名");
        System.out.println(" -si / --subnet 所在子域其一 ip");
        System.out.println(" -sm / --mask 所在子域子网掩码");
        System.out.println(" -p / --port 端口: simple(常用端口) all(所有端口)");
        System.out.println(" -m / --mode 模式: Internet(全网扫描) Target(目标 IP 扫描) Cidr(网域扫描) Domain(目标域名扫描) Subnet(子域扫描)");
        System.out.println(" -v / --version 版本");
        System.out.println(" -h / --help 帮助");
        System.out.println(" jvm arguments(jvm参数):");
        System.out.println(" -Xss 为 jvm 启动的每个线程分配的内存大小");
        System.out.println(" -Xms 为 jvm 启动时分配堆初始(最小)内存");
        System.out.println(" -Xmx 为 jvm 运行过程中分配堆最大内存\n");
        System.out.println(" usages(示例用法):");
        System.out.println(" 全网扫描指定端口: $ java -jar A-Scanner-" + Global.VERSION + ".jar -m internet -p <port>");
        System.out.println(" 全网扫描常用端口: $ java -jar A-Scanner-" + Global.VERSION + ".jar -m internet -p simple");
        System.out.println(" 全网扫描所有端口: $ java -jar A-Scanner-" + Global.VERSION + ".jar -m internet -p all");
        System.out.println(" 扫描目标主机常用端口: $ java -jar A-Scanner-" + Global.VERSION + ".jar -m target -t <ip> -p simple");
        System.out.println(" 扫描目标主机全部端口: $ java -jar A-Scanner-" + Global.VERSION + ".jar -m target -t <ip> -p all");
        System.out.println(" 扫描目标主机指定端口: $ java -jar A-Scanner-" + Global.VERSION + ".jar -m target -t <ip> -p <port>");
        System.out.println(" 扫描 CIDR 网域存活主机常用端口: $ java -jar A-Scanner-" + Global.VERSION + ".jar -m cidr -c <cidr> -p simple");
        System.out.println(" 扫描 CIDR 网域存活主机指定端口: $ java -jar A-Scanner-" + Global.VERSION + ".jar -m cidr -c <cidr> -p <port>");
        System.out.println(" 扫描 CIDR 网域存活主机全部端口: $ java -jar A-Scanner-" + Global.VERSION + ".jar -m cidr -c <cidr> -p all");
        System.out.println(" 扫描域名下所有主机常用端口: $ java -jar A-Scanner-" + Global.VERSION + ".jar -m domain -d <domain> -p simple");
        System.out.println(" 扫描域名下所有主机指定端口: $ java -jar A-Scanner-" + Global.VERSION + ".jar -m domain -d <domain> -p <port>");
        System.out.println(" 扫描域名下所有主机全部端口: $ java -jar A-Scanner-" + Global.VERSION + ".jar -m domain -d <domain> -p all");
        System.out.println(" 扫描子域下所有存活主机常用端口: $ java -jar A-Scanner-" + Global.VERSION + ".jar -m subnet -sn <ip> -sm <mask> -p simple");
        System.out.println(" 扫描子域下所有存活主机指定端口: $ java -jar A-Scanner-" + Global.VERSION + ".jar -m subnet -sn <ip> -sm <mask> -p <port>");
        System.out.println(" 扫描子域下所有存活主机所有端口: $ java -jar A-Scanner-" + Global.VERSION + ".jar -m subnet -sn <ip> -sm <mask> -p all");
        System.out.println(" 获取域名 whois 信息: $ java -jar A-Scanner-" + Global.VERSION + ".jar -m whois -d <domain>");
        System.out.println(" 交互模式: $ java -jar A-Scanner-v1.0.0.jar");
    }
}
