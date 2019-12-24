package com.study.netty.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

/**
 * 工具类
 * @author YCKJ1409
 */
public class XMLUtils {
    public static void main(String[] args) {
        String xmlString = "<?xml version=\"1.0\" encoding=\"GBK\"?>\n" +
                "<data>\n" +
                "<tran_id>11</tran_id>\n" +
                "<cusno>1</cusno>\n" +
                "<amount>1</amount>\n" +
                "<traceno>12</traceno>\n" +
                "</data>\n";
        System.out.println(xmlString.length());
        generateBean(xmlString);
//        generateXML();

    }

    /**
     * 返回解析到的xml内容
     * @param xmlString
     */
    public static XMLRequest generateBean(String xmlString) {
        XMLRequest xmlRequest = new XMLRequest();
        try {
            JAXBContext jc = JAXBContext.newInstance(XMLRequest.class);
            Unmarshaller uma = jc.createUnmarshaller();
            xmlRequest = (XMLRequest) uma.unmarshal(new ByteArrayInputStream(xmlString.getBytes("GBK")));
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return xmlRequest;
    }

    /**
     *  根据请求生成 内容
     * @param
     * @return
     */
    public static String generateXML( XMLResponse xmlResponse) {
        String result = "";
//        XMLResponse xmlResponse = new XMLResponse("00", "交易成功", "", "", "");
        JAXBContext jc = null;
        try {
            //根据XMLResponse类生成上下文对象
            jc = JAXBContext.newInstance(XMLResponse.class);
            //从上下文中获取Marshaller对象，用作将bean编组(转换)为xml
            Marshaller ma = jc.createMarshaller();
            //以下是为生成xml做的一些配置
            //格式化输出，即按标签自动换行，否则就是一行输出
            ma.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
            //设置编码（默认编码就是utf-8）
            ma.setProperty(Marshaller.JAXB_ENCODING, "GBK");
//            //是否省略xml头信息，默认不省略（false）
            ma.setProperty(Marshaller.JAXB_FRAGMENT, false);
            //编组
            StringWriter writer = new StringWriter();
            ma.marshal(xmlResponse, writer);
            result = writer.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return result;
    }

}
