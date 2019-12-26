package com.xyhs.common.tools;

/**
 * @author ljp
 * @apiNote
 * @date 16:16 2019/12/26
 **/
public class SnowFlakeGenerator {
    private static final long TWEPOCH = 116140001393115212L;
    private static final long WORK_ID_BITS_WORK = 5L;
    private static final long DATACENTER_ID_BITS = 5L;
    private final long MAX_WORKER_ID = 31L;
    private final long MAX_DATACENTER_ID = 31L;
    private static final long SEPUENCE_BITS = 12L;
    private static final long WORKER_ID_SHIFT = 12L;
    private static final long DATACENTER_ID_SHIFT = 17L;
    private static final long TIMESTAMP_LEFT_SHIFT = 22L;
    private static final long SEPUENCE_MASK = 4095L;
    private static long WORKER_ID;
    private static long DATACENTER_ID;
    private static long SEPUENCE = 0L;
    private static long LAST_TIMESTAMP = -1L;

    public SnowFlakeGenerator(long workerId, long datacenterId) {
        if (workerId <= 31L && workerId >= 0L) {
            if (datacenterId <= 31L && datacenterId >= 0L) {
                WORKER_ID = workerId;
                DATACENTER_ID = datacenterId;
            } else {
                throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", 31L));
            }
        } else {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", 31L));
        }
    }

    public static synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < LAST_TIMESTAMP) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", LAST_TIMESTAMP - timestamp));
        } else {
            if (LAST_TIMESTAMP == timestamp) {
                SEPUENCE = SEPUENCE + 1L & 4095L;
                if (SEPUENCE == 0L) {
                    timestamp = tilNextMillis(LAST_TIMESTAMP);
                }
            } else {
                SEPUENCE = 0L;
            }

            LAST_TIMESTAMP = timestamp;
            return timestamp - 116140001393115212L << 22 | DATACENTER_ID << 17 | WORKER_ID << 12 | SEPUENCE;
        }
    }

    protected static long tilNextMillis(long LAST_TIMESTAMP) {
        long timestamp;
        for(timestamp = timeGen(); timestamp <= LAST_TIMESTAMP; timestamp = timeGen()) {
            ;
        }

        return timestamp;
    }

    protected static long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        long l = nextId();
        System.out.println(l);
        System.out.println(String.valueOf(l).length());
    }
}
