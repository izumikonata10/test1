package org.izumi.dao;

import org.izumi.model.KmVotePwd;

public interface KmVotePwdMapper {
    int deleteByPrimaryKey(String pwd);

    int insert(KmVotePwd record);

    KmVotePwd selectByPrimaryKey(String pwd);

    int insertSelective(KmVotePwd record);
}