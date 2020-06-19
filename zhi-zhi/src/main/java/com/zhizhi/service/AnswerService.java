package com.zhizhi.service;

import com.zhizhi.mapper.AnswerMapper;
import com.zhizhi.model.Answer;
import com.zhizhi.model.Question;
import com.zhizhi.util.UserAuthenticationUtil;
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
    QuestionService questionService;

    @Autowired
    UserService userService;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 新建回答
     * @param answer 回答Answer对象
     * @return 0表示新建回答失败；1表示成功
     */
    public int insertAnswer(Answer answer) {
        // 如果当前answer对应的username与当前认证用户不一致，则不允许新建回答
        String authenticatedUsername = UserAuthenticationUtil.getUserAuthentication().getUsername();
        String usernameForCurAnswer = answer.getUsername();
        if (usernameForCurAnswer == null || !usernameForCurAnswer.equals(authenticatedUsername)) {
            return 0;
        }
        // 如果qid未空或者qid对应的提问不存在，则创建失败
        Integer qid = answer.getQid();
        if (qid == null || questionService.selectQuestionById(qid) == null) {
            return 0;
        }
        // 设置Answer其他属性
        int uid = userService.selectIdByUsername(usernameForCurAnswer);
        answer.setUid(uid);
        answer.setPublishTime(dateFormat.format(new Date()));
        int result = answerMapper.insertAnswer(answer);
        if (result <= 0) { // 如果数据库没有变动，则新建回答失败
            return 0;
        } else { // 新建回答成功
            return 1;
        }
    }

    /**
     * 更新回答
     * @param answer 新的回答体
     * @return 1表示成功更新回答；0表示回答未更新
     */
    public int updateAnswer(Answer answer) {
        // 如果当前answer对应的username与当前认证用户不一致，则不允许新建回答
        String authenticatedUsername = UserAuthenticationUtil.getUserAuthentication().getUsername();
        String usernameForCurAnswer = answer.getUsername();
        if (usernameForCurAnswer == null || !usernameForCurAnswer.equals(authenticatedUsername)) {
            return 0;
        }
        answer.setPublishTime(dateFormat.format(new Date()));
        int result = answerMapper.updateAnswer(answer);
        if (result > 0) {
            return 1;
        } else { // 未更新
            return 0;
        }
    }

    /**
     * 根据回答id删除对应answer
     * @param id answerId
     * @return 1表示成功删除；0表示删除失败
     */
    public int deleteAnswerById(Integer id) {
        if (id == null || id <= 0) { // 如果回答id不合要求，不允许删除回答
            return 0;
        }
        // 如果回答id所属用户不是当前认证的用户，则不允许删除
        String usernameForCurId = answerMapper.selectUsernameById(id);
        if (!UserAuthenticationUtil.getUserAuthentication().getUsername().equals(usernameForCurId)) {
            return 0;
        }
        // 从数据库删除该回答记录
        int result = answerMapper.deleteAnswerById(id);
        if (result <= 0) { // 删除失败
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * 根据回答的主键id查回答详情
     * @param id 主键
     * @return 回答详情 Answer
     */
    public Answer selectAnswerById(Integer id) {
        return answerMapper.selectAnswerById(id);
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
