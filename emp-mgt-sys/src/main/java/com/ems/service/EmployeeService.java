package com.ems.service;

import com.ems.service.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto emp);

    EmployeeDto updateEmployee(EmployeeDto emp,Integer empId);

    EmployeeDto getEmployeeById(Integer empId);

    List<EmployeeDto> getAllEmployees();

    void delete(Integer empId);
}
