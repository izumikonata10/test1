package org.izumi.model;

/**
 *
 *
 * @author wcyong
 *
 * @date 2021-01-27
 */
public class KmVoteAvg {
    private String fdId;

    private Double fdAvg;

    public String getFdId() {
        return fdId;
    }

    public void setFdId(String fdId) {
        this.fdId = fdId == null ? null : fdId.trim();
    }

    public Double getFdAvg() {
        return fdAvg;
    }

    public void setFdAvg(Double fdAvg) {
        this.fdAvg = fdAvg;
    }
}