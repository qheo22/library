package com.library.wol.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.library.wol.model.BoardCriteria;
import com.library.wol.model.BoardVO;
import com.library.wol.model.Board_Comment;
import com.library.wol.service.BoarderDao;
import com.library.wol.service.BoarderService;



@Controller
public class BoarderController {
	@Autowired
	BoarderService Bs;
	@Autowired
	BoarderDao Bdao;
	
	int viewcnt;
	
	@RequestMapping(value = "WriteForm.do")
	public String writeForm1(){
		return "witeForm";
	}
	
		
	@RequestMapping(value = "write.do")
	public String BoardWrite(BoardVO boader){
		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
		String data1 = date.format(today);
		System.out.println(date.format(today));
		boader.setRegdate(data1);
		System.out.println(boader);
		Bs.BoardWrite(boader);
		return "redirect:/main2.do"; 
	}
	
	
	@RequestMapping(value = "viewDetaill.do")
	public String viewDetaill(Model m,int bno){
		try {
			Bs.viewDetaill(bno);
			m.addAttribute("board",Bs.viewDetaill(bno));
			Bs.viewcnt(bno);
			List<Board_Comment> asd = Bs.board_Comment_view(bno);
			System.out.println("asd : " + asd.get(0).getWritetime());
			m.addAttribute("comment",Bs.board_Comment_view(bno));
			return "viewDetail";
		} catch (Exception e) {
			return "viewDetail";
		}
	}
	
	
	
	//게시판 검색 부분
	@RequestMapping(value = "boardselect.do" , produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String boardselect(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "per", defaultValue = "10") int per, String select,String search, Model m) {
			HashMap<String,Object> map = new HashMap<>();
			map.put("select",select);
			map.put("search", search);
			
			BoardCriteria lista = Bs.list(pageNum, per);
			m.addAttribute("boardList", lista);
		    int number = lista.getCount() - (pageNum - 1) * per;
		    m.addAttribute("number", number);
		    
		    
			System.out.println(map.get("search"));
			System.out.println(map.get("select"));
			m.addAttribute(Bs.boardselect(map));
			Gson json = new Gson();
			return json.toJson(m);
	}
	
	
	
	//게시판 ajax 검색 / 페이징 
	@RequestMapping(value = "boardselectajax.do" , produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String boardselectajax(@RequestParam(value = "p", defaultValue = "1") int pageNum,
		@RequestParam(value = "per", defaultValue = "10") int per, String select,String search, Model m) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("per", per);
		map.put("select", select);
		map.put("search", search);
		map.put("pageNum", pageNum);
	
		BoardCriteria list = Bs.searchlist(map);
		// 검색된 리스트의 카운트 리턴
		int number = list.getCount() - (pageNum - 1) * per;
		m.addAttribute("boardList",list); // 페이징
		m.addAttribute("boardList1",list.getBoardInfoList()); // 검색값 리턴
		m.addAttribute("number", number);
	   
		Gson json = new Gson();
		return json.toJson(m);
	}
	
	
	
	//게시판 검색부분
	@RequestMapping(value = "boardsearch.do" , produces="text/plain;charset=UTF-8")
	public String boardsearch(@RequestParam(value = "p", defaultValue = "1") int pageNum,
		@RequestParam(value = "per", defaultValue = "10") int per, String select,String search, Model m) {
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("per", per);
		map.put("select", select);
		map.put("search", search);
		map.put("pageNum", pageNum);
	
		
		BoardCriteria list = Bs.searchlist(map);
		m.addAttribute("boardList",list); // 페이징
		
		
		m.addAttribute("boardList1",list.getBoardInfoList()); // dto 값 가져오는 부분
		
		
		int number = list.getCount() - (pageNum - 1) * per;
	    m.addAttribute("number", number);
			return "Boardsearch";   
	}
	
	

	
	
	
//	@RequestMapping(value = "/Board.do")
//	public String Board(Model m) throws Exception {
//		List<BoardVO> list = Bs.BoardList();
//		System.out.println(list);
//		m.addAttribute("BoardList", list);
//		return "Board";
//	}
	
	
	// 페이징
		@RequestMapping("/Board.do")
	   public String list(@RequestParam(value = "p", defaultValue = "1") int pageNum,
	       @RequestParam(value = "per", defaultValue = "10") int per, Model m) {
	      BoardCriteria list = Bs.list(pageNum, per);
	      //list == 아까불러온 10개의 리스트가 들어있고 // pagenum  1 , p.totalPageCount 10,
	      //start 0 , count 12
	      m.addAttribute("boardList", list);
	      int number = list.getCount() - (pageNum - 1) * per;
	      System.out.println(number);
	      m.addAttribute("number", number);
	      return "Board";
	   }
	   
	   
	   
	   @GetMapping(value = "/pagingboardajax.do" , produces="text/plain;charset=UTF-8")
	   @ResponseBody
	   public String list1(@RequestParam(value = "p", defaultValue = "1") int pageNum,
	       @RequestParam(value = "per", defaultValue = "10") int per, Model m) {
	      BoardCriteria list = Bs.list(pageNum, per);
	      m.addAttribute("boardList", list);
	      int number = list.getCount() - (pageNum - 1) * per;
	      m.addAttribute("number", number);
	      Gson json = new Gson();
	      System.out.println(json.toJson(m));
	      return json.toJson(m);
	   }
	   
	   
	   
	   //게시글 삭제 기능
	   
	   @RequestMapping(value = "/boarddelete.do")
	   @ResponseBody
	   public int delete(int bno) {
		  return Bs.delete(bno);
	   }
	   
	   
	   //게시글 댓글 기능   
	   @RequestMapping(value = "/boardcomment.do")
	   public String boardcomment(Board_Comment bc,Model m) {
		   //인서트 부분
		   Bs.board_Comment(bc);
		  
		   int comment_no = bc.getComment_no();
		   bc = Bs.board_Comment_select(comment_no);//댓글인서트한 모든정보.
		   System.out.println(bc.getWritetime());
		   
		   m.addAttribute("model",bc);
		  
		   return "commentinsert";
		   
	   }
	   
	   
	   //댓글 삭제 기능
	   @RequestMapping(value = "/commentdelete.do")
	   @ResponseBody
	   public int commentdelete(int comment_no) {
		  return Bs.comment_delete(comment_no);
	   }
	   
	   
	   
	   //댓글 수정 기능
	   @RequestMapping(value = "/commentupdate.do")
	   @ResponseBody
	   public String commentupdate(Model m,int comment_no,String board_comment) {
		   HashMap<String, Object> map = new HashMap<String, Object>();
		   map.put("comment_no", comment_no); 
		   map.put("board_comment", board_comment);
		   
		   m.addAttribute(Bs.commentupdateselect(comment_no));
		   m.addAttribute(Bs.commentupdate(map));
		   
		   Gson json = new Gson();
		   return json.toJson(m);
	   }

	   
	   
	   
	   //수정할 게시물의 정보를 불러오는 컨트롤러
	   @RequestMapping(value = "/boardupdateform.do")   
	   public String updateform(int bno,Model m){
		  m.addAttribute("boarderlist", Bs.boardupdateform(bno));
		  return "boardupdateform";
	   }
	   
	   
	   //게시글 수정 컨트롤러
	   @RequestMapping(value = "/boardupdate.do") 
	   @ResponseBody
	   public int boardupdate(BoardVO BoardVO){	   
		   return Bs.update(BoardVO);
	  }
}
	   


		
