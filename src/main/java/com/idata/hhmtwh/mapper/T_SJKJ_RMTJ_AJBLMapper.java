package com.idata.hhmtwh.mapper;

import com.idata.hhmtwh.model.T_SJKJ_RMTJ_AJBL;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
* @author xht
* @description 针对表【T_SJKJ_RMTJ_AJBL】的数据库操作Mapper
* @createDate 2023-07-31 09:40:57
* @Entity com.idata.hhmtwh.model.T_SJKJ_RMTJ_AJBL
*/

@Mapper
public interface T_SJKJ_RMTJ_AJBLMapper extends BaseMapper<T_SJKJ_RMTJ_AJBL> {
    @Override
    List<T_SJKJ_RMTJ_AJBL> selectBatchIds(Collection<? extends Serializable> idList);

        // 查询所有
//        List<T_SJKJ_RMTJ_AJBL> selectList(@Param("ew") Wrapper<User> wrapper);

}




