package thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int MAX_SIZE = 5;

    // 1. 创建锁
    private final Lock lock = new ReentrantLock();

    // 2. 创建两个条件变量
    // notFull: 当队列满时，生产者在此等待
    private final Condition notFull = lock.newCondition();
    // notEmpty: 当队列空时，消费者在此等待
    private final Condition notEmpty = lock.newCondition();

    // 生产者方法
    public void produce(int value) throws InterruptedException {
        lock.lock(); // 加锁
        try {
            // 如果队列满了，生产者在 notFull 条件上等待
            while (queue.size() == MAX_SIZE) {
                System.out.println("【生产者】队列已满，等待消费...");
                notFull.await(); // 释放锁，进入 notFull 等待队列
            }

            // 执行生产逻辑
            queue.offer(value);
            System.out.println("【生产者】生产了: " + value + ", 当前大小: " + queue.size());

            // 生产完后，队列不再为空，通知在 notEmpty 上等待的消费者
            notEmpty.signal();
        } finally {
            lock.unlock(); // 必须手动解锁
        }
    }

    // 消费者方法
    public void consume() throws InterruptedException {
        lock.lock(); // 加锁
        try {
            // 如果队列空了，消费者在 notEmpty 条件上等待
            while (queue.isEmpty()) {
                System.out.println("【消费者】队列已空，等待生产...");
                notEmpty.await(); // 释放锁，进入 notEmpty 等待队列
            }

            // 执行消费逻辑
            int value = queue.poll();
            System.out.println("【消费者】消费了: " + value + ", 当前大小: " + queue.size());

            // 消费完后，队列不再为满，通知在 notFull 上等待的生产者
            notFull.signal();
        } finally {
            lock.unlock(); // 必须手动解锁
        }
    }

    public static void main(String[] args) {
        ConditionExample example = new ConditionExample();

        // 启动生产者线程
        new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    example.produce(i);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Producer-Thread").start();

        // 启动消费者线程
        new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    example.consume();
                    Thread.sleep(800);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Consumer-Thread").start();
    }
}
//
//关键方法详解
//1. await()
//作用：使当前线程进入等待状态，直到被信号唤醒或被中断。
//行为：
//原子性地释放关联的锁。
//将当前线程加入到该 Condition 的等待队列中并挂起。
//当被唤醒后，线程会尝试重新获取锁，获取成功后从 await() 返回，继续执行后续代码。
//变体：
//awaitUninterruptibly(): 等待过程中不响应中断。
//awaitNanos(long nanosTimeout): 带超时时间的等待，返回剩余纳秒数。
//awaitUntil(Date deadline): 等待到指定时间点。
//        2. signal()
//作用：唤醒在该 Condition 上等待的一个线程。
//注意：被唤醒的线程不会立即执行，它需要从 await() 返回，这意味着它必须重新竞争锁。只有当调用 signal() 的线程释放了锁之后，被唤醒的线程才有可能获取到锁并继续运行。
//        3. signalAll()
//作用：唤醒在该 Condition 上等待的所有线程。
//场景：当条件的改变可能满足多个等待线程的需求，或者你不确定该唤醒哪一个时使用（类似于 notifyAll()）。
//为什么 Condition 比 wait/notify 更好？
//多等待队列（核心优势）：
//在 synchronized 中，如果你既有“队列满”等待又有“队列空”等待，它们都在同一个对象的等待队列里。当你调用 notifyAll() 时，所有线程都醒了，结果发现大部分线程的条件依然不满足（比如唤醒了生产者，但队列还是满的），它们只能再次去睡。这造成了不必要的上下文切换。
//使用 Condition，生产者只在 notFull 队列睡，消费者只在 notEmpty 队列睡。生产者生产完后，只调用 notEmpty.signal()，精准唤醒一个消费者，效率极高。
//可中断与超时：
//Condition 提供了更丰富的 API（如 awaitNanos），让处理超时逻辑变得更加优雅和直观。
//公平性支持：
//如果 ReentrantLock 初始化为公平锁，那么 Condition 的等待队列也是公平的（FIFO），而 synchronized 永远是非公平的。
//常见陷阱
//忘记在 finally 中解锁：这是最致命的错误。如果业务逻辑抛出异常且没有解锁，其他所有线程将永远阻塞。
//用 if 代替 while：和 wait/notify 一样，await() 也必须放在 while 循环中判断条件，以防止虚假唤醒。
//锁不匹配：Condition 是由特定的 Lock 创建的，只能由持有该 Lock 的线程调用 await/signal。不能跨锁使用。
//Signal 丢失：如果在调用 signal() 时没有任何线程在等待，这个信号就会丢失（不会像 semaphore 那样累加）。因此，通常建议先改变状态，再 signal，并且依赖 while 循环来保证逻辑正确性。
//总结
//当你需要精细控制哪些线程被唤醒，或者需要多个不同的等待条件时，Condition 是最佳选择。对于简单的互斥同步，synchronized 依然足够；但对于复杂的生产者 - 消费者模型、读写锁场景或多阶段任务协调，ReentrantLock + Condition 是标准解决方案。