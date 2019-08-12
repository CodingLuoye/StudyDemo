package com.study.dao;

import com.study.model.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * @author YCKJ1409
 */
@Repository
public interface OrderDao {

    int insert(@Param("order") Order order);

}
