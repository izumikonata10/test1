package org.izumi.model;

/**
 * @auther izumi
 * @date 2020-09-19 16:36
 */
public class ReturnCitySN {
    private String cid;
    private String cip;
    private String cname;

    @Override
    public String toString() {
        return "ReturnCitySN{" +
                "cid='" + cid +'\'' +
                ", cip='" + cip +'\'' +
                ", cname='" + cname + '\'' +
                '}';
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCip() {
        return cip;
    }

    public void setCip(String cip) {
        this.cip = cip;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
