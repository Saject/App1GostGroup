package ru.gostgroup.pojo;

import ru.gostgroup.models.EmployeesModel;


import java.util.List;

public class DBEmployeesJSON {

    private List<EmployeesModel> employeesModelsList;

    public List<EmployeesModel> getEmployeesList() {
        return employeesModelsList;
    }

    public void setLogList(List<EmployeesModel> employeesModelsList) {
        this.employeesModelsList = employeesModelsList;
    }

}
