package wr.com.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import wr.com.pojo.EnterTicketResource;
@Component
public interface EnterTicketResourceMapper {
    int insertSelective(EnterTicketResource record);
    
    List<EnterTicketResource> selectAll();
}