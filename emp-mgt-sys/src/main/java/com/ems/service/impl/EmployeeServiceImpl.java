package com.ems.service.impl;

import com.ems.entity.Employee;
import com.ems.exception.ResourceNotFoundException;
import com.ems.repository.EmployeeRepo;
import com.ems.service.EmployeeService;
import com.ems.service.dto.EmployeeDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Employee> getUsers()
    {
        return employeeRepo.findAll();
    }

//    public Employee createUser(Employee employee)
//    {
//        employee.setId(UUID.randomUUID().variant());
//        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
//        return employeeRepo.save(employee);
//    }
    //create method
//    @Override
//    public EmployeeDto createUser(EmployeeDto employeeDto) {
//
//        Employee employee=this.dtoToUser(employeeDto);
//        Employee savedUser=this.employeeRepo.save(employee);
//        return this.userToDto(savedUser);
//    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = dtoToEmployee(employeeDto);
        employee.setId(UUID.randomUUID().hashCode());
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        Employee savedEmployee = employeeRepo.save(employee);  // Save the Employee entity
        return employeeToDto(savedEmployee);
        //return employeeRepo.save(employee);
    }

    //update method
    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto, Integer employeeId) {

        Employee employee=this.employeeRepo.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("Employee"," Id",employeeId));

        employee.setFname(employeeDto.getFname());
        employee.setLname(employeeDto.getLname());
        employee.setPassword(employeeDto.getPassword());
        employee.setEmail(employeeDto.getEmail());

        Employee updatedEmployee=this.employeeRepo.save(employee);
        EmployeeDto employeeDto1=this.employeeToDto(updatedEmployee);
        return employeeDto1;
    }

    //get method
    @Override
    public EmployeeDto getEmployeeById(Integer employeeId) {

        Employee employee=this.employeeRepo.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Employee","Id",employeeId));
        return this.employeeToDto(employee);
    }

    //get all
    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> emps=this.employeeRepo.findAll();
        List<EmployeeDto> employeeDtos=emps.stream().map(employee->this.employeeToDto(employee)).collect(Collectors.toList());
        return employeeDtos;
    }


    //delete method
    @Override
    public void delete(Integer empId) {
        Employee employee=this.employeeRepo.findById(empId).orElseThrow(()-> new ResourceNotFoundException("Employee","Id",empId));
        this.employeeRepo.delete(employee);
    }

    public Employee dtoToEmployee(EmployeeDto employeeDto)
    {
        Employee employee=this.modelMapper.map(employeeDto,Employee.class);
//        Employee employee=new Employee();
//        employee.setId(employeeDto.getId());
//        employee.setName(employeeDto.getName());
//        employee.setEmail(employeeDto.getEmail());
//        employee.setPassword(employeeDto.getPassword());
//        employee.setAbout(employeeDto.getAbout());
        return employee;
    }

    public EmployeeDto employeeToDto(Employee employee)
    {
        EmployeeDto employeeDto=this.modelMapper.map(employee,EmployeeDto.class);
//        EmployeeDto employeeDto=new EmployeeDto();
//        employeeDto.setId(employee.getId());
//        employeeDto.setName(employee.getName());
//        employeeDto.setEmail(employee.getEmail());
//        employeeDto.setPassword(employee.getPassword());
//        employeeDto.setAbout(employee.getAbout());
        return employeeDto;
    }

}
