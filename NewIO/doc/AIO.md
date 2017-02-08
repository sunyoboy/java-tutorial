JDK7 AIO初体验
AsynchronousChannel：支持异步通道，包括服务端AsynchronousServerSocketChannel和普通AsynchronousSocketChannel等实现。
CompletionHandler：用户处理器。定义了一个用户处理就绪事件的接口，由用户自己实现，异步io的数据就绪后回调该处理器消费或处理数据。
AsynchronousChannelGroup：一个用于资源共享的异步通道集合。处理IO事件和分配给CompletionHandler。(具体这块还没细看代码，后续再分析这块)

以一个简单监听服务端为例，基本过程是：
1.    启动一个服务端通道
2.    定义一个事件处理器，用户事件完成的时候处理，如消费数据。
3.    向系统注册一个感兴趣的事件，如接受数据，并把事件完成的处理器传递给系统。
4.    都已经交待完毕，可以只管继续做自己的事情了，操作系统在完成事件后通过其他的线程会自动调用处理器完成事件处理。

以下用一个例子来简单实现，一个服务端和客户端。服务端监听客户端的消息，并打印出来。