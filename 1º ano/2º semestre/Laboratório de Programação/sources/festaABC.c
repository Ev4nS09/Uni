#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

const char *author = "Afonso Rio";

#include "our_ints.h"
#include "our_doubles.h"
#include "our_strings.h"

//A

const char *until_space(char *y, const char *a)
{
	int result = 0;
	for(int i = 0; a[i] != ' '; i++)
		y[result++] = a[i];
	  y[result] = '\0';
	return y;
}

int strings_count_while_3(const char **a, int n, const char *b, int j)
{
    int c = 0;
    char y[100];
    for(int i = j; i >= 0; i--)
    {
        if(strcmp(until_space(y, a[i]), b) == 0)
            c++;
    }
  return c;
}

int strings_count_while_2(const char **a, int n, const char *b)
{
    int c = 0;
    char y[100];
    for(int i = 0; i < n; i++)
    {
        if(strcmp(until_space(y, a[i]), b) == 0)
            c++;
    }
  return c;
}

char *virgula(const char *a, const char *b, char *s)
{
	int result = 0;
	for(int i = 0; a[i] != '\0'; i++)
	s[result++] = a[i];

s[result++] = ',';

	for(int j = 0; b[j] !='\0'; j++)
		s[result++] = b[j];

	s[result] = '\0';
	return s;
}

int nomes_nivel_1(const char **a, int n, const char **b)
{
  int result = 0;
  int i = 0;
  int d = 0;
  char y[1000];
  char s[1000];
  while(i < n)
  {
    d = strings_count_while_2(a + i, n - i, until_space(y, a[i]));
    int c = strings_count_while_3(a, n, until_space(y, a[i]), i);
    if(d <= 1 && c <= 1)
        b[result++] = str_dup(virgula(a[i], until_space(y, a[i]), s));
    else
    	b[result++] = str_dup(virgula(a[i], a[i], s));

    i++;
  }
  return result;
}

void test_festa_A(void)
{
const char *a[1000];
const char *b[1000];
int n = strings_get(a);
int m = nomes_nivel_1(a, n, b);
strings_print_basic(b, m);
}

//B

int until_second_space_2(const char *a)
{
    char x[100];
	int result = str_len(until_space(x, a));
	for(int i = str_len(until_space(x, a)); a[i] != ' '; i++)

	result++;

	return result + 1;
}

const char *until_second_space(char *y, const char *a)
{
	int result = 0;
	for(int i = 0; i < until_second_space_2(a); i++)
		y[result++] = a[i];
	y[result] = '\0';
	return y;
}

int strings_count_while_second(const char **a, int n, const char *b)
{
    int c = 0;
    char y[100];
    for(int i = 0; i < n; i++)
    {
        if(strcmp(until_second_space(y, a[i]), b) == 0)
            c++;
    }
  return c;
}

int nomes_nivel_2(const char **a, int n, const char **b)
{
  int result = 0;
  int i = 0;
  char y[100];
  char s[100];
  while(i < n)
  {
  	int d = strings_count_while_2(a, n, until_space(y, a[i]));
  	int c = strings_count_while_second(a, n, until_second_space(y, a[i]));
        if(c != 1 && d == 1)
        b[result++] = str_dup(virgula(a[i], until_second_space(y, a[i]), s));
        else if(c == 1)
        b[result++] = str_dup(virgula(a[i], until_space(y, a[i]), s));
        else
    	b[result++] = str_dup(virgula(a[i], a[i], s));

    i++;
  }
  return result;
}



void test_festa_B(void)
{
const char *a[100];
const char *b[100];
int n = strings_get(a);
int m = nomes_nivel_2(a, n, b);
strings_print_basic(b, m);
}

//C

void festa_C(void)
{

}

void test_festa_C(void)
{

}


int main(int argc, const char **argv)
{
// unit_tests();
int x = 'U';
if (argc > 1)
x = *argv[1];
if (x == 'A')
test_festa_A();
else if (x == 'B')
test_festa_B();
else if (x == 'C')
test_festa_C();
else if (x == 'U')
printf("All unit tests PASSED\n");
else
printf("%s: Invalid option.\n", argv[1]);
return 0;
}