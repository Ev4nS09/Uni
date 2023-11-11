#include <stdio.h>
#include <math.h>

const char *author = "Afonso Rio";

double azeite(double p, double l, int a)
{
	double result;
	if (a <= 0)
		result = 0;
	else if (l <= 0)
		result = 0;
	else
       result = (p * 0.80) * (1 / l) * (a - floor(a/3)) / a;
   return result;
}

void test_azeite(void)
{
	double p;
	double l;
	int a;
	while(scanf("%lf%lf%d", &p, &l, &a) != EOF)
	{
		double pl = azeite(p, l, a);
		double pl2 = round(pl * 100) / 100;
		printf("%f\n", pl2);
	}
}

int main(void)
{
	test_azeite();
	return 0;
}

