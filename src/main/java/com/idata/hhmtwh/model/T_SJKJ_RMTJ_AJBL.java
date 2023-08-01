package com.idata.hhmtwh.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName T_SJKJ_RMTJ_AJBL
 */
@TableName(value ="T_SJKJ_RMTJ_AJBL")
public class T_SJKJ_RMTJ_AJBL implements Serializable {
    /**
     * 纠纷类型
     */
    @TableField(value = "JFLX")
    private String jflx;

    /**
     * 纠纷来源
     */
    @TableField(value = "JFLY")
    private String jfly;

    /**
     * 行政区划
     */
    @TableField(value = "XZQH")
    private String xzqh;

    /**
     * 受理日期
     */
    @TableField(value = "SLRQ")
    private Date slrq;

    /**
     * 发生日期
     */
    @TableField(value = "FSRQ")
    private Date fsrq;

    /**
     * 
     */
    @TableField(value = "SADSRSL")
    private String sadsrsl;

    /**
     * 涉案金额
     */
    @TableField(value = "SAJE")
    private Long saje;

    /**
     * 涉及特殊群体情况
     */
    @TableField(value = "SJTSQTQK")
    private String sjtsqtqk;

    /**
     * 涉及农民工纠纷情况
     */
    @TableField(value = "SJNMGJFQK")
    private String sjnmgjfqk;

    /**
     * 编号
     */
    @TableField(value = "BH")
    private String bh;

    /**
     * 受理单位
     */
    @TableField(value = "SLDW")
    private String sldw;

    /**
     * 受理人
     */
    @TableField(value = "SLR")
    private String slr;

    /**
     * 调解员
     */
    @TableField(value = "TJY")
    private String tjy;

    /**
     * 调解结果
     */
    @TableField(value = "TJJG")
    private String tjjg;

    /**
     * 案件难度级别 
     */
    @TableField(value = "AJNDJB")
    private String ajndjb;

    /**
     * 纠纷转化情况 
     */
    @TableField(value = "JFJHQK")
    private String jfjhqk;

    /**
     * 
     */
    @TableField(value = "AJYC")
    private String ajyc;

    /**
     * 协议类型
     */
    @TableField(value = "XYLX")
    private String xylx;

    /**
     * 履行情况
     */
    @TableField(value = "LXQK")
    private String lxqk;

    /**
     * 履行方式
     */
    @TableField(value = "LXFS")
    private String lxfs;

    /**
     * 是否确认
     */
    @TableField(value = "SFSFQR")
    private String sfsfqr;

    /**
     * 是否满意
     */
    @TableField(value = "SFMY")
    private String sfmy;

    /**
     * 回访形式
     */
    @TableField(value = "HFXS")
    private String hfxs;

    /**
     * 回访日期
     */
    @TableField(value = "HFRQ")
    private Date hfrq;

    /**
     * 案件状态
     */
    @TableField(value = "AJZT")
    private String ajzt;

    /**
     * 案件编号
     */
    @TableField(value = "AJBH")
    private String ajbh;

    /**
     * 纠纷简介
     */
    @TableField(value = "JFJJ")
    private String jfjj;

    /**
     * 协议内容
     */
    @TableField(value = "XYNR")
    private String xynr;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 纠纷类型
     */
    public String getJflx() {
        return jflx;
    }

    /**
     * 纠纷类型
     */
    public void setJflx(String jflx) {
        this.jflx = jflx;
    }

    /**
     * 纠纷来源
     */
    public String getJfly() {
        return jfly;
    }

    /**
     * 纠纷来源
     */
    public void setJfly(String jfly) {
        this.jfly = jfly;
    }

    /**
     * 行政区划
     */
    public String getXzqh() {
        return xzqh;
    }

    /**
     * 行政区划
     */
    public void setXzqh(String xzqh) {
        this.xzqh = xzqh;
    }

    /**
     * 受理日期
     */
    public Date getSlrq() {
        return slrq;
    }

    /**
     * 受理日期
     */
    public void setSlrq(Date slrq) {
        this.slrq = slrq;
    }

    /**
     * 发生日期
     */
    public Date getFsrq() {
        return fsrq;
    }

