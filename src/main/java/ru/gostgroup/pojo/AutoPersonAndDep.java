package ru.gostgroup.pojo;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import javax.persistence.*;



public class AutoPersonAndDep {

        private Long empId;
        private Long depId;


    public AutoPersonAndDep(Number empId, Number depId) {
        this.empId = empId.longValue();
        this.depId = depId.longValue();
    }

    public AutoPersonAndDep() {

    }

        public Long getEmpId() {
            return empId;
        }

//        public void setEmpId(Number empId) {
//            this.empId = empId.longValue();
//        }

        public Long getDepId() {
            return depId;
        }

//        public void setDepId(Number depId) {
//            this.depId = depId.longValue();
//        }
    }
