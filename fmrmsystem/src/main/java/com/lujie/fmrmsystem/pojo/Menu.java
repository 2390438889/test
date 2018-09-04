package com.lujie.fmrmsystem.pojo;

import com.lujie.fmrmsystem.annotation.Column;
import com.lujie.fmrmsystem.annotation.Id;
import com.lujie.fmrmsystem.annotation.Table;

import java.io.Serializable;
@Table("t_menu")
public class Menu implements Serializable {
    @Id
    @Column(name = "cm_id",type = "int" ,other = "auto_increment primary key",comment = "菜单编号")
    Integer cmId;
    @Column(name = "cm_name",type = "varchar(20)" ,comment = "菜单名称")
    String cmName;
    @Column(name = "cm_desc",type = "varchar(100)" ,comment = "菜单简介")
    String cmDesc;
    @Column(name = "cm_path",type = "varchar(20)" ,comment = "菜单路径")
    String cmPath;
    @Column(name = "cm_logo",type = "varchar(15)" ,comment = "菜单图标")
    String cmLogo;
    @Column(name = "cm_parent",type = "int" ,comment = "菜单编号")
    Integer cmParent;

    public Menu(String cmName, String cmDesc, String cmPath, String cmLogo, Integer cmParent) {
        this.cmName = cmName;
        this.cmDesc = cmDesc;
        this.cmPath = cmPath;
        this.cmLogo = cmLogo;
        this.cmParent = cmParent;
    }

    public Integer getCmId() {
        return cmId;
    }

    public void setCmId(Integer cmId) {
        this.cmId = cmId;
    }

    public String getCmName() {
        return cmName;
    }

    public void setCmName(String cmName) {
        this.cmName = cmName;
    }

    public String getCmDesc() {
        return cmDesc;
    }

    public void setCmDesc(String cmDesc) {
        this.cmDesc = cmDesc;
    }

    public String getCmPath() {
        return cmPath;
    }

    public void setCmPath(String cmPath) {
        this.cmPath = cmPath;
    }

    public String getCmLogo() {
        return cmLogo;
    }

    public void setCmLogo(String cmLogo) {
        this.cmLogo = cmLogo;
    }

    public Integer getCmParent() {
        return cmParent;
    }

    public void setCmParent(Integer cmParent) {
        this.cmParent = cmParent;
    }
}
