package com.quiz.quizapp;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    private final List<Question> questions = new ArrayList<>();

    public QuizService() {
        questions.add(new Question(1, "What is 2 + 2?", List.of("3", "4", "5", "6"), 1));
        questions.add(new Question(2, "Capital of France?", List.of("London", "Berlin", "Paris", "Madrid"), 2));
    }

    public List<Question> getAllQuestions() {
        return questions;
    }

    public boolean checkAnswer(int questionId, int answerIndex) {
        return questions.stream()
                .filter(q -> q.getId() == questionId)
                .findFirst()
                .map(q -> q.getCorrectAnswerIndex() == answerIndex)
                .orElse(false);
    }
}
