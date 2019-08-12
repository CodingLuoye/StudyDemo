package com.study.hystrix;

/**
 * @author YCKJ1409
 */
public class ProductInfo {

    private Long productId;

    private Long  cityId;

    private String cityName;

    private Long brandId;

    private String brandName;


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "productId=" + productId +
                ", cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", brandId=" + brandId +
                ", brandName='" + brandName + '\'' +
                '}';
    }
}
