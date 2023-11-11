#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

const char *author = "Afonso Rio";

#include "our_ints.h"
#include "our_doubles.h"
#include "our_strings.h"

int raiz(int *a, int n)
{
	int result = 0;
	for(int i = 0; i < n - 3; i++)
	{
		if(sqrt(pow(a[i] - a[i + 2], 2) + pow(a[i + 1] - a[i + 3], 2)) > 5)
			result++;
	}
	return result;
}

void test_raiz(void)
{
	int a[100];
	int n = ints_get(a);
	int z = raiz(a, n);
	printf("%d", z);
}

int main()
{
	test_raiz();
	return 0;
}