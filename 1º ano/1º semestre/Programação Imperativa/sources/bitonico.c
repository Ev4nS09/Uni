#include <stdio.h>
#include <math.h>
#include "our_ints.h"

int bitonico(int *a, int n)
{
	int result = 0;
	for(int i = 0; i < ints_find(a, n, ints_max(a,n)); i++)                       // 8 7 5 9 10
	{
	  if(a[i] < a[i+1])
	  	result++;
  }
	  for(int j = ints_find(a, n, ints_max(a,n)); j < n; j++)
	  {
	  		if(a[j] > a[j + 1])
           result++;
	  }
	  return result + 1;
	}

int bitonico2(int *a, int n)
{	
	int result = 0;
	for(int p = 0; p < ints_find(a, n, ints_min(a,n)); p++)                       // 8 7 5 9 10
	{
	  if(a[p] > a[p+1])
	  	result++;
  }
	  for(int k = ints_find(a, n, ints_min(a,n)); k < n; k++)
	  {
	  		if(a[k] < a[k + 1])
           result++;
	  }
	
	   return result;
}

int main()
{
  int a[1000];
  int n = ints_get(a);
  int z;
  if(a[0] < a[1])
  	z = bitonico(a, n);
  else
  	z = bitonico2(a, n);
  if (z == n)
  	printf("bitonico");
  else
  	printf("desordenado");
  return 0;
}