#include <stdio.h>
#include <math.h>
#include "our_ints.h"
#include <assert.h>

 int str_find(const char* a, char x)
 {
 	for(int i = 0; a[i] != '\0'; i++)
 	{
 		if(a[i] == x)
 		 return i;
 	}
 	return -1;
 }

 int bitcoin(const char* a, char* b)
 {
 	int c = 0;
 	int x = 0;
 	for(int i = 0; b[i] != '\0'; i++)
 	{
    c = str_find(a+x, b[i]);
    if(c == -1)
    	return 0;
    x = x + c + 1;
 	} 
 	return 1;    
 }

int find(char* a, char* b)
{
	int result = 0;
	int y = 0;
	for(int i = 0; a[i] != '\0' && y != 1; i++)
	{
		if(a[i] == b[0])
		{
			result = i;
		       y = 1;
		}
	}
  return result;
}
                                                     //find e find2 vão ajudar a ver se o array está na ordem que queremos.
int find2(char* a, char* b)                                 
{
	int result = 0;
	for(int i = 0; a[i] != '\0'; i++)
	{
		if(a[i] == b[1])
		{
			result = i;
		}
	}
  return result;
}

int find3(char* a, char* b)
{
	int result = 0;
	for(int i = 0; a[i] != '\0'; i++)
	{
		if(a[i] == b[2])
		{
			result = i;
		}
	}
	return result;
}

int find22(char* a, char* b)
{
	int result = 0;
	for(int i = find(a, b); a[i] != '\0' && i < find3(a, b); i++)
	{
		if(a[i] == b[1])
		{
			result = i;
		}
	}
  return result;
}


int paises(const char* a, char* b)
{
	int result = 0;
	for(int i = 0; a[i] != '\0'; i++)
	{
		if(a[i] == b[0])
		{
			result++;
		}
	}
   return result;
}
                                            //paises e paises2 vão verificar se as letras estão no array que metemos na consola.
int paises2(const char* a, char* b)
{
	int result = 0;
	for(int i = 0; a[i] != '\0'; i++)
	{
		if(a[i] == b[1])
			result++;

	}
   return result;
}

int paises3(const char* a, char* b)
{
	int result = 0;
	for(int i = 0; a[i] != '\0'; i++)
	{
		if(a[i] == b[2])
			result++;

	}
   return result;
}

void telegrama(const char* a, char* b)
{
	int result = 0;
	for(int i = 0; a[i] != '\0'; i++)
	{
		if(a[i] != '_')
			b[result++] = a[i];
	}
	b[result] = '\0';
}


void test_alpha2(void)
{
char a[1000];
char b[1000];
scanf("%s",b);
while(scanf("%s", a) != EOF)
{
  int n = paises(a, b) + paises2(a, b);	
  if(find(a, b) < find2(a, b))
   n = n;
    else
   n = 0;

if(n >= 2)
	printf("YES\n");
else
	printf("NO\n");
}
}

void test_alpha3(void)
{
char a[1000];
char b[1000];
scanf("%s",b);
while(scanf("%s", a) != EOF)
{
  int n = paises(a, b) + paises2(a, b) + paises3(a, b);	
  if(find(a, b) < find22(a, b) && find22(a, b) < find3(a, b))
   n = n;
    else
   n = 0;

if(n == 3)
	printf("YES\n");
else
	printf("NO\n");
}
}

void test_bitcoin(void)
{
	char a[100];
	char b[100];
  while(scanf("%s%s", b, a) != EOF)
  {
  	int n = bitcoin(a, b);
    if( n == 0)
    		printf("NO\n");
      else
	printf("YES\n");
  }
}

void test_telegrama(void)
{
	char a[1000];
	char b[1000];
	while(scanf("%s", a) != EOF)
	{
	 telegrama(a, b);
		printf("%s\n", b);
	}
}

int main(int argc, char **argv)
{
    //unit_tests();
    int x = 'A';
    if (argc > 1)
        x = *argv[1];
    if (x == 'A')
        test_alpha2();
    else if (x == 'B')
        test_alpha3();
    else if (x == 'C')
        test_bitcoin();
    else if (x == 'D')
        test_telegrama();
   // else if (x == 'U')
       // printf("All unit tests PASSED.\n");
    else
        printf("%s: Invalid option.\n", argv[1]);
    return 0;
}