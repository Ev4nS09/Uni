#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include <string.h>

const char *author = "Afonso Rio";

#include "our_ints.h"
#include "our_doubles.h"
#include "our_strings.h"

int array(int *a, int i, int n, int y)
{
	int result = 0;
	
	while(i < n - 1 && y < n)
	{
		if(a[i] == a[y] && y == i + 1)
		{
		i++;
		//array(a, i, n, i + 1);
		y++;
		result++;
	    }
	    else if(y > i + 1 && a[i] == a[y])
	    {
	    	i++;
	    	//array(a, i, n, i + 1);
	    	y = i + 1;
	    	result++;
	    }
	    else if(a[i] != a[n-1] && y == n - 1)
	    {
	    	i++;
	    	//array(a, i, n, i + 1);
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
	int a[100];
	//int b[100];
	int i = 0;
	int y = 1;
	int n = ints_get(a);
	int z = array(a, i, n, y);
	printf("%d", z);
	//ints_println_basic(a, n);
}

int main()
{
	test_array();
	return 0;
}