#include <stdio.h>

const char *author = "Afonso Rio";

int sum_positive_integers(int x)
{
    int result;
	if (x < 0) 
	 result = 0;
	else
		return sum_positive_integers(x-1) + x;
	return result;
}

void test_sum_positive_integers(void)
{
	int x;
	while (scanf("%d", &x) != EOF)
	{
	int z = sum_positive_integers(x);
	printf("%d\n", z);
    }
	
}

int main(void)
{
	test_sum_positive_integers();
		return 0;
}
