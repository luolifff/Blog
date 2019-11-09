package com.llf.blog.pojo;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_tag")
public class Tag {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "标签名称不能为空")
    private String name;    //标签名

    @ManyToMany(mappedBy = "tags")
    private List<Blog> blogs = new ArrayList<>();   //博客列表

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
