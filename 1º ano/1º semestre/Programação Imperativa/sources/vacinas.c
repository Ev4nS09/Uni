#include <stdio.h>
#include <math.h>
#include "our_ints.h"

int vacinas(int *a, int n, int x)
{
  int result = 0;
  int y = 0;
  int z = 0;
  for(int i = 0; i < n; i++)
  {
    if((a[i] / 10000) == x)
    {
      result = (a[i] % 10000);
       a[i] = -1;
       y = (result / 100);
       z = (result % 100);
       printf("%d ", y);
       printf("%d\n", z);
       return 1;
    }
  }
  return -2;
}


void test_vacinas(void)
{
  int x;
  int a[1000];
  int n = ints_get_until('*', a);
   while (scanf("%d", &x) != EOF)
   {
    int w = vacinas(a, n, x);
    if(w == -2)
      printf("*\n");
   }
}

int main()
{
  test_vacinas();
  return 0;
}