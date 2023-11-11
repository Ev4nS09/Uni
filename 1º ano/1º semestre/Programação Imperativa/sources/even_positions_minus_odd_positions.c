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
	int odd = 0;
	int even = 0;
	for(int i = 0; i < n; i++)
	{
		if(i % 2 == 0)
			even += a[i];
		else
			odd += a[i];
	}
    return even - odd;
}

void test_even_positions_minus_odd_positions(void)
{
  int a[1000];
  int n = ints_get(a);
  int z = even_positions_minus_odd_positions(a, n);
  printf("%d\n", z);
}

 // void unit_test_even_values_minus_odd_values(void)
 // {
 // int a1[8] = {6,7,1,8, 9,3,3,5};
 // assert(even_positions_minus_odd_positions(a1, 8) == );
 // assert(even_positions_minus_odd_positions(a1, 4) == 6);
 // assert(even_positions_minus_odd_positions(a1, 2) == -1);
 // assert(even_positions_minus_odd_positions(a1, 1) == 6);
 // assert(even_positions_minus_odd_positions(a1, 0) == 0);
 // }

int main()
{
	test_even_positions_minus_odd_positions();
    //unit_test_even_positions_minus_odd_positions();
	return 0;
}