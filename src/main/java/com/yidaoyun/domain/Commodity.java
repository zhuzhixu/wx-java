package com.yidaoyun.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yidaoyun.common.base.BaseEntity;

/**
 * 商品
 * @author 云计算应用与开发项目组
 * @since  V2.0
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Commodity extends BaseEntity {

    //名称
    private String name;

    //简介
    private String content;

    //价格
    private String price;

    //图片
    private String img;

    //图片
    private String imgs;
    
    //下架   0 未下架  1 下架
    private Integer flag;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
    
    

}