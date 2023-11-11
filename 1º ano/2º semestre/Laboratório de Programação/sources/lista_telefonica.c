#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

const char *author = "Afonso Rio";

#include "our_ints.h"
#include "our_doubles.h"
#include "our_strings.h"

const char *last_word(const char *a)
{
	int result = 0;
	int c = 0;
	int u = str_len(a);
	for(int i = u - 1; a[i] && c != 1; i--)
		if(a[i] == ' ')
		{
			result = i;
			c = 1;
		}
	return a + result;
}

void strings_exchange_apelido(const char **a, int x, int y)
{
  const char *m = a[x];
  a[x] = a[y];
  a[y] = m;
}

void strings_isort_apelidos(const char **a, int n)
{
  for (int i = 1; i < n; i++)
  {
    int j = i;
    while (j > 0 && strcmp(last_word(a[j-1]), last_word(a[j])) > 0)
    {
      strings_exchange(a, j-1, j);
      j--;
    }
  }
}

void test_string_int(void)
{
// const char *x[1000];
const char *a[1000];
int n = strings_get(a);
strings_isort(a, n);
// int y = unique_first_names(x, n, a);
strings_isort_apelidos(a, n);
strings_print_basic(a, n);
}

int main()
{
	test_string_int();
	return 0;
}