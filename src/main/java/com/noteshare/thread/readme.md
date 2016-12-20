#概念知识
##进程定义
* 狭义定义：进程是正在运行的程序的实例（an instance of a computer program that is being executed）。
* 广义定义：进程是一个具有一定独立功能的程序关于某个数据集合的一次运行活动。它是操作系统动态执行的基本单元，在传统的操作系统中，进程既是基本的分配单元，也是基本的执行单元。
>进程的概念主要有两点：第一，进程是一个实体。每一个进程都有它自己的地址空间，一般情况下，包括文本区域（text region）、数据区域（data region）和堆栈（stack region）。文本区域存储处理器执行的代码；数据区域存储变量和进程执行期间使用的动态分配的内存；堆栈区域存储着活动过程调用的指令和本地变量。第二，进程是一个“执行中的程序”。程序是一个没有生命的实体，只有处理器赋予程序生命时（操作系统执行之），它才能成为一个活动的实体，我们称其为进程。[3] 
进程是操作系统中最基本、重要的概念。是多道程序系统出现后，为了刻画系统内部出现的动态情况，描述系统内部各道程序的活动规律引进的一个概念,所有多道程序设计操作系统都建立在进程的基础上。
* 特征

>动态性：进程的实质是程序在多道程序系统中的一次执行过程，进程是动态产生，动态消亡的。
并发性：任何进程都可以同其他进程一起并发执行
独立性：进程是一个能独立运行的基本单位，同时也是系统分配资源和调度的独立单位；
异步性：由于进程间的相互制约，使进程具有执行的间断性，即进程按各自独立的、不可预知的速度向前推进
结构特征：进程由程序、数据和进程控制块三部分组成。
多个不同的进程可以包含相同的程序：一个程序在不同的数据集里就构成不同的进程，能得到不同的结果；但是执行过程中，程序不能发生改变。

