#include <stdio.h>
#include <math.h>

double factorial(double x, int n)
{
double result = 0.0;
double term = 1.0;
for (int i = 0; i < n; i++)
{
result += term;
term *= x;
}
return result;
}

void test_factorial(void)
{
	int n;
	double x;
	while(scanf("%d%lf", &n, &x) != EOF)
	{
	double z = factorial(x, n);
	printf("%lf\n", z);
	}
}

int main(void)
{
	test_factorial();
	return 0; 
}