package cn.lucode.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yunfeng.lu
 * @create 2017/10/26.
 */
public class CommonResponseModel {
    Map data;

    public CommonResponseModel(ReturnCodeModel code, Map data) {
        if(data==null){
            data=new HashMap();
        }
        data.put("errCode", code.getCode());
        data.put("errMsg", code.getMsg());
        this.data = data;
    }

    public CommonResponseModel(String code, String msg,Map data) {
        if(data==null){
            data=new HashMap();
        }
        data.put("errCode", code);
        data.put("errMsg", msg);
        this.data = data;
    }

    public Map getData() {
        return data;
    }

    public static Map facade(ReturnCodeModel code, Map data) {
        return new CommonResponseModel(code, data).getData();
    }

    public static Map facade(ReturnCodeModel code) {
        return new CommonResponseModel(code, null).getData();
    }

    public static Map facade(String code,String msg) {
        return new CommonResponseModel(code,msg,null).getData();
    }

    public static Map success(Map data) {
        return new CommonResponseModel(ReturnCodeModel.SUCCESS, data).getData();
    }

    public static Map success() {
        return new CommonResponseModel(ReturnCodeModel.SUCCESS, null).getData();
    }

    public static Map fail(Map data) {
        return new CommonResponseModel(ReturnCodeModel.FAIL, data).getData();
    }

    public static Map fail() {
        return new CommonResponseModel(ReturnCodeModel.FAIL, null).getData();
    }

    @Override
    public String toString() {
        return data.toString();
    }

}
