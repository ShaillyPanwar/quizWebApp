package com.backend.quizApp.service;

import com.backend.quizApp.Questions;
import com.backend.quizApp.dao.QuestionsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionsDao questionsDao; //object of DAO layer

    //endpoint 1
    public ResponseEntity<List<Questions>> getAllQuestions() {
        try{
            return new ResponseEntity<>(questionsDao.findAll(), HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    //endpoint 2
    public ResponseEntity<List<Questions>> getQuestionsByCategory(String category) {
        try{
            return new ResponseEntity<>(questionsDao.findByCategory(category), HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    //endpoint 3
    public ResponseEntity<String> addQuestion(Questions question) {
        try {
            questionsDao.save(question);
            return new ResponseEntity<>("Question added successfully!", HttpStatus.CREATED);
        }
        catch(Exception e){
            e.printStackTrace();
        }return new ResponseEntity<>("Error...", HttpStatus.BAD_REQUEST);
    }

    //endpoint 4
    public ResponseEntity<String> deleteQuestionNumber(int number) {
        try {
            questionsDao.deleteById(number);
            return new ResponseEntity<>("Question deleted successfully!", HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }return new ResponseEntity<>("Not found...", HttpStatus.BAD_REQUEST);
    }

    //endpoint 5
    public ResponseEntity<Questions> updateQuestion(int number, Questions updatedQuestion) {
        try{
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
            return new ResponseEntity<>(questionsDao.save(existingQuestion), HttpStatus.OK);
        }catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //endpoint 6
    public ResponseEntity<Questions> replaceQuestion(Questions updatedQuestion) {
       try{
           return new ResponseEntity<>(questionsDao.save(updatedQuestion),HttpStatus.OK);
       } catch (Exception e) {
           e.printStackTrace();
       }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
