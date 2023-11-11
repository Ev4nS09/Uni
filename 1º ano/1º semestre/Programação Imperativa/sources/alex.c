#include <stdio.h>
#include <math.h>
#include <assert.h>
#include "our_ints.h"

int ints_greather_than (const int *a, int n, int min, int *b)
{
	int result = 0;
	for (int i = 0; i < n; i++)
		if (a[i] > min)
			b[result++] = a[i];
	return result;
}

void test_ints_greather_than (void)
{
	int a[1000];
	int n = ints_get_until(-1, a);
	int min;
	while (scanf("%d", &min) != EOF)
	{
		int b[1000];
		int m = ints_greather_than(a, n, min, b);
		ints_println_special(b, m);
	}
}

//B

int ints_less_than (const int *a, int n, int min, int *b)
{
	int result = 0;
	for (int i = 0; i < n; i++)
		if (a[i] < min)
			b[result++] = a[i];
	return result;
}

void test_ints_less_than (void)
{
	int a[1000];
	int n = ints_get_until(-1, a);
	int min;
	while (scanf("%d", &min) != EOF)
	{
		int b[1000];
		int m = ints_less_than(a, n, min, b);
		ints_println_special(b, m);
	}
}

//C

int ints_weight (int x)
{
	int r;
	int weight = 0;
	while (x > 0)
	{
		r = x % 10;
		weight += r;
		x = x / 10;
	}
	return weight;
}

int digits_sum (const int *a, int n, int *b)
{
	int result = 0;
	for (int i = 0; i < n; i++)
	{
		assert(a[i] >= 0);
		b[result++] = ints_weight(a[i]);
	}
	return result;
}

void test_digits_sum (void)
{
	int a[1000];
	int n = ints_get_until(-1, a);
	int b[1000];
	int m = digits_sum(a, n, b);
	ints_println_special(b, m);
}

//D

int ints_append (const int *a, int n, const int *b, int m, int *c)
{
	int result = 0;
	for (int ia = 0; ia < n; ia++)
		c[result++] = a[ia];
	for (int ib = 0; ib < m; ib++)
		c[result++] = b[ib];
	return result;
}

void test_ints_append (void)
{
int a[1000];
int n = ints_get_until(-1, a);
int b[1000];
int m = ints_get_until(-1, b);
int c[1000];
int o = ints_append(a, n, b, m, c);
int d[1000];
int p = ints_append(b, m, a, n, d);
ints_println_special(c, o);
ints_println_special(d, p);
}

//E

int ints_take (const int *a, int n, int x, int *b)
{
	int result = 0;
	for (int i = 0; i < x && i < n ; i++)
		b[result++] = a[i];
	return result;
}

void test_ints_take (void)
{
	int a[1000];
	int n = ints_get_until(-1, a);
	int x;
	while (scanf("%d", &x) != EOF)
	{
		int b[1000];
		int m = ints_take(a, n, x, b);
		ints_println_special(b, m);
	}
}

//F

int ints_drop (const int *a, int n, int x, int *b)
{
	int result = 0;
	for (int i = 0; i < n; i++)
		if (i >= x)
			b[result++] = a[i];
	return result;
}

void test_ints_drop (void)
{
	int a[1000];
	int n = ints_get_until(-1, a);
	int x;
	while (scanf("%d", &x) != EOF)
	{
		int b[1000];
		int m = ints_drop(a, n, x, b);
		ints_println_special(b, m);
	}
}

//G

int ints_ascending (const int *a, int n, int *b)
{
	int result = 0;
	for (int i = 0; i < n; i++)
		if (a[i] >= a[i + 1])
			b[result++] = a[i];
	return result;
}

void test_ints_ascending (void)
{
	int a[1000];
	int n = ints_get_until(-1, a);
	int b[1000];
	int m = ints_ascending(a, n, b);
	ints_println_special(b, m);
}

//

int main ()
{
	test_ints_greather_than();
	return 0;
}