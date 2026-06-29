package com.backend.quizApp.controller;

import com.backend.quizApp.model.QuestionWrapper;
import com.backend.quizApp.model.Questions;
import com.backend.quizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Quiz")
public class QuizController {

    @Autowired
    QuizService quizservice;

    @PostMapping("create")
    public ResponseEntity<String> createquiz(@RequestParam String category,@RequestParam int numOfQues, @RequestParam String title) {
        return quizservice.createQuiz(category, numOfQues, title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id){
        return quizservice.getQuizQuestions(id);
        }
}
