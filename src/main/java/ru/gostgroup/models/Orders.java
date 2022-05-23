package ru.gostgroup.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Orders {

    private int orderId;
    private String orderName;
    private int prodId;
    private String prodName;
    private int depId;
    private String depName;
    private int employeeId;
    private String employeeName;
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
    @Min(value = 1, message = "Минимальное кол-во, 1 шт.")
    private int countProd;

    public int getEmployeeId() {
        return employeeId;
    }

//    public Orders(int orderId, String orderName, int prodId, String prodName, int depId, String depName, int employeeId, String employeeName, LocalDateTime createDate, LocalDateTime deadLineDate, int countProd) {
//        this.orderId = orderId;
//        this.orderName = orderName;
//        this.prodId = prodId;
//        this.prodName = prodName;
//        this.depId = depId;
//        this.depName = depName;
//        this.employeeId = employeeId;
//        this.employeeName = employeeName;
//        this.createDate = createDate;
//        this.deadLineDate = deadLineDate;
//        this.countProd = countProd;
//    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Orders() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public int getProdId() {
        return prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getDeadLineDate() {
        return deadLineDate;
    }

    public void setDeadLineDate(LocalDateTime deadLineDate) {
        this.deadLineDate = deadLineDate;
    }

    public int getCountProd() {
        return countProd;
    }

    public void setCountProd(int countProd) {
        this.countProd = countProd;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", orderName='" + orderName + '\'' +
                ", prodId=" + prodId +
                ", prodName='" + prodName + '\'' +
                ", depId=" + depId +
                ", depName='" + depName + '\'' +
                ", employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", createDate=" + createDate +
                ", deadLineDate=" + deadLineDate +
                ", countProd=" + countProd +
                '}';
    }
}
