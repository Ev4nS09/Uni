#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include <string.h>

const char *author = "Afonso Rio";

#include "our_ints.h"
#include "our_doubles.h"
#include "our_strings.h"

#define MAX_NAVIOS 100000

typedef struct {
int x;
int y;
const char *navio;
} Navios;

// char* str_cpy(char *r, const char *s)
// {
//   int n = 0;
//   for (int i = 0; s[i]; i++)
//     r[n++] = s[i];
//   r[n] = '\0';
//   return r;
// }

Navios navios(int x, int y, const char *navio)
{
Navios result;
result.x = x;
result.y = y;
result.navio = navio;
return result;
}

char *str_dup2(const char *s)
{
  char *result = (char *) malloc(strlen(s) + 1);
  strcpy(result, s);
  return result;
}

int navios_read(Navios *a)
{
	int result = 0;
	int x;
	int y;
	char navio[32];
	while (scanf("%d%d%s", &x, &y, navio) != EOF)
		a[result++] = navios(x, y, str_dup2(navio));
	return result;
}

int navios_quantity(Navios *a, int n)
{
	int result = 0;
	for (int i = 0; i < n; i++)
	{
		if(a[i].x <= 7 && a[i].x >= -3 && a[i].y <= 6 && a[i].y >= -4) 
		{
		   result++;
	   }
}
return result;
}

int navios_write(const Navios *a, int n, const char **x)
{
 int result = 0;
    for (int i = 0; i < n; i++)
    {
	if(a[i].x <= 7 && a[i].x >= -3 && a[i].y <= 6 && a[i].y >= -4) 
      {
        x[result++] = str_dup(a[i].navio);
       }
     }
     return result;
}

// void navios_write2(FILE *f, Point *a, int n)
// {
// 	for (int i = 0; i < n; i++)
// 	{
// 		if(a[i].x <= 7 && a[i].x >= -3 && a[i].y <= 6 && a[i].y >= -4) 
// 		{
// 		   fprintf(f, "%s\n", a[i].navio);	 
// 	   }
// }
// }

void test_navio()
{
const char *x[MAX_NAVIOS];
Navios navios[MAX_NAVIOS];
int n_navios = navios_read(navios);
int y = navios_quantity(navios, n_navios);
//navios_write2(stdout, navios, n_navios);
int m = navios_write(navios, n_navios, x);
strings_isort(x, m);
printf("%d\n", y);
strings_print_basic(x, m);
}

int main()
{
    test_navio();
    return 0;
}