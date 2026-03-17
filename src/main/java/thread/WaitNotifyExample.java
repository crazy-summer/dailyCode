package thread;

import java.util.LinkedList;
import java.util.Queue;

public class WaitNotifyExample {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int MAX_SIZE = 3;

    // 生产者
    public void produce() throws InterruptedException {
        synchronized (queue) {
            // 如果队列满了，生产者等待
            while (queue.size() == MAX_SIZE) {
                System.out.println("队列已满，生产者等待...");
                queue.wait(); // 释放锁，等待消费者通知
            }

            // 生产数据
            int value = (int) (Math.random() * 100);
            queue.offer(value);
            System.out.println("生产者生产了: " + value + ", 当前大小: " + queue.size());

            // 通知消费者（可能有多个消费者在等）
            queue.notifyAll();
        }
    }

    // 消费者
    public void consume() throws InterruptedException {
        synchronized (queue) {
            // 如果队列空了，消费者等待
            while (queue.isEmpty()) {
                System.out.println("队列已空，消费者等待...");
                queue.wait(); // 释放锁，等待生产者通知
            }

            // 消费数据
            int value = queue.poll();
            System.out.println("消费者消费了: " + value + ", 当前大小: " + queue.size());

            // 通知生产者（可能有多个生产者在等）
            queue.notifyAll();
        }
    }

    public static void main(String[] args) {
        WaitNotifyExample example = new WaitNotifyExample();

        // 启动生产者线程
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    example.produce();
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Producer").start();

        // 启动消费者线程
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    example.consume();
                    Thread.sleep(800);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Consumer").start();
    }
}
//
//常见误区与注意事项
//为什么用 while 而不是 if？
//虚假唤醒：JVM 规范允许线程在没有被 notify 的情况下醒来。如果用 if，线程醒来后直接执行后续逻辑，此时条件可能仍不满足，导致错误。while 会再次检查条件，如果不满足继续等待。
//多线程竞争：假设使用了 notifyAll() 唤醒了多个线程，但资源只够一个线程处理。第一个抢到的线程处理完后，其他醒来的线程如果直接用 if 就会直接执行，导致错误；用 while 则会发现资源不足，再次进入等待。
//notify() vs notifyAll() 选哪个？
//notify()：效率高，只唤醒一个。但在复杂场景（如多个不同条件的等待者混在一起，或者“全有或全无”的资源竞争）下，可能会唤醒错误的线程（例如唤醒了另一个生产者而不是消费者），导致死锁或逻辑错误。
//notifyAll()：更安全，唤醒所有人让大家重新竞争。虽然会有短暂的“惊群效应”（所有线程都去抢锁，大部分抢不到又回去睡），但在大多数业务场景下，为了逻辑正确性，推荐优先使用 notifyAll()。
//锁的对象必须一致
//调用 wait() 的对象和调用 notify() 的对象必须是同一个实例。
//错误示范：线程 A 在 obj1 上 wait()，线程 B 在 obj2 上 notify()，这样线程 A 永远醒不过来。
//替代方案
//由于 wait/notify 容易出错且功能有限（只能绑定一个条件），在现代 Java 并发编程中，更推荐使用 java.util.concurrent 包下的工具：
//Condition (ReentrantLock 配套)：支持多条件变量，用法类似但更灵活。
//BlockingQueue：直接封装了生产者 - 消费者逻辑（如 ArrayBlockingQueue, LinkedBlockingQueue），无需手动写 wait/notify。
//Semaphore, CountDownLatch 等。
//总结：wait/notify 是底层原语，理解它们有助于理解并发原理。但在实际开发中，除非是为了学习或极其特殊的轻量级需求，否则建议优先使用 JUC 包中的高级组件来替代。