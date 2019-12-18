package com.github.fb010001.fbfsystem.controller;

import com.github.fb010001.fbfsystem.controller.models.dictionary.Dictionary4Get;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 字典
 */
@Controller
@RequestMapping("/dictionary")
public class DictionaryController {

    @GetMapping()
    public List<Dictionary4Get> getDictionaryList(){

        return null;
    }

    @GetMapping("/{dictionaryId}")
    public Dictionary4Get getDictionaryById(){

        return null;
    }
}
