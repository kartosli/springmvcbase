package com.n5x.common.Filter;


import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map.Entry;


/**
 * @version 1.0
 */
public class SecurityFilter extends HttpServlet implements Filter {
	private static final long serialVersionUID = 1L;

	private ServletContext servletContext;

	private static String[] checkkeyword = {"select","insert","delete","from","update","create","drop","alter"," and ","like","exec","count","chr","mid","master","truncate","char","declare","href","iframe","script","alert","grant"
			,"form","input","onload","img ","onclick","ondbclick","onchange","onfocus","object"};

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
                         FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpSession session = request.getSession(true);
		//ie iframe跨域登录安全级别处理
		response.setHeader("P3P","CP=\"IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT\"");

		//CORS跨域配置
		response.setHeader("Access-Control-Allow-Origin",  request.getHeader("Origin"));//暂时发现只可能增加一个固定域名，现在暂时配置全部
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "0");
		response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("XDomainRequestAllowed","1");
		String message = "";
		try {


		} catch (Exception e) {
			e.printStackTrace();
			message = "运行异常！";
			request.setAttribute("ex", new RuntimeException(message));
			return;
		}

		arg2.doFilter(arg0, arg1);
		return;
	}

	public void init(FilterConfig config) throws ServletException {
		servletContext =config.getServletContext();
	}

	public static void main(String[] args) {
		String checkValue = "jx";
		System.out.println("begin");
		if(checkkeyword!=null&&checkkeyword.length>0){
			for(String word : checkkeyword){
				//System.out.println(word);
				if(checkValue!=null && (word.toUpperCase().equalsIgnoreCase(checkValue.toUpperCase()) || checkValue.toUpperCase().contains(word.toUpperCase()))){
					System.out.println("check");
					return;
				}
			}
		}

//		//html防护
//		if(!Jsoup.isValid(checkValue, Whitelist.none())){
//			System.out.println("check2");
//			return;
//		}
		System.out.println("end");
	}

}
