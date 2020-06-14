package com.zhizhi.controller;

import com.zhizhi.model.Question;
import com.zhizhi.model.ResponseObject;
import com.zhizhi.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 有关提问请求的控制器
 *
 * @author Yu Yang
 * @create 2020-06-14 13:24
 */
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    /**
     * 新建提问
     * @param question
     * @return json对象ResponseObject
     */
    @PostMapping("/new")
    public ResponseObject insertNewQuestion(Question question) {
        int result = questionService.insertQuestion(question);
        if (result == 0) {
            return new ResponseObject("failure", "提问创建失败！");
        } else {
            return new ResponseObject("success", "提问创建成功！");
        }
    }

    /**
     * 根据主键删除问题
     * @param id 问题主键
     * @return json对象ResponseObject
     */
    @DeleteMapping("/delete")
    public ResponseObject deleteQuestion(Integer id) {
        int result = questionService.deleteQuestionById(id);
        if (result == 0) {
            return new ResponseObject("failure", "问题删除失败！");
        } else {
            return new ResponseObject("success", "问题删除成功！");
        }
    }

    /**
     * 根据用户名查询该用户的所有提问
     * @param username 用户名
     * @return 该用户的所有提问组成的list
     */
    @GetMapping("/getall")
    public List<Question> getQuestionsList(String username) {
        List<Question> questions = questionService.selectQuestionByUsername(username);
        return questions;
    }
}
