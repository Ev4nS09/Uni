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
 const char *_1;
 const char *_2;
} StringString;

StringString stringstring(const char *_1, const char *_2)
{
	  StringString result;
    result._1 = _1;
    result._2 = _2;
    return result;
}

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

int stringstring_values(const char **a, int n, StringString *b)
{
	int result = 0;
	for(int i = 0; i < n; i++)
		b[result++] = stringstring(str_dup(a[i]), str_dup(last_word(a[i])));
	return result;
}

void struct_exchange_apelidos(StringString *a, int x, int y)
{
  StringString m = a[x];
  a[x] = a[y];
  a[y] = m;
}

void strings_isort_apelidos(StringString *a, int n)
{
  for (int i = 1; i < n; i++)
  {
    int j = i;
    while (j > 0 && strcmp(a[j-1]._2, a[j]._2) > 0)
    {
      struct_exchange_apelidos(a, j-1, j);
      j--;
    }
  }
}

void stringstring_print_basic(StringString *b, int n)
{
   for(int i = 0; i < n; i++)
   	printf("%s\n", b[i]._1);
}

void test_string_int(void)
{
const char *a[1000];
StringString b[1000];
int n = strings_get(a);
strings_isort(a, n);
int y = stringstring_values(a, n, b);
strings_isort_apelidos(b, y);
stringstring_print_basic(b, y);
}

int main()
{
	test_string_int();
	return 0;
}