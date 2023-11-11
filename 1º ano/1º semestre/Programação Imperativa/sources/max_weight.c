#include <stdio.h>
#include "list.h"
#include <assert.h>

const char *author = "Afonso Rio";



void test_max_weight(void)
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
    test_max_weight();
    return 0;
}