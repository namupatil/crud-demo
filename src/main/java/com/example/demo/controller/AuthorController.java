package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.model.Author;
import com.example.demo.repository.Authorrepository;

@Controller
@RequestMapping(value="/authors")
public class AuthorController {

	@Autowired
	Authorrepository authorrepo;
	
	@RequestMapping(value="/list")
	public String details(Model model)
	{
		model.addAttribute("displaylist",authorrepo.findAll());
		return "authordetails";
	}
	
	@RequestMapping(value="/newpage")
	public String createpage(Model model)
	{
		Author auth=new Author();
		model.addAttribute("newauthor", auth);
		return "enterauthor";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String savepage(@ModelAttribute("auth")Author auth)
	{
		authorrepo.save(auth);
		return "redirect:/authors/";
	}
	
	@RequestMapping(value="/edit/{Id}")
	public String updateauth(Model model,@PathVariable(name="Id") Integer Id)
	{
		model.addAttribute("updateauthor", authorrepo.findById(Id));
		return "updateauthor";
	}
	
	@RequestMapping(value="/delete/{Id}")
	public String deleteauth(Model model,@PathVariable(name="Id") Integer Id)
	{
		authorrepo.deleteById(Id);
		return "redirect:/authors/";
	}
}
