package LoginTask.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import LoginTask.service.MailingService;
import LoginTask.service.ServiceException;
import LoginTask.service.loginService;

@Controller
public class LoginController {
	
	@Autowired
	loginService loginService;
	
	//로그인 폼
	@RequestMapping(value="/login/loginForm", method=RequestMethod.GET)
	public String getlogin(){
		return "loginForm";
	}

	//로그인 처리
	@RequestMapping(value="/login/loginAct", method=RequestMethod.POST)        
	public String postLogin(@RequestParam("id") String id, @RequestParam("pwd") String pwd, HttpSession session ,Model model, HttpServletRequest request) throws ServiceException, SQLException, MessagingException, IOException {
		
		int resultCnt = loginService.login(id, pwd);
		
		if(resultCnt == 1) {
			session.setAttribute("id", id);
			session.setAttribute("pwd", pwd);
			
			model.addAttribute("id", id);
		}
		
		if(resultCnt == 4) {
			model.addAttribute("id", id);
		}
		
		model.addAttribute("resultCntTmp", resultCnt );

		return "loginAct";
	} 
	
	
	@RequestMapping(value="/logoutAct", method=RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		
		request.getSession(false).invalidate();
		return "redirect:/";
		/*return "logoutAct";*/
	}
	

}
