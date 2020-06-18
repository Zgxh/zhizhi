package com.zhizhi.controller;

import com.zhizhi.model.Question;
import com.zhizhi.wrapper.Page;
import com.zhizhi.wrapper.ResponseObject;
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
    public ResponseObject insertNewQuestion(@RequestBody Question question) {
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
    @GetMapping("/get/user")
    public List<Question> getQuestionsList(String username) {
        List<Question> questions = questionService.selectQuestionByUsername(username);
        return questions;
    }

    /**
     * 获取数据库中所有问题
     * @return 问题list
     */
    @GetMapping("/get/all")
    public List<Question> getAllQuestions() {
        List<Question> questions = questionService.selectAllQuestions();
        return questions;
    }

    /**
     * 根据页码获取当前页的问题
     * @param pageNum 页码
     * @return 对应页码的包装类Page
     */
    @GetMapping("/get/page")
    public Page<Question> getQuestionPage(Integer pageNum) {
        Page<Question> page = questionService.selectQuestionByPage(pageNum);
        return page;
    }
}
