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

void tetris_1(int r, int c, int m[r][c], int *a, int n)
{
 int i = 0;
 int rr = r - 1;
 int r1 = rr;
 while(i < n)
 {
 	if(m[rr][a[i + 3]] == 0)
 	{
 	m[rr][a[i + 3]] = a[i + 2];
    }
    else
    {
    	while(r1 >= 0)
    	{
    		 	if(m[r1][a[i + 3]] == 0)
    		 	{
 	              m[r1][a[i + 3]] = a[i + 2];
 	              r1 = -1;
    		 	}
 	            else
 	            	r1--;
    	}
    }
    r1 = rr;
 	i += 4;
 }
}

void get_system_0(int r, int c, int m[r][c])
{
	  for (int i = 0; i < r; i++)
        for (int j = 0; j < c; j++)
             m[i][j] = 0;
}


void matrix_printf2(int r, int c, const int m[r][c])
{
  for (int i = 0; i < r; i++)
        for (int j = 0; j < c; j++)
          printf("%d ", m[i][j]);
}

void print_system(int r, int c, const int m[r][c])
{
  for (int i = 0; i < r; i++)
    ints_printfln(" %d", m[i], c);
}


void test_festa_A(void)
{
  int c = int_get();  //largura do campo
  int r = int_get();  //altura do campo
  int m[r][c];
  get_system_0(r, c, m);
  int a[1000];
  int n = ints_get(a);
  tetris_1(r, c, m, a, n);
  print_system(r, c, m);
}

//B

int scan_line(int r, int c, int m[r][c])
{
	int x = 0;
	int result = 0;
	for (int i = 0; i < r; i++)
	{
        for (int j = 0; j < c; j++)
             {
             	if(m[i][j] != 0)
             		x++;
             }
             if(x == c)
             	result++;
             x = 0;
	}
	return result;
}

void new_matrix(int r, int c, int m[r][c], int z, int h[r][c])
{
	  for (int i = 0; i < r; i++)
        for (int j = 0; j < c; j++)
             h[i + z][j] = m[i][j];
}

void get_system_0_Z(int r, int c, int z, int h[r][c])
{
	  for (int i = 0; i < z; i++)
        for (int j = 0; j < c; j++)
             h[i][j] = 0;
}

void test_festa_B(void)
{
  int c = int_get();  //largura do campo
  int r = int_get();  //altura do campo
  int m[r][c];
  get_system_0(r, c, m);
  int a[1000];
  int n = ints_get(a);
  tetris_1(r, c, m, a, n);
    // print_system(r, c, m);
  int z = scan_line(r, c, m);
  int h[r][c];
  get_system_0(r, c, h);
  new_matrix(r, c, m, z, h);
  // get_system_0_Z(r, c, z, h);
  print_system(r, c, h);

}

//C

void test_festa_C(void)
{
	
}

//D

void test_festa_D(void)
{
	
}

int main(int argc, const char **argv)
{
// unit_tests();
int x = 'U';
if (argc > 1)
x = *argv[1];
if (x == 'A')
test_festa_A();
else if (x == 'B')
test_festa_B();
else if (x == 'C')
test_festa_C();
else if (x == 'D')
test_festa_D();
else if (x == 'U')
printf("All unit tests PASSED\n");
else
printf("%s: Invalid option.\n", argv[1]);
return 0;
}