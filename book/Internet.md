##hostname
##ping
##ifconfig
##iwconfig
##netstat
##nslookup
##traceroute->mtr
##telnet
##ethtool
##finger

## iperf

netstat从内部查看网络的状况，显示网络连接、路由表、接口等信息，我们使用netstat –lntup，l是监听，n是用数字格式显示，如用ip地址、端口号都用数字表示，t是tcp信息，u是udp信息，p是端口

nmap –sT 172.0.0.1
tcpdump -i eth0
wireshark
http://os.51cto.com/art/201402/429890.htm
4个强大的Linux服务器监控工具 http://os.51cto.com/art/201402/428680.htm

Nmap是一个广泛使用的安全扫描工具，自1997年发布以来，已经有十多年历史了，它使用各种特殊的数据包探测网络，包括创建一个IP地址映射，确定目标IP地址的操作系统类型，探测特定IP地址上开放的端口，它最基本的一个功能就是群ping，确定目标计算机上绑定的IP地址，如下面的命令就是群ping 192.168.1.1到192.168.1.255：

$ nmap -sP 192.168.1.1-255
五款好玩又好用的Linux网络测试和监控工具 http://os.51cto.com/art/201403/431950.htm
七大实用命令行工具 http://os.51cto.com/art/201004/196757_1.htm

linux监控工具 http://os.51cto.com/art/201005/200741.htm