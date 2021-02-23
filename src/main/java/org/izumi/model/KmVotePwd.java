package org.izumi.model;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2021-01-29
 */
public class KmVotePwd {
    private String pwd;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    @Override
    public String toString() {
        return "KmVotePwd{" +
                "pwd='" + pwd + '\'' +
                '}';
    }
}