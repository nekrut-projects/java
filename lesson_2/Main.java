package lesson_2;

public class Main {
    public static void main(String[] args) {
        String[][] array = {
                {"25", "62", "12", "0"},
                {"10", "62", "4", "6"},
                {"25", "8", "1", "5"},
                {"5", "62", "12", "7"}
        };

        int sum = 0;

        try {
            sum = sumItemSquareArray(array, 4);
        } catch (MyArraySizeException e) {
            e.printStackTrace();
            return;
        } catch (MyArrayDataException e) {
            e.printStackTrace();
            return;
        }
        System.out.println(sum);

    }
    public static int sumItemSquareArray(String [][] array, int size) throws MyArraySizeException, MyArrayDataException {
        if (array.length == size) {
            for (int i = 0; i < size; i++) {
                if (array[i].length != size) {
                    throw new MyArraySizeException("Количество столбцов в массиве не равно " + size +
                            ". " + i + " строка, содержит массив из " + array[i].length + " элементов.");
                }
            }
        } else {
            throw new MyArraySizeException("Количество строк в массиве не равно " + size);
        }

        int sum = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    sum = 0;
                    throw new MyArrayDataException("Недопустимое значение в ячейке["+ i + "][" + j + "]", j, i);
                }
            }
        }
        return sum;
    }
}
