package com.sbrf.reboot.concurrency;

import com.sbrf.reboot.service.concurrency.Task;
import com.sbrf.reboot.service.concurrency.TaskExecutorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class TaskExecutorServiceTest {

    @Test
    public void successRunMultithreading() throws InterruptedException {

        Task task = Mockito.mock(Task.class);
        CountDownLatch latch = new CountDownLatch(2);

        TaskExecutorService taskExecutorService = new TaskExecutorService(2);

        doAnswer((e -> {
            latch.countDown();
            return null;
        })).when(task).run();

        taskExecutorService.execute(task);

        latch.await();

        assertEquals(0, latch.getCount());
        verify(task, times(2)).run();

        taskExecutorService.shutdown();
    }

    private static final int MAX_AVAILABLE_NUMBER_OF_THREADS = 10;
    private static final int MIN_AVAILABLE_NUMBER_OF_THREADS = 1;

    @Test
    public void getNumberOfThreadsSuccessWithMoreThanMaxAvailable() {
        Assertions.assertEquals(
                MAX_AVAILABLE_NUMBER_OF_THREADS,
                TaskExecutorService.getNumberOfTreads(11)
        );
    }

    @Test
    public void getNumberOfThreadsSuccessWithLessThanMinAvailable() {
        Assertions.assertEquals(
                MIN_AVAILABLE_NUMBER_OF_THREADS,
                TaskExecutorService.getNumberOfTreads(-1)
        );
    }

    @Test
    public void getNumberOfThreadsSuccessWithNormalValue() {
        Assertions.assertEquals(
                6,
                TaskExecutorService.getNumberOfTreads(6)
        );
    }
}