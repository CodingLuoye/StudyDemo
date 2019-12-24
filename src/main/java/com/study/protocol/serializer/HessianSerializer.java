package com.study.protocol.serializer;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @author YCKJ1409
 * 使用hessian进行序列化和反序列化
 * 支持跨语言传输的二进制序列化协议，更好的性能和易用性
 */
public class HessianSerializer {

    public byte[] serialize(Object object) {
        if (object == null) {
            throw new NullPointerException();
        }
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            HessianOutput ho = new HessianOutput(os);
            ho.writeObject(object);
            return os.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public <T> T deserialize(byte[] data,Class<T> tClass){
        if(data == null){
            throw new NullPointerException();
        }
        try {
            ByteArrayInputStream os = new ByteArrayInputStream(data);
            HessianInput hi = new HessianInput(os);
            return (T)hi.readObject();
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

}
