10.1
  (1) 集合框架父接口是Iterable
    * 迭代循环,仅有iterator()方法，通过此方法获取Iterator对象进行循环处理。
  (2) List
    * 根据索引操作数据，允许数据重复。
    * 非并发实现类ArrayList，非线程安全
    * Vector线程安全，不支持Iterator并发删除，并发删除时报ConcurrentModificationException异常。
    * Vector有一个子类Stack
  (3) Queue
    * 非并发实现类PriorityQueue,是基于优先级的无界优先级队列。
    * Deque(double ended queue, 双端队列),非并发实现类有ArrayDeque和LinkedList。
    * 如果只想实现从队列两端获取数据则使用ArrayDeque
    * 如果想实现从队列两端获取数据时还可以根据索引的位置操作数据则使用LinkedList
  (4) Set
    * 元素不允许重复，排序为自然排列，防止元素重复需要重写hashCode()和equals()方法
    * HashSet 不支持并发，默认无序
    * LinkedHashSet 有序
    * TreeSet 实现了Set、SortedSet和NavigableSet接口,可以获取Set内容的子集，以比较的方式获取的子集，支持获取表头和表尾的数据。
10.2 非阻塞队列
  说明：当队列里没有数据， 操作队列会出现异常或返回 null，不等待或阻塞。
  (1) ConcurrentHashMap
    * 支持并发操作的Map，替换线程非安全的HashMap；Hashtable线程安全
    * 不支持Iterator并发的删除对象，建议使用ConcurrentHashMap。
      （当多个线程分别调用Hashtable的iterator()方法获取对象，并使用remove()方法删除对象是会报ConcurrentModificationException异常)
    * 不支持排序
  (2) ConcurrentSkipListMap
    * LinkedHashMap支持Key的顺序性，但不支持并发。
    * ConcurrentSkipListMap支持并发排序
  (3) ConcurrentSkipListSet支持排序，不允许有重复的元术
  (4) ConcurrentLinkedQueue提供并发环境的队列操作
    * 方法element()当没有获得数据时，出现NoSuchElementException异常；如果有数据则返回表头元素。
    * 仅支持对队头进行操作
  (5) ConcurrentLinkedDeque
    * 支持对队头、队尾双向操作。
  (6) CopyOnWriteArrayList
    * 多线程（并发）环境替换线程非安全的ArrayList
  (7) CopyOnWriteArraySet
    * 解决多线程环境下HashSet线程非安全的问题
10.3 阻塞队列
  说明：BlockQueue如果为空，操作被阻塞，进入等待状态，直到有元素时被唤醒；BlockQueue满是也是同样的。
  (1) ArrayBlockingQueue提供有界阻塞队列的功能      
  (2) PriorityBlockingQueue支持在并发情况下的优先级队列         
  (3) LinkedBlockingQueue
    * 与 ArrayBlockingQueue功能大体一致，区别是：前者是无界的，后者是有界的。
    * 也可以定义成有界的。
    * 只支持对队列头的操作。
  (4) LinkedBlockingDeque
    * 支持双端操作         
  (5) SynchronousQueue
    * 同步队列, 非常适合于传递性设计。
  (6) DelayQueue
    * 延时执行任务，无界阻塞队列，只有在延迟期满时才能从中提取元素。         
  (7) LinkedTransferQueue
    * Since JDK 1.7
    * 无界队列，基于链表节点实现。FIFO（先进先出）
    * http://blog.csdn.net/yjian2008/article/details/16951811
  注：
    * LinkedTransferQueue实现了一个重要的接口TransferQueue，该接口含有下面几个重要方法：
    * 1. transfer(E e)：
      1) 如果当前存在一个正在等待取值的消费者线程，即立刻把数据传输过去；
      2) 否则，将当前元素e插入到队列尾部，并且进入阻塞状态，直到有消费者线程取走该元素。
    * 2. tryTransfer(E e)：
      1) 如果当前存在一个正在等待取值的消费者线程（使用take()或者poll()函数），使用该方法会立刻传输元素e；
      2) 若不存在，则返回false，并且数据不放入队列。这是一个不阻塞的操作。
    * 3. tryTransfer(E e, long timeout, TimeUnit unit)  
      1) 如果当前存在一个正在等待取值的消费者线程，会立即传输给它;
      2) 否则将插入元素e到队列尾部，并且等待被消费者线程获取消费掉；
      3)如果在指定的时间内元素e无法被消费者线程获取，则返回false，同时该元素被移除。
    * 4. hasWaitingConsumer()：判断是否存在消费者线程等待数据。
    * 5. getWaitingConsumerCount()：获取所有等待获取数据的消费线程数量。

   






