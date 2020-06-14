package com.zhizhi.controller;

import com.zhizhi.model.Question;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yu Yang
 * @create 2020-06-14 16:16
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/list")
    public List<Question> doGet() {
        List<Question> result = new ArrayList<>();
        Question question1 = new Question();
        question1.setTitle("renyan");
        Question question2 = new Question();
        question2.setTitle("yuyang");
        result.add(question1);
        result.add(question2);
        return result;
    }
}
