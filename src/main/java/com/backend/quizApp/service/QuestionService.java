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
    public List<Questions> getAllQuestions() {
        return questionsDao.findAll();
    }

    //endpoint 2
    public List<Questions> getQuestionsByCategory(String category) {
        return questionsDao.findByCategory(category);
    }

    //endpoint 3
    public String addQuestion(Questions question) {
        questionsDao.save(question);
        return "Question added successfully!";
    }

    //endpoint 4
    public String deleteQuestionNumber(int number) {
        questionsDao.deleteById(number);
        return "Question deleted successfully!";
    }

    //endpoint 5
    public Questions updateQuestion(int number, Questions updatedQuestion) {
        Questions existingQuestion = questionsDao.findById(number)
                .orElseThrow(() -> new RuntimeException("Question not found")); //Exception if question not found in db
        if (updatedQuestion.getQuestion_title() != null) {
            existingQuestion.setQuestion_title(updatedQuestion.getQuestion_title());
        }
        if(updatedQuestion.getOption1()!=null){
           existingQuestion.setOption1(updatedQuestion.getOption1());
        }
        if(updatedQuestion.getOption2()!=null){
            existingQuestion.setOption2(updatedQuestion.getOption2());
        }
        if(updatedQuestion.getOption3()!=null){
            existingQuestion.setOption3(updatedQuestion.getOption3());
        }
        if(updatedQuestion.getOption4()!=null){
            existingQuestion.setOption4(updatedQuestion.getOption4());
        }
        if(updatedQuestion.getRight_answer()!=null){
            existingQuestion.setRight_answer(updatedQuestion.getRight_answer());
        }
        if(updatedQuestion.getCategory()!=null){
            existingQuestion.setCategory(updatedQuestion.getCategory());
        }
        if(updatedQuestion.getDifficulty_level()!=null){
            existingQuestion.setDifficulty_level(updatedQuestion.getDifficulty_level());
        }

        return questionsDao.save(existingQuestion);

    }

    public Questions replaceQuestion(Questions updatedQuestion) {
        return questionsDao.save(updatedQuestion);
    }
}
