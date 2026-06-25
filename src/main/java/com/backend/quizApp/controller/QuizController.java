package com.backend.quizApp.controller;

import com.backend.quizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Quiz")
public class QuizController {

    @Autowired
    QuizService quizservice;
    @PostMapping ("create")
    public ResponseEntity<String> createquiz(@RequestParam String category,@RequestParam int numOfQues, @RequestParam String title){
        return quizservice.createQuiz(category,numOfQues,title);
    }
}
