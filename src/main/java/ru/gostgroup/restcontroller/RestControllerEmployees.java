package ru.gostgroup.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gostgroup.dao.IEmployeesDAO;
import ru.gostgroup.models.EmployeesModel;
import ru.gostgroup.pojo.DBEmployeesJSON;

import java.util.List;

@RestController
@RequestMapping("/test")
public class RestControllerEmployees {

    private final IEmployeesDAO employeesDAO;

    @Autowired
    public RestControllerEmployees(IEmployeesDAO employeesDAO) {
        this.employeesDAO = employeesDAO;

    }

    @GetMapping(produces = "application/json", headers="Accept=application/json")
    public DBEmployeesJSON index() {
        DBEmployeesJSON dbEmployeesJSON = new DBEmployeesJSON();
        dbEmployeesJSON.setLogList(employeesDAO.index());
        return dbEmployeesJSON;
    }

}
