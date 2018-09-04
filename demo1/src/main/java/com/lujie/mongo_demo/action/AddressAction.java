package com.lujie.mongo_demo.action;

import com.lujie.mongo_demo.pojo.Address;
import com.lujie.mongo_demo.pojo.User;
import com.lujie.mongo_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 卢杰
 * 收货地址管理控制器
 * 时间： 2018-8-20
 */
@Controller
@RequestMapping("addr")
public class AddressAction implements Serializable{
    @Autowired
    private UserService userService;
    @Autowired
    private HttpSession hs;
    /**
     * 保存收货地址
     * @param address
     * @return 返回的状态 0为未登录 1为添加成功
     */
    @RequestMapping("/save")
    public Integer SaveAddress(Address address){
        User user= (User) hs.getAttribute("user");
        if(user!=null){
            List list=user.getU_addr_list();
            //如果传过来的对象id为空则为添加操作，否则为修改操作
            if(address.getAdd_id()==null){
                //设置收货地址的创建时间
                address.setAdd_id(user.getU_addr_index().toString());
                //设置收货地址的编号
                address.setAdd_date(new Date());
                //设置收货地址的初始状态
                address.setAdd_status(1);
                //添加到地址集合
                list.add(address);

            }else{
                //修改操作
                for(int i=0;i<list.size();i++){
                    Address addr= (Address) list.get(i);
                    //如果id相等,则修改对应位置的地址
                    if(addr.getAdd_id()==address.getAdd_id()){
                        list.set(i,address);
                    }
                }
            }
            user.setU_addr_list(list);
            //修改数据库中的信息
            userService.userUpdate(user);
            //修改session中的信息
            hs.setAttribute("user",user);
            return 1;

        }
        return 0;
    }

    /**
     * 通过编号获得地址信息
     * @param id    需要获取地址的编号
     * @return  地址对象
     */
    @RequestMapping("/findById")
    public Address findByIdAddr(String id){
        User user= (User) hs.getAttribute("user");
        for(Object obj:user.getU_addr_list()){
            Address addr= (Address) obj;
            if(addr.getAdd_id().equals(id)){
                return addr;
            }
        }
        return null;
    }







}
