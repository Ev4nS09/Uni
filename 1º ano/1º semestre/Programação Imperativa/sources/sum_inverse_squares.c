#include <stdio.h>
#include <math.h>

const char *author = "Afonso Rio";

double sum_powers_from(double x, double y, double n)
{
    double result;
    if (n <= 0)
        result = 0;
    else
    return  pow(x, y) + sum_powers_from(x +1 , y, n - 1);
     return result;
}



double sum_inverse_squares(double n)
{
    double x = 1;
    double y = -2;
    double result;
    if (n <= 0)
        result = 0;
    else
        return sum_powers_from(x, y, n);
    return result;
}


void test_sum_inverse_squares(void)
{
    double n;
    while (scanf("%lf", &n) != EOF)
    {
      double z = sum_inverse_squares(n);
      printf("%f\n", z);
    }
}

int main(void)
{
    test_sum_inverse_squares();
    return 0;
}