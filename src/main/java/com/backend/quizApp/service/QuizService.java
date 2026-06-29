package com.backend.quizApp.service;

import com.backend.quizApp.dao.QuestionsDao;
import com.backend.quizApp.dao.QuizDao;
import com.backend.quizApp.model.QuestionWrapper;
import com.backend.quizApp.model.Questions;
import com.backend.quizApp.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Optional<Quiz> quiz=quizdao.findById(id);
        List<Questions> questionsFromDB=quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser= new ArrayList<>();

        for(Questions q: questionsFromDB){ //loop for converting questions to questionwrapper
            QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestion_title(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4(),q.getDifficulty_level());
            questionsForUser.add(qw);
        }

        return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
    }
}
