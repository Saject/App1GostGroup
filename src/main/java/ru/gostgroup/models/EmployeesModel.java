package ru.gostgroup.models;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;


@NamedNativeQuery(name = "updatedOrder", query = "UPDATE orders \n" +
        "SET emp_id=(select id from employees\n" +
        "where dep_id in (select dep_id from employees where id = :paramEmpId)\n" +
        "order by random() limit 1)\n" +
        "where emp_id = :paramEmpId", resultSetMapping = "updateResult")
@SqlResultSetMapping(name="updateResult", columns = { @ColumnResult(name = "count")})
@Entity(name = "EmployeesModel")
@Table(name = "employees", schema = "public", catalog = "APPGOST")
public class EmployeesModel {
    private long id;
    private String fio;
    private long depId;
    private DepartamentModel departs;

    public EmployeesModel() {
    }

    public EmployeesModel(long id, String fio, long depId) {
        this.id = id;
        this.fio = fio;
        this.depId = depId;
    }


    @OneToOne()
    @JoinColumn(name = "dep_id", referencedColumnName = "id", insertable = false, updatable = false)
    public DepartamentModel getDeparts() {
        return departs;
    }


    public void setDeparts(DepartamentModel departs) {
        this.departs = departs;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_id_seq")
    @SequenceGenerator(name = "emp_id_seq", sequenceName = "emp_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "fio", nullable = true, length = -1)
    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    @Basic
    @Column(name = "dep_id", nullable = false)
    public long getDepId() {
        return depId;
    }

    public void setDepId(long depId) {
        this.depId = depId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeesModel that = (EmployeesModel) o;

        if (id != that.id) return false;
        if (depId != that.depId) return false;
        if (fio != null ? !fio.equals(that.fio) : that.fio != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (fio != null ? fio.hashCode() : 0);
        result = 31 * result + (int) (depId ^ (depId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "EmployeesModel{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", depId=" + depId +
                ", departs=" + departs +
                '}';
    }
}
