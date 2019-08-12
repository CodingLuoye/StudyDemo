package com.study.hystrix.localcache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author
 * 本地缓存内存降级
 */

public class BrandCache {

    private static Map<Long, String> brandMap = new HashMap<>();

    static {
        brandMap.put(1L, "Nike");
    }

    /**
     * brandId 获取 brandName
     *
     * @param brandId 品牌id
     * @return 品牌名
     */
    public static String getBrandName(Long brandId) {
        return brandMap.get(brandId);
    }
}