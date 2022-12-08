package test2;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.List;

public class Specification_test {

    @Autowired
    Customer_spectification student_spectification;

    @Test
    public   void  text1(){
        List<Customer> students=student_spectification.findAll(new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
               Path<Object> custId=root.get("custId");
                Path<Object> custName=root.get("custName");
                Path<Object> custAddress=root.get("custAddress");
                Predicate predicate = criteriaBuilder.equal(custAddress, "beijing");
                return predicate;
            }
        });
    }
}


