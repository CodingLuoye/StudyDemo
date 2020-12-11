package com.study.tool;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author YCKJ1409
 * 从error日志中恢复交易流水数据，并生成插入的sql，吸入自定义的文件中
 */
public class ReadLineError {

    public static void main(String[] args) throws IOException {
        FileWriter writer=new FileWriter("D:\\交付\\info1.log");
        FileReader fr=new FileReader("D:\\交付\\error (7).log");
        BufferedReader br=new BufferedReader(fr);
        String line="";
        String[] arrs=null;
        AtomicInteger integer = new AtomicInteger(0);
        while ((line=br.readLine())!=null) {
            if(line.contains("记录一网通流水记录数据")){
                arrs = line.split("记录一网通流水记录数据");
                String cmb = arrs[1];
                CmbSerial cmbSerial = JSON.parseObject(cmb.substring(1,cmb.length()-1),CmbSerial.class);
                StringBuffer stringBuffer = new StringBuffer("");
                stringBuffer.append("INSERT INTO \"CW_PAY_BANK_SERIAL\" (\"ID\",\"TRADING_DATE_TIME\"," +
                        "\"BANK_SERIAL_NUM\",\"ACCOUNT_NAME\",\"ACCOUNT_ID\",\"PHONE_NUMBER\",\"MERCHANT_CODE\"," +
                        "\"CLIENT_CODE\",\"TRADING_AMOUNT\",\"AFTER_ALLOWANCE_AMOUNT\",\"TRADE_TYPE\",\"TRADE_TYPE_NAME\",\"TRADE_STATUS\",\"TRADE_STATUS_NAME\",\"ORG_ID\",\"CREATE_TIME\",\"CREATE_USER_ID\"" +
                        ",\"LAST_UPDATE_TIME\",\"LAST_UPDATE_USER_ID\",\"MASTER_ID\",\"CLIENT_SERIAL_NUM\",\"AGR_NO\",\"MEAL_TYPE\")VALUES\n");
                stringBuffer.append("(");
                stringBuffer.append("'"+cmbSerial.getId()+"'" + ","+cmbSerial.getTradingDateTime()+",'"+cmbSerial.getBankSerialNum()+"','"+cmbSerial.getAccountName()+"','"+cmbSerial.getAccountId());
                stringBuffer.append("','");
                stringBuffer.append(cmbSerial.getPhoneNumber());
                stringBuffer.append("','");
                stringBuffer.append(cmbSerial.getMerchantCode());
                stringBuffer.append("','");
                stringBuffer.append(cmbSerial.getClientCode());
                stringBuffer.append("',");
                stringBuffer.append(cmbSerial.getTradingAmount());
                stringBuffer.append(",");
                stringBuffer.append(cmbSerial.getAfterAllowanceAmount());
                stringBuffer.append(",");
                stringBuffer.append(cmbSerial.getTradeType());
                stringBuffer.append(",'");
                stringBuffer.append(cmbSerial.getTradeTypeName());
                stringBuffer.append("',");
                stringBuffer.append(cmbSerial.getTradeStatus());
                stringBuffer.append(",'");
                stringBuffer.append(cmbSerial.getTradeStatusName());
                stringBuffer.append("','");
                stringBuffer.append(cmbSerial.getOrgId());
                stringBuffer.append("',");
                stringBuffer.append(cmbSerial.getCreateTime());
                stringBuffer.append(",'");
                stringBuffer.append(cmbSerial.getCreateUserId());
                stringBuffer.append("',");
                stringBuffer.append(cmbSerial.getLastUpdateTime());
                stringBuffer.append(",'");
                stringBuffer.append(cmbSerial.getLastUpdateUserId());
                stringBuffer.append("',");
                stringBuffer.append(cmbSerial.getMasterId());
                stringBuffer.append(",'");
                stringBuffer.append(cmbSerial.getClientSerialNum());
                stringBuffer.append("','");
                stringBuffer.append(cmbSerial.getAgrNo());
                stringBuffer.append("',");
                stringBuffer.append(cmbSerial.getMealType());
                stringBuffer.append(")");
                System.out.println(stringBuffer.toString());
                writer.write(stringBuffer.toString());
                writer.write(";\n");
                integer.getAndIncrement();
            }
        }
        br.close();
        fr.close();
        writer.close();
        System.out.println(integer.get());
    }

}
