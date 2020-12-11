package com.study.tool;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UpdateStatus {
    public static void main(String[] args) throws IOException {
        FileWriter writer=new FileWriter("D:\\交付\\22.log");
        FileReader fr=new FileReader("D:\\交付\\2.log");
        BufferedReader br=new BufferedReader(fr);
        String line="";
        while ((line=br.readLine())!=null) {
            CmbIncomingCallBackDto cmbSerial = JSON.parseObject(line.substring(0,line.length()-1),
                    CmbIncomingCallBackDto.class);
            StringBuffer stringBuffer = new StringBuffer("");
            if(cmbSerial.getNoticeData().getRspMsg().equals("收款交易成功")){
                stringBuffer.append("update  CW_PAY_BANK_SERIAL set TRADE_STATUS = '1' , TRADE_STATUS_NAME = '交易成功' ," +
                        " remark = '' " + " " + "where BANK_SERIAL_NUM = '");
                stringBuffer.append(cmbSerial.getNoticeData().getMerchantSerialNo());
                stringBuffer.append("'; \n");
                writer.write(stringBuffer.toString());
            }else{
                stringBuffer.append("update  CW_PAY_BANK_SERIAL set TRADE_STATUS = '2' , TRADE_STATUS_NAME = '交易失败'");
                stringBuffer.append(" , remark = '");
                stringBuffer.append(cmbSerial.getNoticeData().getRspMsg() + "' where BANK_SERIAL_NUM = '");
                stringBuffer.append(cmbSerial.getNoticeData().getMerchantSerialNo());
                stringBuffer.append("'; \n");
                writer.write(stringBuffer.toString());
            }
        }
        br.close();
        fr.close();
        writer.close();
    }
}
