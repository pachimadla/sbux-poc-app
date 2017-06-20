/*
 *EmployeeServiceImpl.java
 *
 *
 * Copyright (c) 2016 Southwest Airlines, Co.
 * 2702 Love Field Drive, Dallas, TX 75235, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary
 * information of Southwest Airlines, Co.
 */
package com.lm.sbux.poc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lm.sbux.poc.dao.EmployeeDAO;
import com.lm.sbux.poc.entity.Employee;
import com.lm.sbux.poc.eventhub.EventHub;
import com.lm.sbux.poc.service.EmployeeService;

/**
 * Service Impl class for Employee to perform CRUD operation.
 * @author Laxman Pachimadla
 * @version 1.0
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

   @Autowired
   private EmployeeDAO employeeDAO;

   @Autowired
   EventHub eventHub;

   /**
    * Default Constructor
    */
   public EmployeeServiceImpl() {
      super();
   }

   @Override
   public Employee createEmployee(Employee employee) {
      Employee emp = employeeDAO.createEmployee(employee);
      try {
         eventHub.sendEvent(emp.toString());
      } catch (Exception e) {
         e.printStackTrace();
      }
      return emp;
   }

   @Override
   public Employee getEmployee(int id) {
      return employeeDAO.getEmployee(id);
   }

   @Override
   public Employee updateEmployee(Employee employee) {
      return employeeDAO.updateEmployee(employee);
   }

   @Override
   public void deleteEmployee(int id) {
      employeeDAO.deleteEmployee(id);
   }

   @Override
   public List<Employee> getAllEmployees() {
      return employeeDAO.getAllEmployees();
   }

}
