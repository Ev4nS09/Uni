#include <stdio.h>
#include <assert.h>
#include "our_ints.h"

const char *author = "Afonso Rio";

//1
int ints_greater_than (const int *a, int n, int y, int *b)                                           
{
  int result = 0;
  for (int i = 0; i < n; i++)
  	if(a[i] > y)
     b[result++] = a[i];
return result;
}

void test_ints_greater_than(void)
{
	int y;
	int a[1000];
	int n = ints_get_until(-1, a);
	while(scanf("%d", &y) != EOF)
	{
	  int b[1000];
	  int m = ints_greater_than(a, n, y, b);
      ints_println_special(b, m);
  }
  
}
//2

int ints_less_than(const int *a, int n, int y, int *b)                                           
{
  int result = 0;
  for (int i = 0; i < n; i++)
  	if(a[i] < y)
     b[result++] = a[i];
return result;
}

void test_ints_less_than(void)
{
  int y;
  int a[1000];
  int n = ints_get_until(-1, a);
   while(scanf("%d", &y) != EOF)
   {
	   int b[1000];
	   int m = ints_less_than(a, n, y, b);
       ints_println_special(b, m); 
    }
}

int count(int x)
{
	int y;
    int result = 0;
    while(x > 0)
    {
    y = x % 10;
    result += y;
	x = x / 10;
    }
    return result;
}

//3

int digits_sum(const int *a, int n, int *b)                                           
{
  int result = 0;
  for (int i = 0; i < n; i++)
  {
  	assert(a[i] >= 0);
   b[result++] = count(a[i]);
   }
    return result;
}

void test_digits_sum(void)
{
  int a[1000];
  int n = ints_get_until(-1, a);
	   int b[1000];
	   int m = digits_sum(a, n, b);
       ints_println_special(b, m); 
}

//4

int ints_append(const int *a, int n, const int *b, int m, int *c)
{
  int result = 0;
  for(int i = 0; i < n; i++)
  	c[result++] = a[i];
  for(int j = 0; j < m; j++)
  c[result++] = b[j];

 return result;
}


void test_ints_append(void)
{
  int a[1000];
  int n = ints_get_until(-1, a);
	 int b[1000];
	   int m = ints_get_until(-1, b);
	    int c[1000];
     int p = ints_append(a, n, b, m, c);
          int d[1000];
     int u = ints_append(b, m, a, n, d);
     ints_println_special(c, p);
      ints_println_special(d, u);
}

//5

int ints_take(const int *a, int n, int *b, int x)
{
	int result = 0;
	for(int i = 0; i < x && i < n ; i++)
     	b[result++] = a[i];
     return result;

}

void test_ints_take(void)
{
  int a[1000];
  int x;
  int n = ints_get_until(-1, a);
  while(scanf("%d", &x) != EOF)
  {
	 int b[1000];
	   int m = ints_take(a, n, b, x);
     ints_println_special(b, m);
  }
}

//6

int ints_drop(const int *a, int n, int *b, int x)
{
	int result = 0;
	for(int i = 0; i < n; i++)
		if(i >= x)
     	b[result++] = a[i];
     return result;

}

void test_ints_drop(void)
{
	int a[1000];
	int n = ints_get_until(-1, a);
	int x;
	while (scanf("%d", &x) != EOF)
	{
		int b[1000];
		int m = ints_drop(a, n, b, x);
		ints_println_special(b, m);
	}
 
}

//7
int ints_ascending(const int *a, int n, int *b)                                           
{
  int result = 0;
  int f = a[0]; 
  for (int i = 0; i < n; i++)

  	if (a[i] >= f)
    {
       f = a[i];
       b[result++] = f;
    }

    return result;
}

void test_ints_ascending(void)
{
  int a[1000];
  int n = ints_get_until(-1, a);
	   int b[1000];
	   int m = ints_ascending(a, n, b);
       ints_println_special(b, m); 
}

//8
int ints_accumulate(int *a, int n, int *b)                                           
{
  int result = 1;
  int y = 0;
  b[0] = 0;
  for (int i = 0; i < n; i++)   
      {
      y += a[i];
      b[result++] = y;     
     }
    return result;
}



void test_ints_accumulate(void)
{
  int a[1000];
  int n = ints_get_until(-1, a);
	   int b[1000];
	   int m = ints_accumulate(a, n, b);
       ints_println_special(b, m); 
}

//9
 int ints_unaccumulate(int *a, int n, int *b)                                           
 {
   int result = 0;
   int y = 0;
   for (int i = 0; i < n; i++)   
       {
       y = a[i + 1] - a[i];
      b[result++] = y;     
      }
     return result - 1;
 }



 void test_ints_unaccumulate(void)
 {
   int a[1000];
   int n = ints_get_until(-1, a);
 	   int b[1000];
 	   int m = ints_unaccumulate(a, n, b);
        ints_println_special(b, m); 
 }

