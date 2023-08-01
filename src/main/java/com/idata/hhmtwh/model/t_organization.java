package com.idata.hhmtwh.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 机构表
 * @TableName t_organization
 */
@TableName(value ="t_organization")
public class t_organization implements Serializable {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 机构名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 类型
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 省、直辖市编码
     */
    @TableField(value = "province")
    private String province;

    /**
     * 市编码
     */
    @TableField(value = "city")
    private String city;

    /**
     * 区、县编码
     */
    @TableField(value = "county")
    private String county;

    /**
     * 乡、镇、街道编码
     */
    @TableField(value = "town")
    private String town;

    /**
     * 村、社区、居委会编码
     */
    @TableField(value = "village")
    private String village;

    /**
     * 详细地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 负责人
     */
    @TableField(value = "charge_person")
    private String chargePerson;

    /**
     * 电话
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 上级机构id
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 是否删除 1:删除 0:正常
     */
    @TableField(value = "del_flag")
    private Integer delFlag;

    /**
     * 文件图片存放路径
     */
    @TableField(value = "seal_path")
    private String sealPath;

    /**
     * 讯飞机构id
     */
    @TableField(value = "xf_org_id")
    private String xfOrgId;

    /**
     * 讯飞父级id
     */
    @TableField(value = "xf_parent_id")
    private String xfParentId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 机构名称
     */
    public String getName() {
        return name;
    }

    /**
     * 机构名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 省、直辖市编码
     */
    public String getProvince() {
        return province;
    }

    /**
     * 省、直辖市编码
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 市编码
     */
    public String getCity() {
        return city;
    }

    /**
     * 市编码
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 区、县编码
     */
    public String getCounty() {
        return county;
    }

    /**
     * 区、县编码
     */
    public void setCounty(String county) {
        this.county = county;
    }

    /**
     * 乡、镇、街道编码
     */
    public String getTown() {
        return town;
    }

    /**
     * 乡、镇、街道编码
     */
    public void setTown(String town) {
        this.town = town;
    }

    /**
     * 村、社区、居委会编码
     */
    public String getVillage() {
        return village;
    }

    /**
     * 村、社区、居委会编码
     */
    public void setVillage(String village) {
        this.village = village;
    }

    /**
     * 详细地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 详细地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 负责人
     */
    public String getChargePerson() {
        return chargePerson;
    }

    /**
     * 负责人
     */
    public void setChargePerson(String chargePerson) {
        this.chargePerson = chargePerson;
    }

    /**
     * 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 上级机构id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 上级机构id
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 是否删除 1:删除 0:正常
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * 是否删除 1:删除 0:正常
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 文件图片存放路径
     */
    public String getSealPath() {
        return sealPath;
    }

    /**
     * 文件图片存放路径
     */
    public void setSealPath(String sealPath) {
        this.sealPath = sealPath;
    }

    /**
     * 讯飞机构id
     */
    public String getXfOrgId() {
        return xfOrgId;
    }

    /**
     * 讯飞机构id
     */
    public void setXfOrgId(String xfOrgId) {
        this.xfOrgId = xfOrgId;
    }

    /**
     * 讯飞父级id
     */
    public String getXfParentId() {
        return xfParentId;
    }

    /**
     * 讯飞父级id
     */
    public void setXfParentId(String xfParentId) {
        this.xfParentId = xfParentId;
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
        t_organization other = (t_organization) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getCounty() == null ? other.getCounty() == null : this.getCounty().equals(other.getCounty()))
            && (this.getTown() == null ? other.getTown() == null : this.getTown().equals(other.getTown()))
            && (this.getVillage() == null ? other.getVillage() == null : this.getVillage().equals(other.getVillage()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getChargePerson() == null ? other.getChargePerson() == null : this.getChargePerson().equals(other.getChargePerson()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getSealPath() == null ? other.getSealPath() == null : this.getSealPath().equals(other.getSealPath()))
            && (this.getXfOrgId() == null ? other.getXfOrgId() == null : this.getXfOrgId().equals(other.getXfOrgId()))
            && (this.getXfParentId() == null ? other.getXfParentId() == null : this.getXfParentId().equals(other.getXfParentId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getCounty() == null) ? 0 : getCounty().hashCode());
        result = prime * result + ((getTown() == null) ? 0 : getTown().hashCode());
        result = prime * result + ((getVillage() == null) ? 0 : getVillage().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getChargePerson() == null) ? 0 : getChargePerson().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getSealPath() == null) ? 0 : getSealPath().hashCode());
        result = prime * result + ((getXfOrgId() == null) ? 0 : getXfOrgId().hashCode());
        result = prime * result + ((getXfParentId() == null) ? 0 : getXfParentId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", name=").append(name);
        sb.append(", type=").append(type);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", county=").append(county);
        sb.append(", town=").append(town);
        sb.append(", village=").append(village);
        sb.append(", address=").append(address);
        sb.append(", chargePerson=").append(chargePerson);
        sb.append(", phone=").append(phone);
        sb.append(", parentId=").append(parentId);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", sealPath=").append(sealPath);
        sb.append(", xfOrgId=").append(xfOrgId);
        sb.append(", xfParentId=").append(xfParentId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}