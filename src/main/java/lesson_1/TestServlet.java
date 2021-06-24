package lesson_1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] productsName = new String[]{"Стол", "Стул", "Диван", "Шкаф", "Кровать", "Тумба",
                                                "Стеллаж", "Пуф", "Банкетка", "Комод"};

        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<html><head><meta charset=\"utf-8\"><title>List product</title></head><body><table>");

        List<Product> products = new LinkedList<>();

        Random random = new Random();

        for (int i = 0; i < productsName.length; i++) {
            products.add(new Product(productsName[i], random.nextInt(1000), random.nextFloat()*10000));
        }

        for (Product p : products) {
            out.printf("<tr><td>%d</td><td>%s</td><td>%.2f</td></tr>", p.getId(),p.getTitle(), p.getCost());
        }

        out.println("</table></body></html>");
    }
}
