#include <stdio.h>
#include <math.h>

const char *author = "Afonso Rio";

double count(int x)
{
	double result;
	if (x <= 0)
		result = 0;
	else
		result =  x * x + count(x - 1);
	return result;
}


double covid(double hi, double  mi, double  hf, double  mf, double hl, double ml)
{
 double result;
 double hmi = hi + (mi/60);
 double hmf = hf + (mf/60);
 double hml = hl + (ml/60);
 double y = hml - hmi;
 double c = hmf - hml;
 double a = floor(c);
 double l = floor(y);

 if ((hmi+hmf)/2 < hml) 
	result = (0.10 * pow(ceil(c), 2) * (((c - a) * 60))) + (0.10 * ((a * a + count(a - 1))) * 60);
else
    result = ((0.10 * (l * l + count(l - 1))) * 60) + (0.10 * pow(ceil(y), 2) * ((y - l) * 60));
return result;
}

void test_virus(void)
{
	double hi;
	double mi;
	double hf;
	double mf;
	double hl;
	double ml;
	double mu;

	scanf("%lf%lf%lf%lf", &hi, &mi, &hf, &mf);

	while(scanf("%lf%lf", &hl, &ml) != EOF)
	{
		mu = covid(hi, mi, hf, mf, hl, ml);
		printf("%.2f\n", mu);
	}
}

int main(void)
{
	test_virus();
	return 0;
}