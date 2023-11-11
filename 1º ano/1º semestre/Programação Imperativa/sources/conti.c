#include <stdio.h>
#include <math.h>

const char *author = "Afonso Rio";

double conti(double d, int hs, int ms)
{
 double y =  ((hs * 60) + ms) - 1080;
 double x = ((hs * 60 + ms) - 1200);
 double result;
 if (hs + (ms/60) > 20)
 	result = (d * 2) + ((d * 0.5 + d) * (x/60)) ;
 else
 	result = (d / 60) * y;
 return result;
}

void test_conti(void)
{
	double d;
	int hs;
	int ms;
	while(scanf("%lf%d%d", &d, &hs, &ms) != EOF)
	{
		double t = round(100 * conti(d, hs, ms)) / 100;
		printf("%f\n", t);
	}
}

int main(void)
{
	test_conti();
	return 0;
}