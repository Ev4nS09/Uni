#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

const char *author = "Afonso Rio";

#include "our_ints.h"
#include "our_doubles.h"
#include "our_strings.h"

int until_space(char *y, const char *a)
{
	int result = 0;
	for(int i = 0; a[i] != ' '; i++)
		y[result++] = a[i];
	  y[result] = '\0';
	return result;
}

int unique_first_names(const char **x, int n, const char **a)
{
	char y[100];
	int result = 0;
	for(int i = 0; i < n; i++)
	{
		until_space(y, a[i]);
		x[result++] = str_dup(y);
	}
	return result;
}

void test_nomes(void)
{
	const char *a[1000];
	const char *b[1000];
	const char *c[1000];
    int n = strings_get(a);
    int z = unique_first_names(b, n, a);
    strings_isort(b, z);
    //strings_print_basic(b, z);
    int s = strings_unique(b, z, c);
    strings_print_basic(c, s);
}

int main()
{
	test_nomes();
	return 0;
}