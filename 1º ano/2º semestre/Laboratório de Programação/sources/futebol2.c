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
 const char *nome;
 int inter;
 int golos;
 const char *posi;
 const char *clube;
} Futebol;

Futebol futebol(const char *nome, int inter, int golos, const char *posi, const char *clube)
{
	Futebol result;
    result.nome = nome;
    result.inter = inter;
    result.golos = golos;
    result.posi = posi;
    result.clube = clube;
    return result;
}

// int espaco(const char *a)
// {
// 	int result = 0;
// 	int n = str_len(a);
// 	for(int i = n - 1; i >= 0 ; i--)
// 	{
// 		if(a[i] == ' ')
//            result = i + 1
            
// 	}
// 	return result;
// }

// int charg(const char *a)
// {
// 	int result = 0;
// 	int n = str_len(a);
// 	for(int i = n - 1; i >= 0 ; i--)
// 	{
// 		if(a[i] == 'G')
//            result = i - 1;
            
// 	}
// 	return result;
// }

int posi_golo(const char *a)
{
	int result = 0;
	for(int i = 0; a[i]; i++)
	{
		if(a[i] == 'G')
			result = i + 7;
	}
		return result;
}

char *golos(const char*a, char*y)
{
	int result = 0;
	for(int i = posi_golo(a); a[i]; i++)
		if(a[i] == '0' || a[i] == '1' || a[i] == '2' || a[i] == '3' || a[i] == '4' || a[i] == '5' || a[i] == '6' || a[i] == '7' || a[i] == '8' || a[i] == '9')
   y[result++] = a[i];

y[result] = '\0';
return y;
}

char *inter(const char *a, char *y)
{
int result = 0;
for(int i = 0; a[i] != 'G'; i++)
	if(a[i] == '0' || a[i] == '1' || a[i] == '2' || a[i] == '3' || a[i] == '4' || a[i] == '5' || a[i] == '6' || a[i] == '7' || a[i] == '8' || a[i] == '9')
   y[result++] = a[i];

y[result] = '\0';
return y;
}


char *position(const char*a, char *y)
{
	int result = 0;
	int k;
	for(int i = 0; a[i]; i++)
	{
		if(a[i] == ':')
			k = i + 2;

	}
	for(int j = k; a[j] != ' '; j++)
		y[result++] = a[j];

  y[result] = '\0';
  return y;
}


int read_futebol(Futebol *b, const char **a, int n)
{
	int result = 0;
	int i = 0;
	char y[1000];
	char u[1000];
	char j[1000];
	while(i < n)
	{
		b[result++] = futebol(a[i], atoi(inter(a[i + 1], u)), atoi(golos(a[i + 1], j)), position(a[i + 1], y), NULL);
		i += 3;

    }
return result;
}

void futebol_print(Futebol *b, int n)
{
	for(int i = 0; i < n; i++)
	{
		printf("%s,", b[i].nome);
		printf("%d,", b[i].inter);
		printf("%d,", b[i].golos);
		printf("%s,\n", b[i].posi);	
	}
}

void test_futebol(void)
{
    const char *a[1000];
    Futebol b[10000];
    int n = strings_get(a);
    int m = read_futebol(b, a, n);
    futebol_print(b, m);
}

int main()
{
	test_futebol();
	return 0;
}