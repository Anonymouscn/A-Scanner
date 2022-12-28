package core.interact;

import async.TaskPool;
import async.task.NetScanTask;
import async.task.PortScanTask;
import com.google.common.base.Strings;
import config.Global;
import constant.lang.Language;
import constant.param.Mode;
import constant.Network;
import constant.param.Port;
import core.scanner.PortScanner;
import util.NetUtil;
import java.util.stream.IntStream;

/**
 * 参数解释器
 *
 * @author anonymous
 */
public class ParamInterpreter {

    /**
     * 读取参数 read params
     *
     * @param args 参数数组 param array
     * @return 具体参数
     */
    public static Param read(String[] args) {
        Param param = new Param();
        try {
            IntStream.range(0, args.length).forEach(i -> {
                if("-l".equals(args[i]) || "--lang".equals(args[i])) {
                    param.language = "zh".equals(args[++i]) ? Language.CHINESE : "en".equals(args[i]) ? Language.ENGLISH : Global.DEFAULT_LANGUAGE;
                }
            });
        } catch (ArrayIndexOutOfBoundsException e) {
            NetUtil.wrongExpress(Global.DEFAULT_LANGUAGE);
            System.exit(0);
        }
        try {
            IntStream.range(0, args.length).forEach(i -> {
                // 模式 mode
                if("-m".equals(args[i]) || "--mode".equals(args[i])) {
                    param.setMode(args[++i]);
                    // 目标主机 target host
                } else if("-t".equals(args[i]) || "--target".equals(args[i])) {
                    if(!NetUtil.checkIPv4(args[++i])) {
                        NetUtil.wrongIpv4(param.language);
                        System.exit(0);
                    }
                    param.setTarget(args[i]);
                    // 端口 port
                } else if("-p".equals(args[i]) || "--port".equals(args[i])) {
                    param.setPort(args[++i]);
                    // 域名 domain
                } else if("-d".equals(args[i]) || "--domain".equals(args[i])) {
                    if(!NetUtil.checkDomain(args[++i])) {
                        NetUtil.wrongDomain(param.language);
                        System.exit(0);
                    }
                    param.setDomain(args[i]);
                    // 子网其一 IP |
                } else if("-sn".equals(args[i]) || "--subnet".equals(args[i])) {
                    if(!NetUtil.checkIPv4(args[++i])) {
                        NetUtil.wrongIpv4(param.language);
                        System.exit(0);
                    }
                    param.setSubnet(args[i]);
                    // 子网掩码 subnet mask
                } else if("-sm".equals(args[i]) || "--mask".equals(args[i])) {
                    if(!NetUtil.checkMask(args[++i])) {
                        NetUtil.wrongMask(param.language);
                        System.exit(0);
                    }
                    param.setMask(args[i]);
                    // CIDR 网段
                } else if("-c".equals(args[i]) || "--cidr".equals(args[i])) {
                    if(!NetUtil.checkCidr(args[++i])) {
                        NetUtil.wrongCidr(param.language);
                        System.exit(0);
                    }
                    param.setCIDR(args[i]);
                    // 版本 version flag
                } else if("-v".equals(args[i]) || "--version".equals(args[i])) {
                    param.setVersion(true);
                    // 帮助 help flag
                } else if("-h".equals(args[i]) || "--help".equals(args[i])) {
                    param.setHelp(true);
                }
            });
        } catch (ArrayIndexOutOfBoundsException e) {
            NetUtil.wrongExpress(param.language);
        }
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
            Help.print(param.language);
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
                Global.RUNTIME_LANGUAGE = param.language;
                MainMenu.handle();
            } else {
                NetUtil.wrongExpress(Global.RUNTIME_LANGUAGE);
            }
            return;
        }
        Global.RUNTIME_LANGUAGE = param.language == null ? Global.DEFAULT_LANGUAGE : param.language;
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
                                NetUtil.wrongPort(Global.RUNTIME_LANGUAGE);
                                break;
                            }
                            NetScanTask.runInternetScanTask(TaskPool.maxThreads, port);
                            break;
                    }
                } else {
                    NetUtil.wrongExpress(Global.RUNTIME_LANGUAGE);
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
                                NetUtil.wrongPort(Global.RUNTIME_LANGUAGE);
                                break;
                            }
                            new PortScanner(param.target).scan(port);
                            break;
                    }
                } else {
                    NetUtil.wrongExpress(Global.RUNTIME_LANGUAGE);
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
                                NetUtil.wrongPort(Global.RUNTIME_LANGUAGE);
                                break;
                            }
                            NetScanTask.runCidrScanTask(TaskPool.maxThreads, port, param.CIDR);
                            break;
                    }
                } else {
                    NetUtil.wrongExpress(Global.RUNTIME_LANGUAGE);
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
                                NetUtil.wrongPort(Global.RUNTIME_LANGUAGE);
                                break;
                            }
                            NetScanTask.runDomainScanTask(TaskPool.maxThreads, port, param.domain);
                            break;
                    }
                } else {
                    NetUtil.wrongExpress(Global.RUNTIME_LANGUAGE);
                    System.exit(0);
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
                                NetUtil.wrongPort(Global.RUNTIME_LANGUAGE);
                                break;
                            }
                            NetScanTask.runSubnetScanTask(TaskPool.maxThreads, port, param.subnet, param.mask);
                            break;
                    }
                } else {
                    NetUtil.wrongExpress(Global.RUNTIME_LANGUAGE);
                }
                break;
                // whois 查询 | whois search
            case Mode.WHOIS:
                if(param.domain != null) {
                    NetScanTask.getDomainInfo(param.domain);
                } else {
                    NetUtil.wrongExpress(Global.RUNTIME_LANGUAGE);
                }
                break;
            default:
                NetUtil.wrongExpress(Global.RUNTIME_LANGUAGE);
                break;
        }
    }

    /**
     * 获取端口 get an input port
     *
     * @param str port string
     * @return 端口 port
     */
    private static int getPort(String str) {
        int port;
        port = Integer.parseInt(str);
        if(port < Network.PORT_START || port > Network.PORT_END) {
            throw new IllegalArgumentException();
        }
        return port;
    }
}
