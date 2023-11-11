#include <stdio.h>
#include <math.h>
#include "our_ints.h"
#include <assert.h>

typedef struct{
int x;
int y;
}Point;

Point point(int x, int y)
{
	Point result;
	result.x = x;
	result.y = y;
	return result;
}

int vetorx(Point p, Point q)
{
	return (q.x - p.x);
}

int vetory(Point p, Point q)
{
	return (q.y - p.y);
}

double norma(int v1, int v2)
{
  return sqrt(pow(v1, 2) + pow(v2, 2));
}

void test_vetor(void)
{
  int x1, y1, x2, y2;
  while(scanf("%d%d%d%d", &x1, &y1, &x2, &y2) != EOF)
  {
  	Point d1 = point(x1, y1);
  	Point d2 = point(x2, y2);
  	int v1 = vetorx(d1, d2);
  	int v2 = vetory(d1, d2);
  	double m = norma(v1, v2);
  	printf("("); printf("%d", v1); printf(", "); printf("%d", v2); printf(")\n");
    printf("%.2lf\n", m);
  }
}

int main()
{
	test_vetor();
	return 0;
}




