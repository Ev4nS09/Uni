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

void get_system(int x, double m[x][x+1])
{
doubles_get_some((double*)m, x*(x + 1));
}

void print_system(int x, const double m[x][x+1])
{
  for (int i = 0; i < x; i++)
    doubles_printfln("%12f", m[i], x+1);
}

void test_print_system(void)
{
  int r = int_get();
  double m[r][r+1];
  get_system(r, m);
  print_system(r, m);
}

//B

int is_solution_deep(int x, double m[x][x+1], double *n, int z)
{
  int result = 0;
  int u = 0;
  for(int i = 0; i < x; i++)
  {
  result += m[z][i] * n[i];
  }
  if(result == m[z][x])
    u++;
  return u;
}


int is_solution(int x, double m[x][x+1], double *n)
{
	int result = 0;
  int s = 0;
    for(int i = 0; i < x; i++)
    result += is_solution_deep(x, m, n, i); 
  if(result == x)
    s = 1;
  else
    s = 0;
  return s;
}

void test_is_solution(void)
{
  int i = 0;
  int r = int_get();
  double m[r][r+1];
  get_system(r, m);
  double n[100];
  int z = doubles_get(n);
  while(i < z)
  {
  int k = is_solution(r, m, n + i);
  printf("%d\n", k);
  i += r;
 }
}

//C

void test_brute_force(void)
{

  int i = 0;
  int r = int_get();
  double m[r][r+1];
  get_system(r, m);
  double n[100];
  int z = doubles_get(n);
  while(i < z)
  {
  int k = is_solution(r, m, n + i);
  if(k == 1)
   {
     for(int j = i; j < r; j++)
     {
      printf("%.2lf ", n[j]);
     }
    printf("\n");
   }
  i += r;
 }
  
}


int main(int argc, const char **argv)
{
// unit_tests();
int x = 'U';
if (argc > 1)
x = *argv[1];
if (x == 'A')
test_print_system();
 	else if (x == 'B')
 	test_is_solution();
 	else if (x == 'C')
 	test_brute_force();
// 	else if (x == 'D')
// 	test_player_D(filename);
// 	else if (x == 'E')
// 	test_player_E(filename);
// 	 else if (x == 'F')
// 	test_player_F(filename);
 else if (x == 'U')
printf("All unit tests PASSED\n");
else
printf("%s: Invalid option.\n", argv[1]);
return 0;
}