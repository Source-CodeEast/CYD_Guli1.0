package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.OneSubject;
import com.atguigu.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-11-28
 */
@Api(description = "课程分类管理")
@CrossOrigin
@RestController
@RequestMapping("/eduservice/edu-subject")
//public class EduSubjectController {
//
//    @Autowired
//    private EduSubjectService eduSubjectService;
//
//    //添加课程分类
//    @ApiOperation(value = "Excel批量导入")
//    @PostMapping("addSubject")
//    public R addSubject(MultipartFile file){
//        eduSubjectService.saveSubject(file);
////        eduSubjectService.importSubjectData(file,eduSubjectService);
//
//        //判断返回集合是否为空
//        return R.ok();
//    }
//}
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    //添加课程分类
    //获取上传过来文件，把文件内容读取出来
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file) {
        //上传过来excel文件
        subjectService.saveSubject(file,subjectService);
        return R.ok();
    }

    @GetMapping("getAllSubject")
    public R getAllSubject(){
        List<OneSubject> list = subjectService.getAllOneTwoSubject();
        return R.ok().data("list",list);
    }

}

