#include <stdio.h>
#include <math.h>
#include "our_ints.h"
#include <assert.h>

// int find(const char* a, char* x)
// {
// 	int result = 0;
// 	for(int i = 0; a[i] != '\0'; i++)
// 	{
// 		if(a[i] == x)
// 		 result++;
//     }
// 		return result;
// }

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
	int y = 0;
	for(int i = 0; a[i] != '\0' && y != 1; i++)
	{
		if(a[i] == b[1])
		{
			result = i;
		       y = 1;
		}
	}
  return result;
}

int find3(char* a, char* b)
{
	int result = 0;
	int y = 0;
	for(int i = 0; a[i] != '\0' && y != 1; i++)
	{
		if(a[i] == b[2])
		{
			result = i;
			y = 1;
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

void unit_test_alpha2(void)
{
 assert(alpha2("pt", "portugal"));
 assert(alpha2("fr", "franca"));
 assert(alpha2("lk", "sri lanka"));
 assert(!alpha2("de", "alemanha"));
 assert(!alpha2("zb", "brazil"));
}

void test_paises(void)
{
char a[1000];
char b[1000];
scanf("%s",b);
while(scanf("%s", a) != EOF)
{
  int n = paises(a, b) + paises2(a, b) + paises3(a, b);	
  if(find(a, b) < find2(a, b) && find2(a, b) < find3(a, b))
   n = n;
    else
   n = 0;

if(n >= 3)
	printf("YES\n");
else
	printf("NO\n");
}
}

int main()
{
	test_paises();
	return 0;
}