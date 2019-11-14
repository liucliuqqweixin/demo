package com.imooc.sell.controller;

import com.alibaba.excel.EasyExcelFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EasyExcelController {

    @RequestMapping("/readNoEntity")
    @ResponseBody
    public Map<String, Object> noEntity(MultipartFile excelFile) throws IOException {
        Map<String, Object> result = new HashMap<>();
//        List<Object> list = EasyExcel.read(excelFile.getInputStream()).sheet(0).doReadSync();
//        result.put("list", list);
        return result;
    }
}
