package com.atguigu.eduservice.entity;

import java.util.ArrayList;
import java.util.List;

public class OneSubject {
        private String id;
        private String title;

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

        List<TwoSubject> children = new ArrayList<>();

        public List<TwoSubject> getChildren() {
        return children;
        }

        public void setChildren(List<TwoSubject> children) {
        this.children = children;
        }
}
