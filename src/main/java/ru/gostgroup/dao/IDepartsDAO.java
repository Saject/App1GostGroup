package ru.gostgroup.dao;

import ru.gostgroup.models.DepartamentModel;
import ru.gostgroup.models.EmployeesModel;

import java.util.List;

public interface IDepartsDAO {

    public List<DepartamentModel> index();
    public DepartamentModel show(long id);
    public void update(long id, DepartamentModel updatedDepartament);
    public void save(DepartamentModel depart);
    public void delete(long id);

}
