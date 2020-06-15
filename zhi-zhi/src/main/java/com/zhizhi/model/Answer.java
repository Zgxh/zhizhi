package com.zhizhi.model;

/**
 * Answer Bean
 */
public class Answer {

    private Integer id; // 回答的id

    private String publishTime; // 回答发布的时间

    private Integer uid; // 回答所属的用户的id

    private String username; // 回答所属的用户的用户名

    private Integer qid; // 回答对应的问题的id

    private String content; // 回答的内容

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}