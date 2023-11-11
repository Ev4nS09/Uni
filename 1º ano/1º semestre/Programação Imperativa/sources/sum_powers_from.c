#include <stdio.h>
#include <math.h>

const char *author = "Afonso Rio";

double sum_powers_from(double x, double y, double n)
{
    double result;
    if (n <= 0)
        result = 0;
    else
        return pow(x, y) + sum_powers_from(x + 1, y, n - 1);
    return result;
}


void test_sum_powers_from(void)
{
    double x;
    double y;
    double n;
    while (scanf("%lf%lf%lf", &x, &y, &n) != EOF)
    {
      double z = sum_powers_from(x, y, n);
      printf("%f\n", z);
    }
}

int main(void)
{
    test_sum_powers_from();
    return 0;
}