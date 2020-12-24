package com.study.algorithm;

import java.time.Instant;
import java.time.temporal.TemporalField;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author YCKJ1409
 */
public class ConcurrentHashMapTest {

    public static void main(String[] args) {
        ConcurrentHashMap<String,Object>  map = new ConcurrentHashMap();
        map.put("1",1);
        Instant instant = Instant.now();
        System.out.println(instant);
        System.out.println(instant.toEpochMilli());
        System.out.println(System.currentTimeMillis());
        Instant now = Instant.now().plusMillis(TimeUnit.HOURS.toMillis(8));
        System.out.println("now:"+now);
        System.out.println(now.toEpochMilli());
        System.out.println(now.getEpochSecond());

        LongAdder longAdder = new LongAdder();
        longAdder.increment();
        longAdder.increment();
        System.out.println(longAdder.intValue());

        System.out.println(Integer.valueOf("0.5"));


    }

}
