public class Test {
    public static void main(String[] args) throws InterruptedException {
        int count = 0;

        do{
            Thread.sleep(1000);
            count++;
            System.out.printf("已经睡眠%ds%n",count);

        }while (true);

    }
}
