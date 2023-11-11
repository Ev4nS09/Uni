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

int even_values_minus_odd_values(int *a, int n)
{
	int odd = 0;
	int even = 0;
	for(int i = 0; i < n; i++)
	{
		if(a[i] % 2 == 0)
			even += a[i];
		else
			odd += a[i];
	}
    return even - odd;
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

void test_even_values_minus_odd_values(void)
{
  int a[1000];
  int n = ints_get(a);
  int z = odds_even(a, n);
  printf("%d\n", z);
}

void unit_test_even_values_minus_odd_values(void)
{
int a1[8] = {6,7,1,8, 9,3,3,5};
assert(even_values_minus_odd_values(a1, 8) == -14);
assert(even_values_minus_odd_values(a1, 4) == 6);
assert(even_values_minus_odd_values(a1, 2) == -1);
assert(even_values_minus_odd_values(a1, 1) == 6);
assert(even_values_minus_odd_values(a1, 0) == 0);
int a2[6] = {2, 7, 4, 6,  8, 9}
assert(even_values_minus_odd_values(a1, 8) == 4);
assert(even_values_minus_odd_values(a1, 4) == 5);
assert(even_values_minus_odd_values(a1, 2) == -5);
assert(even_values_minus_odd_values(a1, 1) == 2);
assert(even_values_minus_odd_values(a1, 0) == 0);
int a3[9] = {5, 3, 7, 8,  2, 1, 9, 4,  10}
assert(even_values_minus_odd_values(a1, 8) == -1);
assert(even_values_minus_odd_values(a1, 4) == -7);
assert(even_values_minus_odd_values(a1, 2) == -8);
assert(even_values_minus_odd_values(a1, 1) == -5);
assert(even_values_minus_odd_values(a1, 0) == 0);
}

void unit_test_even_values_minus_odd_values(void)
{
int a1[8] = {6,7,1,8, 9,3,3,5};
assert(even_positions_minus_odd_positions(a1, 8) == -22);
assert(even_positions_minus_odd_positions(a1, 4) == -5);
assert(even_positions_minus_odd_positions(a1, 2) == -1);
assert(even_positions_minus_odd_positions(a1, 1) == 0);
assert(even_positions_minus_odd_positions(a1, 0) == 0);
int a2[6] = {2, 7, 4, 6,  8, 9}
assert(even_positions_minus_odd_positions(a1, 8) == 3);
assert(even_positions_minus_odd_positions(a1, 4) == 4);
assert(even_positions_minus_odd_positions(a1, 2) == -1);
assert(even_positions_minus_odd_positions(a1, 1) == 0);
assert(even_positions_minus_odd_positions(a1, 0) == 0);
int a3[9] = {5, 3, 7, 8,  2, 1, 9, 4,  10}
assert(even_positions_minus_odd_positions(a1, 8) == 8);
assert(even_positions_minus_odd_positions(a1, 4) == 1);
assert(even_positions_minus_odd_positions(a1, 2) == -1);
assert(even_positions_minus_odd_positions(a1, 1) == 0);
assert(even_positions_minus_odd_positions(a1, 0) == 0);
}

void unit_tests(void)
{
  unit_test_even_values_minus_odd_values();
  unit_test_even_positions_minus_odd_positions();
  unit_test_all_equal();
  unit_test_ints_second_max();
  unit_test_ints_argsmax();
}

int main(int argc, char **argv)
{
  unit_tests();
  int x = 'A';
  if (argc > 1)
    x = *argv[1];
  if (x == 'A')
    test_even_values_minus_odd_values();
  else if (x == 'B')
    test_even_positions_minus_odd_positions();
  else if (x == 'C')
    test_all_equal();
  else if (x == 'D')
    test_ints_second_max();
  else if (x == 'E')
    test_ints_argsmax();
  else if (x == 'U')
    printf("All unit tests PASSED\n");
  else
    printf("%s: Invalid option.\n", argv[1]);
  return 0;
 }