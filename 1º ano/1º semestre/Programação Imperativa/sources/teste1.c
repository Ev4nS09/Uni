#include <stdio.h>
#include <math.h>
#include <assert.h>

int ints_get(int *a)
{
int result = 0;
int x;
while (scanf("%d", &x) != EOF)
a[result++] = x;
return result;
}



int even_positions_minus_odd_positions(int *a, int n)
{
	int result;
	int y = 0;
	if(n == 0)
		result = 1;
	for(int i = 0; i < n; i++)
	{
    y += a[i];
    if( y / a[0] == n)
    	result = 1;
    else
    	result = 0;
   }

    return result;
}


void test_even_positions_minus_odd_positions(void)
{
  int a[1000];
  int n = ints_get(a);
  int z = even_positions_minus_odd_positions(a, n);
  printf("%d\n", z);
}

int main()
{
	test_even_positions_minus_odd_positions();
	// unit_test_even_positions_minus_odd_positions();
	return 0;
}