//10
int ints_find_triple (const int *a, int n)
{
    int result = 0;
    int c = 0;
    for (int i = 0; i < n - 2 && c != 1; i++)
        if (a[i] == a[i + 1] && a[i] == a[i + 2])
        {
            result = i;
            c = 1;
        }
        else
            result = -1;
    if (n < 3)
        result = -1;
    return result;
}



 void test_ints_find_triple(void)
 {
   int a[1000];
   int n  = ints_get_until(-1, a);
   int z = ints_find_triple(a, n);
   printf("%d\n", z);
 }

//u

void unit_test_ints_greater_than(void)
{
  int a1[10] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
  int b[1000];
  assert(ints_greater_than(a1, 10, 4, b) && b[0] == 5 && b[1] == 6 && b[2] == 7 && b[3] == 8 && b[4] == 9 && b[5] == 10);
  assert(ints_greater_than(a1, 10, 8, b) && b[0] == 9 && b[1] == 10);
  assert(ints_greater_than(a1, 10, 7, b) && b[0] == 8 && b[1] == 9 && b[2] == 10);
  assert(ints_greater_than(a1, 10, 6, b) && b[0] == 7 && b[1] == 8 && b[2] == 9 && b[3] == 10);
}

 void unit_test_ints_less_than (void)
 {
   int a1[10] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
   int b[1000];
   assert(ints_less_than(a1, 10, 4, b) && b[0] == 1 && b[1] == 2 && b[2]);
   assert(ints_less_than(a1, 10, 2, b) && b[0] == 1);
   assert(ints_less_than(a1, 10, 3, b) && b[0] == 1 && b[1] == 2);
   assert(ints_less_than(a1, 10, 5, b) && b[0] == 1 && b[1] == 2 && b[2] == 3 && b[3] == 4);
 }

void unit_test_digits_sum(void)
{
  int a1[5] = {15, 25, 35, 45, 55};
  int b[1000];
  assert(digits_sum(a1, 5, b) && b[0] == 6 && b[1] == 7 && b[2] == 8 && b[3] == 9 && b[4] == 10);
  int a2[2] = {163, 756};
  assert(digits_sum(a2, 2, b) && b[0] == 10 && b[1] == 18);
  int a3[10] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
  assert(digits_sum(a3, 10, b) && b[0] == 1 && b[1] == 2 && b[2] == 3 && b[3] == 4 && b[4] == 5 && b[5] == 6 && b[6] == 7 && b[7] == 8 && b[8] == 9 && b[9] == 1);
  int a4[5] = {0, 22, 33, 44, 1001};
  assert(digits_sum(a4, 5, b) && b[0] == 0 && b[1] == 4 && b[2] == 6 && b[3] == 8 && b[4] == 2);
}

void unit_test_ints_append(void)
{
  int a1[3] = {5, 2, 9};
  int b1[2] = {1, 4};
  int c[1000];
  int d[1000];
  assert(ints_append(a1, 3, b1, 2, c) && c[0] == 5 && c[1] == 2 && c[2] == 9 && c[3] == 1 && c[4] == 4);
  assert(ints_append(b1, 2, a1, 3, d) && d[0] == 1 && d[1] == 4 && d[2] == 5 && d[3] == 2 && d[4] == 9);
  int a2[2] = {2, 3};
  int b2[2] = {8, 9};
  assert(ints_append(a2, 2, b2, 2, c) && c[0] == 2 && c[1] == 3 && c[2] == 8 && c[3] == 9);
  assert(ints_append(b2, 2, a2, 2, d) && d[0] == 8 && d[1] == 9 && d[2] == 2 && d[3] == 3);
}


void unit_test_ints_take (void)
{
  int a1[5] = {1, 2, 3, 4, 5};
  int b[1000];
  assert(ints_take(a1, 5, b, 4) && b[0] == 1 && b[1] == 2 && b[2] == 3);
  assert(ints_take(a1, 5, b, 3) && b[0] == 1 && b[1] == 2);
  assert(ints_take(a1, 5, b, 5) && b[0] == 1 && b[1] == 2 && b[2] == 3 && b[3] == 4);
  assert(ints_take(a1, 5, b, 2) && b[0] == 1);
}

