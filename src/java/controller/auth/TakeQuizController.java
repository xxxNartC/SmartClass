package controller.auth;

import dao.QuizDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.AnswerDetail;
import model.Question;
import model.Quiz;
import model.QuizResult;

@WebServlet(name = "TakeQuizController", urlPatterns = {"/takequiz"})
public class TakeQuizController extends HttpServlet {

    static List<AnswerDetail> answerDetail = new ArrayList<>();

    // list chua question 
    static void addToAnswerDetail(List<Question> listQ) {
        for (Question q : listQ) {
            AnswerDetail ans = new AnswerDetail(q.getQuestion_id(),
                    q.getContent(),
                    q.getOption1(),
                    q.getOption2(),
                    q.getOption3(),
                    q.getOption4(),
                    q.getAnswer(),
                    q.getSubject_id(),
                    q.getQuiz_id(),
                    q.getLevel(),
                    null,
                    null);
            answerDetail.add(ans);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        QuizDAO qd = new QuizDAO();

        HttpSession session = request.getSession();
        String account_id = String.valueOf(session.getAttribute("account_id"));
        String learner_id = account_id;
        request.setAttribute("learner_id", learner_id);

        String chapter_id = request.getParameter("chapter_id");
        request.setAttribute("chapter_id", chapter_id);

        String subject_id = request.getParameter("subject_id");
        request.setAttribute("subject_id", subject_id);

        String course_id = request.getParameter("course_id");
        request.setAttribute("course_id", course_id);

        String quiz_id = request.getParameter("quiz_id");

        if (quiz_id == null || learner_id == null || chapter_id == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing quiz or learner information.");
            return;
        }

        Quiz quiz = qd.getQuizByChapterID(chapter_id);
        request.setAttribute("quiz_id", quiz.getQuiz_id());
        request.setAttribute("quiz_name", quiz.getName());

        if (quiz == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Quiz not found for this chapter.");
            return;
        }

        int noq;
        try {
            noq = Integer.parseInt(quiz.getNo_question());
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Invalid number of questions.");
            return;
        }

        // list random question for quiz
        List<Question> listQ = qd.getRandomQuestion(chapter_id, noq);
        // add vao list tam thoi
        answerDetail.clear();
        addToAnswerDetail(listQ);
        String listAnswer = "";
        String listQuestionId = "";
        for (Question q : listQ) {
            listQuestionId += ("," + q.getQuestion_id());
            listAnswer += ("," + q.getAnswer());
        }

        request.setAttribute("listQuestionId", listQuestionId);
        request.setAttribute("listAnswer", listAnswer);
        request.setAttribute("listQ", listQ);
        request.getRequestDispatcher("takequiz.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // dopost xu li ket qua cua bai quiz
        QuizDAO qd = new QuizDAO();
        // lay list question tu bai quiz 
        String listQuestionId_raw = request.getParameter("listQuestionId").substring(1);
        String[] listQuestionId = listQuestionId_raw.split(",");
        // lay list cau tra loi tu bai quiz
        String listAnswer_raw = request.getParameter("listAnswer").substring(1);
        String[] listAnswer = listAnswer_raw.split(",");

        String listAnswerByLearner_raw = "";
        // lay list cau tra loi cua learner
        for (String question : listQuestionId) {
            listAnswerByLearner_raw += ("," + request.getParameter("question-" + question));
        }
        listAnswerByLearner_raw = listAnswerByLearner_raw.substring(1);
        String[] listAnswerByLearner = listAnswerByLearner_raw.split(",");

        int count = 0;
        // Mark
        for (int i = 0; i < listAnswer.length; i++) {

            answerDetail.get(i).setLearnerAns(listAnswerByLearner[i]);

            if (listAnswerByLearner[i].equalsIgnoreCase(listAnswer[i])) {
                answerDetail.get(i).setIsTrue("true");
                count++;
            } else {
                answerDetail.get(i).setIsTrue("false");
            }
        }
        double mark_raw = count * 10 / listQuestionId.length;

        BigDecimal bd = new BigDecimal(Double.toString(mark_raw));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        double mark = bd.doubleValue();

        request.setAttribute("listQ", answerDetail);
        request.setAttribute("mark", mark);

        String quiz_id = request.getParameter("quiz_id");
        String learner_id = request.getParameter("learner_id");

        // Xu ly lam lai quiz
        QuizResult result = qd.getQuizResult(quiz_id, learner_id);
        if (result != null) {

            if (result.getStatus().equals("0")) {
                qd.updateQuizResult(mark + "", mark >= 5 ? "1" : "0", result.getId());

            }

        } else {
            // Insert result to database             
            qd.insertQuizResult(learner_id,
                    quiz_id,
                    mark + "",
                    mark >= 5 ? "1" : "0");
        }

        String subject_id = request.getParameter("subject_id");
        request.setAttribute("subject_id", subject_id);

        String course_id = request.getParameter("course_id");
        request.setAttribute("course_id", course_id);

        String chapter_id = request.getParameter("chapter_id");
        request.setAttribute("chapter_id", chapter_id);
        request.getRequestDispatcher("quizresult.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Handles quiz-taking functionality";
    }
}
