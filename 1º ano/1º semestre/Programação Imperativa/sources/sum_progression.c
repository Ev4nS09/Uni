#include <stdio.h>

const char *author = "Afonso Rio";

int sum_positive_integers(int n)
{
    int result;
	if (n < 0) 
	 result = 0;
	else
		return sum_positive_integers(n-1) + n;
	return result;
}

int sum_progression(int n, int r, int x0)
{
    int result;
	if (n < 0)
	 result = 0;
	else
		return r *(sum_positive_integers(n)) + (x0 * n) - (n * r);
	return result;
}

void test_sum_progression(void)
{
	int n;
	int r;
	int x0;
	while (scanf("%d%d%d", &x0, &r, &n) != EOF)
	{
	int z = sum_progression(n, r, x0);
	printf("%d\n", z);
    }
}

int main(void)
{
	test_sum_progression();
	return 0;
}