    /**
     * 发生日期
     */
    public void setFsrq(Date fsrq) {
        this.fsrq = fsrq;
    }

    /**
     * 
     */
    public String getSadsrsl() {
        return sadsrsl;
    }

    /**
     * 
     */
    public void setSadsrsl(String sadsrsl) {
        this.sadsrsl = sadsrsl;
    }

    /**
     * 涉案金额
     */
    public Long getSaje() {
        return saje;
    }

    /**
     * 涉案金额
     */
    public void setSaje(Long saje) {
        this.saje = saje;
    }

    /**
     * 涉及特殊群体情况
     */
    public String getSjtsqtqk() {
        return sjtsqtqk;
    }

    /**
     * 涉及特殊群体情况
     */
    public void setSjtsqtqk(String sjtsqtqk) {
        this.sjtsqtqk = sjtsqtqk;
    }

    /**
     * 涉及农民工纠纷情况
     */
    public String getSjnmgjfqk() {
        return sjnmgjfqk;
    }

    /**
     * 涉及农民工纠纷情况
     */
    public void setSjnmgjfqk(String sjnmgjfqk) {
        this.sjnmgjfqk = sjnmgjfqk;
    }

    /**
     * 编号
     */
    public String getBh() {
        return bh;
    }

    /**
     * 编号
     */
    public void setBh(String bh) {
        this.bh = bh;
    }

    /**
     * 受理单位
     */
    public String getSldw() {
        return sldw;
    }

    /**
     * 受理单位
     */
    public void setSldw(String sldw) {
        this.sldw = sldw;
    }

    /**
     * 受理人
     */
    public String getSlr() {
        return slr;
    }

    /**
     * 受理人
     */
    public void setSlr(String slr) {
        this.slr = slr;
    }

    /**
     * 调解员
     */
    public String getTjy() {
        return tjy;
    }

    /**
     * 调解员
     */
    public void setTjy(String tjy) {
        this.tjy = tjy;
    }

    /**
     * 调解结果
     */
    public String getTjjg() {
        return tjjg;
    }

    /**
     * 调解结果
     */
    public void setTjjg(String tjjg) {
        this.tjjg = tjjg;
    }

    /**
     * 案件难度级别 
     */
    public String getAjndjb() {
        return ajndjb;
    }

    /**
     * 案件难度级别 
     */
    public void setAjndjb(String ajndjb) {
        this.ajndjb = ajndjb;
    }

    /**
     * 纠纷转化情况 
     */
    public String getJfjhqk() {
        return jfjhqk;
    }

    /**
     * 纠纷转化情况 
     */
    public void setJfjhqk(String jfjhqk) {
        this.jfjhqk = jfjhqk;
    }

    /**
     * 
     */
    public String getAjyc() {
        return ajyc;
    }

    /**
     * 
     */
    public void setAjyc(String ajyc) {
        this.ajyc = ajyc;
    }

    /**
     * 协议类型
     */
    public String getXylx() {
        return xylx;
    }

    /**
     * 协议类型
     */
    public void setXylx(String xylx) {
        this.xylx = xylx;
    }

    /**
     * 履行情况
     */
    public String getLxqk() {
        return lxqk;
    }

    /**
     * 履行情况
     */
    public void setLxqk(String lxqk) {
        this.lxqk = lxqk;
    }

    /**
     * 履行方式
     */
    public String getLxfs() {
        return lxfs;
    }

    /**
     * 履行方式
     */
    public void setLxfs(String lxfs) {
        this.lxfs = lxfs;
    }

    /**
     * 是否确认
     */
    public String getSfsfqr() {
        return sfsfqr;
    }

    /**
     * 是否确认
     */
    public void setSfsfqr(String sfsfqr) {
        this.sfsfqr = sfsfqr;
    }

    /**
     * 是否满意
     */
    public String getSfmy() {
        return sfmy;
    }

    /**
     * 是否满意
     */
    public void setSfmy(String sfmy) {
        this.sfmy = sfmy;
    }

    /**
     * 回访形式
     */
    public String getHfxs() {
        return hfxs;
    }

    /**
     * 回访形式
     */
    public void setHfxs(String hfxs) {
        this.hfxs = hfxs;
    }

