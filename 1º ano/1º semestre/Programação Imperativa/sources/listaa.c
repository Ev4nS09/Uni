#include <stdio.h>
#include "list.h"
#include <assert.h>

const char *author = "Afonso Rio";

double digits(List s)
{
 return digits(head(s));
}

void test_digits(void)
{
 List s;
 while (lst_scan(&s) != EOF)
 {
  lst_put(s);
  int z2 = (int) digits(s);
  printf("%d\n", z2);
 }
}

int main(void)
{
    test_digits();
    return 0;
}