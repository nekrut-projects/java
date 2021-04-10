package Lesson_7;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.logging.*;

public class CheckDZ {
    private static Logger logger;
    static {
        try {
            logger = Logger.getLogger(Lesson_7.CheckDZ.class.getName());
            Handler fileHandler = new FileHandler("checkDZ.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        ArrayList<Class> listTestFiles = getFilesFromFolder(args[0]);
        ArrayList<Class> listTestFiles = getFilesFromFolder("testFiles");

        for (Class file : listTestFiles) {
            logger.log(Level.INFO, "Проверяется файл - " + file.getSimpleName());

            Method[] methods = file.getDeclaredMethods();
            boolean result = false;

            for (Method method : methods) {
                if (!void.class.equals(method.getReturnType())) {
                    try {
                        Object instanceClass = file.getConstructor().newInstance();
                        method.setAccessible(true);

                        switch (method.getParameterCount()) {
                          /* Написать метод, которому в качестве параметра передается целое число.
                             Метод должен вернуть true, если число отрицательное, и вернуть false если положительное. */
                            case 1:
                                logger.log(Level.INFO, "Проверяется метод: " + method.getName());
                                result = test (true, (boolean)method.invoke(instanceClass, -5));
                                break;
                          /* Написать метод, принимающий на вход два целых числа и проверяющий,
                             что их сумма лежит в пределах от 10 до 20 (включительно),
                             если да – вернуть true, в противном случае – false. */
                            case 2:
                                logger.log(Level.INFO, "Проверяется метод: " + method.getName());
                                result = test(false,
                                        (boolean)method.invoke(instanceClass,8, 13));
                                break;
                          /* Написать метод, вычисляющий выражение a * (b + (c / d)) и возвращающий результат,
                             где a, b, c, d – аргументы этого метода, имеющие тип float. */
                            case 4:
                                logger.log(Level.INFO, "Проверяется метод: " + method.getName());
                                result = test(3.0f,
                                        (float) method.invoke(instanceClass, 1.5f, 1.0f, 3.0f, 3.0f));
                                break;
                        }
                        if (!result) {
                            logger.log(Level.WARNING, "Ожидаемый результат не совпадает с результатом пользователя!");
                        }
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
            }
            logger.log(Level.INFO, "Файл проверен!");
        }
    }
    private static ArrayList<Class> getFilesFromFolder(String pathFolder) {
        ArrayList<Class> listTestFiles = new ArrayList<>();
        File file = new File(pathFolder);
        String[] namesFilesInFolder = file.list();

        for (String nameFile : namesFilesInFolder) {

            String[] mass = nameFile.split("\\.");
            if (!mass[1].equalsIgnoreCase("class")) {
                throw new RuntimeException(nameFile, new Exception());
            }
            try {
                listTestFiles.add(URLClassLoader.newInstance(new URL[]{file.toURL()}).loadClass(mass[0]));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return listTestFiles;
    }

    private static boolean test(boolean expectedResult, boolean userFunctionResult) {
        return expectedResult == userFunctionResult ? true : false;
    }

    private static boolean test(float expectedResult, float userFunctionResult) {
        return Math.abs(expectedResult - userFunctionResult) < 0.000001f;
    }
}
