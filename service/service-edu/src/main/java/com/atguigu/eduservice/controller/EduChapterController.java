package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.Chapter.ChapterVo;
import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-11-30
 */
@CrossOrigin
@RestController
@Api(description="课程管理")
@RequestMapping("/eduservice/edu-chapter")
public class EduChapterController {
    @Autowired
    private EduChapterService eduChapterService;

    @ApiOperation(value = "嵌套章节数据列表")
    @GetMapping("nested-list/{courseId}")
    public R nestedListByCourseId(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId) {
        List<ChapterVo> chapterVoList = eduChapterService.nestedList(courseId);
        return R.ok().data("items", chapterVoList);
    }

    @ApiOperation(value = "新增章节")
    @PostMapping("addChapter")
    public R save(
            @ApiParam(name = "chapterVo", value = "章节对象", required = true)
            @RequestBody EduChapter eduChapter) {

        eduChapterService.save(eduChapter);
        return R.ok();

    }

    @ApiOperation(value = "根据id查询")
    @GetMapping("getChapterInfo/{chapterId}")
    public R getById(
            @ApiParam(name = "id", value = "章节Id", required = true)
            @PathVariable String id) {
        EduChapter eduChapter = eduChapterService.getById(id);
        return R.ok().data("item", eduChapter);
    }

    @ApiOperation(value = "根据ID修改章节")
    @PutMapping("updateChapter")
    public R updateById(
            @ApiParam(name = "id", value = "章节id", required = true)
            @PathVariable String id,
            @ApiParam(name = "chapter", value = "章节对象", required = true)
            @RequestBody EduChapter eduChapter
    ) {
        eduChapter.setId(id);
        eduChapterService.updateById(eduChapter);
        return R.ok();
    }

    @ApiOperation(value = "根据Id删除章节")
    @DeleteMapping("{chapterId}")
    public R deleteById(
            @ApiParam(name = "id", value = "章节ID", required = true)
            @PathVariable String id
    ) {

        //判断是否根据id删除了数据
        Boolean result = eduChapterService.removeById(id);
        if (result) {
            return R.ok();
        } else {
            return R.error();

        }
    }

}