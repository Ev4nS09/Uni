#include <stdio.h>

const char *author = "Afonso Rio";

double sum_squares_from(double x, double n)
{
	double result;
	if (n <= 0)
		result = 0;
	else
		return  x * x + sum_squares_from(x + 1, n - 1);
	return result;
}


void teste_sum_squares_from(void)
{
	double x;
	double n;
	while(scanf("%lf%lf", &x, &n) != EOF)
	{
		double z = sum_squares_from(x, n);
		printf("%f\n", z);
	}
}

int main(void)
{
	teste_sum_squares_from();
		return 0;
}