package com.study.dao;
import com.study.model.OrderCount;
import org.springframework.stereotype.Repository;

/**
 * @author YCKJ1409
 */
@Repository
public interface OrderCountDao {

    int update(OrderCount order);

    Integer insert(OrderCount orderCount);
}
