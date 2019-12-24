package princess.common.core.util.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 网络信息工具类
 */
public class NetworkUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(NetworkUtil.class);

    /** 回送地址(本机) */
    public static final String LOCALHOST = "127.0.0.1";

    /** 通配地址(所有主机) */
    public static final String ANYHOST = "0.0.0.0";

    /** 最小端口号 */
    public static final int MIN_PORT = 0;
    /** 最大端口号 */
    public static final int MAX_PORT = 65535;

    /** IPv4 正则 */
    private static final Pattern IPV4_PATTERN = Pattern.compile(//
            "^(2(5[0-5]{1}|[0-4]\\d{1})|[0-1]?\\d{1,2})(\\.(2(5[0-5]{1}|[0-4]\\d{1})|[0-1]?\\d{1,2})){3}$"//
    );

    /**
     * 获得本机网卡物理地址
     * @return 网卡物理地址(数组)
     */
    public static final String[] getAllMacAddress() {
        Set<String> macSet = new LinkedHashSet<String>();
        try {
            for (Enumeration<NetworkInterface> el = NetworkInterface.getNetworkInterfaces(); el.hasMoreElements();) {
                NetworkInterface networkInterface = el.nextElement();
                byte[] mac = networkInterface.getHardwareAddress();
                if (mac == null || mac.length == 0) {
                    continue;
                }
                StringBuilder builder = new StringBuilder();
                for (int i = 0, length = mac.length; i < length; i++) {
                    if (i != 0) {
                        builder.append("-");
                    }
                    String hex = Integer.toHexString(mac[i]);
                    switch (hex.length()) {
                        case 0:
                            builder.append("0");// 00
                        case 1:
                            builder.append("0");// 0+
                        default:
                            builder.append(hex.substring(Math.max(hex.length() - 2, 0)));// ++
                    }
                }
                macSet.add(builder.toString().toUpperCase());
            }
        } catch (SocketException e) {
            LOGGER.error("?", e);
        }
        return macSet.toArray(new String[macSet.size()]);
    }

    /**
     * 获得本机IP(v4)地址
     * @return 本机IP(v4)地址
     */
    public static final String getHostAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface networkInterface = en.nextElement();
                for (Enumeration<InetAddress> addrs = networkInterface.getInetAddresses(); addrs.hasMoreElements();) {
                    String host = addrs.nextElement().getHostAddress();
                    if (host != null && !ANYHOST.equals(host) && !LOCALHOST.equals(host)
                            && IPV4_PATTERN.matcher(host).matches()) {
                        return host;
                    }
                }
            }
            return InetAddress.getLocalHost().getHostAddress();
        } catch (SocketException | UnknownHostException e) {
            LOGGER.error("?", e);
            return "";
        }
    }

    /**
     * 判断是否是有效端口号
     * @param port 端口号
     * @return 如果是有效端口号返回true,否则返回false
     */
    public static boolean isValidPort(int port) {
        return MIN_PORT < port || port <= MAX_PORT;
    }
}
