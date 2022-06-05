package ru.gostgroup.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;
import ru.gostgroup.pojo.AutoPersonAndDep;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;

@NamedNativeQuery(name = "AutoPersonAndDepDTO", query = "select e.dep_id as \"depId\", e.id as \"empId\" from production p\n" +
        "join employees e on p.dep_id = e.dep_id\n" +
        "where p.id = :paramId order by random() limit 1", resultSetMapping = "AutoPersonAndDepDTO")
@SqlResultSetMapping(name = "AutoPersonAndDepDTO",
        classes = @ConstructorResult(targetClass = AutoPersonAndDep.class,
                columns = {@ColumnResult(name = "empId"), @ColumnResult(name = "depId")})
)
@Entity
@Table(name = "orders", schema = "public", catalog = "APPGOST")
public class OrdersModel {
    public static final String ORDER_NAME = "Заказ№";
    private long orderId;
    private long prodId;
    private Long depId;
    @Min(value = 1, message = "Минимальное кол-во, 1 шт.")
    private int countProd;
    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy'T'HH:mm")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createDate;
    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy'T'HH:mm")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @NotNull(message = "Дата не должна быть пустой")
    private LocalDateTime deadLineDate;
    private Long empId;


    private DepartamentModel depart;
    private ProductionModel product;
    private EmployeesModel employee;

    @OneToOne()
    @JoinColumn(name = "dep_id", referencedColumnName = "id", insertable = false, updatable = false)
    public DepartamentModel getDepart() {
        return depart;
    }

    public void setDepart(DepartamentModel depart) {
        this.depart = depart;
    }

    @OneToOne()
    @JoinColumn(name = "prod_id", referencedColumnName = "id", insertable = false, updatable = false)
    public ProductionModel getProduct() {
        return product;
    }

    public void setProduct(ProductionModel product) {
        this.product = product;
    }

    @OneToOne()
    @JoinColumn(name = "emp_id", referencedColumnName = "id", insertable = false, updatable = false)
    public EmployeesModel getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeesModel employee) {
        this.employee = employee;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ord_id_seq")
    @SequenceGenerator(name = "ord_id_seq", sequenceName = "ord_id_seq", allocationSize = 1)
    @Column(name = "orderid", nullable = false)
    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderid) {
        this.orderId = orderid;
    }

    @Basic
    @Column(name = "prod_id", nullable = false)
    public long getProdId() {
        return prodId;
    }

    public void setProdId(long prodId) {
        this.prodId = prodId;
    }

    @Basic
    @Column(name = "dep_id", nullable = true)
    public Long getDepId() {
        return depId;
    }

    public void setDepId(Long depId) {
        this.depId = depId;
    }

    @Basic
    @Column(name = "countprod", nullable = false)
    public int getCountProd() {
        return countProd;
    }

    public void setCountProd(int countProd) {
        this.countProd = countProd;
    }

    @Basic
    @Column(name = "create_date", nullable = true)
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "deadline_date", nullable = true)
    public LocalDateTime getDeadLineDate() {
        return deadLineDate;
    }

    public void setDeadLineDate(LocalDateTime deadLineDate) {
        this.deadLineDate = deadLineDate;
    }

    @Basic
    @Column(name = "emp_id", nullable = true)
    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrdersModel that = (OrdersModel) o;

        if (orderId != that.orderId) return false;
        if (prodId != that.prodId) return false;
        if (countProd != that.countProd) return false;
        if (depId != null ? !depId.equals(that.depId) : that.depId != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (deadLineDate != null ? !deadLineDate.equals(that.deadLineDate) : that.deadLineDate != null) return false;
        if (empId != null ? !empId.equals(that.empId) : that.empId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (int) (prodId ^ (prodId >>> 32));
        result = 31 * result + (depId != null ? depId.hashCode() : 0);
        result = 31 * result + countProd;
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (deadLineDate != null ? deadLineDate.hashCode() : 0);
        result = 31 * result + (empId != null ? empId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrdersModel{" +
                "orderId=" + orderId +
                ", prodId=" + prodId +
                ", depId=" + depId +
                ", countProd=" + countProd +
                ", createDate=" + createDate +
                ", deadLineDate=" + deadLineDate +
                ", empId=" + empId +
                ", depart=" + depart +
                ", product=" + product +
                ", employee=" + employee +
                '}';
    }
}
