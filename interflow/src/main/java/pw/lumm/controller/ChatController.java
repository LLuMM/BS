package pw.lumm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pw.lumm.model.Msg;
import pw.lumm.service.inf.MessageService;

@Controller
@RequestMapping("/chat")
public class ChatController {
	@Autowired
	private MessageService messageService;
	// 好友邀请后到聊天室页面
	@RequestMapping(value = "/friendchat", method = RequestMethod.GET)
	public ModelAndView mainpage(HttpServletRequest request, @RequestParam String id) throws Exception {
		//判断，如果没有session，则跳到登录页面
		HttpSession session = request.getSession();
		if(null==session.getAttribute("user")){
			System.out.println(session.getAttribute("user").toString());
			return new ModelAndView("user/login");
		}else{
			Msg msg = new Msg();
			msg.setId(id);
			messageService.setMessasgeStatus(msg);
			return new ModelAndView("user/chatmain");
		}
	}
	@RequestMapping(value = "/tochat", method = RequestMethod.GET)
	public ModelAndView tochat(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
			return new ModelAndView("user/chatmain");
	}
}