    /**
     * 回访日期
     */
    public Date getHfrq() {
        return hfrq;
    }

    /**
     * 回访日期
     */
    public void setHfrq(Date hfrq) {
        this.hfrq = hfrq;
    }

    /**
     * 案件状态
     */
    public String getAjzt() {
        return ajzt;
    }

    /**
     * 案件状态
     */
    public void setAjzt(String ajzt) {
        this.ajzt = ajzt;
    }

    /**
     * 案件编号
     */
    public String getAjbh() {
        return ajbh;
    }

    /**
     * 案件编号
     */
    public void setAjbh(String ajbh) {
        this.ajbh = ajbh;
    }

    /**
     * 纠纷简介
     */
    public String getJfjj() {
        return jfjj;
    }

    /**
     * 纠纷简介
     */
    public void setJfjj(String jfjj) {
        this.jfjj = jfjj;
    }

    /**
     * 协议内容
     */
    public String getXynr() {
        return xynr;
    }

    /**
     * 协议内容
     */
    public void setXynr(String xynr) {
        this.xynr = xynr;
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
        T_SJKJ_RMTJ_AJBL other = (T_SJKJ_RMTJ_AJBL) that;
        return (this.getJflx() == null ? other.getJflx() == null : this.getJflx().equals(other.getJflx()))
            && (this.getJfly() == null ? other.getJfly() == null : this.getJfly().equals(other.getJfly()))
            && (this.getXzqh() == null ? other.getXzqh() == null : this.getXzqh().equals(other.getXzqh()))
            && (this.getSlrq() == null ? other.getSlrq() == null : this.getSlrq().equals(other.getSlrq()))
            && (this.getFsrq() == null ? other.getFsrq() == null : this.getFsrq().equals(other.getFsrq()))
            && (this.getSadsrsl() == null ? other.getSadsrsl() == null : this.getSadsrsl().equals(other.getSadsrsl()))
            && (this.getSaje() == null ? other.getSaje() == null : this.getSaje().equals(other.getSaje()))
            && (this.getSjtsqtqk() == null ? other.getSjtsqtqk() == null : this.getSjtsqtqk().equals(other.getSjtsqtqk()))
            && (this.getSjnmgjfqk() == null ? other.getSjnmgjfqk() == null : this.getSjnmgjfqk().equals(other.getSjnmgjfqk()))
            && (this.getBh() == null ? other.getBh() == null : this.getBh().equals(other.getBh()))
            && (this.getSldw() == null ? other.getSldw() == null : this.getSldw().equals(other.getSldw()))
            && (this.getSlr() == null ? other.getSlr() == null : this.getSlr().equals(other.getSlr()))
            && (this.getTjy() == null ? other.getTjy() == null : this.getTjy().equals(other.getTjy()))
            && (this.getTjjg() == null ? other.getTjjg() == null : this.getTjjg().equals(other.getTjjg()))
            && (this.getAjndjb() == null ? other.getAjndjb() == null : this.getAjndjb().equals(other.getAjndjb()))
            && (this.getJfjhqk() == null ? other.getJfjhqk() == null : this.getJfjhqk().equals(other.getJfjhqk()))
            && (this.getAjyc() == null ? other.getAjyc() == null : this.getAjyc().equals(other.getAjyc()))
            && (this.getXylx() == null ? other.getXylx() == null : this.getXylx().equals(other.getXylx()))
            && (this.getLxqk() == null ? other.getLxqk() == null : this.getLxqk().equals(other.getLxqk()))
            && (this.getLxfs() == null ? other.getLxfs() == null : this.getLxfs().equals(other.getLxfs()))
            && (this.getSfsfqr() == null ? other.getSfsfqr() == null : this.getSfsfqr().equals(other.getSfsfqr()))
            && (this.getSfmy() == null ? other.getSfmy() == null : this.getSfmy().equals(other.getSfmy()))
            && (this.getHfxs() == null ? other.getHfxs() == null : this.getHfxs().equals(other.getHfxs()))
            && (this.getHfrq() == null ? other.getHfrq() == null : this.getHfrq().equals(other.getHfrq()))
            && (this.getAjzt() == null ? other.getAjzt() == null : this.getAjzt().equals(other.getAjzt()))
            && (this.getAjbh() == null ? other.getAjbh() == null : this.getAjbh().equals(other.getAjbh()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getJflx() == null) ? 0 : getJflx().hashCode());
        result = prime * result + ((getJfly() == null) ? 0 : getJfly().hashCode());
        result = prime * result + ((getXzqh() == null) ? 0 : getXzqh().hashCode());
        result = prime * result + ((getSlrq() == null) ? 0 : getSlrq().hashCode());
        result = prime * result + ((getFsrq() == null) ? 0 : getFsrq().hashCode());
        result = prime * result + ((getSadsrsl() == null) ? 0 : getSadsrsl().hashCode());
        result = prime * result + ((getSaje() == null) ? 0 : getSaje().hashCode());
        result = prime * result + ((getSjtsqtqk() == null) ? 0 : getSjtsqtqk().hashCode());
        result = prime * result + ((getSjnmgjfqk() == null) ? 0 : getSjnmgjfqk().hashCode());
        result = prime * result + ((getBh() == null) ? 0 : getBh().hashCode());
        result = prime * result + ((getSldw() == null) ? 0 : getSldw().hashCode());
        result = prime * result + ((getSlr() == null) ? 0 : getSlr().hashCode());
        result = prime * result + ((getTjy() == null) ? 0 : getTjy().hashCode());
        result = prime * result + ((getTjjg() == null) ? 0 : getTjjg().hashCode());
        result = prime * result + ((getAjndjb() == null) ? 0 : getAjndjb().hashCode());
        result = prime * result + ((getJfjhqk() == null) ? 0 : getJfjhqk().hashCode());
        result = prime * result + ((getAjyc() == null) ? 0 : getAjyc().hashCode());
        result = prime * result + ((getXylx() == null) ? 0 : getXylx().hashCode());
        result = prime * result + ((getLxqk() == null) ? 0 : getLxqk().hashCode());
        result = prime * result + ((getLxfs() == null) ? 0 : getLxfs().hashCode());
        result = prime * result + ((getSfsfqr() == null) ? 0 : getSfsfqr().hashCode());
        result = prime * result + ((getSfmy() == null) ? 0 : getSfmy().hashCode());
        result = prime * result + ((getHfxs() == null) ? 0 : getHfxs().hashCode());
        result = prime * result + ((getHfrq() == null) ? 0 : getHfrq().hashCode());
        result = prime * result + ((getAjzt() == null) ? 0 : getAjzt().hashCode());
        result = prime * result + ((getAjbh() == null) ? 0 : getAjbh().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", jflx=").append(jflx);
        sb.append(", jfly=").append(jfly);
        sb.append(", xzqh=").append(xzqh);
        sb.append(", slrq=").append(slrq);
        sb.append(", fsrq=").append(fsrq);
        sb.append(", sadsrsl=").append(sadsrsl);
        sb.append(", saje=").append(saje);
        sb.append(", sjtsqtqk=").append(sjtsqtqk);
        sb.append(", sjnmgjfqk=").append(sjnmgjfqk);
        sb.append(", bh=").append(bh);
        sb.append(", sldw=").append(sldw);
        sb.append(", slr=").append(slr);
        sb.append(", tjy=").append(tjy);
        sb.append(", tjjg=").append(tjjg);
        sb.append(", ajndjb=").append(ajndjb);
        sb.append(", jfjhqk=").append(jfjhqk);
        sb.append(", ajyc=").append(ajyc);
        sb.append(", xylx=").append(xylx);
        sb.append(", lxqk=").append(lxqk);
        sb.append(", lxfs=").append(lxfs);
        sb.append(", sfsfqr=").append(sfsfqr);
        sb.append(", sfmy=").append(sfmy);
        sb.append(", hfxs=").append(hfxs);
        sb.append(", hfrq=").append(hfrq);
        sb.append(", ajzt=").append(ajzt);
        sb.append(", ajbh=").append(ajbh);
        sb.append(", jfjj=").append(jfjj);
        sb.append(", xynr=").append(xynr);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}