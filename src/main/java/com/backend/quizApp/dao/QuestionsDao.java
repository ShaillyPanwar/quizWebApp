package com.backend.quizApp.dao;

import com.backend.quizApp.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuestionsDao extends JpaRepository<Questions, Integer> {

    List<Questions> findByCategory(String category);  //JPA is smart enough to find category by itself, so we don't have to write any SQL ourself for data fetching

}
