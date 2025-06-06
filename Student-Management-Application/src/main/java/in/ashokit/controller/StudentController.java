package in.ashokit.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import in.ashokit.entity.Student;
import in.ashokit.service.StudentService;

@Controller
public class StudentController {

	private StudentService service;
	
	public StudentController(StudentService service)
	{
		this.service = service;
	}
	
	//method to display empty form
	
	@GetMapping("/student")
	public String loadForm(Model model)
	{
		model.addAttribute("msg", "Hello Students");
		return "index";
	}
	
	
	@GetMapping("/")
	public ModelAndView index()
	{
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("index");
		
		return mav;
	}
	
	//method for save student form data
	
	@PostMapping("/saveStudent")
	public ModelAndView handleSubmitButton(Student s)
	{
		ModelAndView mav = new ModelAndView();
		
		boolean isSaved = service.saveStudent(s);
		
		if(isSaved) {
			mav.addObject("smsg", "Student Saved");
		} else {
			mav.addObject("emsg", "Failed to save Student");
		}
		
		mav.setViewName("index");
		
		return mav;
	}
	
	//method to display student table data
	
	@GetMapping("/getData")
	public ModelAndView getAllStudents()
	{
		List<Student> allStudents = service.getAllStudents();
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("students", allStudents);
		mav.setViewName("data");
		
		return mav;
	}
	
	@GetMapping("/delete")
	public ModelAndView deleteStudent(@RequestParam("sid") Integer sid)
	{
		service.deleteStudent(sid);
		
		List<Student> allStudents = service.getAllStudents();
		ModelAndView mav = new ModelAndView();
		mav.addObject("students", allStudents);
		mav.addObject("dmsg","Student Deleted Successfully");
		mav.setViewName("data");
		
		return mav;
	}
	
}
