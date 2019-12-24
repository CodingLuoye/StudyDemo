package com.study.protocol.serializer;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @author YCKJ1409
 * 使用XML进行序列化和反序列化
 * 可读性好，利于调试。码流大，效率不高，适用于对性能要求不高，QPS较低
 */
public class XmlSerializer {

    private static final XStream xstrema= new XStream(new DomDriver());

    public <T> byte[] serialize(T object) {
        return xstrema.toXML(object).getBytes();
    }

    public <T> T deserialize(byte[] data,Class<T> tClass){
       String xml = new String(data);
       return (T)xstrema.fromXML(xml);
    }

}
class XmlSerializer2 {

    public <T> byte[] serialize(T object) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XMLEncoder xe = new XMLEncoder(out,"utf-8",true,0);
        xe.writeObject(object);
        xe.close();
        return out.toByteArray();
    }

    public <T> T deserialize(byte[] data,Class<T> tClass){
        XMLDecoder xd = new XMLDecoder(new ByteArrayInputStream(data));
        Object obj = xd.readObject();
        xd.close();
        return (T)obj;
    }

}
