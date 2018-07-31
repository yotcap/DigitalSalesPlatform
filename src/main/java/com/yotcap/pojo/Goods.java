package com.yotcap.pojo;

public class Goods {
    private Integer id;
    private String goodsName;
    private String goodsImage;
    private String goodsDesc;

    @Override
    public String toString() {
        return "goods{" +
                "id=" + id +
                ", goodsName='" + goodsName + '\'' +
                ", goodsImage='" + goodsImage + '\'' +
                ", goodsDesc='" + goodsDesc + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }
}
