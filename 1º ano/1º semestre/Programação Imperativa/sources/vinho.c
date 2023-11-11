#include <stdio.h>
#include <math.h>
#include "our_ints.h"

int vinho(int *a, int n, int x)
{
int result = 0;
for(int i = 0; i < n; i++)

if(a[i] >= x)
{
a[i] /= x;
 result += a[i];
}
return result;
}

int main()
{
  int a[1000];
  int x;
  scanf("%d", &x);
  int n = ints_get(a);
  int z = vinho(a, n, x);
  printf("%d\n", z);
  return 0;
}