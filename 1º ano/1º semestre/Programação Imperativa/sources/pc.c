#include <stdio.h>
#include "list.h"

double sq(double x)
{
 return x*x;
}
List squares(List s)
{
 return is_empty(s) ? NULL : cons(sq(head(s)),
squares(tail(s)));
}

void test_sq(void)
{
	List s = List squares;
	while(lst_scan(&s) != EOF)
	{
		int z1 = List s
		printf("%d\n", z1);
	}
}

int main(void)
{
	test_sq();
	return 0;
}