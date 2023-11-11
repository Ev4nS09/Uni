#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include <string.h>

const char *author = "Afonso Rio";

#include "list.h"
#include "our_ints.h"
#include "our_doubles.h"
#include "our_ints.h"

#define MAX_NAVIOS 100

typedef struct {
int x;
int y;
const char *navio;
} Point;



Point point(int x, int y, const char *navio)
{
Point result;
result.x = x;
result.y = y;
result.navio = navio;
return result;
}

const char *str_dup(const char *s)
{
  char *result = (char *) malloc(strlen(s) + 1);
  strcpy(result, s);
  return result;
}

int navios_read(FILE *f, Point *a)
{
	int result = 0;
	int x;
	int y;
	char navio[32];
	while (fscanf(f, "%d%d%s", &x, &y, navio) != EOF)
		a[result++] = point(x, y, str_dup(navio));
	return result;
}

void navios_write(FILE *f, Point *a, int n)
{
	int result = 0;
	for (int i = 0; i < n; i++)
	{
		if(a[i].x <= 7 && a[i].x >= -3 && a[i].y <= 6 && a[i].y >= -4) 
		   result++;
		 printf("%d\n", result);
		fprintf(f,"%s\n", a[i].navio);
	}
}

void test_navio()
{
FILE *f = fopen("navios.txt", "r");
assert(f != NULL);
Point navios[MAX_NAVIOS];
int n_navios = navios_read(f, navios);
navios_write(f, navios, n_navios);
}


// int even(List s)
// {
//   return is_empty(s) ? 0.0 : head(s) % 2 == 0 ? head(s) + even(tails(s)) : even(tails(s));
// }

// int odd(List s)
// {
//   return is_empty(s) ? 0.0 : head(s) % 2 != 0 ? head(s) + odd(tails(s)) : odd(tails(s));
// }

 double lista(List s)
 {
  return is_empty(s) ? 0.0 : head(s) + (10*lista(tail(s)));
 }

 int even(int x)
 {
 	return x < 1 ? 0.0 : (x%10) % 2 == 0 ? x % 10 + even(x / 10) : even(x/10);
 }

  int odd(int x)
 {
 	return x < 1 ? 0.0 : (x%10) % 2 != 0 ? x % 10 + odd(x / 10) : odd(x/10);
 }

void test_even_odd()
{
	List s;
	while(lst_scan(&s) != EOF)
	{
      int n = lista(s);
      int z = even(n) - odd(n);
     // int n = even(s) - odd(s);
      printf("%d\n", z);
	}
}

int uber(int m, int k)
{
	return (m * 9 + 70 * k) + 100;
}

void test_uber()
{
	int m;
	int k;
	while(scanf("%d%d", &m, &k) != EOF)
	{
	  int n = uber(m, k);
      if(n < 250)
      	printf("250\n");
      else
      	printf("%d\n", n);
	}
}

int soma_array(int *a, int n)
{
	int result = 0;
	for(int i = 0; i < n - 2; i++)
	{
		if(a[i+2] > a[i] + a[i+1])
			result++;
	}
	return result;
}


void test_soma()
{
  int a[1000];
  int n = ints_get(a);
  int z = soma_array(a, n);
  if(z == n - 2)
  	printf("1");
  else
  printf("0");
}

int main(void)
{
	test_even_odd();
	test_uber();
  test_soma();
   test_navio();	
 printf("OK\n");
  return 0;
}
