#include <stdio.h>
#include "list.h"
#include <assert.h>

const char *author = "Afonso Rio";

int decimal(List s)
{
    return  is_empty(s) ? 0.0 : head(s) + (10*decimal(tail(s)));
}

void test_decimal(void)
{
    List s;

    while (lst_scan(&s) != EOF)
    {
        lst_put(s);
        int z1 = decimal(s);
        printf("%d\n", z1);
    }
}

int main(void)
{
    test_decimal();
    return 0;
}