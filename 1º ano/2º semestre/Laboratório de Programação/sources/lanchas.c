#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

const char *author = "Afonso Rio";

#include "our_ints.h"
#include "our_doubles.h"
#include "our_strings.h"

#define MAX_NAVIOS 10000


typedef struct {
double x;
double y;
const char *navio;
} Point;

Point point(double x, double y, const char *navio)
{
Point result;
result.x = x;
result.y = y;
result.navio = navio;
return result;
}

// char *str_dup(const char *s)
// {
//   char *result = (char *) malloc(strlen(s) + 1);
//   strcpy(result, s);
//   return result;
// }

// void strings_exchange(const Point **a, int x, int y)
// {
//   const char *m = a[x].navio;
//   a[x].navio = a[y].navio;
//   a[y].navio = m;
// }

// void strings_isort(const Point **a, int n)
// {
//   for (int i = 1; i < n; i++)
//   {
//     int j = i;
//     while (j > 0 && strcmp(a[j-1].navio, a[j].navio) > 0)
//     {
//       strings_exchange(a, j-1, j);
//       j--;
//     }
//   }
// }

double navios_read(Point *a)
{
    int result = 0;
    double x;
    double y;
    char navio[32];
    while (scanf("%lf%lf%s", &x, &y, navio) != EOF)
        a[result++] = point(x, y, str_dup(navio));
    return result;
}



int navios_write2(Point *a, int n)
{
    int i = 0;
    int j = 1;
    int result = 0;
    while(i < n - 1 && j < n)
    { 
       
         if(strcmp(a[i].navio, a[j].navio) == 0 && ceil(sqrt(pow(a[i].x -a[j].x, 2) + pow(a[i].y -a[j].y, 2))) > 5)
         {
            i++;
            j = i + 1;
            result++;
        }
        else if(strcmp(a[i].navio, a[j].navio) != 0 && j == n - 1)
        {
          i++;
          j = i + 1; 
       }
       else
        j++;
    }
        return result;
// printf("%d\n", result2);

}

void lanchas_write(Point *a, int n)
{
    for (int i = 0; i < n; i++)
        printf("[%lf][%lf][%s]\n", a[i].x, a[i].y, a[i].navio);
}

int organize(const char **a, int n, const char **c)
{
    int result = 0;
    int i = 0;
    int j = 1;
    while(i < n && j < n)
    {
       if(strcmp(a[i], a[j]) == 0)
       {
        j++;
        i++;
      }
    else if(strcmp(a[i], a[j]) != 0 && j == n - 1)
    {
        c[result++] = str_dup(a[i]);
        i++;
        j = i + 1;
    }
    else
        j++;
    }
    return result;
}

// double raiz(double x1, double x2, double y1, double y2)
// {
//     double q = x1 - x2;
//     double p = y1 - y2;
//     return sqrt(pow(q, 2) + pow(p, 2));
// }

double raiz(Point a, Point b)
{
    return sqrt(pow(a.x - b.x, 2) + pow(a.y - a.y,2));
}

double navios_write(Point *a, int n, const char **p)
{
   int i = 0;
    int j = 1;
    int result = 0;
    while(i < n - 1 && j < n)
    { 
        if(strcmp(a[i].navio, a[j].navio) == 0 && raiz(a[i], a[j]) > 5)
         {
            i++;
            j = i + 1;
            p[result++] = str_dup(a[i].navio);

         }
        else if(strcmp(a[i].navio, a[n - 1].navio) != 0 && j == n - 1)
        {
          i++;
          j = i + 1; 
       }
        else if(strcmp(a[i].navio, a[j].navio) == 0 && raiz(a[i], a[j]) <= 5)
         j++;
    }
        return result;

}


void test_navio()
{
const char *p[1000];
 //const char *c[MAX_NAVIOS];
Point navios[MAX_NAVIOS];
int n_navios = navios_read(navios);
int m = navios_write(navios, n_navios, p);
//int u = navios_write2(navios, n_navios);
lanchas_write(navios, n_navios);
 //printf("%d\n", u);
 printf("%d\n", n_navios);
 //int t = organize(p, m, c);
 strings_isort(p, m);
 strings_print_basic(p, m);

}

int main()
{
    test_navio();
    return 0;
}