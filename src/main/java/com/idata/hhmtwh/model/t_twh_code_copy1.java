package com.idata.hhmtwh.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 
 * @TableName t_twh_code_copy1
 */
@TableName(value ="t_twh_code_copy1")
public class t_twh_code_copy1 implements Serializable {
    /**
     * 
     */
    @TableField(value = "address")
    private String address;

    /**
     * 
     */
    @TableField(value = "place_code")
    private String placeCode;

    /**
     * 
     */
    @TableField(value = "twh")
    private String twh;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public String getAddress() {
        return address;
    }

    /**
     * 
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 
     */
    public String getPlaceCode() {
        return placeCode;
    }

    /**
     * 
     */
    public void setPlaceCode(String placeCode) {
        this.placeCode = placeCode;
    }

    /**
     * 
     */
    public String getTwh() {
        return twh;
    }

    /**
     * 
     */
    public void setTwh(String twh) {
        this.twh = twh;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        t_twh_code_copy1 other = (t_twh_code_copy1) that;
        return (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getPlaceCode() == null ? other.getPlaceCode() == null : this.getPlaceCode().equals(other.getPlaceCode()))
            && (this.getTwh() == null ? other.getTwh() == null : this.getTwh().equals(other.getTwh()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getPlaceCode() == null) ? 0 : getPlaceCode().hashCode());
        result = prime * result + ((getTwh() == null) ? 0 : getTwh().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", address=").append(address);
        sb.append(", placeCode=").append(placeCode);
        sb.append(", twh=").append(twh);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}