package com.backend.quizApp.service;

import com.backend.quizApp.Questions;
import com.backend.quizApp.dao.QuestionsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionsDao questionsDao; //object of DAO layer

    //endpoint 1
    public List<Questions> getAllQuestions(){
        return questionsDao.findAll();
    }

    //endpoint 2
    public List<Questions> getQuestionsByCategory(String category) {
        return questionsDao.findByCategory(category);
    }

    //endpoint 3
    public String addQuestion(Questions question){
        questionsDao.save(question);
        return "Question added successfully!";
    }


    public String deleteQuestionNumber(int number) {
        questionsDao.deleteById(number);
        return "Question deleted successfully!";
    }

}
