package com.sls.security.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sls.security.entity.Indent;

public interface IndentLineItemRepository extends CrudRepository<Indent, Long> {

   List<Indent> findByIndentHeaderId(String indentHeaderId);
   Indent findByIndentHeaderIdAndItemId(String indentNo, String itemCode);
   Indent findByIndentHeaderIdAndItemIdAndQualityCode(String indentNo, String itemCode, String quality);
//
//    @Query("select c from Customer c where c.email = :email")
//    Stream<Customer> findByEmailReturnStream(@Param("email") String email);
//
//    List<Customer> findByDate(Date date);

    //@Query("select c from Customer c")
    //Stream<Customer> findAllAndStream();

    //List<Customer> findByDateBetween(Date from, Date to);

}
