package ru.gostgroup.dao;

import ru.gostgroup.models.EmployeesModel;

import java.util.List;

public interface IEmployeesDAO {

    public List<EmployeesModel> index();
    public void update(long id, EmployeesModel updatedEmployee);
    public void delete(long id);
    public EmployeesModel show(long id);
    public void save(EmployeesModel employee);





}
