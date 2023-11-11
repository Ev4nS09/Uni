#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include <string.h>

const char *author = "Afonso Rio";

#include "our_ints.h"
#include "our_doubles.h"
#include "our_strings.h"

int length(const char *s)
{
  int result = 0;
  for(int i = 0; s[i] != '\0'; i++)
  {
    if(s[i] != '\0')
      result++;
  }
  return result;
}

int passx(const char x)
{
  return ((x - 'a') + 1) %26;
}

char pass(char s, int r)
{
  return (((s - 'a') + r) % 26) + 'a';
}

void espaco(char* s, char *b)
{
  int result = 0;
  for(int i = 0; s[i] != '\0'; i++)
  {
    if(s[i] != ' ')
      b[result++] = s[i];
  }
  b[result] = '\0';
}

char *password( char *s, char *r, char *w)
{
  int result = 0;
  int i = 0;
  int j = 0;
while(i <= length(s) - 1 && j <= length(r))
{
if(j == length(r))
{
  j = 0;
}
else
{
  w[result++] = pass(s[i], passx(r[j]));
  i++;
  j++;
}

}
w[result++] = '\0';
return w;

}

void test_password(void)
{
  char r[100];
  char s[100];
  char w[100];
  char b[100];
  str_getline(s);
  espaco(s, b);
  while (scanf("%s", r) != EOF)
  {
  password(b, r, w);
  printf("%s\n", w);
  }
}

int main()
{
  test_password();
  return 0;
}