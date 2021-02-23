package org.izumi.model;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2021-01-27
 */
public class KmVoteSub {
    private Integer fdId;

    private Integer fdAnswer;

    private String fdQuestionId;

    public Integer getFdId() {
        return fdId;
    }

    public void setFdId(Integer fdId) {
        this.fdId = fdId;
    }

    public Integer getFdAnswer() {
        return fdAnswer;
    }

    public void setFdAnswer(Integer fdAnswer) {
        this.fdAnswer = fdAnswer;
    }

    public String getFdQuestionId() {
        return fdQuestionId;
    }

    public void setFdQuestionId(String fdQuestionId) {
        this.fdQuestionId = fdQuestionId == null ? null : fdQuestionId.trim();
    }
}