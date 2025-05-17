package com.quiz.quizapp;


import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    private final QuizService quizService;


    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/questions")
    public List<Question> getQuestions() {
        return quizService.getAllQuestions();
    }

    @PostMapping("/answer")
    public Map<String, Object> submitAnswer(@RequestBody Map<String, Integer> payload) {
        if (!payload.containsKey("questionId") || !payload.containsKey("answerIndex")) {
            throw new IllegalArgumentException("Payload must contain 'questionId' and 'answerIndex'");
        }

        int questionId = payload.get("questionId");
        int answerIndex = payload.get("answerIndex");
        boolean isCorrect = quizService.checkAnswer(questionId, answerIndex);

        return Map.of(
                "questionId", questionId,
                "correct", isCorrect
        );
    }
}
