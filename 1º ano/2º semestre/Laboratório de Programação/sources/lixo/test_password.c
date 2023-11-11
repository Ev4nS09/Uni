#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include <string.h>

const char *author = "Afonso Rio";

#include "our_ints.h"
#include "our_doubles.h"
#include "our_strings.h"

// int passx(const char x)
// {
//   return ((x - 'a') + 1)%26;
// }

int length(char *s)
{
  int result = 0;
  for(int i = 0; s[i] != '\0'; i++)
  {
    if(s[i] != '\0')
      result++;
  }
  return result;
}

char pass(char s)
{
  return (((s - 'a') + 5) % 26) + 'a';
}

char *password(const char *s, char *w)
{
int result = 0;
for(int i = 0; s[i] != '\0'; i++)
{
  w[result++] = pass(s[i]);
}
w[result++] = '\0';
return w;
}

void test_password(void)
{
  // char r[100];
  char s[100];
  char w[100];
  while (scanf("%s", s) != EOF)
  {
  password(s, w);
  // int z = passx('d');
  printf("%s\n", w);
  int z = length(s);
  // printf("%d", z);
  printf("%d\n", z);
  }
}

int main()
{
  test_password();
  return 0;
}