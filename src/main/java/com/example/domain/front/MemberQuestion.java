package com.example.domain.front;

/**
 * @author 18190
 * @Date: 2021/7/30  20:19
 * @VERSION 1.0
 */
public class MemberQuestion {
    private String id;
    private String questionId;
    private String examinationpaperId;
    private String answerResult;
    private String rightAnswer;

    @Override
    public String toString() {
        return "MemberQuestion{" +
                "id='" + id + '\'' +
                ", questionId='" + questionId + '\'' +
                ", examinationpaperId='" + examinationpaperId + '\'' +
                ", answerResult='" + answerResult + '\'' +
                ", rightAnswer='" + rightAnswer + '\'' +
                '}';
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getExaminationpaperId() {
        return examinationpaperId;
    }

    public void setExaminationpaperId(String examinationpaperId) {
        this.examinationpaperId = examinationpaperId;
    }

    public String getAnswerResult() {
        return answerResult;
    }

    public void setAnswerResult(String answerResult) {
        this.answerResult = answerResult;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
