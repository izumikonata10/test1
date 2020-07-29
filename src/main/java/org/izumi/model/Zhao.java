package org.izumi.model;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2020-07-28
 */
public class Zhao {
    private Integer zId;

    private String zName;

    private Integer zMoney;

    public Integer getzId() {
        return zId;
    }

    public void setzId(Integer zId) {
        this.zId = zId;
    }

    public String getzName() {
        return zName;
    }

    public void setzName(String zName) {
        this.zName = zName == null ? null : zName.trim();
    }

    public Integer getzMoney() {
        return zMoney;
    }

    public void setzMoney(Integer zMoney) {
        this.zMoney = zMoney;
    }
}