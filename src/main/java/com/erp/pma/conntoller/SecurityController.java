package com.erp.pma.conntoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.erp.pma.dao.UserAccountRepository;
import com.erp.pma.entities.UserAccount;

@Controller
public class SecurityController {
	
	@Autowired
	BCryptPasswordEncoder bCryptEncoder;
	
	@Autowired
	UserAccountRepository userRepo;

	@GetMapping("/register")
	public String register(Model model) {
		UserAccount userAccount = new UserAccount();
		model.addAttribute("userAccount", userAccount);
		return "security/register";
	}
	
	@PostMapping("/register/save")
	public String save(Model model,UserAccount user) {
		user.setPassword(bCryptEncoder.encode(user.getPassword()));
		userRepo.save(user);
		return "redirect:/";
	}
}
