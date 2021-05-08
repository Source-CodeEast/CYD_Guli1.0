package com.atguigu.eduservice.entity.Chapter;

import java.util.ArrayList;
import java.util.List;

public class ChapterVo {

    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private List<VideoVo> children = new ArrayList<>();

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<VideoVo> getChildren() {
        return children;
    }

    public void setChildren(List<VideoVo> children) {
        this.children = children;
    }
}
