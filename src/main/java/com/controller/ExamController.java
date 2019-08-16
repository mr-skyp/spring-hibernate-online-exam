package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bean.ExamBean;
import com.bean.QuestionBean;
import com.bean.StudentBean;
import com.bean.UserExamBean;
import com.bean.UserExamListBean;
import com.dao.ExamDao;

@Controller
public class ExamController
{

	@Autowired
	ExamDao examDao;

	@RequestMapping(value = "searchexam", method = RequestMethod.GET)
	public String searchExam(Model model, ExamBean examBean)
	{
		model.addAttribute("exam", examBean);
		return "ExamCode";
	}

	@RequestMapping(value = "addexam", method = RequestMethod.POST)
	public String addExam(ExamBean examBean)
	{
		examDao.insertExam(examBean);
		return "ListExam";
	}

	@RequestMapping(value = "newexam", method = RequestMethod.GET)
	public String newExam(Model model, ExamBean examBean)
	{
		model.addAttribute("exam", examBean);
		return "NewExam";
	}

	@RequestMapping(value = "listexam", method = RequestMethod.POST)
	public String listExam(Model model, ExamBean examBean)
	{
		ExamBean exam = examDao.getExam(examBean.getCode());
		if (exam == null)
		{
			model.addAttribute("exam", examBean);
			return "ExamCode";
		}
		else
		{
			UserExamListBean userExamListBean = new UserExamListBean();
			List<UserExamBean> userExamBeans = new ArrayList<UserExamBean>();
			StudentBean studentBean = new StudentBean();
			studentBean.setStudentId(1);// session
			for (QuestionBean q : exam.getQuestions())
			{
				UserExamBean userExamBean = new UserExamBean();
				userExamBean.setExam(exam);
				userExamBean.setQuestion(q);
				userExamBean.setStudent(studentBean);
				userExamBeans.add(userExamBean);
			}
			userExamListBean.setUserExam(userExamBeans);
			model.addAttribute("exams", userExamListBean);
			return "Exam";
		}
	}
	
}