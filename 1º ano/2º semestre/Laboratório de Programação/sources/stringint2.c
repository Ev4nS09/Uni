#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

const char *author = "Afonso Rio";

#include "our_ints.h"
#include "our_doubles.h"
#include "our_strings.h"

typedef struct {
 const char *string;
 int number;
} StringInt;

StringInt stringint(const char *string, int number)
{
    StringInt result;
    result.string = string;
    result.number = number;
    return result;
}

int until_space(char *x, const char *a)
{
    int result = 0;
    for(int i = 0; a[i] != ' '; i++)
        x[result++] = a[i];
      x[result] = '\0';
    return result;

}

int unique_first_names(const char **x, int n, const char **a)
{
    char y[100];
    int result = 0;
    for(int i = 0; i < n; i++)
    {
        until_space(y, a[i]);
        x[result++] = str_dup(y);
    }
    return result;
}

int strings_count_while_2(const char **a, int n, const char *b)
{
    int c = 0;
    for(int i = 0; i < n; i++)
    {
        if(strcmp(a[i], b) == 0)
            c++;
    }
  return c;
}

int strings_tally(const char **a, int n, StringInt *b)
{
  int result = 0;
  int i = 0;
  while (i < n)
  {
    int c = strings_count_while_2(a+i, n-i, a[i]);
    b[result++] = stringint(str_dup(a[i]), c);
    i += c;
  }
  return result;
}

void struct_exchange(StringInt *a, int x, int y)
{
  StringInt m = a[x];
  a[x] = a[y];
  a[y] = m;
}

void string_ints_isort(StringInt *a, int n)
{
  for (int i = 1; i < n; i++)
  {
    int j = i;
    while (j > 0 && a[j-1].number < a[j].number)
    {
      struct_exchange(a, j-1, j);
      j--;
    }
  }
}

void string_ints_print(const StringInt *a, int n)
{
for(int i = 0; i < n; i++)
{
    printf("%d ", a[i].number);
    printf("%s\n", a[i].string);
}
}

void test_string_int(void)
{
const char *x[1000];
const char *a[1000];
StringInt b[1000];
int n = strings_get(a);
int m = unique_first_names(x, n, a);
strings_isort(x, m);
int s = strings_tally(x, m, b);
string_ints_isort(b, s);
string_ints_print(b, s);
}

int main()
{
    test_string_int();
    return 0;
}