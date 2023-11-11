#include <stdio.h>
#include "list.h"
#include <assert.h>

int head(int x)
{
    head(tail(x));
}

void test_decimal(void)
{
 List s;
 while (lst_scan(&s) != EOF)
 {
 lst_put(s);
 int z1 = lst_put(head(s));
 printf("%d\n", z1);
 int z2 = lst_put(s);
 assert(z1 == z2);
 }
}

int main(void)
{
    test_decimal();
    return 0;
}

