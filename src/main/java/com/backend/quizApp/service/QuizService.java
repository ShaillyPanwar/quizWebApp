package com.backend.quizApp.service;

import com.backend.quizApp.dao.QuestionsDao;
import com.backend.quizApp.dao.QuizDao;
import com.backend.quizApp.model.Questions;
import com.backend.quizApp.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {
    @Autowired
    QuizDao quizdao;
    @Autowired
    QuestionsDao questionsdao;

    public ResponseEntity<String> createQuiz(String category, int numOfQues, String title) {

        List<Questions> questions=questionsdao.findRandomQuestionsByCategory(category,numOfQues);

        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizdao.save(quiz);
        return new ResponseEntity<>("Successfully created quiz!", HttpStatus.valueOf(200));
    }
}
