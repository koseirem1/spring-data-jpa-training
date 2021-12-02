package com.aep.training.controller.v1;

import com.aep.training.annotation.StudentAPI;
import com.aep.training.domain.entity.Student;
import com.aep.training.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@RequestMapping(ApiConstant.ROOT+"/students")
//@RestController
@StudentAPI
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student create(@RequestBody Student student) throws Exception {
        Student createdStudent = this.studentService.createOrUpdate(student);
        return createdStudent;
    }

    @GetMapping
    public List<Student> getAll(){
        List<Student> students = this.studentService.getAll();
        return students;
    }

    @GetMapping("/{studentId}")
    public Student getById(@PathVariable("studentId") Long studentId) throws Exception {
        Student foundStudent = this.studentService.getById(studentId);
        return foundStudent;
    }

    @GetMapping("/search")
    public List<Student> search(@RequestParam("sc") String searchCriteria,
                                @RequestParam(value = "name",required = false) String name,
                                @RequestParam(value = "surname",required = false) String surname ) throws Exception {
        List<Student> resultList = new ArrayList<>();
        if("name".equals(searchCriteria)){
            resultList.add(this.studentService.getByName(name));
        } else if("surname".equals(searchCriteria)){
            resultList =this.studentService.getAllBySurname(surname);
        }else if("nameandsurname".equals(searchCriteria)){
            resultList =this.studentService.getByNameAndSurname(name,surname);
        }else if("nameorsurname".equals(searchCriteria)){
            resultList =this.studentService.getByNameOrSurname(name,surname);
        }
        return resultList;
    }

    @GetMapping("/{studentId}/")
    public boolean isExist(@PathVariable("studentId") Long studentId,@RequestParam("operation") String operation) throws Exception {
        if("ec".equals(operation))
            return this.studentService.isExist(studentId);

        return false;
    }

    @GetMapping("/")
    public long getCount(@RequestParam("operation") String operation) throws Exception {
        if("count".equals(operation))
            return this.studentService.getCount();
        return 0;
    }

    @PutMapping
    public Student update(@RequestBody Student student) throws Exception {
        Student updatedStudent = this.studentService.createOrUpdate(student);
        return updatedStudent;
    }

    @DeleteMapping("/{studentId}")
    public void deleteById(@PathVariable("studentId") Long studentId) throws Exception {
        this.studentService.deleteById(studentId);
    }


}
