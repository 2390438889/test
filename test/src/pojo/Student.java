package pojo;

import java.io.Serializable;

@Table("student")
public class Student implements Serializable{
    @Column(name="s_no",type="varchar(12)")
    private String sNO;     //学号
    @Column(name="s_name",type="varchar(20)")
    private String sName;   //姓名
    @Column(name="s_sex",type="varchar(1)")
    private String sSex;    //性别
    @Column(name="s_info",type="varchar(500)")
    private String sInfo;    //自我简介

    public String getsNO() {
        return sNO;
    }

    public void setsNO(String sNO) {
        this.sNO = sNO;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsSex() {
        return sSex;
    }

    public void setsSex(String sSex) {
        this.sSex = sSex;
    }

    public String getsInfo() {
        return sInfo;
    }

    public void setsInfo(String sInfo) {
        this.sInfo = sInfo;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sNO='" + sNO + '\'' +
                ", sName='" + sName + '\'' +
                ", sSex='" + sSex + '\'' +
                ", sInfo='" + sInfo + '\'' +
                '}';
    }
}
