package com.tomhurry.utils;

import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 网络工具类
 *
 * @author taozhi
 * @date 2022/5/19 10:13
 * @since 1.0.0
 */
public class NetworkUtil {

    public static final String LOCAL_IP = "127.0.0.1";

    private NetworkUtil() {
    }

    /**
     * 获取本机ipv4地址
     *
     * @return 返回所有的ipv4地址
     */
    public static List<String> getIpv4Address() {
        List<String> ipv4AddressList = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
            while (enumeration.hasMoreElements()) {
                NetworkInterface networkInterface = enumeration.nextElement();
                if (!networkInterface.isVirtual() && networkInterface.isUp()) {
                    Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        InetAddress inetAddress = addresses.nextElement();
                        if (inetAddress instanceof Inet4Address) {
                            ipv4AddressList.add(inetAddress.getHostAddress());
                        }
                    }
                }
            }
        } catch (SocketException ignored) {
            return null;
        }
        return ipv4AddressList;
    }

    /**
     * 获取本机ipv6地址
     *
     * @return 返回所有的ipv6地址
     */
    public static List<String> getIpv6Address() {
        List<String> ipv6AddressList = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
            while (enumeration.hasMoreElements()) {
                NetworkInterface networkInterface = enumeration.nextElement();
                if (!networkInterface.isVirtual() && networkInterface.isUp()) {
                    Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        InetAddress inetAddress = addresses.nextElement();
                        if (inetAddress instanceof Inet6Address) {
                            ipv6AddressList.add(inetAddress.getHostAddress());
                        }
                    }
                }
            }
        } catch (SocketException ignored) {
            return null;
        }
        return ipv6AddressList;
    }


    public static void main(String[] args) {

        System.out.println("ipv4 is " + NetworkUtil.getIpv4Address());
        System.out.println("ipv6 is " + NetworkUtil.getIpv6Address());

    }

}
