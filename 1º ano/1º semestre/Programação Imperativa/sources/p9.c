#include <stdio.h>
#include <math.h>
#include <assert.h>

const char *author = "Afonso Rio";

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

int ints_max(const int *a, int n)
{
    assert(n > 0);
    double result = a[0];
    for (int i = 1; i < n; i++)        //i = 1
        if (result < a[i])
            result = a[i];
    return result;
}

int ints_min(const int *a, int n)
{
    assert (n > 0);
    int result = a[0];
    for (int i = 0; i < n; i++)
        if (result > a[i])
            result = a[i];
    return result;
}

int all_equal(const int *a, int n)
{
    int result;
        if (n == 0)
            result = 1;
        else if (ints_max(a, n) != ints_min(a, n))
            result = 0;
        else
            result = 1;
    return result;
}

int ints_second_max(const int *a, int n)
{
    assert(n > 0);
    double result = a[0];
    for (int i = 1; i < n; i++)        //i = 1
        if (result < a[i] && a[i] != ints_max(a, n))
            result = a[i];
    return result;
}

int ints_argsmax(const int *a, int n, int *b)
{
    assert(n > 0);
    int result = 0;
    for (int i = 0; i < n; i++)        //i = 1
        if (a[i] == ints_max(a, n))
            b[result++] = i;
    return result;
}

void test_even_values_minus_odd_values(void)
{
  int a[1000];
  int n = ints_get(a);
  int z = even_values_minus_odd_values(a, n);
  printf("%d\n", z);
}

void test_even_positions_minus_odd_positions(void)
{
  int a[1000];
  int n = ints_get(a);
  int z = even_positions_minus_odd_positions(a, n);
  printf("%d\n", z);
}

void test_all_equal(void)
{
  int a[1000];
  int n = ints_get(a);
  int z = all_equal(a, n);
  printf("%d\n", z);
}

void test_ints_second_max(void)
{
  int a[1000];
  int n = ints_get(a);
  int z = ints_second_max(a, n);
  printf("%d\n", z);
}

void ints_println_basic(const int *a, int n)
{
  if (n > 0)
  {
    printf("%d", a[0]);
    for (int i = 1; i < n; i++)  // i = 1
      printf(" %d", a[i]);
  }
  printf("\n");
}

void test_ints_argsmax(void)
{
  int a[1000];
  int b[1000];
  int n = ints_get(a);
  int z = ints_argsmax(a, n, b);
  ints_println_basic(b, z);
}

void unit_test_even_values_minus_odd_values(void)
{
int a1[8] = {6,7,1,8, 9,3,3,5};
assert(even_values_minus_odd_values(a1, 8) == -14);
assert(even_values_minus_odd_values(a1, 4) == 6);
assert(even_values_minus_odd_values(a1, 2) == -1);
assert(even_values_minus_odd_values(a1, 1) == 6);
assert(even_values_minus_odd_values(a1, 0) == 0);
int a2[6] = {2, 7, 4, 6,  8, 9};
assert(even_values_minus_odd_values(a2, 6) == 4);
assert(even_values_minus_odd_values(a2, 4) == 5);
assert(even_values_minus_odd_values(a2, 2) == -5);
assert(even_values_minus_odd_values(a2, 1) == 2);
assert(even_values_minus_odd_values(a2, 0) == 0);
int a3[9] = {5, 3, 7, 8,  2, 1, 9, 4,  10};
assert(even_values_minus_odd_values(a3, 9) == -1);
assert(even_values_minus_odd_values(a3, 4) == -7);
assert(even_values_minus_odd_values(a3, 2) == -8);
assert(even_values_minus_odd_values(a3, 1) == -5);
assert(even_values_minus_odd_values(a3, 0) == 0);
}

void unit_test_even_positions_minus_odd_positions(void)
{
int a1[8] = {6,7,1,8, 9,3,3,5};
assert(even_positions_minus_odd_positions(a1, 8) == -4);
assert(even_positions_minus_odd_positions(a1, 4) == -8);
assert(even_positions_minus_odd_positions(a1, 2) == -1);
assert(even_positions_minus_odd_positions(a1, 1) == 6);
assert(even_positions_minus_odd_positions(a1, 0) == 0);
int a2[6] = {2, 7, 4, 6,  8, 9};
assert(even_positions_minus_odd_positions(a2, 6) == -8);
assert(even_positions_minus_odd_positions(a2, 4) == -7);
assert(even_positions_minus_odd_positions(a2, 2) == -5);
assert(even_positions_minus_odd_positions(a2, 1) == 2);
assert(even_positions_minus_odd_positions(a2, 0) == 0);
int a3[9] = {5, 3, 7, 8,  2, 1, 9, 4,  10};
assert(even_positions_minus_odd_positions(a3, 9) == 17);
assert(even_positions_minus_odd_positions(a3, 4) == 1);
assert(even_positions_minus_odd_positions(a3, 2) == 2);
assert(even_positions_minus_odd_positions(a3, 1) == 5);
assert(even_positions_minus_odd_positions(a3, 0) == 0);
}

void unit_test_all_equal(void)
{
int a1[8] = {6,6,1,8, 9,3,3,5};
assert(all_equal(a1, 8) == 0);
assert(all_equal(a1, 4) == 0);
assert(all_equal(a1, 2) == 1);
assert(all_equal(a1, 1) == 1);
assert(all_equal(a1, 0) == 1);
int a2[6] = {2, 2, 2, 2,  8, 9};
assert(all_equal(a2, 6) == 0);
assert(all_equal(a2, 4) == 1);
assert(all_equal(a2, 2) == 1);
assert(all_equal(a2, 1) == 1);
assert(all_equal(a2, 0) == 1);
int a3[9] = {5, 3, 7, 8,  2, 1, 9, 4,  10};
assert(all_equal(a3, 9) == 0);
assert(all_equal(a3, 4) == 0);
assert(all_equal(a3, 2) == 0);
assert(all_equal(a3, 1) == 1);
assert(all_equal(a3, 0) == 1);
}

// void unit_test_all_equal(void)
// {
// int a1[8] = {6,6,1,8, 9,3,3,5};
// assert(ints_second_max(a1, 8) == 8);
// assert(ints_second_max(a1, 4) == 6);
// assert(ints_second_max(a1, 2) == 1);
// assert(ints_second_max(a1, 1) == 1);
// assert(ints_second_max(a1, 0) == 1);
// int a2[6] = {2, 2, 2, 2,  8, 9};
// assert(ints_second_max(a2, 6) == 0);
// assert(ints_second_max(a2, 4) == 1);
// assert(ints_second_max(a2, 2) == 1);
// assert(ints_second_max(a2, 1) == 1);
// assert(ints_second_max(a2, 0) == 1);
// int a3[9] = {5, 3, 7, 8,  2, 1, 9, 4,  10};
// assert(ints_second_max(a3, 9) == 0);
// assert(ints_second_max(a3, 4) == 0);
// assert(ints_second_max(a3, 2) == 0);
// assert(ints_second_max(a3, 1) == 1);
// assert(ints_second_max(a3, 0) == 1);
// }


void unit_tests(void)
{
  unit_test_even_values_minus_odd_values();
  unit_test_even_positions_minus_odd_positions();
  unit_test_all_equal();
  //unit_test_ints_second_max();
  //unit_test_ints_argsmax();
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
  // else if (x == 'U')
  //   printf("All unit tests PASSED\n");
  else
    printf("%s: Invalid option.\n", argv[1]);
  return 0;
 }