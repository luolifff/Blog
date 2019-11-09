package com.llf.blog.web.admin;

import com.llf.blog.pojo.Tag;
import com.llf.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping("/tags")
    public String tags(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC)
                               Pageable pageable, Model model){
        model.addAttribute("page", tagService.listTag(pageable));
        return "/admin/tags";
    }

    @PostMapping("/tags")
    public String postTag(@Valid Tag tag, BindingResult result, RedirectAttributes attributes){
        Tag tag1 = tagService.getTagByName(tag.getName());
        if(tag1 != null){
            result.rejectValue("name", "nameError", "不能重复添加标签");
        }

        if(result.hasErrors()){
            return "admin/tags-input";
        }
        tag1 = tagService.saveTag(tag);
        if(tag1 == null){
            attributes.addFlashAttribute("message", "新增失败");
        }else{
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/tags";
    }

    @PostMapping("/tags/{id}")
    public String postEdit(@Valid Tag tag, BindingResult result,@PathVariable Long id , RedirectAttributes attributes){
        Tag tag1 = tagService.getTagByName(tag.getName());
        if(tag1 != null){
            result.rejectValue("name", "nameError", "标签名称没有变化");
        }

        if(result.hasErrors()){
            return "admin/tags-input";
        }
        tag1 = tagService.updateTag(id, tag);
        if(tag1 == null){
            attributes.addFlashAttribute("message", "更新失败");
        }else{
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("tag", tagService.getTag(id));
        return "admin/tags-input";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        tagService.delete(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }

}
