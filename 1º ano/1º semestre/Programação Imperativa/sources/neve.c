#include <stdio.h>
#include <math.h>

int ints_take(const double *a, int n, double *b, int x)
{
  int result = 0;
  for(int i = 0; i < x && i < n ; i++)
      b[result++] = a[i];
     return result;

}

void doubles_println_basic(const double *a, int n)
{
  if (n > 0)
  {
    printf("%g", a[0]);
    for (int i = 1; i < n; i++)  // i = 1
      printf(" %g", a[i]);
  }
  printf("\n");
}


void doubles_println_special(const double *a, int n)
{
  if (n == 0)
    printf("*\n");
  else
    doubles_println_basic(a, n);
}


int doubles_get(double *a)
{
  int result = 0;
  double x;
  while (scanf("%lf", &x) != EOF)
    a[result++] = x;
  return result;
}

int neve2(double *a, int n, int x)
{
  double result = 0;
  double y = 0;
  for(int i = 0; i < n; i++)
  {
    y += a[i];
    result = y / x;
  }
    return result;
}

int neve(double *a, int n, double *b, int x)
{
int result = 0;
double y = 0;
double u = 0;
int t = 1;
for(int i = 0; i < n - x; i++)
{
if(t <= 4)
{
  t++;
}
 u += a[i] + a[i + t*x];
 y = u - neve2(a, n, x);
 b[result++] = y;
}
return result;
}


int main()
{
  int x;
  scanf("%d", &x);
  double a[1000];
  double b[1000];
  double c[1000];
  int n = doubles_get(a);
  int z = neve(a, n, b, x);
  int o = ints_take(b, z, c, x);
  doubles_println_special(c, o);
  return 0;
}

























