package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bean.ListStudentBean;
import com.bean.StudentBean;
import com.dao.StudentDao;

@Controller
public class StudentController
{
	
	@Autowired
	StudentDao studentDao;

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model, StudentBean studentBean)
	{
		model.addAttribute("studentBean", studentBean);
		return "Signup";
	}

	@RequestMapping(value = "/msignup", method = RequestMethod.GET)
	public String msignup(Model model, ListStudentBean listStudentBean, List<StudentBean> studentBeans)
	{
		studentBeans.add(new StudentBean());
		studentBeans.add(new StudentBean());
		studentBeans.add(new StudentBean());
		listStudentBean.setStudents(studentBeans);
		model.addAttribute("listStudentBean", listStudentBean);
		return "MSignup";
	}

	@RequestMapping(value = "signupdata", method = RequestMethod.POST)
	public String signupdata(StudentBean studentBean)
	{
		studentDao.insertStudent(studentBean);
		return "redirect:/searchexam";
	}

	@RequestMapping(value = "msignupdata", method = RequestMethod.POST)
	public String msignupdata(ListStudentBean listStudentBean)
	{
		System.out.println(listStudentBean.getStudents().size());
		for (StudentBean b : listStudentBean.getStudents())
		{
			System.out.println(b.getFirstName());
		}
		return "redirect:/searchexam";
	}

}