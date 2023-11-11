#include <stdio.h>
#include <math.h>
#include "list.h"

List digits(int x)
{
 return x == 0 ? NULL : cons(x % 10, digits(x / 10));
}

void test_digits(void)
{
 int x;
 while (scanf("%d", &x) != EOF)
 {
 List s = digits(x);
 lst_put(s);
 int z1 = (int) lst_sum(s);
 printf("%d\n", z1);
 int z2 = (int) lst_sum(digits(x));
 assert(z1 == z2);
 }
}