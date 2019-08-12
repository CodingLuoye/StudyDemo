package com.study.cmb;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author YCKJ1409
 * RSA加密工具类
 */
public class RSAUtils {
    protected final Logger logger = LoggerFactory.getLogger(RSAUtils.class);

    private static final String KEY_ALGORITHM = "PKCS12";

    private static final String SIGNATURE_ALGORITHM = "SHA256withRSA";

    private static final String CIPHER_TYPE = "X.509";

    private static final String ENCRYPT_TYPE = "RSA";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    /**
     * 初始化证书
     *
     * @param priKeyPath
     * @param alias
     * @param password
     * @param pubKeyPath
     */
    public void initCert(String priKeyPath, String alias, String password, String pubKeyPath) {
        try {
            logger.info("证书初始化开始！");
            KeyStore keystore = KeyStore.getInstance(KEY_ALGORITHM);
            keystore.load(new FileInputStream(new File(priKeyPath)), password.toCharArray());
            privateKey = (PrivateKey) keystore.getKey(alias, password.toCharArray());
            CertificateFactory certificateFactory = CertificateFactory.getInstance(CIPHER_TYPE);

            X509Certificate cert = (X509Certificate) certificateFactory.generateCertificate(new FileInputStream(new File(pubKeyPath)));
            publicKey = cert.getPublicKey();
            logger.info("证书初始化结束！");
        } catch (Exception e) {
            logger.error("证书初始化异常！{}{}", e.getMessage(), e);
        }
    }

    /**
     * 签名
     *
     * @param signStr
     * @return
     */
    public String sign(String signStr) {
        try {
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(privateKey);
            signature.update(signStr.getBytes("UTF-8"));
            byte[] result = signature.sign();
            return new String(Base64.encodeBase64String(result));
        } catch (Exception e) {
            logger.error("签名异常！{}{}", e.getMessage(), e);
        }
        return "";
    }

    /**
     * 验签
     *
     * @param str
     * @param signStr
     * @return
     */
    public boolean verify(String str, String signStr) {
        try {
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(publicKey);
            signature.update(str.getBytes());
            return signature.verify(Base64.decodeBase64(signStr));
        } catch (Exception e) {
            logger.error("验签异常！{}{}", e.getMessage(), e);
        }
        return false;
    }


    /**
     * 招行公钥验签示例
     * @param strToSign  待签名字符串为strToSign
     * @param strSign     签名内容为strSign
     * @param publicKey 招行返回的公钥
     * @return
     */
    public static boolean isValidSignature(String strToSign, String strSign, String publicKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(ENCRYPT_TYPE);
            byte[] encodedKey = Base64.decodeBase64(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
            java.security.Signature signature = java.security.Signature
                    .getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(pubKey);
            signature.update(strToSign.getBytes("UTF-8"));
            boolean bverify = signature.verify(Base64.decodeBase64(strSign));
            return bverify;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
