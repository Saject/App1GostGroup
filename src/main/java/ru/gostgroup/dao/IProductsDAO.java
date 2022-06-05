package ru.gostgroup.dao;

import ru.gostgroup.models.DepartamentModel;
import ru.gostgroup.models.ProductionModel;


import java.util.List;

public interface IProductsDAO {

    public List<ProductionModel> index();
    public ProductionModel show(long id);
    public void update(long id, ProductionModel updatedProd);
    public void save(ProductionModel prod);
    public void delete(long id);

}
