package controller.common;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

@WebServlet("/GeminiChatbot")
public class GeminiChatbot1 extends HttpServlet {

    private static final String API_KEY = "AIzaSyD6JHKApC5p-ZWl7FxaJVZF8_QyxOW5Zdc";
    private static final String API_ENDPOINT = "https://generativelanguage.googleapis.com/v1/models/gemini-1.5-pro:generateContent?key=" + API_KEY;
    private static final int MAX_HISTORY = 5; // Giới hạn số câu hỏi trước đó.
    private static final Queue<String> conversationHistory = new LinkedList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle GET request directly, no need to redirect
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy câu hỏi từ request
        String userInput = request.getParameter("question");

        if (userInput == null || userInput.trim().isEmpty()) {
            request.setAttribute("error", "Question parameter is missing or empty.");
            request.getRequestDispatcher("chatbot.jsp").forward(request, response);
            return;
        }

        // Lưu câu hỏi của người dùng vào lịch sử
        addMessageToHistory("User: " + userInput);

        // Tạo context từ lịch sử hội thoại
        String context = String.join("\n", conversationHistory);
        String botResponse = getChatbotResponse(context);

        // Lưu câu trả lời của mô hình vào lịch sử
        addMessageToHistory("Model: " + botResponse);

        // Gửi câu trả lời và lịch sử hội thoại đến JSP
        request.setAttribute("userQuestion", userInput);
        request.setAttribute("botResponse", botResponse);
        request.setAttribute("conversationHistory", conversationHistory);

        // Chuyển tiếp yêu cầu và dữ liệu đến `chatbot.jsp` để hiển thị
        request.getRequestDispatcher("chatbot.jsp").forward(request, response);
    }

    private static void addMessageToHistory(String message) {
        if (conversationHistory.size() >= MAX_HISTORY) {
            conversationHistory.poll(); // Xóa câu cũ nhất nếu vượt quá giới hạn.
        }
        conversationHistory.add(message);
    }

    private static String getChatbotResponse(String context) {
        // Chỉ thoát ký tự " để tránh lỗi JSON, không thoát \n
        String escapedContext = context.replace("\"", "\\\"");

        String jsonInput = """
    {
      "contents": [
        {
          "role": "user",
          "parts": [
            {
              "text": "%s"
            }
          ]
        }
      ]
    }
    """.formatted(escapedContext);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_ENDPOINT))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonInput))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return parseResponse(response.body());
            } else {
                return "Error: " + response.statusCode() + " - " + response.body();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    private static String parseResponse(String responseBody) {
        // Sử dụng Gson để phân tích JSON từ phản hồi của API
        Gson gson = new Gson();
        try {
            JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);

            // Truy cập vào phần "candidates" và lấy nội dung trả lời của mô hình
            JsonArray candidates = jsonObject.getAsJsonArray("candidates");
            if (candidates != null && candidates.size() > 0) {
                JsonObject firstCandidate = candidates.get(0).getAsJsonObject();
                JsonObject content = firstCandidate.getAsJsonObject("content");
                JsonArray parts = content.getAsJsonArray("parts");
                if (parts != null && parts.size() > 0) {
                    // Lấy nội dung từ phần đầu tiên của "parts"
                    String text = parts.get(0).getAsJsonObject().get("text").getAsString();
                    return text; // Trả về chỉ phần văn bản của mô hình
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error parsing response.";
    }

    public static String generateReport(int orderVolumeToday, int orderVolumeYesterday, Map<String, Integer> orderVolumeByMonth,
            Map<String, Map<Integer, Integer>> userTrends, Map<String, Map<Integer, Integer>> userTrendsByMonth,
            Map<String, Double> totalRevenueLast4Months, Map<String, Double> totalRevenueByYear) {
        // Prepare the report content for GeminiChatbot
        StringBuilder reportContent = new StringBuilder();
        reportContent.append("## Order Analysis Report\n\n");
        reportContent.append("**Order Volume:**\n");
        reportContent.append("* Today: ").append(orderVolumeToday).append("\n");
        reportContent.append("* Yesterday: ").append(orderVolumeYesterday).append("\n\n");

        reportContent.append("**Order Volume by Month:**\n");
        for (Map.Entry<String, Integer> entry : orderVolumeByMonth.entrySet()) {
            reportContent.append("* Month ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        reportContent.append("\n");

        reportContent.append("**User Trends (Last 4 Months):**\n");
        for (Map.Entry<String, Map<Integer, Integer>> entry : userTrendsByMonth.entrySet()) {
            reportContent.append("* **").append(entry.getKey()).append("**: \n");
            for (Map.Entry<Integer, Integer> trendEntry : entry.getValue().entrySet()) {
                reportContent.append("  * Month ").append(trendEntry.getKey()).append(": ").append(trendEntry.getValue()).append("\n");
            }
            reportContent.append("\n");
        }

        reportContent.append("**Total Revenue (Last 4 Months):**\n");
        for (Map.Entry<String, Double> entry : totalRevenueLast4Months.entrySet()) {
            reportContent.append("* ").append(entry.getKey()).append(": $").append(entry.getValue()).append("\n");
        }
        reportContent.append("\n");

        reportContent.append("**Total Revenue (Current Year):**\n");
        for (Map.Entry<String, Double> entry : totalRevenueByYear.entrySet()) {
            reportContent.append("* Month ").append(entry.getKey()).append(": $").append(entry.getValue()).append("\n");
        }

        // Send the report content to GeminiChatbot for analysis and formatting
        String report = getChatbotResponse(
                "Generate a comprehensive analysis report based on the following data. "
                + "The report should include these sections:\n"
                + "1. Summary of key metrics.\n"
                + "2. Comparison of current and previous periods (e.g., daily, monthly, quarterly).\n"
                + "3. Insights on user trends and behavior over the past week and four months.\n"
                + "4. Total revenue analysis for recent months and the current year.\n"
                + "5. Key findings and notable patterns or anomalies.\n\n"
                + "In addition to the analysis, please provide:\n"
                + "6. A conclusion summarizing the overall performance and trends.\n"
                + "7. Actionable recommendations based on the data, including any suggestions for improvement or potential areas of opportunity.\n\n"
                + "Data provided:\n"
                + reportContent.toString()
                + "\n\nPlease ensure the report is well-structured, clear, and insightful."
        );

        return report;
    }
}
