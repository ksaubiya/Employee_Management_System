package com.ems.controller;

import com.ems.payload.ApiResponse;
import com.ems.service.EmployeeService;
import com.ems.service.dto.EmployeeDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    //POST- create employee
    @PostMapping("/")
    public ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody EmployeeDto employeeDto)
    {
        EmployeeDto createdemployeeDto=this.employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(createdemployeeDto, HttpStatus.CREATED);
    }

    //PUT- update employee

    @PutMapping("/{empId}")
    public ResponseEntity<EmployeeDto> updateEmployee(@Valid @RequestBody EmployeeDto employeeDto,@PathVariable("empId") Integer eId)
    {
        EmployeeDto updatedEmployee=this.employeeService.updateEmployee(employeeDto,eId);

        return ResponseEntity.ok(updatedEmployee);
    }

    //DELETE- delete employee
    @DeleteMapping("/{empId}")
    public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable("empId") Integer eid)
    {
        this.employeeService.delete(eid);
        return new ResponseEntity<ApiResponse>(new ApiResponse("employee deleted successfully",true), HttpStatus.OK);
    }

    //GET- employee get
    @GetMapping("/")
    public ResponseEntity<List<EmployeeDto>> getAllEmployee()
    {
        return ResponseEntity.ok(this.employeeService.getAllEmployees());
    }

    @GetMapping("/{empId}")
    public ResponseEntity<EmployeeDto> getSingleEmployee(@PathVariable Integer eId)
    {
        return  ResponseEntity.ok(this.employeeService.getEmployeeById(eId));
    }
}
