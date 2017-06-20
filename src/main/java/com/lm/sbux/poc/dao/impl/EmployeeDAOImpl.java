/*
 *EmployeeDAOImpl.java
 *
 *
 * Copyright (c) 2016 Southwest Airlines, Co.
 * 2702 Love Field Drive, Dallas, TX 75235, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary
 * information of Southwest Airlines, Co.
 */
package com.lm.sbux.poc.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lm.sbux.poc.dao.EmployeeDAO;
import com.lm.sbux.poc.entity.Employee;
import com.lm.sbux.poc.util.MyCassandraTemplate;

/**
 * DAOImpl class for Employee to perform CRUD operation.
 * @author Laxman Pachimadla
 * @version 1.0
 */
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

   @Autowired
   private MyCassandraTemplate myCassandraTemplate;

   @Override
   public Employee createEmployee(Employee employee) {
      return myCassandraTemplate.create(employee);
   }

   @Override
   public Employee getEmployee(int id) {
      return myCassandraTemplate.findById(id, Employee.class);
   }

   @Override
   public Employee updateEmployee(Employee employee) {
      return myCassandraTemplate.update(employee, Employee.class);
   }

   @Override
   public void deleteEmployee(int id) {
      myCassandraTemplate.deleteById(id, Employee.class);
   }

   @Override
   public List<Employee> getAllEmployees() {
      return myCassandraTemplate.findAll(Employee.class);
   }
}
