#include <stdio.h>
#include <math.h>
#include "our_ints.h"
#include <assert.h>

typedef struct {
int x;
int y;
} Point;

Point point(int x, int y)
{
Point result;
result.x = x;
result.y = y;
return result;
}

//Euclidean distance
double distance(Point p, Point q)
{
return sqrt((p.x-q.x)*(p.x-q.x) + (p.y-q.y)*(p.y-q.y));
}

//q.y - p.y r.y - q.y
//--------- == ---------
//q.x - p.x r.x - q.x
int collinear(Point p, Point q, Point r)
{
return (q.x-p.x)*(r.y-p.y) == (r.x-p.x)*(q.y-p.y);
}


void test_points(void)
{
int x1, y1, x2, y2, x3, y3;
while (scanf("%d%d%d%d%d%d", &x1, &y1, &x2, &y2, &x3, &y3) != EOF)
{
Point p1 = point(x1, y1);
Point p2 = point(x2, y2);
Point p3 = point(x3, y3);
double d12 = distance(p1, p2);
double d23 = distance(p2, p3);
double d31 = distance(p3, p1);
int c = collinear(p1, p2, p3);
printf("%.4f %.4f %.4f\n", d12, d23, d31);
printf("%d\n", c);
}
}

int main()
{
	test_points();
	return 0;
}