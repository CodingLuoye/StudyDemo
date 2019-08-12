package com.study.cmb;

import com.study.cmb.request.RequestData;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 生成sign的签名
 * @author YCKJ1409
 */
public class CreateSignUtil {

    /**
     * 利用反射获取key - value 的map
     * @param requestData
     * @return
     */
    public static String getFiledName(RequestData requestData) {
        TreeMap<String, String> map = new TreeMap<String, String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });
        Field[] fields = requestData.getClass().getDeclaredFields();
        String[] fieldNamStrings = new String[fields.length];
        for (int i = 0; i < fieldNamStrings.length; i++) {
            fieldNamStrings[i] = fields[i].getName();
            String name = fields[i].getName();
            String getter = "get"+ name.substring(0, 1).toUpperCase() +  name.substring(1);
            Method method;
            String value;
            try{
                method = requestData.getClass().getMethod(getter, new Class[] {});
                value = (String)method.invoke(requestData,new RequestData[]{});
            }catch (Exception e){
                e.printStackTrace();
                value = "";
            }
            map.put(name,value);
        }
        StringBuffer sb = new StringBuffer();
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
            sb.append("&");
        }
        String string = sb.toString();
        string = string.substring(0,string.length()-1);
        return string;
    }
}
