/*
 * 
 */
package com.n5x.common.util;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author 
 *
 * @version 1.0
 * @since   1.0
 */
public class StringUtil
{
	public static final boolean isBlank(Object obj) {
		return obj == null || obj.toString().trim().length() == 0;
	}

	public static final boolean hasText(Object obj) {
		return !isBlank(obj);
	}

	public static final boolean isNumeric(Object obj) {
		return hasText(obj) && trim(obj).matches("^\\d+$");
	}
	public static final boolean isNumeric(Object... args) {
		if(args!=null&&args.length>0){
			for (Object arg:
					args) {
				if(!isNumeric(arg)){
					return false;
				}
			}
			return true;
		}
		return false;
	}
	public static final String trim(Object obj) {
		return obj == null?"":obj.toString().trim();
	}
	/**
	 * 比较字符串
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean equalsAll(String str1, String str2)
	{
		//把中文符号换成英文符号 再进行比较
		str1 = str1.replaceAll("，", ",");//中文逗号
		str1 = str1.replaceAll("。", ".");//中文句
		str1 = str1.replaceAll("！", "!");//中文句
		str1 = str1.replaceAll("？", "?");//中文句
		
		str2 = str2.replaceAll("，", ",");//中文逗号
		str2 = str2.replaceAll("。", ".");//中文句
		str2 = str2.replaceAll("！", "!");//中文句
		str2 = str2.replaceAll("？", "?");//中文句
		
		return StringUtils.equals(str1, str2);
	}

	/**
	 * 比较字符串
	 * 
	 * @param str
	 * @param strs
	 * @return
	 */
	public static boolean equalsAny(String str, String... strs)
	{
		for (String s : strs)
			if (StringUtils.equals(str, s))
				return true;

		return false;
	}

	public static List<String> split(String str, String separator)
	{
		if (StringUtils.isEmpty(str))
			return new ArrayList<String>();

		StringTokenizer st = new StringTokenizer(str, separator);
		List<String> result = new ArrayList<String>();

		while (st.hasMoreTokens())
			result.add(StringUtils.trim(st.nextToken()));

		return result;
	}

	/**
	 * 判断是否为空
	 * resin与tomcat  对""传值解析有差异
	 * @param args
	 * @return
	 */
	public static boolean isEmpty(String... args){
		if(args!=null&&args.length>0){
			for (String arg:
				 args) {
				if(isEmpty(arg)){
					return true;
				}
			}
			return false;
		}
		return true;
	}

	/**
	 * 判断是否为空
	 * resin与tomcat  对""传值解析有差异
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(StringUtils.isEmpty(str) || StringUtils.isEmpty(str.trim()))
			return true;
		return false;
	}
	
	/**
	 * 判断是否含有非空格的值
	 * @param str
	 * @return
	 */
	public static boolean isNotEmptyBlank(String str)
	{
		return StringUtils.isNotEmpty(str) && StringUtils.isNotBlank(str);
	}
	
	/**
	 * @param obj
	 * @return
	 */
	public static String valueOf(Object obj)
	{
		return (obj == null) ? "" : obj.toString();
	}

	public static String join(String... strs)
	{
		StringBuilder buf = new StringBuilder();

		if (strs == null)
			return buf.toString();

		for (String str : strs)
			if (!StringUtils.isEmpty(str))
				buf.append(str);

		return buf.toString();
	}

	/**
	 * 判断是否含有非空格的值
	 * @param str
	 * @return
	 */
	public static String objectToString(Object str)
	{
		String result="";
		if (str!=null) {
			result=str.toString();
		}
		return result;
	}

}
