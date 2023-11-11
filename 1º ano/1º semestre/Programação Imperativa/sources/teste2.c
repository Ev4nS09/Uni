#include <stdio.h>
#include <assert.h>
#include "our_ints.h"

int count(int x)
{
    int result = 0;
    int y;
    while(x > 0)
    {
	x /= 10;
	y = x % 10;
	result += y;
    }
    return result;
}

int main()
{
	int x;
	scanf("%d", &x);
	int z = count(x);
	printf("%d", z);
    return 0;
}