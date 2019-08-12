package com.study.cmb;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Test {

    public static void main(String[] args) {
        NoticeData noticeData = new NoticeData("20160622182921","https://...","POST","0755","002346","BKPAY",
                "201606238888888","aaa","bbb");
        System.out.println("---------默认排序-------");
//        createDefinitionSortTreeMap();
        System.out.println("====================");
        Map<String,String> map = getFiledName(noticeData);
        String str = print(map);
        String SHAStr = SHA256Util.getSHA256Str(str);
        System.out.println("---------SHA加密后的字符串为：");
        System.out.println(SHAStr);

    }

    public static void createDefinitionSortTreeMap() {
        TreeMap<String, String> map = new TreeMap<String, String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });
        init(map);
        print(map);
    }

    public static void init(Map<String, String> map) {
        map.put("DateTime", "20160622182921");
        map.put("noticeUrl", "https://...");
        map.put("httpMethod", "POST");
        map.put("branchNo", "0755");
        map.put("merchantNo","002346");
        map.put("noticeType","BKPAY");
        map.put("noticeSerialNo","201606238888888");
        map.put("param1","aaa");
        map.put("param2","bbb");
    }

    public static String print(Map<String, String> map) {
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        StringBuffer stringBuffer = new StringBuffer();
        while(it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            stringBuffer.append(entry.getKey());
            stringBuffer.append("=");
            stringBuffer.append(entry.getValue());
            stringBuffer.append("&");
        }
        System.out.println(stringBuffer.toString());
        return stringBuffer.toString();
    }

    /**
     * 利用反射获取key - value 的map
     * @param noticeData
     * @return
     */
    public static Map<String,String> getFiledName(NoticeData noticeData) {
        TreeMap<String, String> map = new TreeMap<String, String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });
        Field[] fields = noticeData.getClass().getDeclaredFields();
        String[] fieldNamStrings = new String[fields.length];
        for (int i = 0; i < fieldNamStrings.length; i++) {
            fieldNamStrings[i] = fields[i].getName();
            String name = fields[i].getName();
            String getter = "get"+ name.substring(0, 1).toUpperCase() +  name.substring(1);
            Method method;
            String value;
            try{
                method = noticeData.getClass().getMethod(getter, new Class[] {});
                value = (String)method.invoke(noticeData,new NoticeData[]{});
            }catch (Exception e){
                e.printStackTrace();
                value = "";
            }
            map.put(name,value);
        }
        print(map);
        return map;
    }

    /**
     * 回调数据
     */
    static class NoticeData{

        private String dateTime;

        private String noticeUrl;

        private String httpMethod;

        private String branchNo;

        private String merchantNo;

        private String noticeType;

        private String noticeSerialNo;

        private String param1;

        private String param2;

        public String getDateTime() {
            return dateTime;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
        }

        public String getNoticeUrl() {
            return noticeUrl;
        }

        public void setNoticeUrl(String noticeUrl) {
            this.noticeUrl = noticeUrl;
        }

        public String getHttpMethod() {
            return httpMethod;
        }

        public void setHttpMethod(String httpMethod) {
            this.httpMethod = httpMethod;
        }

        public String getBranchNo() {
            return branchNo;
        }

        public void setBranchNo(String branchNo) {
            this.branchNo = branchNo;
        }

        public String getMerchantNo() {
            return merchantNo;
        }

        public void setMerchantNo(String merchantNo) {
            this.merchantNo = merchantNo;
        }

        public String getNoticeType() {
            return noticeType;
        }

        public void setNoticeType(String noticeType) {
            this.noticeType = noticeType;
        }

        public String getNoticeSerialNo() {
            return noticeSerialNo;
        }

        public void setNoticeSerialNo(String noticeSerialNo) {
            this.noticeSerialNo = noticeSerialNo;
        }

        public String getParam1() {
            return param1;
        }

        public void setParam1(String param1) {
            this.param1 = param1;
        }

        public String getParam2() {
            return param2;
        }

        public void setParam2(String param2) {
            this.param2 = param2;
        }

        public NoticeData(String dateTime, String noticeUrl, String httpMethod, String branchNo, String merchantNo,
                          String noticeType, String noticeSerialNo, String param1, String param2) {
            this.dateTime = dateTime;
            this.noticeUrl = noticeUrl;
            this.httpMethod = httpMethod;
            this.branchNo = branchNo;
            this.merchantNo = merchantNo;
            this.noticeType = noticeType;
            this.noticeSerialNo = noticeSerialNo;
            this.param1 = param1;
            this.param2 = param2;
        }
    }
}
