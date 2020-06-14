package com.zhizhi.service;

import com.zhizhi.mapper.AnswerMapper;
import com.zhizhi.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 回答的业务处理类
 *
 * @author Yu Yang
 * @create 2020-06-14 18:18
 */
@Service
@Transactional
public class AnswerService {

    @Autowired
    AnswerMapper answerMapper;

    @Autowired
    UserService userService;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 新建回答
     * @param answer 回答Answer对象
     * @return 0表示新建回答失败；1表示成功
     */
    public int insertAnswer(Answer answer) {
        int uid = userService.selectIdByUsername(answer.getUsername());
        if (uid <= 0) { // 用户不存在
            return 0;
        }
        answer.setUid(uid);
        answer.setPublishTime(dateFormat.format(new Date()));
        int result = answerMapper.insertAnswer(answer);
        if (result <= 0) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * 更新回答
     * @param answer 新的回答体
     * @return 1表示成功更新回答；0表示回答未更新
     */
    public int updateAnswer(Answer answer) {
        answer.setPublishTime(dateFormat.format(new Date()));
        int result = answerMapper.updateAnswer(answer);
        if (result > 0) {
            return 1;
        } else { // 未更新
            return 0;
        }
    }

    /**
     * 根据id删除对应answer
     * @param id answerId
     * @return 1表示成功删除；0表示删除失败
     */
    public int deleteAnswerById(Integer id) {
        int result = answerMapper.deleteAnswerById(id);
        System.out.println("===== result = " + result);
        if (result <= 0) { // 删除失败
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * 获取某个用户的所有回答
     * @param username 用户名
     * @return 回答的List
     */
    public List<Answer> selectAnswerByUsername(String username) {
        List<Answer> answers = answerMapper.selectAnswerByUsername(username);
        return answers;
    }

    /**
     * 获取某个提问的所有回答
     * @param qid 提问的id
     * @return list对象
     */
    public List<Answer> selectAnswerByQid(Integer qid) {
        List<Answer> answers = answerMapper.selectAnswerByQid(qid);
        return answers;
    }
}
