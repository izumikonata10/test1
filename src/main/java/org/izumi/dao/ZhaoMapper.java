package org.izumi.dao;

import org.izumi.model.Zhao;

public interface ZhaoMapper {
    int insert(Zhao record);

    int insertSelective(Zhao record);
}