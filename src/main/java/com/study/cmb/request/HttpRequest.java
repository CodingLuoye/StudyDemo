package com.study.cmb.request;

import com.study.cmb.CreateSignUtil;
import com.study.cmb.SHA256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author YCKJ1409
 *
 */
public class HttpRequest {

    private static final String URL = "http://121.15.180.72/CmbBank_B2B/UI/NetPay/DoBusiness.ashx";

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMddHHmmss");

    private static final String TXCODE = "FBPK";

    private static final String VERSION = "1.0";

    private static final String CHARSET = "UTF-8";

    private static final String SIGNTYPE = "SHA-256";

    private RestTemplate restTemplate;

    public HttpRequest(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public String getPublicKey(){
        JsonRequest request = new JsonRequest();
        request.setVersion(VERSION);
        request.setCharset(CHARSET);
        request.setSingType(SIGNTYPE);
        RequestData reqData = new RequestData();
        reqData.setDateTime(SDF.format(new Date()));
        reqData.setTxCode(TXCODE);
        reqData.setBranchNo("0755");
        reqData.setMerchantNo("002346");
        request.setReqData(reqData);
        String sign = CreateSignUtil.getFiledName(reqData);
        System.out.println("----------待加密的sing="+sign);
        String signStr = SHA256Util.getSHA256Str(sign);
        System.out.println("======加密的后的sing="+signStr);
        request.setSign(signStr);
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<JsonRequest> formEntity = new HttpEntity<>(request, headers);
        try{
            JsonResponse response  = restTemplate.postForObject(URL, formEntity,
                    JsonResponse.class);
            if("SUC0000".equals(response.getRspData().getRspCode())){
                String fbPubKey = response.getRspData().getFbPubKey();
                System.out.println("招行的公钥为"+fbPubKey);
                return fbPubKey;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        HttpRequest httpRequest = new HttpRequest(restTemplate);
        String str = httpRequest.getPublicKey();
        System.out.println(str);
    }
}
