package com.ruin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruin.domain.Soul;
import com.ruin.service.SoulService;
import com.ruin.util.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ruin
 * @date 2020/2/5-22:13
 */
@Controller
public class SoulController {

    @Autowired
    SoulService soulService;

    @GetMapping(value = {"index","/"})
    public String goIndex(@RequestParam(defaultValue = "1") Integer pageNum, Model model){
        PageInfo<Soul> pageInfo=soulService.findAll(pageNum);
        model.addAttribute("souls",pageInfo);
        return "index";
    }

    @GetMapping("soul/{id}")
    @ResponseBody
    public Soul getSoul(@PathVariable("id")Long id){
        return soulService.findById(id);
    }

    @DeleteMapping("soul/{id}")
    @ResponseBody
    public Integer delSoul(@PathVariable("id")Long id){
        soulService.deleteById(id);
        return 200;
    }

    @GetMapping("randomSoul")
    @ResponseBody
    public Soul getRandomSoul(){
        return soulService.randomSoul();
    }

    @GetMapping("appoint")
    public String goAppoint(Model model){
        model.addAttribute("imgs", Storage.souls);
        return "appoint";
    }

    @GetMapping("designated")
    public String goDesignated(String name,Model model){
        model.addAttribute("name",name);
        return "designated";
    }

    @GetMapping("designatedExchange")
    @ResponseBody
    public Soul getDesignatedSoul(String name,Integer pos){
        return soulService.designatedSoul(name,pos);
    }

    @GetMapping("enhance")
    public String goEhance(Long id,Model model){
        model.addAttribute("soul",soulService.findById(id));
        return "enhance";
    }

    @GetMapping("enhanceSoul")
    public String enhanceSoul(Long id,Integer targetLevel,Model model){

        Soul soul = soulService.enhanceSoul(id, targetLevel);
        model.addAttribute("soul",soul);
        return "enhance";
    }
}
