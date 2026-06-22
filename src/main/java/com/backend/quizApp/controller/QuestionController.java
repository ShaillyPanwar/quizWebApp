package com.backend.quizApp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Question")
public class QuestionController {
    @GetMapping("allQuestions")
    public String getAllQuestions(){
        return questionService.getAllQuestions; //uses getQuestion as service layer object where getAllQuestions is method name
    }
}
