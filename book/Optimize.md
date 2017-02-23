http://www.geeksforgeeks.org/java/
http://mingxinglai.com/cn/2013/06/linux-performance-analysis-and-tools/
1. 衡量系统现状
  先粗粒度的划分，再细粒度的查找具体的点
2. 设定调优目标
3. 寻找性能瓶颈
  1、根据现状信息衡量目前系统（资源）的瓶颈
    CPU、I/O、网络？
  2、寻找消耗资源的代码
    （1）CPU
        us——>代码循环、计算量大；GC频繁
        sy——>线程上下文——>线程多 & I/O多等待
    （2）文件I/O
        并发读写多；文件打
    （3）内存
        JVM堆内存——创建过多对象；持有不必要的引用
        JVM堆外内存——创建太多线程；使用的ByteBuffer未释放
    （4）网络I/O
        读写网络操作太频繁
  3、寻找执行慢的原因和代码
    访问量不大——锁竞争激烈；未充分使用硬件资源；数据量大
4. 性能调优
  1、针对系统资源消耗过多的优化
    （1）CPU
        us——适当释放CPU；wait/notify机制；JVM和内存的优化
        sy——降低锁竞争
    （2）文件I/O
    （3）内存
  2、资源消耗不多，仍然执行慢

Current Fields:  AEHIOQTWKNMbcdfgjplrsuvyzX  for window 1:Def
Toggle fields via field letter, type any other key to return 
#top 
1. 查看每个CPU的使用率
  top之后按数字1
  在top窗口中，按数字键“1”就可以看到每个核心的使用情况。
2. 查看top列的含义及是否显示控制
  top之后按f键
3. 查看CPU命令
  lscpu
4. 下面是如何利用你的CPU有一个应用程序的示例：
    # md5sum /dev/zero &
    这个'forks'md5sum然后加工成的背景。
* A: PID        = Process Id
* E: USER       = User Name
* H: PR         = Priority
* I: NI         = Nice value
* O: VIRT       = Virtual Image (kb)
* Q: RES        = Resident size (kb)
* T: SHR        = Shared Mem size (kb)
* W: S          = Process Status
* K: %CPU       = CPU usage
* N: %MEM       = Memory usage (RES)
* M: TIME+      = CPU Time, hundredths
  b: PPID       = Parent Process Pid
  c: RUSER      = Real user name
  d: UID        = User Id
  f: GROUP      = Group Name
  g: TTY        = Controlling Tty
  j: P          = Last used cpu (SMP)
  p: SWAP       = Swapped size (kb)
  l: TIME       = CPU Time
  r: CODE       = Code size (kb)
  s: DATA       = Data+Stack size (kb)
  u: nFLT       = Page Fault count
  v: nDRT       = Dirty Pages count
  y: WCHAN      = Sleeping in Function
  z: Flags      = Task Flags <sched.h>
* X: COMMAND    = Command name/line
5. 指定对某个或某些进程进行TOP信息显示
  得到进程的pid ——>  Command: pidof mysqld
  top查看指定pid ——> Command: top -p p1, p2
6. pidstat
7. iostat


    public int climbStairs(int n) {
        int answer = 0;
        int nextToLast = 1;
        int last = 2;
        if(n == 1) {
            answer = 1;
            last = 1;
        } else if (n == 2) {
            answer = 2;
            last = 2;
            nextToLast = 1;
        }
        for(int i=3; i<n; i++) {
            answer = last + nextToLast;
            last = answer;
            nextToLast = last;
        }
        return answer;
    }
