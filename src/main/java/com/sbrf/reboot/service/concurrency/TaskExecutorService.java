package com.sbrf.reboot.service.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskExecutorService {
    private static final int MAX_AVAILABLE_NUMBER_OF_THREADS = 10;
    private static final int MIN_AVAILABLE_NUMBER_OF_THREADS = 1;
    private final int numberOfThreads;

    private final ExecutorService service;

    public TaskExecutorService(int numberOfThreads) {
        this.service = Executors.newFixedThreadPool(numberOfThreads);

        this.numberOfThreads = getNumberOfTreads(numberOfThreads);
    }

    public void execute(Task task) {
        for (int i = 0; i < numberOfThreads; i++) {
            service.execute(task);
        }
    }

    public void shutdown() {
        service.shutdown();
    }

    public static int getNumberOfTreads(final int numberOfThreads) {
        return Math.max(
                Math.min(
                        numberOfThreads,
                        MAX_AVAILABLE_NUMBER_OF_THREADS
                ),
                MIN_AVAILABLE_NUMBER_OF_THREADS
        );
    }
}
