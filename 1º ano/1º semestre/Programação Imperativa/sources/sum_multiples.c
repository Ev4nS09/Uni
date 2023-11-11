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


int sum_multiples(int r, int n)
{
   int result;
    if (n<0)
    	result = 0;
    else 
    	return r * sum_positive_integers(n-1);
    return result;

}

void test_sum_multiples(void)
{
	int r;
	int n;
	while (scanf("%d%d", &r, &n) != EOF)
	{
	int z = sum_multiples(r, n);
	printf("%d\n", z);
    }
}

int main(void)
{
	test_sum_multiples();
	return 0;
}