package com.zhizhi.mapper;

import com.zhizhi.model.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper {

    /**
     * 提交新的提问
     * @param record 问题类 Question
     * @return 涉及记录的行数
     */
    int insertQuestion(Question record);

    /**
     * 根据提问的id删除对应问题
     * @param id 问题主键id
     * @return 涉及记录的行数
     */
    int deleteQuestionById(Integer id);

    // /**
    //  * 根据用户id查询该用户的所有提问
    //  * @param uid
    //  * @return 该用户所有提问对应的list
    //  */
    // List<Question> selectQuestionByUid(int uid);

    /**
     * 根据用户名查询该用户的所有提问
     * @param username
     * @return 该用户所有提问对应的list
     */
    List<Question> selectQuestionByUsername(String username);
}