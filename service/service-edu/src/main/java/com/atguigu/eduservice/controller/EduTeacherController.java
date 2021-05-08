package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.service.EduTeacherService;
import com.atguigu.servicebase.config.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-11-13
 */

@Api(description="讲师管理")
@RestController
@RequestMapping(value = "/eduservice/edu-teacher",method = {RequestMethod.POST})
@CrossOrigin
//@RequestMapping(value = “/PostRequest”, method = {RequestMethod.POST})
//@CrossOrigin(origins = "http://localhost:9528")
public class EduTeacherController {
    @Autowired
    private EduTeacherService teacherService;
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
//    @CrossOrigin
    //查询讲师表所有数据
    public R findAllTeacher(){
        List<EduTeacher> list=teacherService.list(null);
        return R.ok().data("items",list);
    }
    //讲师逻辑删除功能
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")
    public R removeById(@ApiParam(name = "id",value = "讲师ID",required = true) @PathVariable String id){
        boolean flag = teacherService.removeById(id);
        if (flag){
            return R.ok();
        }else {
           return R.error();
        }
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageList(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable Long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit){
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        teacherService.page(pageTeacher,null);
        long total = pageTeacher.getTotal();

        List<EduTeacher> records = pageTeacher.getRecords();

        return  R.ok().data("total", total).data("rows", records);
    }
    @ApiOperation(value = "分页讲师类表")

    @PostMapping("{current}/{limit}")
    public R pageQuery(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable Long current,

            @ApiParam(name = "limit", value = "每页记录 数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
                   @RequestBody(required = false) TeacherQuery teacherQuery){ // @RequestBody   使用json文件传递数据  需要使用post 而不能使用get
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        //多条件查询
        //判断值是否为空
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        //判断是否为空
        if(!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if (!ObjectUtils.isEmpty(level)){
            wrapper.eq("level",level);

        }
        if (!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            wrapper.le("end",end);
        }




        teacherService.page(pageTeacher, wrapper);
        List<EduTeacher> records = pageTeacher.getRecords();
        long total = pageTeacher.getTotal();

        return  R.ok().data("total", total).data("rows", records);
    }

    //添加讲师接口方法
    @PostMapping("addTeacher")
    public R save(@ApiParam(name = "teacher", value = "讲师对象", required = true)
                      @RequestBody EduTeacher eduTeacher){
        boolean save = teacherService.save(eduTeacher);
        if (save)
        { return R.ok();}
        else{
        return R.error();
        }


    }
    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("{id}")
    public R getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){

        //设施异常处理
//        try {
//            int a = 100/0;
//
//        }catch (Exception e){
//
//            //这里报错但却还可以运行，在我看来是一个@Data包的错误
//           // throw new GuliException(20001,"出现自定义异常");
//
//        }


        EduTeacher teacher = teacherService.getById(id);
        return R.ok().data("item", teacher);
    }
    @ApiOperation(value = "根据ID修改讲师")
    @PutMapping("{id}")
    public R updateById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id,

            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher eduTeacher){

        eduTeacher.setId(id);
        teacherService.updateById(eduTeacher);
        return R.ok();
    }

}

