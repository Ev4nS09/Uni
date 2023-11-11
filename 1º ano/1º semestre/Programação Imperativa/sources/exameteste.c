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

const char *str_dup(const char *s)
{
  char *result = (char *) malloc(strlen(s) + 1);
  strcpy(result, s);
  return result;
}

Point point(int x, int y, const char *navio)
{
Point result;
result.x = x;
result.y = y;
result.navio = navio;
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
		  // fprintf(f,"%s\n", a[i].navio);	 
	   }
}
printf("%d\n", result);
}

int navios_write2(FILE *f, Point *a, int n, char *b)
{
	int result = 0;
	for (int i = 0; i < n; i++)
	{
		if(a[i].x <= 7 && a[i].x >= -3 && a[i].y <= 6 && a[i].y >= -4) 
		{
		   a[i].navio = b[result++];
	   }
}
b[result] = '\0';
return b;
}

int array(char *b, char *c, int n) 
{
	int result = 0;
   for (int i = 0; i < n; i++) 
   {
      for (int j = i+1; j < n; j++)
         if (strcmp(b[i], b[j]) > 0) 
         {
            char* temp = b[i]; 
            b[i] = b[j]; 
            b[j] = temp; 
         }
         return c[result++] = b[i];
     }
   getchar();
}


void test_navio()
{
 char b[100];
 char c[100];
FILE *f = fopen("navios.txt", "r");
assert(f != NULL);
Point navios[MAX_NAVIOS];
int n_navios = navios_read(f, navios);
navios_write(stdout, navios, n_navios);
int m = navios_write2(f, navios, n_navios, b);
int j = array(b, c, m);
ints_println_basic(c, j);
}

int main()
{
	test_navio();
	return 0;
}