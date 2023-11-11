#include <stdio.h>
#include <math.h>

const char *author = "Afonso Rio";

double mar(int x, int a, double hi, double g, double h)
{
	if(x == 8)
		return mar(x-8, a, hi, g * 1.25, h);
	else
	 return hi >= h ? a : mar(x+1, a+1, hi + g, g, h);
}


void test_mar(void)
{
	double h;
	int a = 0;
	int x = 0;
	int hi = 0;
	double g = 0.5;
	while(scanf("%lf", &h) != EOF)
	{
		int z = mar(x, a, hi, g, h);
		printf("%d\n", z);
	}
}

int main(void)
{
	test_mar();
	return 0;
}