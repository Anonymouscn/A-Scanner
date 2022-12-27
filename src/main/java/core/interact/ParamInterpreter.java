package core.interact;

import async.TaskPool;
import async.task.NetScanTask;
import async.task.PortScanTask;
import com.google.common.base.Strings;
import config.Global;
import constant.Mode;
import constant.Network;
import constant.Port;
import core.scanner.PortScanner;
import java.util.stream.IntStream;

/**
 * 参数解释器
 *
 * @author anonymous
 */
public class ParamInterpreter {

    /**
     * 读取参数
     *
     * @param args 参数数组
     * @return 具体参数
     */
    public static Param read(String[] args) {
        Param param = new Param();
        IntStream.range(0, args.length).forEach(i -> {
            if("-m".equals(args[i]) || "--mode".equals(args[i])) {
                param.setMode(args[++i]);
            } else if("-t".equals(args[i]) || "--target".equals(args[i])) {
                param.setTarget(args[++i]);
            } else if("-p".equals(args[i]) || "--port".equals(args[i])) {
                param.setPort(args[++i]);
            } else if("-d".equals(args[i]) || "--domain".equals(args[i])) {
                param.setDomain(args[++i]);
            } else if("-sn".equals(args[i]) || "--subnet".equals(args[i])) {
                param.setSubnet(args[++i]);
            } else if("-sm".equals(args[i]) || "--mask".equals(args[i])) {
                param.setMask(args[++i]);
            } else if("-c".equals(args[i]) || "--cidr".equals(args[i])) {
                param.setCIDR(args[++i]);
            } else if("-v".equals(args[i]) || "--version".equals(args[i])) {
                param.setVersion(true);
            } else if("-h".equals(args[i]) || "--help".equals(args[i])) {
                param.setHelp(true);
            }
        });
        return param;
    }

    /**
     * 执行指令 action the command
     *
     * @param param 具体参数 specific parameters
     */
    public static void action(Param param) {
        // 版本 print version
        if(param.version) {
            return;
        }
        // 帮助 print help
        if(param.help) {
            Help.print();
            return;
        }
        // 交互模式 interact mode
        if(param.mode == null) {
            if(param.port== null &&
                            param.mask == null &&
                            param.domain == null &&
                            param.subnet == null &&
                            param.target == null &&
                            param.CIDR == null) {
                MainMenu.handle();
            } else {
                wrongExpress();
            }
            return;
        }
        // 模式 scan mode
        switch (param.mode) {
            // 因特网扫描 scan on the Internet
            case Mode.INTERNET:
                if(!Strings.isNullOrEmpty(param.port)) {
                    switch (param.port) {
                        case Port.ALL:
                            NetScanTask.runInternetFullScanTask(TaskPool.maxThreads);
                            break;
                        case Port.SIMPLE:
                            NetScanTask.runInternetSimpleScanTask(TaskPool.maxThreads);
                            break;
                        default:
                            int port;
                            try {
                                port = getPort(param.port);
                            } catch (Exception e) {
                                wrongPort();
                                break;
                            }
                            NetScanTask.runInternetScanTask(TaskPool.maxThreads, port);
                            break;
                    }
                } else {
                    wrongExpress();
                }
                break;
                // 目标主机扫描 scan on a host
            case Mode.TARGET:
                if(param.target != null) {
                    switch (param.port) {
                        case Port.ALL:
                            PortScanTask.runSingleTargetScanTask(TaskPool.maxThreads, param.target);
                            break;
                        case Port.SIMPLE:
                            PortScanTask.runSingleTargetSimpleScanTask(TaskPool.maxThreads, param.target);
                            break;
                        default:
                            int port;
                            try {
                                port = getPort(param.port);
                            } catch (Exception e) {
                                wrongPort();
                                break;
                            }
                            new PortScanner(param.target).scan(port);
                            break;
                    }
                } else {
                    wrongExpress();
                }
                break;
                // CIDR 网段扫描
            case Mode.CIDR:
                if(param.CIDR != null && param.port != null) {
                    switch (param.port) {
                        case Port.ALL:
                            NetScanTask.runCidrFullScanTask(TaskPool.maxThreads, param.CIDR);
                            break;
                        case Port.SIMPLE:
                            NetScanTask.runCidrSimpleScanTask(TaskPool.maxThreads, param.CIDR);
                            break;
                        default:
                            int port;
                            try {
                                port = getPort(param.port);
                            } catch (Exception e) {
                                wrongPort();
                                break;
                            }
                            NetScanTask.runCidrScanTask(TaskPool.maxThreads, port, param.CIDR);
                            break;
                    }
                } else {
                    wrongExpress();
                }
                break;
                // 域名扫描 scan on a domain
            case Mode.DOMAIN:
                if(param.domain != null && param.port != null) {
                    switch (param.port) {
                        case Port.ALL:
                            NetScanTask.runDomainFullScanTask(TaskPool.maxThreads, param.domain);
                            break;
                        case Port.SIMPLE:
                            NetScanTask.runDomainSimpleScanTask(TaskPool.maxThreads, param.domain);
                            break;
                        default:
                            int port;
                            try {
                                port = getPort(param.port);
                            } catch (Exception e) {
                                wrongPort();
                                break;
                            }
                            NetScanTask.runDomainScanTask(TaskPool.maxThreads, port, param.domain);
                            break;
                    }
                } else {
                    wrongExpress();
                }
                break;
                // 子网扫描 scan on a subnet
            case Mode.SUBNET:
                if(param.subnet != null && param.mask != null && param.port != null) {
                    switch (param.port) {
                        case Port.ALL:
                            NetScanTask.runSubnetFullScanTask(TaskPool.maxThreads, param.subnet, param.mask);
                            break;
                        case Port.SIMPLE:
                            NetScanTask.runSubnetSimpleScanTask(TaskPool.maxThreads, param.subnet, param.mask);
                            break;
                        default:
                            int port;
                            try {
                                port = getPort(param.port);
                            } catch (Exception e) {
                                wrongPort();
                                break;
                            }
                            NetScanTask.runSubnetScanTask(TaskPool.maxThreads, port, param.subnet, param.mask);
                            break;
                    }
                } else {
                    wrongExpress();
                }
                break;
                // whois 查询 | whois search
            case Mode.WHOIS:
                if(param.domain != null) {
                    NetScanTask.getDomainInfo(param.domain);
                } else {
                    wrongExpress();
                }
                break;
            default:
                wrongExpress();
                break;
        }
    }

    private static int getPort(String str) {
        int port;
        port = Integer.parseInt(str);
        if(port < Network.PORT_START || port > Network.PORT_END) {
            throw new IllegalArgumentException();
        }
        return port;
    }

    private static void wrongExpress() {
        System.out.println("无法解析的命令行表达式，详细查看帮助: $ java -jar A-Scanner-" + Global.VERSION + ".jar -h");
    }

    private static void wrongPort() {
        System.out.println("非法端口值，详细查看帮助: $ java -jar A-Scanner-" + Global.VERSION + ".jar -h");
    }
}
