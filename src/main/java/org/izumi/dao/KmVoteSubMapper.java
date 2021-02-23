package org.izumi.dao;

import org.izumi.model.KmVoteSub;

import java.util.List;

public interface KmVoteSubMapper {
    int deleteByPrimaryKey(Integer fdId);

    int insert(KmVoteSub record);

    int insertSelective(KmVoteSub record);

    KmVoteSub selectByPrimaryKey(Integer fdId);

    List<KmVoteSub> selectByQId(String fdQuestionId);

    int updateByPrimaryKeySelective(KmVoteSub record);

    int updateByPrimaryKey(KmVoteSub record);
}