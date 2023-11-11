#include <stdio.h>
#include <assert.h>
#include "our_doubles.h"

double soma(double *a, double n)
{
	int result = 0;
	for(int i = 0; i < n; i++)
	{
		if((a[i] + a[i+1]) > 0)
			result++;
	}
	return result;
}

void test_soma()
{
	double a[100];
	double n = doubles_get_until(-1, a);
	double z = soma(a, n);
	printf("%.2lf", z);
}

int main()
{
	test_soma();
	return 0;
}