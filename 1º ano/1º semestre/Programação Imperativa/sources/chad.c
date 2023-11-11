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

void test_telegrama(void)
{
	char a[1000];
	char b[5] = "alex";
	printf("es gay? Digita o teu nome:");
	while(scanf("%s", a) != EOF)
	{
		int n = bitcoin(a, b);
	 if(n == 1)
	 	printf("GAY\n");
	   else
	   	printf("CHAD\n");
	   printf("es gay? Digita o teu nome:");
	}
}

int main()
{
	test_telegrama();
	return 0;
}