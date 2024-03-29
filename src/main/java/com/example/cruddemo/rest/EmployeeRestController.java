package com.example.cruddemo.rest;

import com.example.cruddemo.entity.Employee;
import com.example.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("employees")
public class EmployeeRestController {

    private EmployeeService employeeService;


    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService){
        this.employeeService = theEmployeeService;
    }

    //add mapping GET "/employee" and return list of employees
    @GetMapping("/all")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    //add mapping GET "/employee/{employeeId}" and return an employee
    @GetMapping("/find/{employeeId}")
    public Optional<Employee> getEmployee(@PathVariable int employeeId){

        Optional<Employee> theEmployee = employeeService.findById(employeeId);
        if(theEmployee.isPresent()) {
            return theEmployee;
        }else{
            throw new RuntimeException("Employee id not found - "+employeeId);
        }
    }

    //add mapping for POST "/employee" and add a new employee
    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee theEmployee){
        //just in case they pass an id in JSON ... set id to 0
        //this is to force a save of new item ... instead of update
        theEmployee.setId(0);
        employeeService.save(theEmployee);
        return theEmployee;
    }

    ///add mapping for PUT "/employees" update existing employee
    @PutMapping("/update")
    public Employee updateEmployee(@RequestBody Employee theEmployee){

        employeeService.save(theEmployee);
        return theEmployee;
    }

    @DeleteMapping("/delete/{employeeId}")
    public Optional<Employee> deleteEmployee(@PathVariable int employeeId){

        Optional<Employee> tempEmployee =employeeService.findById(employeeId);

        //throw exception if null
        if(tempEmployee.isPresent()) {
            employeeService.deleteById(employeeId);
            return tempEmployee;
        }else{
            throw new RuntimeException("Employee id not found -"+employeeId);
        }
    }

}
