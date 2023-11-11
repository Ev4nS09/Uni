#include <stdio.h>
#include <math.h>

int digits_positive(int x, int *a)
{
int result = 0;
while (x > 0)
{
a[result++] = x % 10;
x /= 10;
}
return result;
}

int digits(int x, int *a)
{
    int result = 1;
    a[0] = 0;
    if (x > 0)
        digits_positive(x, a);
      return result;
}

void ints_println_basic(const int *a, int n)
{
if (n > 0)
{
printf("%d", a[0]);
for (int i = 1; i < n; i++) // i = 1
printf(" %d", a[i]);
}
printf("\n");
}

int mirror(const int *a, int n, int *b)
{
int result = 0;
for (int i = n-1; i >= 0; i--)
b[result++] = a[i];
return result;
}

int ints_get(int *a)
{
int result = 0;
int x;
while (scanf("%d", &x) != EOF)
a[result++] = x;
return result;
}

// int even_values_minus_odd_values(int x, int n, int *a)
// {
//     int result = 0;
//     for (int i = 0; i < n; i++)
//         if (a[i] % 2 == 0)
// }

int main()
{
int a[1000];
int n = ints_get(a);
int b[1000];
int m = mirror(a, n, b);
ints_println_basic(a, n);
ints_println_basic(b, m);
printf("%d\n", ints_get(a));
return 0;
}

