package org.izumi.dao;

import org.izumi.model.KmVoteAvg;

public interface KmVoteAvgMapper {
    int deleteByPrimaryKey(String fdId);

    int insert(KmVoteAvg record);

    int insertSelective(KmVoteAvg record);

    KmVoteAvg selectByPrimaryKey(String fdId);

    int updateByPrimaryKeySelective(KmVoteAvg record);

    int updateByPrimaryKey(KmVoteAvg record);
}