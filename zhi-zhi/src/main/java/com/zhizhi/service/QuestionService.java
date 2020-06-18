package com.zhizhi.service;

import com.zhizhi.mapper.QuestionMapper;
import com.zhizhi.util.UserAuthenticationUtil;
import com.zhizhi.wrapper.Page;
import com.zhizhi.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
     * 新建提问
     * @param question 问题信息
     * @return 0表示新建失败；1表示新建成功
     */
    public int insertQuestion(@RequestBody Question question) {
        // 若没传username或者username与当前认证的账户不一致，则不允许新建提问
        String authenticatedUsername = UserAuthenticationUtil.getUserAuthentication().getUsername();
        String usernameForCurQuestion = question.getUsername();
        if (usernameForCurQuestion == null || !usernameForCurQuestion.equals(authenticatedUsername)) {
            return 0;
        }
        // 设置Question的其他属性
        int uid = userService.selectIdByUsername(usernameForCurQuestion);
        question.setUid(uid);
        question.setPublishTime(dateFormat.format(new Date()));
        // 在数据库中新建提问记录
        int result = questionMapper.insertQuestion(question);
        if (result > 0) { // 数据库中记录增加，即提问创建成功
            return 1;
        } else { // 数据库没有变化，即记录创建失败
            return 0;
        }
    }

    /**
     * 根据问题主键删除对应问题记录
     * @param id 问题主键
     * @return 1表示删除成功；0表示删除失败
     */
    public int deleteQuestionById(Integer id) {
        if (id == null || id <= 0) { // 没传问题id或id不符合要求，则不允许删除
            return 0;
        }
        // 如果问题id对应的用户不是当前认证的用户，则不允许删除该提问
        String usernameForCurId = questionMapper.selectUsernameById(id);
        if (!UserAuthenticationUtil.getUserAuthentication().getUsername().equals(usernameForCurId)) {
            return 0;
        }
        // 从数据库删除该提问记录
        int result = questionMapper.deleteQuestionById(id);
        if (result <= 0) { // 数据库中没有记录变动，则删除失败
            return 0;
        } else { // 成功删除该提问
            return 1;
        }
    }

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
     * 查询数据库中的所有问题
     * @return 问题list
     */
    public List<Question> selectAllQuestions() {
        List<Question> questions = questionMapper.selectAllQuestions();
        return questions;
    }

    /**
     * 根据页码查当前页面的所有提问
     * @param pageNum 页码index: 1,2,3,4,...,maxPageNum
     * @return 当前页面的所有提问的Page包装类
     */
    public Page<Question> selectQuestionByPage(Integer pageNum) {
        if (pageNum == null || pageNum <= 0) { // 防止空指针或左侧越界
            pageNum = 1;
        }
        // 查询与设置页码相关属性
        Page<Question> page = new Page<>();
        page.setCurrentPageIndex(pageNum);
        int totalRecordNum = questionMapper.selectCount();
        page.setTotalRecordNum(totalRecordNum);
        int pageSize = page.getPageSize();
        int totalPageNum =  totalRecordNum / pageSize;
        totalPageNum = totalRecordNum % pageSize > 0 ? totalPageNum + 1: totalPageNum; // 未满一页的按一页计算
        page.setTotalPageNum(totalPageNum);
        if (totalPageNum < pageNum) { // 如果请求的页码超过了数据库页码总数，则直接返回空问题列表
            return page;
        }
        // 查询与设置提问记录
        int startIndex = (pageNum - 1) * pageSize;
        List<Question> currentPage = questionMapper.selectQuestionByPage(startIndex, pageSize);
        System.out.println(currentPage);
        page.setCurrentPage(currentPage);

        return page;
    }
}