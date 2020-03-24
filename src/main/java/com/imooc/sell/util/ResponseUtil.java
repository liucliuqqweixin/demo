package com.imooc.sell.util;


import com.imooc.sell.common.exception.ServiceException;
import com.imooc.sell.enums.ResultEnum;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtil {
    public static ServletOutputStream getServletOutputStream(HttpServletResponse response) {
        response.setHeader("Content-disposition", "attachment; filename=" + "catagory.xls");
        response.setContentType("application/msexcel;charset=UTF-8");//设置类型
        response.setHeader("Pragma", "No-cache");//设置头
        response.setHeader("Cache-Control", "no-cache");//设置头
        response.setDateHeader("Expires", 0);//设置日期头
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
        } catch (IOException e) {
            throw new ServiceException(ResultEnum.EXCEL_EPORTEXCEL_ERROR);
        }
        return outputStream;
    }
}