void unit_test_ints_drop (void)
{
  int a1[5] = {1, 2, 3, 4, 5};
  int b[1000];
  assert(ints_drop(a1, 5, b, 4) && b[0] == 5);
  assert(ints_drop(a1, 5, b, 3) && b[0] == 4 && b[1] == 5);
  assert(ints_drop(a1, 5, b, 1) && b[0] == 2 && b[1] == 3 && b[2] == 4 && b[3] == 5);
  assert(ints_drop(a1, 5, b, 2) && b[0] == 3 && b[1] == 4 && b[2] == 5);
}


void unit_test_ints_ascending (void)
{
  int a1[5] = {1, 2, 3, 4, 5};
  int b[1000];
  assert(ints_ascending(a1, 5, b) && b[0] == 1 && b[1] == 2 && b[2] == 3 && b[3] == 4 && b[4] == 5);
  int a2[3] = {9,10,3};
  assert(ints_ascending(a2, 3, b) && b[0] == 9 && b[1] == 10);
  int a3[5] = {7, 2, 7, 8, 1};
  assert(ints_ascending(a3, 5, b) && b[0] == 7 && b[1] == 7 && b[2] == 8);
  int a4[5] = {5, 4, 9, 2, 1};
  assert(ints_ascending(a4, 5, b) && b[0] == 5 && b[1] == 9);
}

void unit_test_ints_accumulate (void)
{
  int a1[5] = {1, 2, 3, 4, 5};
  int b[1000];
  assert(ints_accumulate(a1, 5, b) && b[0] == 0 && b[1] == 1 && b[2] == 3 && b[3] == 6 && b[4] == 10 && b[5] == 15);
  int a2[3] = {3, 2, 2};
  assert(ints_accumulate(a2, 3, b) && b[0] == 0 && b[1] == 3 && b[2] == 5 && b[3] == 7);
  int a3[5] = {1, 2, 2, 1, 1};
  assert(ints_accumulate(a3, 5, b) && b[0] == 0 && b[1] == 1 && b[2] == 3 && b[3] == 5 && b[4] == 6 && b[5] == 7);
  int a4[2] = {5, 2};
  assert(ints_accumulate(a4, 2, b) && b[0] == 0 && b[1] == 5 && b[2] == 7);
}

void unit_test_ints_unaccumulate (void)
{
  int a1[6] = {0, 1, 3, 6, 10, 15};
  int b[1000];
  assert(ints_unaccumulate(a1, 5, b) && b[0] == 1 && b[1] == 2 && b[2] == 3 && b[3] == 4 && b[4] == 5);
  int a2[4] = {0, 3, 5, 6};
  assert(ints_unaccumulate(a2, 3, b) && b[0] == 3 && b[1] == 2 && b[2] == 1);
  int a3[6] = {0, 1, 2, 3, 4, 5};
  assert(ints_unaccumulate(a3, 5, b) && b[0] == 1 && b[1] == 1 && b[2] == 1 && b[3] == 1 && b[4] == 1);
  int a4[3] = {0, 5, 7};
  assert(ints_unaccumulate(a4, 2, b) && b[0] == 5 && b[1] == 2);
}

void unit_test_ints_find_triple (void)
{
  int a1[5] = {1, 2, 3 ,7 ,2};
  assert(ints_find_triple(a1, 5) == -1);
  int a2[6] = {1, 1, 1, 3, 3, 3};
  assert(ints_find_triple(a2, 6) == 0);
  int a3[5] = {1 ,2 ,2 ,2 ,3};
  assert(ints_find_triple(a3, 5) == 1);
  int a4[10] = {1, 2, 2, 3, 3, 3, 4, 4, 4, 4};
  assert(ints_find_triple(a4, 10) == 3);
}

 void unit_tests(void)
 {
   unit_test_ints_greater_than();
   unit_test_ints_less_than();
   unit_test_digits_sum();
    unit_test_ints_append();
   unit_test_ints_take();
   unit_test_ints_drop();
   unit_test_ints_ascending();
   unit_test_ints_accumulate();
   unit_test_ints_unaccumulate();
  unit_test_ints_find_triple();
 }


int main(int argc, char **argv)
{
 unit_tests();
 int x = 'A';
 if (argc > 1)
 x = *argv[1];
 if (x == 'A')
 test_ints_greater_than();
 else if (x == 'B')
  test_ints_less_than();
 else if (x == 'C')
  test_digits_sum();
   else if (x == 'D')
  test_ints_append();
   else if (x == 'E')
    test_ints_take();
   else if (x == 'F')
   test_ints_drop();
  else if (x == 'G')
  test_ints_ascending();
  else if (x == 'H')
  test_ints_accumulate();
   else if (x == 'I')
   test_ints_unaccumulate();
  else if (x == 'J')
  test_ints_find_triple();
  else if (x == 'U')
   printf("All unit tests PASSED.\n");
 else
 printf("%s: Invalid option.\n", argv[1]);
 return 0;
}