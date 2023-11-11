#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

const char *author = "Afonso Rio";

#include "our_ints.h"
#include "our_doubles.h"
#include "our_strings.h"

int position(const char*a)
{
	int result = 0;
	// int n = str_len(a);
	// int c = 0;
	for(int i = 0; a[i]; i++)
	{
		if(a[i] == ':')
			result = i + 2;

	}
  return result;
}

char *ponto(char *y, const char *a, const char *b, int n)
{
	int result = 0;
	for(int j = 0; b[j] != '\0'; j++)
	
       y[result++] = b[j];
	
	for(int i = 0; i < n; i++)
	{
        if(a[i] == ' ' && a[i - 1] == ':')
        y[result++] = ',';

		else if(a[i] == '1' || a[i] == '2' || a[i] == '3' || a[i] == '4' || a[i] == '5' || a[i] == '6' || a[i] == '7' || a[i] == '8' || a[i] == '9')
			y[result++] = a[i];
	}
	for(int p = n; a[p] != ' '; p++)
        y[result++] = a[p];

     y[result++] = ',';
     
	y[result] = '\0';
	return y;

}

int pontos(const char **b, int n, const char **a)
{
	int result = 0;
	char y[1000];
	for(int i = 0; i < n; i += 3)
	{
		int u = position(a[i + 1]);
		ponto(y, a[i + 1], a[i], u);
		b[result++] = str_dup(y);
	}
	return result;
}

void test_futebol(void)
{
    const char *a[1000];
    const char *b[1000];
    int n = strings_get(a);
    int m = pontos(b, n, a);
    strings_print_basic(b, m);
}

int main()
{
	test_futebol();
	return 0;
}