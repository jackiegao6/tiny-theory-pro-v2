package com.gzc.infrastructure.redis.lock;

public interface ILock {

    boolean tryLock(long timeoutSec);

    void unlock();
}
