package com.study.bcasf;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.util.Random;

@RestController
public class BaseController {

    @Value("${sm4.key}")
    private String sm4Key;

    @ResponseBody
    @RequestMapping(value = "/bcasf/zn/v1/faceScanSign.do",method = RequestMethod.POST)
    public ResponseData<SignResponse> sign(@RequestParam String sn){
        try{
            String key = "6B6F6D6F334C64364554696B46766F6E";
            BASE64Encoder encoder = new BASE64Encoder();
            SM4_Context sm4_context2 = new SM4_Context();
            byte[] iv2 = new byte[16];
            for (int i =0;i<16;i++){
                iv2[i] = 48;
            }
            SM4 sm42 = new SM4();
            sm42.sm4_setkey_enc(sm4_context2,Util.hexStringToBytes(key));
            byte[] crypt_cbc = sm42.sm4_crypt_cbc(sm4_context2,iv2,"ABCDABCDABCDABCDABCDABCDABCDABCD".getBytes());
            String jsonData = encoder.encodeBuffer(crypt_cbc);
            SignResponse signResponse = new SignResponse();
            signResponse.setMerchId("104110045258623");
            signResponse.setTerminalId("11008623");
            signResponse.setZek_dek(jsonData);
            signResponse.setZek_kcv("313131");
            ResponseData responseData = new ResponseData("0000","SUCCESS",JSON.toJSONString(signResponse));
            return responseData;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/bcasf/zn/v1/faceScanPurchase.do")
    public ResponseData<FacePayResp> faceScan(@RequestParam String sn, @RequestBody String data){
        JSONObject json = JSON.parseObject(data);
        BASE64Decoder decoder = new BASE64Decoder();
        BASE64Encoder encoder = new BASE64Encoder();
        ResponseData responseData = null;
        Random random = new Random();
        String jsonData= "";
        try {
            String key = "ABCDABCDABCDABCDABCDABCDABCDABCD";
            SM4_Context sm4_context = new SM4_Context();
            byte[] bytes = decoder.decodeBuffer(json.get("data").toString());
            byte[] iv = new byte[16];
            for (int i =0;i<16;i++){
                iv[i] = 48;
            }
            SM4 sm4 = new SM4();
            sm4.sm4_setkey_dec(sm4_context,Util.hexStringToBytes(key));
            byte[] contentByte = sm4.sm4_crypt_cbc(sm4_context,iv,bytes);
            String content = new String(contentByte);
            System.out.println(new String(content));
            FacePayReq model = JSON.parseObject(content, FacePayReq.class);
            System.out.println(JSON.toJSONString(model));
            FacePayResp facePayResp = new FacePayResp();
            int randomNumber = random.nextInt(10);
            if(randomNumber>2){
                facePayResp.setRespCode("0000000000");
                facePayResp.setRespMsg("SUCCESS");
            }else{
                facePayResp.setRespCode("`1111111111");
                facePayResp.setRespMsg("我也不知道啥错");
            }
            facePayResp.setCardNo("123");
            facePayResp.setTransAmount("1234");
            facePayResp.setTranNo("123343434");
            facePayResp.setTranTime("152830");
            facePayResp.setTranDate("0711");
            facePayResp.setPosConditionCode("1");
            facePayResp.setRrn("11");
            facePayResp.setAuthNum("1234");
            facePayResp.setTerminalId("11008623");
            facePayResp.setMerchId("104110045258623");
            facePayResp.setUsername("MIKE");
            facePayResp.setTelNum("0831");
            String str = JSON.toJSONString(facePayResp);
            SM4_Context sm4_context2 = new SM4_Context();
            byte[] iv2 = new byte[16];
            for (int i =0;i<16;i++){
                iv2[i] = 48;
            }
            SM4 sm42 = new SM4();
            sm42.sm4_setkey_enc(sm4_context2,Util.hexStringToBytes(key));
            byte[] crypt_cbc = sm42.sm4_crypt_cbc(sm4_context2,iv2,str.getBytes());
            jsonData = encoder.encodeBuffer(crypt_cbc);
            if(randomNumber>2){
                responseData = new ResponseData("0000","SUCCESS",jsonData);
            }else{
                responseData = new ResponseData("1111","数据非法","");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseData;
    }

    public static void main(String[] args) {
        try{
            BASE64Decoder decoder = new BASE64Decoder();
            String key = "6B6F6D6F334C64364554696B46766F6E";
            BASE64Encoder encoder = new BASE64Encoder();
            SM4_Context sm4_context2 = new SM4_Context();
            byte[] iv2 = new byte[16];
            for (int i =0;i<16;i++){
                iv2[i] = 48;
            }
            SM4 sm42 = new SM4();
            sm42.sm4_setkey_enc(sm4_context2,Util.hexStringToBytes(key));
            byte[] crypt_cbc = sm42.sm4_crypt_cbc(sm4_context2,iv2,"ABCDABCDABCDABCDABCDABCDABCDABCD".getBytes());
            String jsonData = encoder.encodeBuffer(crypt_cbc);
            String content = new String(jsonData);
            System.out.println(new String(content));
            System.out.println(new String(content).length());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping("/bcasf/zn/v1/faceScanReversal.do")
    public ResponseData<ReversqlResp> reversal(@RequestParam String sn, @RequestBody String data){
        JSONObject json = JSON.parseObject(data);
        BASE64Decoder decoder = new BASE64Decoder();
        BASE64Encoder encoder = new BASE64Encoder();
        ResponseData responseData = null;
        Random random = new Random();
        String jsonData= "";
        try{
            if(random.nextInt(10)>2){
                Thread.sleep(10000);
            }
            String key = "ABCDABCDABCDABCDABCDABCDABCDABCD";
            SM4_Context sm4_context = new SM4_Context();
            byte[] bytes = decoder.decodeBuffer(json.get("data").toString());
            byte[] iv = new byte[16];
            for (int i =0;i<16;i++){
                iv[i] = 48;
            }
            SM4 sm4 = new SM4();
            sm4.sm4_setkey_dec(sm4_context,Util.hexStringToBytes(key));
            byte[] contentByte = sm4.sm4_crypt_cbc(sm4_context,iv,bytes);
            String content = new String(contentByte);
            System.out.println(new String(content));
            ReversqlReq model = JSON.parseObject(content, ReversqlReq.class);
            System.out.println(JSON.toJSONString(model));
            ReversqlResp facePayResp = new ReversqlResp();
            if(random.nextInt(10)>5){
                facePayResp.setRespCode("0000000000");
                facePayResp.setRespMsg("SUCCESS");
            }else{
                facePayResp.setRespCode("1111111111");
                facePayResp.setRespMsg("我也不知道啥错");
            }
            facePayResp.setCardNo("356");
            facePayResp.setTransAmount("100");
            facePayResp.setTranNo("101010101");
            facePayResp.setTranTime("155230");
            facePayResp.setTranDate("0711");
            facePayResp.setPosConditionCode("1");
            facePayResp.setRrn("11");
            facePayResp.setAuthNum("4321");
            facePayResp.setTerminalId("11008623");
            facePayResp.setMerchId("104110045258623");
            String str = JSON.toJSONString(facePayResp);
            SM4_Context sm4_context2 = new SM4_Context();
            byte[] iv2 = new byte[16];
            for (int i =0;i<16;i++){
                iv2[i] = 48;
            }
            SM4 sm42 = new SM4();
            sm42.sm4_setkey_enc(sm4_context2,Util.hexStringToBytes(key));
            byte[] crypt_cbc = sm42.sm4_crypt_cbc(sm4_context2,iv2,str.getBytes());
            jsonData = encoder.encodeBuffer(crypt_cbc);
            if(random.nextInt(10)>5){
                responseData = new ResponseData("0000","SUCCESS",jsonData);
            }else{
                responseData = new ResponseData("2301","终端不存在","");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return responseData;
    }


    @ResponseBody
    @RequestMapping("/bcasf/zn/v1/faceScanDiscern.do")
    public ResponseData<FacePayResp> faceScanDiscern(@RequestParam String sn, @RequestBody String data){
        JSONObject json = JSON.parseObject(data);
        BASE64Decoder decoder = new BASE64Decoder();
        BASE64Encoder encoder = new BASE64Encoder();
        Random random = new Random();
        ResponseData responseData = null;
        String jsonData= "";
        try {
            String key = "ABCDABCDABCDABCDABCDABCDABCDABCD";
            SM4_Context sm4_context = new SM4_Context();
            byte[] bytes = decoder.decodeBuffer(json.get("data").toString());
            byte[] iv = new byte[16];
            for (int i =0;i<16;i++){
                iv[i] = 48;
            }
            SM4 sm4 = new SM4();
            sm4.sm4_setkey_dec(sm4_context,Util.hexStringToBytes(key));
            byte[] contentByte = sm4.sm4_crypt_cbc(sm4_context,iv,bytes);
            String content = new String(contentByte);
            System.out.println(new String(content));
            FaceScanReq model = JSON.parseObject(content, FaceScanReq.class);
            System.out.println(JSON.toJSONString(model));
            FaceScanResp resp = new FaceScanResp();
            if(random.nextInt(10)>5){
                resp.setRespCode("0000000000");
                resp.setRespMsg("SUCCESS");
            }else{
                resp.setRespCode("1111111111");
                resp.setRespMsg("我也不知道啥错");
            }
            resp.setHeadingCode("12345");
            resp.setUsername("hahahaha");
            resp.setTelNum("15623193800");
            String str = JSON.toJSONString(resp);
            SM4_Context sm4_context2 = new SM4_Context();
            byte[] iv2 = new byte[16];
            for (int i =0;i<16;i++){
                iv2[i] = 48;
            }
            SM4 sm42 = new SM4();
            sm42.sm4_setkey_enc(sm4_context2,Util.hexStringToBytes(key));
            byte[] crypt_cbc = sm42.sm4_crypt_cbc(sm4_context2,iv2,str.getBytes());
            jsonData = encoder.encodeBuffer(crypt_cbc);
            if(random.nextInt(10)>5){
                responseData = new ResponseData("0000","SUCCESS",jsonData);
            }else{
                responseData = new ResponseData("1111","数据非法","");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseData;
    }

}
