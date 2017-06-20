/*
 *EmployeeDAO.java
 *
 *
 * Copyright (c) 2016 Southwest Airlines, Co.
 * 2702 Love Field Drive, Dallas, TX 75235, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary
 * information of Southwest Airlines, Co.
 */
package com.lm.sbux.poc.dao;

import java.util.List;

import com.lm.sbux.poc.entity.Employee;


/**
 * DAO interface for Employee to perform CRUD operation.
 * @author Laxman Pachimadla
 * @version 1.0
 */
public interface EmployeeDAO {
   /**
    * Used to Create the Employee Information
    * @param employee
    * @return {@link Employee}
    */
   public Employee createEmployee(Employee employee);

   /**
    * Getting the Employee Information using Id
    * @param id
    * @return {@link Employee}
    */
   public Employee getEmployee(int id);

   /**
    * Used to Update the Employee Information
    * @param employee
    * @return {@link Employee}
    */

   public Employee updateEmployee(Employee employee);

   /**
    * Deleting the Employee Information using Id
    * @param id
    */
   public void deleteEmployee(int id);

   /**
    * Getting the All Employees information
    * @return
    */
   public List<Employee> getAllEmployees();
}
