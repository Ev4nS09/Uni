#include <stdio.h>
#include <math.h>

const char *author = "Afonso Rio";

double hourp(int m, int v)
{
	if(m <= 0)
		return v * 3;
	else
		return v++, hourp(m - 15, v++);
}

double e1(int m)
{
	double result;
	double m1 = m;
	double x = ceil((m1/5) - 2);
    if(m1 <= 10)
	result = 0;
     else if (m1 <= 60)
	result = x;
    else
	result = 10 + hourp(m - 60, 0);
   return result;
}

double e2(int m)
{
	double result;
	double m1 = m;
	if(m1 <= 60)
		result = ceil((m1/10) * 2);
	else
		result = 12 + hourp(m - 60, 0);
	return result;
}

double kiss_and_fly(int e, int m)
{
	double result;
    if(e <= 1)
    	result = e1(m);
    else if(e >= 2)
    	result = e2(m);
    return result;
}

void test_kiss_and_fly(void)
{
	int e;
	int m;
	while(scanf("%d%d", &e, &m) != EOF)
	{
		double z = kiss_and_fly(e, m);
		printf("%.2f\n", z);
	}
}

int main(void)
{
	test_kiss_and_fly();
	return 0;
}