package com.xyhs.common.tools;


import com.xyhs.common.enumcode.ResultCode;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ljp
 * @date 9:56 2019/11/14
 * 执行结果信息类，用于封装增删改方法的结果信息 失败时必须将错误信息设置到errorMessages中
 **/

public class ExecuteResult<T> implements Serializable {


    private static final long serialVersionUID = -1398496212359882820L;
    /**
     * 执行成功返回结果集
     */
    private T result;
    /**
     * 执行成功结果信息
     */
    private String resultMessage;
    /**
     * 调用失败时，返回的错误集合
     */
    private List<String> errorMessages = new ArrayList<>();

    /**
     * 错误信息
     */
    private String errorMessage ;


    private int code;

    /**
     * 存入错误信息
     */
    @Deprecated
    public void addErrorMsg(String errorMsg) {
        errorMessages.add(errorMsg);
    }

    /**
     * 判断执行是否成功
     *
     * @return true:成功 false:失败
     */
    public boolean isSuccess() {
        return CollectionUtils.isEmpty(errorMessages) && StringUtils.isEmpty(errorMessage);
    }

    /**
     * 获取执行成功返回的结果集
     *
     * @return T 返回结果
     */
    public T getResult() {
        return result;
    }

    /**
     * 执行成功时，设置返回结果集
     *
     * @param result 结果集
     */
    public void setResult(T result) {
        this.result = result;
    }

    /**
     * 获取执行成功返回信息
     *
     * @return 返回信息
     */
    public String getResultMessage() {
        return resultMessage;
    }

    /**
     * 执行成功时，设置返回信息
     *
     * @param resultMessage 返回信息
     */
    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    /**
     * 获取错误信息列表
     *
     * @return 错误信息列表
     */
    public List<String> getErrorMessages() {
        return errorMessages;
    }

    /**
     * 设置错误信息
     *
     * @param errorMessages 错误信息列表
     */
    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    /**
     * 添加单个错误信息
     *
     * @param errorMessage 错误信息
     */
    public void addErrorMessage(String errorMessage) {
        errorMessages.add(errorMessage);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private static <T> ExecuteResult<T> initExecuteResult(T data, int code, String errorMessage,List<String> errorMessages,String resultMessage) {
        ExecuteResult<T> executeResult = new ExecuteResult<>();
        executeResult.setCode(code);
        executeResult.setResult(data);
        executeResult.setErrorMessage(errorMessage);
        executeResult.setErrorMessages(errorMessages);
        executeResult.setResultMessage(resultMessage);
        return executeResult;
    }

    public static <T>ExecuteResult<T> ok(T data){
       return initExecuteResult(data,ResultCode.SUCCESS.getCode(),null,null,ResultCode.SUCCESS.getMessage());
    }

    public static <T>ExecuteResult<T> ok(T data,int code,String resultMessage){
        return initExecuteResult(data,code,null,null,resultMessage);
    }

    public static <T>ExecuteResult<T> faield(int code,String errorMessage){
        return initExecuteResult(null,code,errorMessage, null,null);
    }
    public static <T>ExecuteResult<T> faield(){
        return initExecuteResult(null,ResultCode.INTERNAL_SERVER_ERROR.getCode(), ResultCode.INTERNAL_SERVER_ERROR.getMessage(),null,null);
    }

    public static <T>ExecuteResult<T> faield(int code,List<String> errorMessages){
        return initExecuteResult(null,code,null,errorMessages,null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
