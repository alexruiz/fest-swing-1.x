/*
 * Created on Dec 6, 2009
 *
 * Copyright @2009 the original author or authors.
 */
package org.fest.swing.driver;

import static java.util.concurrent.Executors.newSingleThreadExecutor;
import static org.fest.swing.driver.JProgressBarIncrementValueTask.incrementValue;
import static org.fest.util.Strings.concat;

import java.util.concurrent.*;
import java.util.logging.Logger;

import javax.swing.JProgressBar;

import org.fest.swing.core.Robot;

/**
 * Understands a task that asynchronously increments the value of a <code>{@link JProgressBar}</code> 3 times, given an
 * increment and a period of time in between increments.
 *
 * @author Alex Ruiz
 */
class JProgressBarIncrementValueAsyncTask {

  private static Logger logger = Logger.getAnonymousLogger();

  static TaskBuilder with(JProgressBar progressBar) {
    return new TaskBuilder(progressBar);
  }

  private static final int INCREMENT_COUNT = 3;

  private final Executor executor = newSingleThreadExecutor();
  private final FutureTask<Void> task;

  private final Robot robot;
  private final JProgressBar progressBar;
  private final int increment;
  private final long periodInMs;

  private JProgressBarIncrementValueAsyncTask(Robot robot, JProgressBar progressBar, int increment, long periodInMs) {
    this.robot = robot;
    this.progressBar = progressBar;
    this.increment = increment;
    this.periodInMs = periodInMs;
    task = createInnerTask();
  }

  private FutureTask<Void> createInnerTask() {
    return new FutureTask<Void>(new Callable<Void>() {
      public Void call() {
        try {
          for (int i = 0; i < INCREMENT_COUNT; i++) sleepAndIncrementValue();
        } catch (InterruptedException e) {
          logger.info("Task has been cancelled");
        }
        return null;
      }
    });
  }

  private void sleepAndIncrementValue() throws InterruptedException {
    logger.info(concat("Going to sleep for ", periodInMs, " ms"));
    Thread.sleep(periodInMs);
    int newValue = incrementValue(progressBar, increment);
    logger.info(concat("Incremented JProgressBar value to ", newValue));
    robot.waitForIdle();
  }

  synchronized void runAsynchronously() {
    if (task.isDone()) throw new IllegalStateException("This task is already executed");
    executor.execute(task);
  }

  synchronized void cancelIfNotFinished() {
    if (!task.isDone()) task.cancel(true);
  }

  static class TaskBuilder {
    private final JProgressBar progressBar;
    private int increment = 40;
    private long periodInMs = 1000;

    TaskBuilder(JProgressBar progressBar) {
      this.progressBar = progressBar;
    }

    TaskBuilder increment(int value) {
      increment = value;
      return this;
    }

    TaskBuilder every(long duration, TimeUnit timeUnit) {
      periodInMs = timeUnit.toMillis(duration);
      return this;
    }

    JProgressBarIncrementValueAsyncTask createTask(Robot robot) {
      return new JProgressBarIncrementValueAsyncTask(robot, progressBar, increment, periodInMs);
    }
  }
}
