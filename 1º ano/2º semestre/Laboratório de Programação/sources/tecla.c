#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include <string.h>

const char *author = "Afonso Rio";

#include "our_ints.h"
#include "our_doubles.h"
#include "our_strings.h"

char *tecla(const char *a, char *r)
{
	int result = 0;
	for(int i = 0; a[i] != '\0'; i++)
	 {
	 	if(a[i - 1] == a[i] && a[i - 2] != a[i - 1])
	 		r[result++] = a[i];
	 	else if(a[i - 1] != a[i] && a[i - 2] != a[i - 1])
	 		r[result++] = a[i];	 
	 	else if(a[i] != a[i - 1] && a[i] != a[i - 2])
	 		r[result++] = a[i];	
	 }
	 r[result] = '\0';
	 return r;
}

void test_tecla(void)
{
 char r[100];
 char a[100];
 while(scanf("%s", a) != EOF)
 {
 	tecla(a, r);
 	printf("%s\n", r);
 }
}

void unit_test_tecla(void)
{
  char s[1000];
  assert(strcmp(tecla("carrrrrrro", s), "carro") == 0);
  assert(strcmp(tecla("interesssssssssante", s), "interessante") == 0);
  assert(strcmp(tecla("coooooperar", s), "cooperar") == 0);
  assert(strcmp(tecla("guerrrrrrrrra", s), "guerra") == 0);
  assert(strcmp(tecla("corrrrrrrer", s), "correr") == 0);
  assert(strcmp(tecla("arrrrrroz", s), "arroz") == 0);
  assert(strcmp(tecla("beterrrrrrraba", s), "beterraba") == 0);
}

int main()
{
	test_tecla();
	unit_test_tecla();
	return 0;
}