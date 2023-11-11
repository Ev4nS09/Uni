//FESTA NORMAL

#include <stdio.h>
#include <math.h>
#include "our_ints.h"
#include <assert.h>

const char *author = "Afonso Rio";

char *al_gharb(char *r, const char *s)
{
  int n = 0;
  for (int i = 0; s[i]; i++)
  {
    r[n++] = 48 + s[i] % 10;
    r[n++] = 48 + s[i] / 10 % 10;
  }
  r[n] = 0;
  return r;
}

   char *inverse_al_gharb(const char *r, char *s)
   {
	  int n = 0;
	  for(int i = 0; r[i]; i++)
	  {
	  	s[n++] = r[i] * 10 - 48 ;
	  	s[n++] = r[i] * 10 * 10 - 48;
	  }
     s[n] = 0;
     return s;
   }

void test_p1(void)
{
char a[100];
char b[100];
while(scanf("%s", a) != EOF)
{
  char *m = inverse_al_gharb(a, b);
  char *n = al_gharb(b, a);
  printf("%s\n", m);
  printf("%s\n", n);
}	

}

int main()
{
test_p1();
return 0;
}