//FESTA NORMAL

#include <stdio.h>
#include <math.h>
#include "our_ints.h"
#include <assert.h>

		 // c = str_find(r+k, y[i]);
		 // result = k + c + 1;	
		 // if(c != -1)
		 //   s[result++] = y[i];  

const char *author = "Afonso Rio";

 int str_find(const char* a, char x)
 {
 	for(int i = 0; a[i] != '\0'; i++)
 	{
 		if(a[i] == x)
 		 return i;
 	}
 	return -1;
 }

char *linear_b(char *r, const char *s)
{
	int result = 0;
	char y[10] = "aeiou";
	for(int i = 0; s[i] != '\0'; i++)
    {
     if(str_find(y, s[i]) != -1)
     {
        r[result++] = s[i];
        r[result++] = '-';
     }
     	 if(str_find(y, s[i]) == -1 && str_find(y, s[i+1]) == -1)
     	{
	 	r[result++] = s[i];
	    r[result++] = 'e';
	    r[result++] = '-';
	    }
      else if(str_find(y, s[i]) == -1)
        r[result++] = s[i];
	}
	r[result-1] = '\0';
	return r;
}


void test_linearb(void)
{
 char r[100];
 char s[100];
 while(scanf("%s", s) != EOF)
  {
  	linear_b(r, s);
  	printf("%s\n", r);
  }
}

int main()
{
test_linearb();
return 0;
}