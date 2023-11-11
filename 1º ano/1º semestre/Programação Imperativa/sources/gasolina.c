#include <stdio.h>
#include <math.h>
#include "our_doubles.h"
#include "our_ints.h"

int gasolina(double *a, int n)
{
	int result = 0;
	int c = 0;
	for(int i = n - 1; i >= 0 && c != 1; i--)
	{
	if(a[i] > a[i - 1] && a[i] > a[i + 1])
	  {
	c = 1;
	result = i + 1;
      }
      else
      result = n - 1; 
    }
    return (n-1) - result ;
}



int main()
{
  double a[1000];
  int n = doubles_get(a);
  int z = gasolina(a, n);
  if(n == 0 || z == 0)
  	printf("void");
  else
  printf("%d\n", z);
  return 0;
}