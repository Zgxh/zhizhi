package com.zhizhi.controller;

import com.zhizhi.model.Answer;
import com.zhizhi.wrapper.ResponseObject;
import com.zhizhi.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 有关Answer回答的控制器类
 *
 * @author Yu Yang
 * @create 2020-06-14 13:24
 */
@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    /**
     * 新建回答
     * @param answer 回答体
     * @return ResponseObject消息
     */
    @PostMapping("/new")
    public ResponseObject insertNewAnswer(Answer answer) {
        int result = answerService.insertAnswer(answer);
        if (result == 0) {
            return new ResponseObject("failure", "回答上传失败！");
        } else {
            return new ResponseObject("success", "回答上传成功！");
        }
    }

    /**
     * 更新回答
     * @param answer 新的回答体
     * @return ResponseObject消息
     */
    @PutMapping("/update")
    public ResponseObject updateAnswer(Answer answer) {
        int result = answerService.updateAnswer(answer);
        if (result == 0) {
            return new ResponseObject("failure", "回答更新失败！");
        } else {
            return new ResponseObject("success", "回答更新成功！");
        }
    }

    /**
     * 根据answer id删除该条回答
     * @param id answer的主键id
     * @return ResponseObject消息
     */
    @DeleteMapping("/delete")
    public ResponseObject deleteAnswer(Integer id) {
        int result = answerService.deleteAnswerById(id);
        if (result == 0) {
            return new ResponseObject("failure", "回答删除失败！");
        } else {
            return new ResponseObject("success", "回答删除成功！");
        }
    }

    /**
     * 查询某用户的所有回答记录
     * @param username 用户名
     * @return ResponseObject消息
     */
    @GetMapping("/byuser")
    public List<Answer> selectAnswerByUsername(String username) {
        List<Answer> answers = answerService.selectAnswerByUsername(username);
        return answers;
    }

    /**
     * 查询某个问题的所有回答
     * @param qid 问题的id
     * @return ResponseObject消息
     */
    @GetMapping("/byquestion")
    public List<Answer> selectAnswerByQid(Integer qid) {
        List<Answer> answers = answerService.selectAnswerByQid(qid);
        return answers;
    }
}
