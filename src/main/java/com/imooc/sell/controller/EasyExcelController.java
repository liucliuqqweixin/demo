package com.imooc.sell.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.imooc.sell.entity.User;
import com.imooc.sell.mapper.UserMapper;
import com.imooc.sell.util.ExcelUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EasyExcelController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/readNoEntity")
    @ResponseBody
    public Map<String, Object> noEntity(@RequestParam("file") MultipartFile excelFile) throws IOException {
        Map<String, Object> result = new HashMap<>();
        List<User> users = ExcelUtil.readExcelWithModel(excelFile.getInputStream(), User.class, ExcelTypeEnum.XLSX);
        System.out.println(users);
        userMapper.insertBach(users);
//        List<Object> list = EasyExcel.read(excelFile.getInputStream()).sheet(0).doReadSync();
//        result.put("list", list);
        return result;
    }

    @RequestMapping("/eportExcel")
    @ResponseBody
    public void eportExcel(HttpServletResponse response) throws IOException {
        response.setHeader("Content-disposition", "attachment; filename=" + "catagory.xls");
        response.setContentType("application/msexcel;charset=UTF-8");//设置类型
        response.setHeader("Pragma", "No-cache");//设置头
        response.setHeader("Cache-Control", "no-cache");//设置头
        response.setDateHeader("Expires", 0);//设置日期头
        ServletOutputStream outputStream = response.getOutputStream();
        List<User> users = userMapper.seletListUser();
        ExcelUtil.writeExcelWithModel(outputStream, users, User.class, ExcelTypeEnum.XLSX);

//        return result;
    }


}
