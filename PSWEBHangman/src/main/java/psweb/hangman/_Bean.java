package psweb.hangman;

import java.util.Locale;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class _Bean 
{
	public _Bean() {
		Locale.setDefault(new Locale("pt","BR"));
	}
	
	public Object getJsfParam(String param){
		return getHttpServletRequest().getAttribute(param);
	}
	
	public HttpServletRequest getHttpServletRequest(){
		return (HttpServletRequest)getExternalContext().getRequest();
	}
	
	public HttpServletResponse getHttpServletResponse() {
		return (HttpServletResponse)getExternalContext().getResponse();
	}
	
	public HttpSession getHttpSession(){
		return getHttpServletRequest().getSession();
	}	
	
	public ExternalContext getExternalContext(){
		return getFacesContext().getExternalContext();
	}
	
	public ServletContext getServletContext(){
		return (ServletContext)getExternalContext().getContext();
	}
	
	public String getFileSeparator(){
		return System.getProperty("file.separator");
	}
	
	public FacesContext getFacesContext(){
		return FacesContext.getCurrentInstance();
	}
}
