9.1
  Fork/Join框架，支持并行执行。
    * Since JDK 1.7
  分治思想：把大任务分成小任务，再对小任务得到的结果进行汇总。
  优势：充分利用CPU资源，提高任务执行效率。
  (1) ForkJoinPool的主要作用是创建一个任务池
  (2) ForkJoinTask执行具体的任务,
    * ForkJoinTask是抽象类，不能实例化，所以继承其二个子类(RecursiveAction, RecursiveTask，都是抽象类)来实现具体的功能。
9.2
  RecursiveAction：用于没有返回结果的任务。
  RecursiveTask ：用于有返回结果的任务。
  http://www.infoq.com/cn/articles/fork-join-introduction
  RecursiveTask方法join()与get()方法区别是在出现异常时的处理不一样。
  get()方法执行任务时，当子任务出现异常时，可以在main主线程中进行捕获。
  join()方法遇到异常直接抛出
9.9 
  ForkJoinPool的execute()方法以异步方式执行任务。
    * public void execute(Runnable task)
    * public void execute(ForkJoinTask<?> task) 无返回值，可以通过RecursiveTask对象处理返回值。
    * public <T> ForkJoinTask<T> submit(ForkJoinTask<T> task)有返回值
    * public <T> ForkJoinTask<T> submit(Callable<T> task)
    * public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) 有阻塞特性