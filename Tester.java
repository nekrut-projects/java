package lesson7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

public class Tester {
    private static Method beforeAnnotMethod;
    private static Method afterAnnotMethod;
    private static ArrayList<Method> testAnnotMethods;

    static {
        testAnnotMethods = new ArrayList<>();
    }

    public static void start(String className) {
        try {
            start(Class.forName(className));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void start(Class testClass) {
        Method[] methods = testClass.getDeclaredMethods();
        int countBeforeAnnotMethods = 0;
        int countAfterAnnotMethods = 0;
        for (Method m : methods) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                countBeforeAnnotMethods++;
                if (countBeforeAnnotMethods > 1) throw new RuntimeException();
                beforeAnnotMethod = m;

            } else if (m.isAnnotationPresent(AfterSuite.class)) {
                countBeforeAnnotMethods++;
                if (countAfterAnnotMethods > 1) throw new RuntimeException();
                afterAnnotMethod = m;

            } else if (m.isAnnotationPresent(Test.class)) {
                testAnnotMethods.add(m);
            }
        }

        if (countBeforeAnnotMethods > 0) {
            try {
                beforeAnnotMethod.invoke(createInstance(testClass));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        testAnnotMethods.sort(new Comparator<Method>() {
            @Override
            public int compare(Method o1, Method o2) {
                return o2.getAnnotation(Test.class).priority() - o1.getAnnotation(Test.class).priority();
            }
        });

        for (Method method : testAnnotMethods) {
            try {
                method.setAccessible(true);
                method.invoke(createInstance(testClass));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        if (countAfterAnnotMethods > 0) {
            try {
                afterAnnotMethod.invoke(createInstance(testClass));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public static Object createInstance(Class testClass) {
        Object newInstance = null;
        try {
            newInstance = testClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return newInstance;
    }
}
