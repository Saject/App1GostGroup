//package ru.gostgroup.contollers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import ru.gostgroup.dao.SupplyTypeDAO;
//import ru.gostgroup.models.Orders;
//import ru.gostgroup.pojo.DBSupplyTypeJSON;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/supplytype")
//public class SupplyContoller {
//
//    private SupplyTypeDAO supplyTypeDAO;
//
//    @Autowired
//    public SupplyContoller(SupplyTypeDAO supplyTypeDAO) {
//        this.supplyTypeDAO = supplyTypeDAO;
//    }
//
//    @GetMapping(produces = "application/json", headers="Accept=application/json")
//    public DBSupplyTypeJSON index() {
//        System.out.println(supplyTypeDAO.index());
//        DBSupplyTypeJSON dbSupplyTypeJSON = new DBSupplyTypeJSON();
//        dbSupplyTypeJSON.setSupplyTypeList(supplyTypeDAO.index());
//        return dbSupplyTypeJSON;
//    }
//}
