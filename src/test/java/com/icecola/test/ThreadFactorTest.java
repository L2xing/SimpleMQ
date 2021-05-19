package com.icecola.test;

import com.icecola.simplemq.utils.ThreadPoolUtils;
import org.junit.jupiter.api.Test;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * description:
 *
 * @author liuxingxing
 * @date 2021.05.19 22:58
 */
public class ThreadFactorTest {


    @Test
    public void execute() {
        ThreadPoolUtils.execute(() -> {
            System.out.println(12312312);
        });
        System.out.println(1231231232112312312L);
    }

    @Test
    public void syncExecute() {
        Integer i = 1;
        Integer integer = ThreadPoolUtils.syncExecute(() -> {
            TimeUnit.SECONDS.sleep(1);
            return i + 1;
        });
        System.out.println(i);
        System.out.println("开始");
        Queue
    }

}
