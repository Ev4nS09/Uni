// less_equal, elementary, recursive.

#include <stdio.h>

const char *author = "Afonso Rio";

int succ(int x);
int pred(int x);
int is_zero(int x);
int is_pos(int x);

int less_equal(int x, int y)
{
 return is_zero(y) ? x : less_equal(x, y);
}

int main(void)
{
 int x;
 int y;
 scanf("%d%d", &x, &y);
 int z = less_equal(x, y);
 printf("%d\n", z);
 return 0;
}