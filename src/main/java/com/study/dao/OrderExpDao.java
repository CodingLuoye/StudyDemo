package com.study.dao;


import com.study.model.OrderExp;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author YCKJ1409
 */
@Repository
public interface OrderExpDao {

    int deleteByPrimaryKey(Long id);

    int insert(OrderExp record);

    int insertSelective(OrderExp record);

    OrderExp selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderExp orderExp);

    int updateByPrimaryKey(OrderExp orderExp);

    int inserDelayOrder(@Param("order") OrderExp orderExp,@Param("expire_duration") Long expire_duration);

    int updateExpireOrder(Long id);

    int updateExpireOrders();

    List<OrderExp> selectUnPayOrders();
}
