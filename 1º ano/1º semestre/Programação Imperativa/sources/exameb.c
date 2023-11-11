#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include <string.h>

const char *author = "Afonso Rio";

#include "list.h"
#include "our_ints.h"
#include "our_doubles.h"
#include "our_ints.h"

#define MAX_NAVIOS 10000

typedef struct {
int x;
int y;
const char *navio;
} Point;

char* str_cpy(char *r, const char *s)
{
  int n = 0;
  for (int i = 0; s[i]; i++)
    r[n++] = s[i];
  r[n] = '\0';
  return r;
}

Point point(int x, int y, const char *navio)
{
Point result;
result.x = x;
result.y = y;
result.navio = navio;
return result;
}

char *str_dup(const char *s)
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
		{
		   result++;
	   }
}
printf("%d\n", result);
}

char *array(FILE *f, Point *a, int n)
{
    char *x;
    for (int i = 0; i < n; i++)
{
	if(a[i].x <= 7 && a[i].x >= -3 && a[i].y <= 6 && a[i].y >= -4) 
{
        x = str_dup(a[i].navio);
    }
}

return x;

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
FILE *f = fopen("navios.txt", "r");
assert(f != NULL);
Point navios[MAX_NAVIOS];
int n_navios = navios_read(f, navios);
navios_write(stdout, navios, n_navios);
//navios_write2(stdout, navios, n_navios);
char *x = array(stdout, navios, n_navios);
printf("%s", x);
}

int main()
{
	test_navio();
	return 0;
}