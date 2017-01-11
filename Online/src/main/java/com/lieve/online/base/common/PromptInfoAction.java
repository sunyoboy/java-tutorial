/**
 * 
 */
package com.lieve.online.base.common;

import org.apache.log4j.Logger;


/**
 * @author Administrator
 *
 */
public class PromptInfoAction {
	
	protected Logger logger=Logger.getLogger(this.getClass());  

/*
	public String getPromtpInfo(String key)
	{
		
		
		//从cookie中读取语言标识信息
		HttpServletRequest request=this.getRequest();
		String languageFlag=RequestUtils.getCookieValue(request, Constant.COOKIE_LANGUAGEFLAG);
		
		if(Util.isNull(languageFlag ))
		{
			String language=request.getHeader("Accept-Language");
			int temp=language.indexOf(",");
			if(temp>0)
			{
				language=language.substring(0, temp);
			}
			languageFlag=language.toLowerCase();
		}
		
		String result=this.getLanguagePromptInfo(languageFlag, key);
		
		logger.debug("languageFlag="+languageFlag+",key="+key+",result="+result);
		
		return result;
	}

	public String getLanguagePromptInfo(String languageFlag,String key)
	{
		StringBuilder fileName=new StringBuilder("resource_").append(languageFlag);
		
		String result=ConfigFileReader.getPropertyFromBuffer(fileName.toString(), key, languageFlag+":"+key);
		
		logger.debug("fileName="+fileName.toString()+",key="+key+",result="+result);
		
		return result;	
	}
	
	public void setClientLanguageFlag(String flag)
	{
		
		RequestUtils.setCookie(this.getResponse(), Constant.COOKIE_LANGUAGEFLAG, flag,"");
	}
	
	private HttpServletRequest getRequest()
	{
		WebContext webContext = WebContextFactory.get();
		
		return  webContext.getHttpServletRequest();
		
	}
	
	private HttpServletResponse getResponse()
	{
		WebContext webContext = WebContextFactory.get();
		
		return  webContext.getHttpServletResponse();
		
	}*/
}
