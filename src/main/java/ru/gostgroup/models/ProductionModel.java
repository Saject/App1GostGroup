package ru.gostgroup.models;

import javax.persistence.*;

@Entity
@Table(name = "production", schema = "public", catalog = "APPGOST")
public class ProductionModel {
    private long id;
    private String nameProd;
    private long depId;



    private DepartamentModel departs;

    @OneToOne
    @JoinColumn(name = "dep_id", referencedColumnName = "id", insertable = false, updatable = false)
    public DepartamentModel getDeparts() {
        return departs;
    }

    public void setDeparts(DepartamentModel departs) {
        this.departs = departs;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nameprod", nullable = true, length = 60)
    public String getNameProd() {
        return nameProd;
    }

    public void setNameProd(String nameProd) {
        this.nameProd = nameProd;
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

        ProductionModel that = (ProductionModel) o;

        if (id != that.id) return false;
        if (depId != that.depId) return false;
        if (nameProd != null ? !nameProd.equals(that.nameProd) : that.nameProd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (nameProd != null ? nameProd.hashCode() : 0);
        result = 31 * result + (int) (depId ^ (depId >>> 32));
        return result;
    }
}
