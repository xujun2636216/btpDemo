package ThreadOperation;

import java.util.concurrent.*;

public class FuturesandCallables {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        System.out.println("Factorial Service called for 10!");
        Future<Long> result10 = executor.submit(new FactorialService(10));

        System.out.println("Factorial Service called for 20!");
        Future<Long> result20 = executor.submit(new FactorialService(20));

        Long factorial10 = result10.get();
        System.out.println("10! = " + factorial10);

        Long factorial20 = result20.get();
        System.out.println("20! = " + factorial20);

        executor.shutdown();
    }


    public  void  GetName() {
        ExecutorService executor = Executors.newFixedThreadPool(10);
    }

    public static class FactorialService implements Callable<Long> {
        private int number;

        public FactorialService(int number) {
            this.number = number;
        }

        @Override
        public Long call() throws Exception {
            return factorial();
        }

        private Long factorial() throws InterruptedException {
            long result = 1;
            while (number != 0) {
                result = number * result;
                number--;
                Thread.sleep(100);
            }
            return result;
        }
    }

}
