package wr.com.mapper;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import wr.com.pojo.TicketGoods;

@Repository
public interface TicketGoodsMapper {
    int deleteByPrimaryKey(String gid);

    int insertSelective(TicketGoods record);

    TicketGoods selectByPrimaryKey(String gid);
    
    List<TicketGoods> findAll();

    int updateByPrimaryKeySelective(TicketGoods record);

    int updateByPrimaryKey(TicketGoods record);
}