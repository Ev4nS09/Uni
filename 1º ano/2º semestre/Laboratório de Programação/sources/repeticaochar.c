#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include <string.h>

const char *author = "Afonso Rio";

#include "our_ints.h"
#include "our_doubles.h"
#include "our_strings.h"

int until_space(char *x, const char *a)
{
	int result = 0;
	for(int i = 0; a[i] != ' '; i++)
		x[result++] = a[i];
	  x[result] = '\0';
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

int array(const char **a, int i, int n, int y)
{
	int result = 0;
	
	while(i < n - 1 && y < n)
	{
	    
	    if(strcmp(a[i], a[y]) == 0)
	    {
            i++;
	    	//array(a, i, n, i + 1);
	    	y = i + 1;
	    	result++;
	    }
	    else if(y == n - 1)
	    {
	    	i++;
	    	y = i + 1;
	    }
		else
			y++;
			//array(a, i, n, y + 1);
	}
	
	return result;
}

void test_array()
{
	const char *a[100];
	const char *b[100];
	//int b[100];
	int i = 0;
	int y = 1;
	int n = strings_get(a);
	int u = unique_first_names(b, n, a);
	int z = array(b, i, u, y);
	printf("%d\n", z);
	printf("%d", n);
	//ints_println_basic(a, n);
}

int main()
{
	test_array();
	return 0;
}