public class laba1 {

    static int[] list = new int[] {2, 3, 5, 6, 12, 13, 14, 17}; //инициализация массива для фильтрации по значениям c[i]
    public static boolean f(int n) {
        for (int i = 0; i < 8; ++i) {
            if (list[i] == n) return true; //возвращаем истину, если встречаем значение C[i][j] (в рамках функции - n) в массиве
        }
        return false; //возвращаем ложь, если значение не встретилось в массиве
    }

    public static double res1(double n) {
        return Math.cos((2.0/3) / (Math.cbrt(n) - 3.0/4));
    }

    public static double res2(double n) {
        double ans = n;
        ans = Math.tan(ans);
        ans = ans * ans;
        ans = Math.log(ans);
        ans = Math.pow(Math.E, ans);
        return ans;
    }

    public static double res3(double n) {
        double ans = n;
        ans = Math.abs(ans);
        ans = Math.pow(ans, n);
        ans = Math.log(ans);
        ans = Math.tan(ans);
        ans = ans * ans;
        ans = Math.log(ans);
        ans = Math.sin(ans);
        ans = Math.atan(ans);
        return ans;
    }
        public static void main (String[] args) {
                int[] c = new int[18]; //декларация массива c
                for (int i = 18; i >= 2; --i) c[18 - i] = i; //заполнение массива С числами от 2 до 18 в порядке убыванич
                double[] x = new double[16]; // декларация массива x
                for (int i = 0; i < 16; ++i) x[i] = Math.random() * 24 - 11; //масштабируем диапазон вещественных с [0; 1] до [0; 23] и сдвигаем на 11 влево
                double[][] C = new double[17][16]; //декларация массива С
                for (int i = 0; i < 17; ++i) {
                    for (int j = 0; j < 16; ++j) {
                        if (c[i] == 4) C[i][j] = res1(x[j]); //запускаем функцию расчёта значения для первой ветви условия
                    else if (f(c[i])) C[i][j] = res2(x[j]); //запускаем функцию расчёта значения для второй ветви условия
                    else C[i][j] = res3(x[j]); //запускаем функцию расчёта значения для третьей ветви условия
                    }
                }
                /*-
                *
                * hello
                */
                for (int i = 0; i < 17; ++i) {
                    for (int j = 0; j < 16; ++j) {
                        System.out.printf("%.5f", C[i][j]); //выводим каждый элемент массива с точностью 5 символов после запятой
                        System.out.print(" "); //выводим разделяющий пробел
                    }
                    System.out.print('\n'); //переходим на новую строку
                }
        }
}