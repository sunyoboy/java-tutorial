CLOSED: CLOSED 状态不是一个真正的状态，是假想的一个起点或终点。
	起始点，在超时或者连接关闭时候进入此状态。

LISTEN: 服务器等待建立连接的状态。
	服务器经过 socket，bind，listen 函数之后进入此状态，开始监听客户端发过来的连接请求。此称为应用程序被动打开（等到客户端连接请求）。

SYN_SENT: 客户端发起连接（主动打开），状态变为SYN_SENT。如果SYN 超时，或服务器不存在直接变为CLOSED状态。
	第一次握手发生阶段，客户端发起连接。客户端调用 connect，发送 SYN 给服务器端，然后进入SYN_SENT 状态，等待服务器端确认（三次握手中的第二个报文）。如果服务器端不能连接，则直接进入CLOSED状态。

SYN_RCVD: 服务端收到SYN包，变为SYN_RCVD状态。

ESTABLISHED: 完成三次握手，进入连接建立状态。C/S可以进行数据传输了。

FIN_WAIT_1: 客户端执行主动关闭，发送完FIN包之后便进入FIN_WAIT_1状态。

FIN_WAIT_2: 客户端发送FIN包之后，收到ACK，进入FIN_WAIT_2 状态（是半关闭状态）。

CLOSING: 应用程序两端同时发出FIN请求。

TIME_WAIT: 
	有3种情况。
		1. 从FIN_WAIT_2 进入，客户端收到服务器发送的FIN包后进入TIME_WAIT 状态。
		2. 从CLOSING 状态进入，这是同时关闭的状态，同时发起FIN 请求，同时接收并做了ACK的回复。
		3. 从FIN_WAIT_1 进入，收到对端的FIN，ACK，并回复ACK。FIN和ACK是一块到的。

LAST_ACK: 被动的一端发送FIN包之后，处于LAST_ACK 状态。

CLOSE_WAIT: 接收到FIN之后，被动的一端进入CLOSE_WAIT状态，并回复ACK。


2MSL等待状态
TIME_WAIT状态也成为2MSL状态，设立这个状态的是因为来保证可靠的实现TCP全双工的关闭，MSL指的是报文最大的生存时间。对于一个具体实现所给定的MSL值，处理原则是：当TCP执行一个主动关闭，并发回最后一个ACK，该连接必须在TIME_WAIT状态停留的时间为2MSL。这样可以让TCP再次发送最后的ACK，以防这个ACK丢失！

处于TIME_WAIT状态的连接，这个socket的四要素（Source IP,Source Port，Dest IP,Dest Port）不能被使用，只能在2MSL结束后才能再次被使用，其实这个地方，严格意义上来说是Port不能使用。

某些实现中，可以使用SO_REUSEADDR选项，可以让调用者对处于2MSL等待的本地端口进行赋值，但是我们将看到TCP原则上扔将避免使用仍处于2MSL连接中的端口。

对于客户端来讲，主动关闭是处于2MSL状态下，但是如果不bind对应的PORT其实看起来没什么影响的，因为下一次启动客户端，就应该分配到别的PORT了，但是对于bind Port的客户端或者服务器来讲，肯定是会报告Address already in use的错误。

有一个怪异的问题，别以为SO_REUSEADDR就万事大吉了，四要素处于2MSL，将不能被使用，尽管许多具体实现中允许一个进程重新使用仍处于2MSL等待的端口，但是TCP不能允许一个新的连接建立在相同的四元素上【有遇到过这样的问题】。

为什么需要TIME WAIT 状态，原因有二：

1、保证TCP协议的全双工连接能够可靠关闭（防止最后FIN的ACK在网络中丢失，如果最后发送FIN的那段，在规定时间内没有收到FIN的ACK，就会重发FIN，由于有2MSL的等待时间，那么另一端就可以再次收到FIN，再次发送一个ACK）
2、保证这次连接的迷途数据段从网络中消失（防止立即建立这个连接的替身，如果没有2 MSL等待迷途数据消失，那么有可能上一个连接的数据被当作当前连接的数据）


Reference:
	https://blog.csdn.net/wenqian1991/article/details/40110703	