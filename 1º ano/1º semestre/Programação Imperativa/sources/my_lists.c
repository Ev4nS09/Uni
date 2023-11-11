#include <stdio.h>
#include "list.h"

void test_read_write(void)
{
 List s;
 while (lst_scan(&s) != EOF)
 lst_put(s);
}

void test_head_tail(void)
{
 List s;
 while (lst_scan(&s) != EOF)
 if (is_empty(s))
 printf("Empty list.\n");
 else
 {
 printf("%g\n", head(s));
 lst_put(tail(s));
 }
}

int main(void)
{
 //test_read_write();
 test_head_tail();
 return 0;
}