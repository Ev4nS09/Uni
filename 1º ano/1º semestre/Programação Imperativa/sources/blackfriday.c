#include <stdio.h>
#include <math.h>
#include "our_ints.h"
#include <assert.h>

const char *author = "Afonso Rio";

double doubles_max2(const int *a, int n)
{
  assert(n > 0);
  double result = a[0];
  for (int i = 1; i < n; i++)  // i = 1
    if (result < a[i])
      result = a[i];
  return result;
}

int blackfridaymaior(int *a, int n, int *b)
{
   int result = 0;
   for(int i = 0; i < n; i++)
   if(a[i] == a[i + 1] && a[i] == a[i + 2] && a[i] == a[i + 3] && a[i] == a[i + 4] && a[i] == a[i + 5] && a[i] == a[i + 6])
    b[result++] = a[i];
   return result;
}

int blackfriday(int *a, int n, int *b)
{
  int r = blackfridaymaior(a, n, b);
  int result = 0;
  for(int i = 0; i < n; i++)
  result = ints_max(b, r) - a[0];
  return result;
}

double blackfridayper(int *a, int n, int *b)
{
  double r = blackfridaymaior(a, n, b);
  double result = 0;
  for(int i = 0; i < n; i++)
  result = (blackfriday(a, n, b) / doubles_max2(b, r)) * 100;
  return result;
}

int main()
{
  int a[1000];
  int b[1000];
  int n = ints_get(a);
  int z = blackfriday(a, n, b);
  double t = blackfridayper(a, n , b);
  printf("%d\n", z);
  printf("%.1lf\n", t);
  return 0;
}
