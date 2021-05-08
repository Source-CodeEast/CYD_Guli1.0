package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduCourseDescription;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.mapper.EduCourseMapper;
import com.atguigu.eduservice.service.EduCourseDescriptionService;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.servicebase.config.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-11-30
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //1.向课程表添加课程数据基本信息
        EduCourse eduCourse = new EduCourse();
        //get set
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        eduCourse.setGmtCreate(courseInfoVo.getGmtCreate());
        eduCourse.setGmtModified(courseInfoVo.getGmtModified());
        int insert = baseMapper.insert(eduCourse);
        if (insert==0){
            throw new GuliException(20001,"添加课程信息失败");
        }

        //2.获取添加之后课程id,之后向课程简介表添加课程简介
        String cid = eduCourse.getId();
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());

        //3.设置课程简介表添加课程简介
        eduCourseDescription.setId(cid);
        eduCourseDescriptionService.save(eduCourseDescription);

        //
        return cid;
    }
}
