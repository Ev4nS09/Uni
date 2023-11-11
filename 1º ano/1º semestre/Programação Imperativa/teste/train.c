#include <stdio.h>
#include <math.h>

double train(int x)
{
    double x1 = x * 0.087;
	double result;
 if(round(x1) > x1)
 	result = round(x1);
 else
 	result = round(x1) + 0.5;
 return result;
}

double train_iv(int x)
{
 return train(x) * 2 * 0.85;
}

void test_train(void)
{
	int x;
	while(scanf("%d", &x) != EOF)
	{
		double z1 = train(x);
		printf("%.2f", z1);
		double z2 = train_iv(x);
		printf(" %.2f\n", z2);
	}
}

int main(void)
{
	test_train();
	return 0;
}