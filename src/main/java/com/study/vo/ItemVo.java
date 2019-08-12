package com.study.vo;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
/**
 * 延时对象
 * @author YCKJ1409
 */
public class ItemVo<T> implements Delayed {

    /**
     *     到期时间
     */
    private long activeTime;

    private T data;

    /**传入的数值代表过期时长，单位秒 需要*1000转换为毫秒和到期时间*/
    public ItemVo(long expirationTime,T data){
        super();
        this.activeTime = expirationTime*1000 + System.currentTimeMillis();
        this.data = data;
    }

    public long getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(long activeTime) {
        this.activeTime = activeTime;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long d = unit.convert(this.activeTime-System.currentTimeMillis(),unit);
        return d;
    }

    @Override
    public int compareTo(Delayed o) {
        long d = (getDelay(TimeUnit.MILLISECONDS)-o.getDelay(TimeUnit.MILLISECONDS));
        if(d==0){
            return 0;
        }else if(d<0){
            return -1;
        }else if(d >0){}{
            return 1;
        }
    }
}
