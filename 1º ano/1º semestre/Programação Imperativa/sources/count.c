#include <stdio.h>
#include <math.h>

const char *author = "Afonso Rio";

void test_count(void)
{
	double a;
	while(scanf("%lf", &a) != EOF)
	{
		double b = ceil(a);
		printf("%lf\n", b);
	}

}

int main(void)
{
	test_count();
	return 0;
}