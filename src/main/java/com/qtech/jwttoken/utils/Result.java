package com.qtech.jwttoken.utils;



/**
 * Copyright: (c) 2019
 * @Description: 返回结果封装实体
 * @author: 邹亚平
 * @date: 2019年6月6日  上午9:45:29
 */
 
public class Result
{
	public static final int CODE_SUCCESS = 20000;

	public static final int CODE_ERROR = 50000;

	public static final int TOKEN_ERROR = 50014;

	public static final String LEVEL_ERROR = "error";

	public static final String LEVEL_INFO = "info";

	public static final String LEVEL_DEBUG = "debug";

	public static final String LOAD_SUCCESS = "加载数据成功!";

	public static final String LOAD_ERROR = "加载数据失败!";

	public static final String OPERATE_SUCCESS = "操作成功!";

	public static final String OPERATE_FAIL = "操作失败!";

	private int code = 20000;

	// 消息提示级别
	private String level;
	// 消息内容
	private String message;
	// 相应数据
	private Object data;

	public Result()
	{

	}

	/**
	 * <pre>
	 * 		说明：数据加载类型：level=debug,操作类型:level=info,异常类型:level=error
	 * </pre>
	 * 
	 * @param success
	 * @param level
	 * @param message
	 * @param data
	 */
	public Result(int code, String level, String message, Object data)
	{
		super();
		this.code = code;
		this.level = level;
		this.message = message;
		this.data = data;
	}

	/**
	 * @Description: 创建Result对象
	 * @param success 是否成功
	 * @param level 信息级别
	 * @param message 提示信息
	 * @param data 数据
	 * @return Result
	 */
	public static Result newResult(int success, String level, String message, Object data)
	{
		return new Result(success, level, message, data);
	}

	/**
	 * @Description: 操作成功
	 * @param level 信息级别
	 * @param message 提示信息
	 * @param data 数据
	 * @return Result
	 */
	public static Result success(String level, String message, Object data)
	{
		return new Result(Result.CODE_SUCCESS, level, message, data);
	}

	/**
	 * @Description: 操作成功
	 * @param message 提示信息
	 * @param data 数据
	 * @return Result
	 */
	public static Result success(String message, Object data)
	{
		return new Result(Result.CODE_SUCCESS, Result.LEVEL_INFO, message, data);
	}

	/**
	 * @Description: 操作成功
	 * @param data 数据
	 * @return Result
	 */
	public static Result successWithData(Object data)
	{
		return new Result(Result.CODE_SUCCESS, Result.LEVEL_INFO, Result.LOAD_SUCCESS, data);
	}

	/**
	 * @Description: 操作成功
	 * @return Result
	 */
	public static Result success()
	{
		return new Result(Result.CODE_SUCCESS, Result.LEVEL_INFO, Result.LOAD_SUCCESS, null);
	}

	/**
	 * @Description: 操作成功
	 * @param message 提示信息
	 * @return Result
	 */
	public static Result successWithMsg(String message)
	{
		return new Result(Result.CODE_SUCCESS, Result.LEVEL_INFO, message, null);
	}

	/**
	 * @Description: 操作失败
	 * @param level 信息级别
	 * @param message 提示信息
	 * @param data 数据
	 * @return Result
	 */
	public static Result error(String level, String message, Object data)
	{
		return new Result(Result.CODE_ERROR, level, message, data);
	}

	/**
	 * @Description: 操作失败
	 * @param message 提示信息
	 * @param data 数据
	 * @return Result
	 */
	public static Result error(String message, Object data)
	{
		return new Result(Result.CODE_ERROR, Result.LEVEL_ERROR, message, data);
	}

	/**
	 * @Description: 操作失败
	 * @param data 数据
	 * @return Result
	 */
	public static Result error(Object data)
	{
		return new Result(Result.CODE_ERROR, Result.LEVEL_ERROR, Result.LOAD_ERROR, data);
	}

	/**
	 * @Description: 操作失败
	 * @return Result
	 */
	public static Result error()
	{
		return new Result(Result.CODE_ERROR, Result.LEVEL_ERROR, Result.LOAD_ERROR, null);
	}

	/**
	 * @Description: 操作失败
	 * @param String message 失败提示信息
	 * @return Result
	 */
	public static Result error(String message)
	{
		return new Result(Result.CODE_ERROR, Result.LEVEL_ERROR, message, null);
	}

	/**
	 * @Description: 操作失败
	 * @param String message 失败提示信息
	 * @return Result
	 */
	public static Result tokenError(String message)
	{
		return new Result(Result.TOKEN_ERROR, Result.LEVEL_ERROR, message, null);
	}

	public String getLevel()
	{
		return level;
	}

	public void setLevel(String level)
	{
		this.level = level;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public Object getData()
	{
		return data;
	}

	public void setData(Object data)
	{
		this.data = data;
	}

	public int getCode()
	{
		return code;
	}

	public void setCode(int code)
	{
		this.code = code;
	}

}
