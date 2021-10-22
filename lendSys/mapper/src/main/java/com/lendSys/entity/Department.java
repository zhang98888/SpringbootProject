package com.lendSys.entity;

import javax.persistence.*;

public class Department {
    @Id
    @Column(name = "departmentId")
    private Integer departmentid;

    @Column(name = "department_name")
    private String departmentName;

    /**
     * @return departmentId
     */
    public Integer getDepartmentid() {
        return departmentid;
    }

    /**
     * @param departmentid
     */
    public void setDepartmentid(Integer departmentid) {
        this.departmentid = departmentid;
    }

    /**
     * @return department_name
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * @param departmentName
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}