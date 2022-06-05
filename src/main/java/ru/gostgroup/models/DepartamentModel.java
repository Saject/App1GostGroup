package ru.gostgroup.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity(name = "DepartamentModel")
@Table(name = "departament", schema = "public", catalog = "APPGOST")
public class DepartamentModel {

    private long id;
    private String name;
    private Set<EmployeesModel> emp;


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "dep_id", referencedColumnName = "id", insertable = false, updatable = false)
    public Set<EmployeesModel> getEmp() {
        return emp;
    }

    public void setEmp(Set<EmployeesModel> emp) {
        this.emp = emp;
    }



    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DepartamentModel that = (DepartamentModel) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
