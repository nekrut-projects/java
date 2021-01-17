public class FirstLesson {
        public static void main(String[] args) {
            byte b = 1;
            short s = 2;
            int i = 3;
            long l = 23L;

            float f = 3.5f;
            double d = 5.6;

            boolean bl = true;

            char c = 'c';
            String str = "Строка";

            System.out.println(calculationExpression(1.2f, 2.2f, 5f, 2.4f));
            System.out.println(isSumNumbersInRange(5, 10, 10, 20));
            isPositiveOrNegativeNumber(-5);
            System.out.println(isNegativeNumber(0));
            printName("Name");
            isLeapYear(84);
        }

        /*
         * Метод, вычисляющий выражение a * (b + (c / d)) и возвращающий результат
         */
        public static float calculationExpression(float a, float b, float c, float d) {
            return a * (b + (c / d));
        }

        /*
         * Метод, принимающий на вход два целых числа и проверяющий, что их сумма лежит в пределах
         */
        public static boolean isSumNumbersInRange(int firstNumber, int secondNumber,
                                                  int lowerLimitRange, int upperLimitRange) {

            int sumNumbers = firstNumber + secondNumber;

            if (sumNumbers >= lowerLimitRange && sumNumbers <= upperLimitRange) {
                return true;
            } else {
                return false;
            }
        }

        /*
         * Метод, которому в качестве параметра передается целое число,
         * метод должен напечатать в консоль, положительное ли число передали или отрицательное.
         */
        public static void isPositiveOrNegativeNumber(int number) {
            if (number >= 0) {
                System.out.println("Число " + number + " - положительное.");
            } else {
                System.out.println("Число " + number + " - отрицательное.");
            }
        }

        /*
         * Метод, которому в качестве параметра передается целое число.
         * Метод должен вернуть true, если число отрицательное, и вернуть false если положительное.
         */
        public static boolean isNegativeNumber(int number) {
            return number < 0;
        }

        /*
         * Метод, которому в качестве параметра передается строка, обозначающая имя.
         * Метод должен вывести в консоль указанное имя.
         * */
        public static void printName(String name) {
            System.out.println("Привет, " + name + "!");
        }

        /*
         * Метод, который определяет, является ли год високосным, и выводит сообщение в консоль
         * */
        public static void isLeapYear(int year) {
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                System.out.println(year + " год является високосным.");
            } else {
                System.out.println(year + " год является невисокосным.");
            }
        }
    }


