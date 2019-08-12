package com.study.cmb;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.*;

/**
 * @author YCKJ1409
 * SHA256工具栏
 */
public class SHA256Util {
    /**
     * 　　* 利用java原生的摘要实现SHA256加密
     * 　　* @param str 加密后的报文
     * 　　* @return
     *
     */

    public static String getSHA256Str(String str) {
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        encodeStr = encodeStr.toUpperCase();
        return encodeStr;
    }

    /**
     * 招商银行  商户支付密钥签名示例
     * @param strToSign  已排序字符串为strToSign
     * @param sMerchantKey 商户密钥
     * @return
     */
    public static String sing(StringBuffer strToSign,String sMerchantKey){
        String encodeStr = "";
        try{
            strToSign.append("&").append(sMerchantKey);
            // 创建加密对象
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            // 传入要加密的字符串,按指定的字符集将字符串转换为字节流
            messageDigest.update(strToSign.toString().getBytes("UTF-8"));
            // 將 byte数组转换为16进制string
            encodeStr = byte2Hex(messageDigest.digest());
        }catch (Exception e){
            e.printStackTrace();
        }
        return encodeStr;
    }

    /**
     * 　　* 将byte转为16进制
     * 　　* @param bytes
     * 　　* @return
     *
     */
    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) throws Exception {
        String aaa = "agrNo=1234567891234568789&amount=10.01&bankSerialNo=2016062310143099&dateTime=20160623101430&merchantSerialNo=2016062310143088&rspCode=SUC0000&rspMsg=退款成功&settleDate=20161212";
        aaa += "&" + "43080219800401Aa";
        String bbb = getSHA256Str(aaa);
        System.out.println(bbb);

        String content = "study hard and make progress everyday";

        KeyPair keyPair = getKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();

        String sha1Sign = getSha1Sign(content, privateKey);
        System.out.println("sign with sha1 and rsa :" + sha1Sign);
    }

    /**
     * 生成密钥对
     *
     * @return
     * @throws Exception
     */
    static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        //可以理解为：加密后的密文长度，实际原文要小些 越大 加密解密越慢
        keyGen.initialize(512);
        KeyPair keyPair = keyGen.generateKeyPair();
        return keyPair;
    }

    /**
     * 用sha1生成内容摘要，再用RSA的私钥加密，进而生成数字签名
     *
     * @param content
     * @param privateKey
     * @return
     * @throws Exception
     */
    static String getSha1Sign(String content, PrivateKey privateKey) throws Exception {
        byte[] contentBytes = content.getBytes("utf-8");
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initSign(privateKey);
        signature.update(contentBytes);
        byte[] signs = signature.sign();
        return Base64.encodeBase64String(signs);
    }

    /**
     * 对用md5和RSA私钥生成的数字签名进行验证
     *
     * @param content
     * @param sign
     * @param publicKey
     * @return
     * @throws Exception
     */
    static boolean verifyWhenSha1Sign(String content, String sign, PublicKey publicKey) throws Exception {
        byte[] contentBytes = content.getBytes("utf-8");
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initVerify(publicKey);
        signature.update(contentBytes);
        return signature.verify(Base64.decodeBase64(sign));
    }

}
