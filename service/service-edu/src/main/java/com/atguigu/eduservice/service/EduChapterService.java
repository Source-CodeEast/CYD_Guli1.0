package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.Chapter.ChapterVo;
import com.atguigu.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-11-30
 */
public interface EduChapterService extends IService<EduChapter> {
    List<ChapterVo> nestedList(String courseId);
    boolean removeChapterById(String id);
}
