package com.ch4njun.spring;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.MemberDAO;
import service.MemberService;
import vo.MemberVO;

@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	private MemberService MemberService;
	
	public void setMemberService(MemberService MemberService) {
		this.MemberService = MemberService;
	}

	@RequestMapping(value= {"/", "home.do"})
	public String home(Locale locale) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		return "home";
	}
	
	@RequestMapping("result.do")
	public String result(String message, String location, Model model) {
		model.addAttribute("message", message);
		model.addAttribute("location", location);
		
		return "result";
	}
	
	@RequestMapping("loginView.do")
	public String login() {
		return "member/loginView";
	}
	
	@RequestMapping("loginLogic.do")
	public String login_action(HttpServletRequest request, HttpSession session, Model model) {
		MemberVO vo = new MemberVO();
		vo.setId(request.getParameter("id"));
		vo.setPw(request.getParameter("pw"));
		
		int result = MemberService.loginMember(vo);
		
		String message = null;
		String location = null;
		
		if(result == 1) {
			String nickname = MemberService.viewMember(vo.getId()).getNickname();
			message = nickname + "님 환영합니다!";
			
			session.setAttribute("login_id", vo.getId());
			session.setAttribute("login_nickname", nickname);
			location = "home.do";
		} else if(result == -1) {
			message = "아이디가 일치하지 않습니다.";
			location = "loginView.do";
		} else if(result == 0) {
			message = "패스워드가 일치하지 않습니다.";
			location = "loginView.do";
		} else {
			message = "로그인 실패!";
			location = "loginView.do";
		}
		
		model.addAttribute("message", message);
		model.addAttribute("location", location);
	
		return "result";
	}
	
	@RequestMapping("joinView.do")
	public String join() {
		return "member/joinView";
	}
	
	@RequestMapping("joinLogic.do")
	public String join_action(HttpServletRequest request, Model model) {
		int result = 0;
		
		MemberVO vo = new MemberVO();
		vo.setId(request.getParameter("id"));
		vo.setPw(request.getParameter("pw"));
		vo.setNickname(request.getParameter("nickname"));
		vo.setEmail(request.getParameter("user_email1"), request.getParameter("user_email2"));
		vo.setPhone(request.getParameter("phone_number1"),
				request.getParameter("phone_number2"),
				request.getParameter("phone_number3"));
		
		result = MemberService.insertMember(vo);
		
		String message = null;
		if(result == 1)
			message = "회원가입 성공.";
		else if(result == -1) {
			message = "중복된 아이디입니다.";
			model.addAttribute("message", message);
			model.addAttribute("location", "joinView.do");
			return "result";
		}else {
			message = "회원가입 실패!";
		}
		
		logger.info(vo.getId() + "'s " + message);
		
		model.addAttribute("message", message);
		model.addAttribute("location", "home.do");
		
		return "result";	
	}
	
	@RequestMapping("logoutLogic.do")
	public String logout_action(HttpSession session, HttpServletResponse response,HttpServletRequest request, Model model) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(int i=0; i<cookies.length; ++i) {
				cookies[i].setMaxAge(0);
				response.addCookie(cookies[i]);
			}
		}
		
		session.removeAttribute("login_id");
		session.removeAttribute("login_nickname");
		model.addAttribute("message", "로그아웃 성공.");
		model.addAttribute("location", "home.do");
		
		return "result";
	}
	
	@RequestMapping("mypageView.do")
	public String mypage(HttpSession session, Model model) {
		if(session.getAttribute("login_id") == null) {
			model.addAttribute("message", "로그인을 먼저 해주세요.");
			model.addAttribute("location", "loginView.do");
			
			return "result";
		}
		return "member/mypageView";
	}
	
	@RequestMapping("mypageLogic.do")
	public String mypage_action(HttpSession session, HttpServletRequest request, Model model) {
		String id = (String) session.getAttribute("login_id");	
		String pw = request.getParameter("pw");
		
		if("admin".equals(id)) {
			List<MemberVO> voList = MemberService.memberList();
			model.addAttribute("voList", voList);
			
			return "member/adminResultView";
		}else {
			MemberVO vo = MemberService.viewMypage(id, pw);
			if(vo == null) {
				model.addAttribute("message", "패스워드가 일치하지 않습니다.");
				model.addAttribute("location", "mypageView.do");
				
				return "result";
			}
			model.addAttribute("vo", vo);
			
			return "member/mypageResultView";
		}
	}
	
	@RequestMapping("explusionLogic.do")
	public String explusion(String id, String pw, Model model) {
		if("admin".equals(id)) {
			model.addAttribute("message", "admin은 추방할 수 없습니다.");
			model.addAttribute("location", "mypageLogic.do");
		}else {
			int result = MemberService.deleteMember(id, pw);

			if(result == 0) {
				model.addAttribute("message", "회원탈퇴 실패");
				model.addAttribute("location", "mypageLogic.do");
			}else {
				model.addAttribute("message", id + " 회원 추방 완료");
				model.addAttribute("location", "mypageLogic.do");
			}
		}
		
		return "result";
	}
	
	@RequestMapping("signoutView.do")
	public String signout() {
		return "member/signoutView";
	}
	
	@RequestMapping("signoutLogic.do")
	public String signout_action(HttpServletRequest request, HttpSession session, Model model) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		int result = MemberService.deleteMember(id, pw);
		if(result == -2) {
			model.addAttribute("message", "아이디가 일치하지 않습니다.");
			model.addAttribute("location", "signoutView.do");
		}else if(result == -1) {
			model.addAttribute("message", "패스워드가 일치하지 않습니다.");
			model.addAttribute("location", "signoutView.do");
		}else if(result == 0) {
			model.addAttribute("message", "회원탈퇴 실패");
			model.addAttribute("location", "signoutView.do");
		}else {
			session.removeAttribute("login_id");
			session.removeAttribute("login_nickname");
			
			model.addAttribute("message", "회원탈퇴 완료");
			model.addAttribute("location", "home.do");
		}
		return "result";
	}
}
