package com.idata.hhmtwh.mapper;

import com.idata.hhmtwh.model.t_twh_code_copy1;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author xht
* @description 针对表【t_twh_code_copy1】的数据库操作Mapper
* @createDate 2023-08-01 17:33:11
* @Entity com.idata.hhmtwh.model.t_twh_code_copy1
*/
@Mapper
public interface t_twh_code_copy1Mapper extends BaseMapper<t_twh_code_copy1> {
    void updateOrInsertClientInfo(List<t_twh_code_copy1> tTwhCodeCopy1List);
}




