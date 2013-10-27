/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awstrainers.devcourse.sdkdemos;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author ciberado
 */
public class AsyncDemo {

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        Future<String> lprA = executor.submit(new LongProcess("A", 1000));
        Future<String> lprB = executor.submit(new LongProcess("B", 500));
        // Uncomment the following line to see how Future.get is a blocking invocation
        //System.out.println("**** " + lprA.get());
        Future<String> lprC = executor.submit(new LongProcess("C", 800));

    }

}

class LongProcess implements Callable<String> {

    private String name;
    private int pause;

    public LongProcess(String name, int pause) {
        this.name = name;
        this.pause = pause;
    }

    @Override
    public String call() throws Exception {
        System.out.println("Into call  " + name);
        Thread.sleep(pause);
        System.out.println("Exiting call " + name);
        return "Ok from " + name;
    }

}
