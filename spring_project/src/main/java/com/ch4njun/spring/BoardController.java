package com.ch4njun.spring;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import service.BoardService;
import util.UploadFileUtil;
import vo.BoardVO;

@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	private BoardService boardService;
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	private String uploadPath;
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	
	@RequestMapping("boardView.do")
	public String boardHome(Integer page, HttpServletRequest request, Model model) {
		int now_page = 1;
		if(page != null)
			now_page = page;
		
		int max_page = boardService.boardCount() / util.pageData.BLOCKLIST + 1;
		
		int start = (now_page - 1) * util.pageData.BLOCKLIST + 1;
		int end = start + util.pageData.BLOCKLIST - 1;
		
		List<BoardVO> voList = boardService.selectList(start, end);
		
		model.addAttribute("now_page", now_page);
		model.addAttribute("max_page", max_page);
		model.addAttribute("voList", voList);
		
		return "board/boardView";
	}
	
	@RequestMapping("contentView.do")
	public String contentView(HttpServletResponse response, HttpServletRequest request, HttpSession session, Integer idx, Model model) {
		Cookie[] cookies = request.getCookies();
		boolean flag = false;
		if(cookies != null) {
			for(int i=0; i<cookies.length; ++i) {
				if(cookies[i].getName().equals(String.valueOf(idx))) {
					flag = true;
					break;
				}
			}
		}
		if(!flag) {
			if(boardService.upHit(idx) != 1) {
				model.addAttribute("message", "????????? ???????????????.");
				model.addAttribute("location", "boardView.do");
				
				return "result";
			}
			
			Cookie hitCookie_temp = new Cookie(String.valueOf(idx), "already_hit");
			hitCookie_temp.setMaxAge(60*30);
			response.addCookie(hitCookie_temp);
		}
		
		BoardVO vo = boardService.selectOne(idx);
		
		if(session.getAttribute("login_id") == null) {
			model.addAttribute("message", "???????????? ??????????????????.");
			model.addAttribute("location", "boardView.do");
			
			return "result";
		}
		
		int self = 0;
		if(session.getAttribute("login_id").equals(vo.getId()))
			self = 1;
		
		model.addAttribute("vo", vo);
		model.addAttribute("self", self);

		return "board/contentView";
	}
	
	@RequestMapping("writeView.do")
	public String WriteView() {
		return "board/writeView";
	}
	
	@RequestMapping("writeLogic.do")
	public String WriteLogic(MultipartFile file, HttpServletRequest request, HttpSession session, Model model) throws Exception {
		BoardVO vo = new BoardVO();
		
		vo.setId((String)session.getAttribute("login_id"));
		vo.setContext(request.getParameter("context").replace("\r\n", "<br>"));
		vo.setTitle(request.getParameter("title"));
		vo.setWrite_pw(request.getParameter("pw"));
		
		if(file != null) {
			logger.info("originalName:" + file.getOriginalFilename());
			logger.info("size:" + file.getSize());
			logger.info("ContentType:" + file.getContentType());
			
			String savedName = UploadFileUtil.uploadFile(this.uploadPath, file.getOriginalFilename(), file.getBytes());
			vo.setFile_path(savedName);
		}else {
			vo.setFile_path(null);
		}

		int result = boardService.insertBoard(vo);

		if(result == 1) 
			model.addAttribute("message", "????????? ????????? ?????????????????????!");
		else 
			model.addAttribute("message", "????????? ????????? ?????????????????????!");
		
		model.addAttribute("location", "boardView.do");
		return "result";
	}
	
	@RequestMapping("deleteLogic.do")
	public String DeleteLogic(int idx, Model model) {
		if(boardService.selectOne(idx) == null) {
			model.addAttribute("message", "????????? ???????????????.");
		}else {
			if(boardService.deleteBoard(idx) != 1) 
				model.addAttribute("message", "????????? ?????????????????????!");
			else 
				model.addAttribute("message", "????????? ?????????????????????!.");
		}
		
		model.addAttribute("location", "boardView.do");
		return "result";
	}
	
	@RequestMapping("updateView.do")
	public String UpdateView(int idx, Model model) {
		BoardVO vo = boardService.selectOne(idx);
		if(vo == null) {
			model.addAttribute("message", "????????? ???????????????.");
			model.addAttribute("location", "boardView.do");
			
			return "result";
		}
		
		vo.setContext(vo.getContext().replace("<br>", "\r\n"));
		model.addAttribute("vo", vo);
		
		return "board/updateView";
	}
	
	@RequestMapping("updateLogic.do")
	public String UpdateLogic(Model model, HttpServletRequest request) {
		BoardVO vo = new BoardVO();
		vo.setIdx(Integer.parseInt(request.getParameter("idx")));
		vo.setTitle(request.getParameter("title"));
		vo.setContext(request.getParameter("context").replace("\r\n", "<br>"));
		vo.setWrite_pw(request.getParameter("pw"));
		
		String fileName = request.getParameter("filename");
		vo.setFile_path(fileName == null ? "noFile" : fileName);
		
		int result = boardService.updateBoard(vo);
		if(result != 1) {
			model.addAttribute("message", "??????????????? ?????????????????????!");
			model.addAttribute("location", "boardView.do");
		}else {
			model.addAttribute("message", "??????????????? ?????????????????????!");
			model.addAttribute("location", "contentView.do?idx=" + vo.getIdx());
		}
		
		return "result";
	}
	
	@RequestMapping("fileDownload.do")
	public ModelAndView FileDownloadLogic(int idx) {
		String filename = boardService.selectFileName(idx);
		if(filename.equals("noFile")) {
			return null;
		}
		
		String filepath = uploadPath + "\\" + filename;
		
		File file = new File(filepath);
		
		return new ModelAndView("downloadView", "downloadFile", file);
	}
}
