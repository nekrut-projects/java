package lesson_2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Если хотите сформировать корзину товаров введите 1, для выхода 0");
            int value = scanner.nextInt();
            if (value == 1) {
                Cart cart = context.getBean(Cart.class);
                while (true) {
                    System.out.println("Введите id товара, который вы хотите добавить в корзину(от 1 до 5), " +
                            "или 0 чтобы закончить заполнение корзины: ");
                    int id = scanner.nextInt();
                    if (id <= 5 && id > 0) {
                        cart.addProduct(id);
                    }
                    if (id == 0) {
                        break;
                    }
                }
                System.out.println("Товары в корзине: ");
                printContentsCart(cart);

                System.out.println("Если хотите удалить продукт(ы) из корзины введите 1: ");

                if (scanner.nextInt() == 1) {
                    while (true) {
                        printContentsCart(cart);
                        System.out.println("Введите id товара, который хотите удалить, или 0 для выхода: ");
                        int id = scanner.nextInt();
                        if (id == 0) {
                            break;
                        }
                        cart.deleteProductById(id);
                    }
                }
            } else if (value == 0){
                break;
            }
        }

        context.close();
    }

    public static void printContentsCart(Cart cart) {
        for (Product p : cart.getSelectedProducts()) {
            System.out.printf("\nProduct name: %s, \n\t id: %d\n\t price: %.2f\n",
                    p.getName(), p.getId(), p.getPrice());
        }
    }
}
