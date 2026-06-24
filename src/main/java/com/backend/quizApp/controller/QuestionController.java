package com.backend.quizApp.controller;
import com.backend.quizApp.Questions;
import com.backend.quizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Question")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @GetMapping("allQuestions")
    public List<Questions> getAllQuestions(){
        return questionService.getAllQuestions(); //uses getQuestion as service layer object where getAllQuestions is method name
    }


    @GetMapping("category/{category}")
    public List<Questions> getQuestionsByCategory(@PathVariable String category){  //@PathVariable ensures that the category passed in path is passed to the method
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public String addQuestion(@RequestBody Questions question) {
        return questionService.addQuestion(question);
    }

    @DeleteMapping("delete/{number}")
    public String deleteQuestion(@PathVariable int number){
        return questionService.deleteQuestionNumber(number);
    }

    @PatchMapping("update/{number}")  //Partial updates
    public Questions updateQuestion(@PathVariable int number,@RequestBody Questions updatedQuestion) {

        return questionService.updateQuestion(number, updatedQuestion);
    }

    @PutMapping("replace/{id}")
    public Questions replaceQuestion(@PathVariable int id,@RequestBody Questions updatedQuestion){
        updatedQuestion.setId(id);
        return questionService.replaceQuestion(updatedQuestion);
    }



}
