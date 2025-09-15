package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    @Autowired
    private StudentRepo studentRepo;

    @RequestMapping("/")
    public String home(){
		return "students.html";
	}
    
    
    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("students", studentRepo.findAll());
        model.addAttribute("student", new StudentEntity());
        return "students"; 
    }

    
    @RequestMapping("/students/save")
    public String saveStudent(@ModelAttribute("student") StudentEntity student) {
        studentRepo.save(student);
        return "redirect:/students"; // ✅ reload table with updated data
    }


    // Delete student
    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentRepo.deleteById(id);
        return "redirect:/students";
    }
}
