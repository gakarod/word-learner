package com.curieo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Abhi on 28-Feb-18.
 */

public class Post {

    @SerializedName("exercise_id")
    @Expose
    private Integer exercise_id;

    @SerializedName("completed")
    @Expose
    private boolean completed;
    @SerializedName("correct")
    @Expose
    private boolean[] correct;
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("id")
    @Expose
    private Integer id;

    public boolean getTitle() {
        return completed;
    }

    public void setTitle(boolean completed) {
        this.completed = completed;
    }

    public boolean[] getCorrect() {
        return correct;
    }

    public void setCorrect(boolean[] correct) {
        this.correct = correct;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExerciseId(Integer id){ return exercise_id;}

    @Override
    public String toString() {
        return "Post{" +
                "completed='" + completed + '\'' +
                ", correct='" + correct + '\'' +
                ", userId=" + userId +
                ", id=" + id +
                '}';
    }
}