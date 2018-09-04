package com.lujie.fmrmsystem.util;

import com.lujie.fmrmsystem.pojo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MenuDaoImpl extends TableUtil<Menu> {
    @Autowired
    public void set(JdbcTemplate jdbcTemplate){
        super.jdbcTemplate=jdbcTemplate;
    }

    public MenuDaoImpl() {
    }
}
