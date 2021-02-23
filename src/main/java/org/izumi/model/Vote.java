package org.izumi.model;

import java.util.List;

/**
 * @author izumi
 * @date 2021-01-21 11:02
 */
public class Vote {
    private String id;

    private String fdQuestionExplain;

    private List<Score> score;

    private String docSubject;

    public String getDocSubject() {
        return docSubject;
    }

    public void setDocSubject(String docSubject) {
        this.docSubject = docSubject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFdQuestionExplain() {
        return fdQuestionExplain;
    }

    public void setFdQuestionExplain(String fdQuestionExplain) {
        this.fdQuestionExplain = fdQuestionExplain;
    }

    public List<Score> getScore() {
        return score;
    }

    public void setScore(List<Score> score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id='" + id + '\'' +
                ", fdQuestionExplain='" + fdQuestionExplain + '\'' +
                ", score=" + score +
                ", docSubject='" + docSubject + '\'' +
                '}';
    }
}
