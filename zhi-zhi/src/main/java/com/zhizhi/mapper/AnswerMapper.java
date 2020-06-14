package com.zhizhi.mapper;

import com.zhizhi.model.Answer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnswerMapper {

    /**
     * 新建回答
     * @param record
     * @return 本次插入受影响的行数
     */
    int insertAnswer(Answer record);

    /**
     * 更新回答
     * @param record
     * @return 本次更新受影响的行数
     */
    int updateAnswer(Answer record);

    /**
     * 根据answer id删除回答记录
     * @param id 回答的id
     * @return 受影响的行数
     */
    int deleteAnswerById(Integer id);

    /**
     * 根据用户名查询用户的所有回答
     * @param username
     * @return 该用户所有回答组成的 list
     */
    List<Answer> selectAnswerByUsername(String username);

    /**
     * 获取某个提问的所有回答
     * @param qid
     * @return 该提问对应的回答组成的list
     */
    List<Answer> selectAnswerByQid(Integer qid);
}