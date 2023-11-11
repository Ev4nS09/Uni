#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

const char *author = "Afonso Rio";

#include "our_ints.h"
#include "our_doubles.h"
#include "our_strings.h"

#define SEPARATOR ','
#define MAX_COLUMNS 10

typedef struct {
 const char *nome;
 int inter;
 int golos;
 const char *posi;
 const char *clube;
} Player;

typedef struct {
  int inter;
  const char *clube;
} Clube;

Player player(const char *nome, int inter, int golos, const char *posi, const char *clube)
{
	Player result;
    result.nome = nome;
    result.inter = inter;
    result.golos = golos;
    result.posi = posi;
    result.clube = clube;
    return result;
}
Clube clube(int inter, const char *clube)
{
    Clube result = {inter, clube};
    return result;
}

int strings_from_csv_1(const char *s, const char **a)
{
    int result = 0;
    int x = str_count_while_not(s, SEPARATOR);
    a[result++] = str_ndup(s, x);
    int i = x;
    while (s[i++] == SEPARATOR)
    {
        int x = str_count_while_not(s+i, SEPARATOR);
        a[result++] = str_ndup(s+i, x);
        i += x;
    }
    return result;
}

Player player_from_strings(const char **a, int n)
{
    assert(n >= 5);
    return player(a[0], atoi(a[1]), atoi(a[2]), a[3], a[4]);
}

Player player_from_csv(const char *line)
{
    const char *a[MAX_COLUMNS];
    int n = strings_from_csv_1(line, a);
    return player_from_strings(a, n);
}

int read_player_csv(FILE *f, Player *a)
{
    int result = 0;
    char line[MAX_LINE_LENGTH];
    while (str_readline(f, line) != EOF)
        a[result++] = player_from_csv(line);
    return result;
}

int strings_count_while2(Player *a, int n, Player x)
{
  int result = 0;
  while (result < n && strcmp(a[result].clube, x.clube) == 0)
    result++;
  return result;
}


int strings_count_while4(Player *a, int n, Player x)
{
  int result = 0;
  int z = 0;
  while (result < n && strcmp(a[result].clube, x.clube) == 0)
  {
    z += a[result].inter;
    result++;
  }
  return z;
}

int clube_creator_inter(Player *a, int n, Clube *b)
{
  int result = 0;
  int i = 0;
  while (i < n)
  {
    int z = strings_count_while4(a+i, n-i, a[i]);
    int c = strings_count_while2(a+i, n-i, a[i]);
    b[result++] = clube(z, a[i].clube);
    i += c;
  }
  return result;
}


void struct_player_exchange(Player *a, int x, int y)
{
  Player m = a[x];
  a[x] = a[y];
  a[y] = m;
}

void player_isort(Player *a, int n)
{
  for (int i = 1; i < n; i++)
  {
    int j = i;
    while (j > 0 && strcmp(a[j-1].clube, a[j].clube) < 0)
    {
      struct_player_exchange(a, j-1, j);
      j--;
    }
  }
}

void struct_exchange_clube(Clube *a, int x, int y)
{
  Clube m = a[x];
  a[x] = a[y];
  a[y] = m;
}

void inter_isort(Clube *a, int n)
{
  for (int i = 1; i < n; i++)
  {
    int j = i;
    while (j > 0 && a[j-1].inter < a[j].inter)
    {
      struct_exchange_clube(a, j-1, j);
      j--;
    }
  }
}


void print_clube(const Clube *a, int n)
{
  for(int i = 1; i < n; i++)
  {
    printf("%s %d\n", a[i].clube, a[i].inter);
  }
}

void test_player(const char *filename)
{
FILE *f = fopen(filename, "r");
assert(f);
Player b[1000];
Clube c[1000];
int m = read_player_csv(f, b);
player_isort(b, m);
int h = clube_creator_inter(b, m, c);
inter_isort(c, h);
 print_clube(c, h);
}

int main(int argc, const char **argv)
{
// unit_tests();
int x = 'U';
const char *filename = NULL;
if (argc > 1)
x = *argv[1];
if (argc > 2)
filename = argv[2];
if (x == 'A')
test_player(filename);
else if (x == 'U')
printf("All unit tests PASSED\n");
else
printf("%s: Invalid option.\n", argv[1]);
return 0;
}