##线程概述
>线程，有时被称为轻量级进程(Lightweight Process，LWP），是程序执行流的最小单元。一个标准的线程由线程ID，当前指令指针(PC），寄存器集合和堆栈组成。另外，线程是进程中的一个实体，是被系统独立调度和分派的基本单位，线程自己不拥有系统资源，只拥有一点儿在运行中必不可少的资源，但它可与同属一个进程的其它线程共享进程所拥有的全部资源。一个线程可以创建和撤消另一个线程，同一进程中的多个线程之间可以并发执行。由于线程之间的相互制约，致使线程在运行中呈现出间断性。线程也有就绪、阻塞和运行三种基本状态。就绪状态是指线程具备运行的所有条件，逻辑上可以运行，在等待处理机；运行状态是指线程占有处理机正在运行；阻塞状态是指线程在等待一个事件（如某个信号量），逻辑上不可执行。每一个程序都至少有一个线程，若程序只有一个线程，那就是程序本身。
线程是程序中一个单一的顺序控制流程。进程内一个相对独立的、可调度的执行单元，是系统独立调度和分派CPU的基本单位指运行中的程序的调度单位。在单个程序中同时运行多个线程完成不同的工作，称为多线程。

###线程与进程的区别可以归纳为以下4点

>1）地址空间和其它资源（如打开文件）：进程间相互独立，同一进程的各线程间共享。某进程内的线程在其它进程不可见。
2）通信：进程间通信IPC，线程间可以直接读写进程数据段（如全局变量）来进行通信——需要进程同步和互斥手段的辅助，以保证数据的一致性。
3）调度和切换：线程上下文切换比进程上下文切换要快得多。
4）在多线程OS中，进程不是一个可执行的实体。
###守护线程

>守护线程是特殊的线程，一般用于在后台为其他线程提供服务.
Java中，isDaemon()：判断一个线程是否为守护线程.
Java中，setDaemon()：设置一个线程为守护线程.
java线程还可以分为前台线程（也称用户线程或普通线程)和后台线程（Daemon thread）
1.  后台线程会随着主程序的结束而结束，但是前台进程则不会；或者说，只要有一个前台线程未退出，进程就不会终止。（下面的例子会充分说明这一点）;
2.  默认情况下，程序员创建的线程是用户线程；用setDaemon(true)可以设置线程为后台线程；而用isDaemon( )则可以判断一个线程是前台线程还是后台线程；
3. jvm的垃圾回收器其实就是一个后台线程；
4. setDaemon函数必须在start函数之前设定，否则会抛出IllegalThreadStateException异常;


### 关键知识点
* CPU缓存：
目前CPU一般采用层次结构的多级缓存的架构，有的CPU提供了L1、L2和L3三级缓存。当CPU需要读取主存中某个位置的数据时，会一次检查各级缓存中是否存在对应的数据。如果有，直接从缓存中读取，这比从主存中读取速度快很多。当CPU需要写入时，数据先被写入缓存中，之后再某个时间点写回主存。所以某些时间点上，缓存中的数据与主存中的数据可能是不一致。
* Java内存模型（Java Memory Model）
>屏蔽了CPU缓存等细节，只关注主存中的共享变量；关注对象的实例域、静态域和数组元素；关注线程间的动作。
1、volatile关键词：用来对共享变量的访问进行同步，上一次写入操作的结果对下一次读取操作是肯定可见的。（在写入volatile变量值之后，CPU缓存中的内容会被写回内存；在读取volatile变量时，CPU缓存中的对应内容会被置为失效，重新从主存中进行读取），volatile不使用锁，性能优于synchronized关键词。
* synchronized
* daemon
* final 
> 当对象中的共享变量的值不可能发生变化时，在多线程中也就不需要同步机制来进行处理，故在多线程开发中应尽可能使用不可变对象。
另外，在代码执行时，final域的值可以被保存在寄存器中，而不用从主存中频繁重新读取。
* java基本类型的原子操作
1）基本类型，引用类型的复制引用是原子操作；（即一条指令完成）
2）long与double的赋值，引用是可以分割的，非原子操作；
3）要在线程间共享long或double的字段时，必须在synchronized中操作，或是声明成volatile
* 线程状态转换：
已经废弃的方法：stop、suspend、resume、destroy，这些方法在实现上时不安全的。
线程的状态：NEW、RUNNABLE、BLOCKED(堵塞的)、WAITING、TIMED_WAITING（有超时的等待）、TERMINATED(终止)。
a、方法sleep()进入的阻塞状态，不会释放对象的锁（即大家一起睡，谁也别想执行代码），所以不要让sleep方法处在synchronized方法或代码块中，否则造成其他等待获取锁的线程长时间处于等待。
b、方法**join()**则是主线程等待子线程完成。
```
public class JoinTest {
	/**如果把join注释掉，则输出顺序是不固定的*/
	public static void main(String[] args) throws Exception {
		Thread t1 = new Thread(new ThreadTesterA());
		Thread t2 = new Thread(new ThreadTesterB());
		t1.start();
		t1.join(); //等t1执行完再往下执行  
		t2.start();
		t2.join(); //在虚拟机执行中，这句可能被忽略 
	}
}

class ThreadTesterA implements Runnable{
	@Override
	public void run() {
		try {
			Thread.sleep(1000);
			System.out.println("===ThreadTesterA====");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
class ThreadTesterB implements Runnable{
	@Override
	public void run() {
		try {
			Thread.sleep(1000);
			System.out.println("===ThreadTesterB====");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
```
* c、方法interrupt()，向被调用的对象线程发起中断请求。如线程A通过调用线程B的d的interrupt方法来发出中断请求，线程B来处理这个请求，当然也可以忽略，这不是必须的。Object类的wait()、Thread类的join()和sleep方法都会抛出受检异常java.lang.InterruptedException，通过interrupt方法中断该线程会导致线程离开等待状态。对于wait()调用来说，线程需要重新获取监视器对象上的锁之后才能抛出InterruptedException异常，并致以异常的处理逻辑。
可以通过Thread类的isInterrupted方法来判断是否有中断请求发生，通常可以利用这个方法来判断是否退出线程（类似上面的volatitle修饰符的例子）；
* d、Thread类还有个方法Interrupted()，该方法不但可以判断当前线程是否被中断，还会清楚线程内部的中断标记，如果返回true，即曾被请求中断，同时调用完后，清除中断标记。
如果一个线程在某个对象的等待池，那么notify和interrupt 都可以使该线程从等待池中被移除。如果同时发生，那么看实际发生顺序。如果是notify先，那照常唤醒，没影响。如果是interrupt先，并且虚拟机选择让该线程中断，那么即使nofity，也会忽略该线程，而唤醒等待池中的另一个线程。
* e、yield()，尝试让出所占有的CPU资源，让其他线程获取运行机会,（让出线程后进入就绪状态和其他线程有同等机会抢占线程）,对操作系统上的调度器来说是一个信号，不一定立即切换线程。（在实际开发中，测试阶段频繁调用yeid方法使线程切换更频繁，从而让一些多线程相关的错误更容易暴露出来）。
* 补充性知识CAS

##五、高级同步机制（比synchronized更灵活的加锁机制）
synchronized和volatile，以及wait、notify等方法抽象层次低，在程序开发中使用比较繁琐，易出错。
而多线程之间的交互来说，存在某些固定的模式，如生产者-消费者和读者-写者模式，把这些模式抽象成高层API，使用起来会非常方便。
java.util.concurrent包为多线程提供了高层的API，满足日常开发中的常见需求。
常用接口
1、Lock接口，表示一个锁方法：
a、lock()，获取所，如果无法获取所锁，会处于等待状态
b、unlock()，释放锁。（一般放在finally代码块中）
c、lockInterruptibly()，与lock()类似，但允许当前线程在等待获取锁的过程中被中断。（所以要处理InterruptedException）
d、tryLock()，以非阻塞方式获取锁，如果无法获取锁，则返回false。（tryLock()的另一个重载可以指定超时，如果指定超时，当无法获取锁，会等待而阻塞，同时线程可以被中断）
2、ReadWriteLock接口，表示两个锁，读取的共享锁和写入的排他锁。（适合常见的读者--写者场景）
ReadWriteLock接口的readLock和writeLock方法来获取对应的锁的Lock接口的实现。
在多数线程读取，少数线程写入的情况下，可以提高多线程的性能，提高使用该数据结构的吞吐量。
如果是相反的情况，较多的线程写入，则接口会降低性能。
3、ReentrantLock类和ReentrantReadWriteLock，分别为上面两个接口的实现类。
他们具有重入性：即允许一个线程多次获取同一个锁（他们会记住上次获取锁并且未释放的线程对象，和加锁的次数，getHoldCount()）
同一个线程每次获取锁，加锁数+1，每次释放锁，加锁数-1，到0，则该锁被释放，可以被其他线程获取。
```
public class LockIdGenrator {
	// new ReentrantLock(true)是重载，使用更加公平的加锁机制，在锁被释放后，会优先给等待时间最长的线程，避免一些线程长期无法获得锁
	private ReentrantLock lock = new ReentrantLock();
	
	private static int value = 0;
	public int getNext() {
		lock.lock(); //进来就加锁，没有锁会等待
		try {
			value++;// 实际操作
			System.out.println(value);
			return value;
		} finally {
			lock.unlock();// 释放锁
		}
	}
	
	public static void main(String[] args) {
		LockIdGenrator obj = new LockIdGenrator();
		for(int i=0;i < 20;i++){
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					int num = obj.getNext();
					System.out.println("num" + num);
				}
			});
			thread.start();
		}
	}
}
```
>注：重入性减少了锁在各个线程之间的等待，例如便利一个HashMap，每次next()之前加锁，之后释放，可以保证一个线程一口气完成便利，而不会每次next()之后释放锁，然后和其他线程竞争，降低了加锁的代价， 提供了程序整体的吞吐量。（即，让一个线程一口气完成任务，再把锁传递给其他线程）。
4、Condition接口，Lock接口代替了synchronized，Condition接口替代了object的wait、nofity。
a、await()，使当前线程进入等待状态，直到被唤醒或中断。重载形式可以指定超时时间。
b、awaitNanos()，以纳秒为单位等待。
c、awaitUntil()，指定超时发生的时间点，而不是经过的时间，参数为java.util.Date。
d、awaitUninterruptibly()，前面几种会响应其他线程发出的中断请求，他会无视，直到被唤醒。
注：与Object类的wait()相同，await()会释放其所持有的锁。
e、signal()和signalAll， 相当于 notify和notifyAll
```
Lock lock = new ReentrantLock();  
Condition condition = lock.newCondition();  
lock.lock();  
try{  
   while(/*逻辑条件不满足*/){  
      condition.await();     
   }  
}finally{  
   lock.unlock();  
}  
```


###计划
* java.util.concurrent里面的额常用类熟悉下。
* cas
* 了解Hashtable和StringBuffer的线程安全实现方式
* ThreadLocal




#写的比较好的博客
* Java 多线程 并发编程http://blog.csdn.net/escaflone/article/details/10418651



