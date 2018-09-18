package ThreadOperation;

import java.util.concurrent.*;

public class _FutureTask<I extends Number> {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(10);
        Task result = new Task();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(result);
        executor.submit(futureTask);
        try {
            Integer ret = futureTask.get();
        } catch (InterruptedException e) {
            executor.shutdown();
            e.printStackTrace();
        } catch (ExecutionException e) {
            executor.shutdown();
            e.printStackTrace();
        }
        executor.shutdown();
    }


    public static class Task implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            return 10;
        }

    }
}

