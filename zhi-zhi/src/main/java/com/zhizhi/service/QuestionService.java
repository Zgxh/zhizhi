package com.zhizhi.service;

import com.zhizhi.mapper.QuestionMapper;
import com.zhizhi.model.Question;
import com.zhizhi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 问题服务类
 *
 * @author Yu Yang
 * @create 2020-06-14 13:25
 */
@Service
@Transactional
public class QuestionService {

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    UserService userService;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 根据用户名查该用户的所有提问
     * @param username 用户名
     * @return 问题list
     */
    public List<Question> selectQuestionByUsername(String username) {
        List<Question> questions = questionMapper.selectQuestionByUsername(username);
        return questions;
    }

    /**
     * 新建提问
     * @param question
     * @return 0表示新建失败；1表示新建成功
     */
    public int insertQuestion(Question question) {
        int uid = userService.selectIdByUsername(question.getUsername());
        if (uid <= 0) { // 不存在该用户
            return 0;
        }
        question.setUid(uid);
        question.setPublishTime(dateFormat.format(new Date()));
        int result = questionMapper.insertQuestion(question);
        if (result > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 根绝问题主键删除对应问题记录
     * @param id 问题的主键
     * @return 1表示删除成功；0表示删除失败
     */
    public int deleteQuestionById(Integer id) {
        int result = questionMapper.deleteQuestionById(id);
        if (result <= 0) { // 删除失败
            return 0;
        } else {
            return 1;
        }
    }
}