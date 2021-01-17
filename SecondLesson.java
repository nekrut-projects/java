import java.util.Arrays;

public class SecondLesson {
    public static void main(String[] args) {
        int[] arr1 = {1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0};
        replaceValuesInArray1To0And0To1(arr1);
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = new int[8];
        for (int i = 0, j = 0; i < arr2.length; i++, j += 3) {
            arr2[i] = j;
        }
        System.out.println(Arrays.toString(arr2));

        int[] arr3 = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
        for (int i = 0; i < arr3.length; i++) {
            if (arr3[i] < 6) {
                arr3[i] *= 2;
            }
        }
        System.out.println(Arrays.toString(arr3));

        int[][] arr4 = new  int[10][10];

        for (int i = 0; i < arr4.length; i++) {
            for (int j = 0; j < arr4[i].length; j++) {
                arr4[i][j] = i + j + 3;
            }
        }
        printTwoDimensionalArray(arr4);
        fillDiagonalsArrayWithOnes(arr4);
        System.out.println();
        printTwoDimensionalArray(arr4);

        findMaxAndMinElementArray(arr2);

        int[] arr5 = {2, 10, 6, 4, 11, 8, 10, 4, 8, 25, 14};
        System.out.println(checkBalance(arr5));

        int[] arr7 = {2, 10, 6, 4, 11, 8};
        shiftElementsArray(arr7, 2);
        System.out.println(Arrays.toString(arr7));

    }

    /*
    * Метод заменяет элементы массива 0 на 1, 1 на 0;
    * */
    public static void replaceValuesInArray1To0And0To1(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if(array[i] == 0) {
                array[i] = 1;
            } else if (array[i] == 1) {
                array[i] = 0;
            }
        }
    }

    /*
    * Метод заполняет диагональные элементы квадратного двумерного
    * целочисленного массивмассива единицами
    * */
    public static void fillDiagonalsArrayWithOnes (int[][] array) {
        for (int i = 0; i < array.length; i++) {
            if (array.length != array[i].length) {
                System.out.println("Этот массив не является квадратным");
                return;
            }
        }

        for (int y = 0; y < array.length; y++) {
            for (int x = 0; x < array.length; x++) {
                if (y == x || (y == (array.length - 1) - x)) {
                    array[y][x] = 1;
                }
            }
        }
    }

    public static void printTwoDimensionalArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " \t");
            }
            System.out.println();
        }
    }

    /*
    * Метод находит водномерном массиве минимальный и максимальный элементы и
    * выводит их в консоль
    * */
    public static void findMaxAndMinElementArray(int[] array) {
        int maxElement, minElement;
        maxElement = minElement = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > maxElement) {
                maxElement = array[i];
            } else {
                minElement = array[i];
            }
        }
        System.out.println("Максимальный элемент = " + maxElement +
                           "\nМинимальный элемент = " + minElement);
    }

    /*
    * Метод, в который передается не пустой одномерный целочисленный массив,
    * метод должен вернуть true, если в массиве есть место,
    * в котором сумма левой и правой части массива равны.
    * */
    public static boolean checkBalance(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) {
                break;
            } else if (i == array.length - 1) {
                System.out.println("Пустой одномерный целочисленный массив");
                return false;
            }
        }

        int leftPartArray = array[0];
        int rightPartArray = array[array.length - 1];
        int i = 0, j = array.length - 1;

        do {
            if (leftPartArray < rightPartArray) {
                i++;
                leftPartArray += array[i];
            } else if (leftPartArray > rightPartArray) {
                j--;
                rightPartArray += array[j];
            } else {
                i++;
                if (i != j) {
                    leftPartArray += array[i];
                } else {
//              ===================================================
//                    Вывод результата в консоль
//              ---------------------------------------------------
//                    for (int k = 0; k < array.length; k++) {
//                        if (k == 0) {
//                            System.out.print("Array [ ");
//                        }
//                        System.out.print(array[k]);
//                        if (k == array.length - 1) {
//                            System.out.print(" ]\n");
//                        } else {
//                            System.out.print(", ");
//                        }
//                        if (k == i - 1) {
//                            System.out.print("|| ");
//                        }
//                    }
//              =====================================================
                    return true;
                }
            }
        } while (i < j);
        return false;
    }

    /*
    * Метод, которому на вход подается одномерный массив и число 'n'
    * (может быть положительным, или отрицательным), при этом метод должен
    * сместить все элементы массива на n позиций. Элементы смещаются циклично.
    * */
    public static void shiftElementsArray(int[] array, int n) {
        int temp = 0;

        if (n < 0) {
            for (int i = 0; i > n; i--) {
                temp = array[0];
                for (int j = 1; j < array.length; j++) {
                    array[j - 1] = array[j];
                }
                array[array.length - 1] = temp;
            }
        } else if (n > 0) {
            for (int i = 0; i < n; i++) {
                temp = array[array.length - 1];
                for (int j = array.length - 2; j >= 0; j--) {
                    array[j + 1] = array[j];
                }
                array[0] = temp;
            }
        }
    }
}
