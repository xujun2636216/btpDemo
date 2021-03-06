package ThreadOperation;

import Common.LogHelper;

import java.util.concurrent.*;

public class _FutureTask<I extends Number> {

    /**
     * 多线程并行异步操作(FutureTask)
     */
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(10);
        Task result = new Task();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(result);
        executor.submit(futureTask);
        try {
            Integer ret = futureTask.get();
        } catch (InterruptedException e) {
            executor.shutdown();
            LogHelper.Error(e.getMessage(),e);
        } catch (ExecutionException e) {
            executor.shutdown();
            LogHelper.Error(e.getMessage(),e);
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

