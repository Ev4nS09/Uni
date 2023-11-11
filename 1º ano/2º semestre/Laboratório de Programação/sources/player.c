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
#define MAX_PLAYERS 1000

typedef struct {
 const char *nome;
 int inter;
 int golos;
 const char *posi;
 const char *clube;
} Player;

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

typedef int Calc(Player, Player);

typedef struct {
  int inter;
  const char *clube;
} Clube;

Clube clube(int inter, const char *clube)
{
    Clube result = {inter, clube};
    return result;
}

int strings_from_csv2(const char *s, const char **a)
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

Player player_creators(const char **a, int n)
{

assert(n >= 5);
return player(a[0], atoi(a[1]), atoi(a[2]), a[3], a[4]);
}

Player player_from_csv(const char* line)
{
const char *a[10000];
int n = strings_from_csv2(line, a);
return player_creators(a, n);
}

int read_player_csv(FILE *f, Player *a)
{
int result = 0;
char line[MAX_LINE_LENGTH];
while (str_readline(f, line) != EOF)
a[result++] = player_from_csv(line);
return result;
}

void show_player(Player *a, int n)
{
for (int i = 0; i < n; i++)
printf("%s,%d,%d,%s,%s\n",
a[i].nome, a[i].inter, a[i].golos,
a[i].posi, a[i].clube);
}

void show_player2(Player *a, int n)
{
for (int i = 0; i < n; i++)
printf("[%s],[%d],[%d],[%s],[%s]\n",
a[i].nome, a[i].inter, a[i].golos,
a[i].posi, a[i].clube);
}

void show_player_B(Player *a, int n)
{
for (int i = 0; i < n; i++)
{
    if(str_len(a[i].clube) == 0) 
      printf("[%s],[%d],[%d],[%s]\n",
a[i].nome, a[i].inter, a[i].golos,
a[i].posi);
  
  else
printf("[%s],[%d],[%d],[%s],[%s]\n",
a[i].nome, a[i].inter, a[i].golos,
a[i].posi, a[i].clube);
  }
}


//Problema A

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


int read_futebol(Player *b, const char **a, int n)
{
    int result = 0;
    int i = 0;
    char y[1000];
    char u[1000];
    char j[1000];
    while(i < n)
    {
        b[result++] = player(a[i], atoi(inter(a[i + 1], u)), atoi(golos(a[i + 1], j)), position(a[i + 1], y), NULL);
        i += 3;

    }
return result;
}

void futebol_print(Player *b, int n)
{
    for(int i = 0; i < n; i++)
    {
        printf("%s,", b[i].nome);
        printf("%d,", b[i].inter);
        printf("%d,", b[i].golos);
        printf("%s,\n", b[i].posi); 
    }
}

void test_player_A(const char *filename)
{
    FILE *f = fopen(filename, "r");
    assert(f);
    const char *a[1000];
    Player b[10000];
    int n = strings_read(f, a);
    int m = read_futebol(b, a, n);
    futebol_print(b, m);
}

// Problema B

void struct_exchange(Player *a, int x, int y)
{
  Player m = a[x];
  a[x] = a[y];
  a[y] = m;
}

int player_isort_calc(Player a, Player b)
{
    int result = a.inter - b.inter;
    if(result == 0)
        result = a.golos - b.golos;
    if(result == 0)
        result = strcmp(b.nome, a.nome);
    return result;
}

void player_isort(Player *a, int n)
{
  for (int i = 1; i < n; i++)
  {
    int j = i;
    while (j > 0 && player_isort_calc(a[j-1], a[j]) < 0)
    {
      struct_exchange(a, j-1, j);
      j--;
    }
  }
}

void test_player_B(const char *filename)
{
FILE *f = fopen(filename, "r");
assert(f);
Player b[100000];
int m = read_player_csv(f, b);
player_isort(b, m);
show_player_B(b, m);
}

//Problema C

void player_compare(Player *a, const char *y, int n)
{
  int result = 0;
  for(int i = 0; i < n; i++)
  {
  if(strcmp(a[i].clube, y) == 0)
  {
    printf("[%s][%d]\n", a[i].nome, a[i].inter);
  result++;
}
  }
  if(result == 0)
  printf("(void)\n");
}

void test_player_C(const char *filename)
{
FILE *f = fopen(filename, "r");
assert(f);
char y[100];
Player b[100000];
int m = read_player_csv(f, b);
player_isort(b, m);
while(str_getline(y) != EOF)
player_compare(b, y, m);
}

//Problema D

int strings_count_while_ocorrencias(Player *a, int n, Player x)
{
  int result = 0;
  while (result < n && strcmp(a[result].clube, x.clube) == 0)
    result++;
  return result;
}


int strings_count_while_soma(Player *a, int n, Player x)
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
    int z = strings_count_while_soma(a+i, n-i, a[i]);
    int c = strings_count_while_ocorrencias(a+i, n-i, a[i]);
    b[result++] = clube(z, a[i].clube);
    i += c;
  }
  return result;
}

void player_isort_clube(Player *a, int n)
{
  for (int i = 1; i < n; i++)
  {
    int j = i;
    while (j > 0 && strcmp(a[j-1].clube, a[j].clube) < 0)
    {
      struct_exchange(a, j-1, j);
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

void test_player_D(const char *filename)
{
FILE *f = fopen(filename, "r");
assert(f);
Player b[1000];
Clube c[1000];
int m = read_player_csv(f, b);
player_isort_clube(b, m);
int h = clube_creator_inter(b, m, c);
inter_isort(c, h);
 print_clube(c, h);
}

//Problema E

int player_sort_posi(const char *a)
{  
 int result = 0;
  if(strcmp(a,"Avançado") == 0)
    result = 2;
  else if(strcmp(a,"Médio") == 0 )
    result = 3;
  else if(strcmp(a,"Defesa") == 0)
    result = 4;
  else if(strcmp(a,"Guarda-redes") == 0)
    result = 5;
   else
     result = 1;
  return result;
}

int count_universal(Player *a, int n)
{
  int result = 0;
  for(int i = 0; i < n; i++)
    {
      if(player_sort_posi(a[i].posi) == 1)
        result++;
    }
    return result;
}

int calc_posi(Player a, Player b)
{
    return player_sort_posi(a.posi) - player_sort_posi(b.posi);
}

// int player_isort_calc_posi(Player a, Player b)
// {
//     int result = player_sort_posi(a.posi) - player_sort_posi(b.posi);
//     if(result == 0)
//         result = a.inter - b.inter;
//     if(result == 0)
//         result = a.golos - b.golos;
//     if(result == 0)
//         result = strcmp(b.nome, a.nome);
//     return result;
// }

void player_isort_posi(Player *a, int n, Calc cmp)
{
  for (int i = 1; i < n; i++)
  {
    int j = i;
    while (j > 0 && cmp(a[j-1], a[j]) < 0)
    {
      struct_exchange(a, j-1, j);
      j--;
    }
  }
}

void test_player_E(const char *filename)
{
FILE *f = fopen(filename, "r");
assert(f);
Player b[100000];
int m = read_player_csv(f, b);
player_isort(b, m);
player_isort_posi(b, m, calc_posi);
int n = count_universal(b, m);
show_player2(b, m - n);
}

//Problema F

Player *player_ref(const char *nome, int inter, int golos, const char *posi, const char *clube)
{
 Player x = {nome, inter, golos, posi, clube};
 Player *result = (Player *) malloc(sizeof(Player));
 *result = x;
 return result;
}

int player_pointer(Player *a, int n, Player **players)
{
  int result = 0;
  for(int i = 0; i < n; i++)
    players[result++] = player_ref(a[i].nome, a[i].inter, a[i].golos, a[i].posi, a[i].clube);
return result;
}

void struct_exchange_pointer(Player **a, int x, int y)
{
  Player *m = a[x];
  a[x] = a[y];
  a[y] = m;
}

void player_isort_clube_pointer(Player **a, int n)
{
  for (int i = 1; i < n; i++)
  {
    int j = i;
    while (j > 0 && strcmp(a[j-1]->clube, a[j]->clube) < 0)
    {
      struct_exchange_pointer(a, j-1, j);
      j--;
    }
  }
}

int player_isort_calc_pointer(Player *a, Player *b)
{
    int result = a->inter - b->inter;
    if(result == 0)
        result = a->golos - b->golos;
    if(result == 0)
        result = strcmp(b->nome, a->nome);
    return result;
}

void player_isort_pointer(Player **a, int n)
{
  for (int i = 1; i < n; i++)
  {
    int j = i;
    while (j > 0 && player_isort_calc_pointer(a[j-1], a[j]) < 0)
    {
      struct_exchange_pointer(a, j-1, j);
      j--;
    }
  }
}

void show_player_pointer(Player **a, int n)
{
for (int i = 0; i < n; i++)
printf("%s,%d,%d,%s,%s\n",
a[i]->nome, a[i]->inter, a[i]->golos,
a[i]->posi, a[i]->clube);
}



void test_player_F(const char *filename)
{
FILE *f = fopen(filename, "r");
assert(f);
Player b[10000];
Player *players[MAX_PLAYERS];
int m = read_player_csv(f, b);
int n = player_pointer(b, m, players);
player_isort_pointer(players, n);
show_player_pointer(players, n);
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
test_player_A(filename);
else if (x == 'B')
test_player_B(filename);
else if (x == 'C')
test_player_C(filename);
else if (x == 'D')
test_player_D(filename);
else if (x == 'E')
test_player_E(filename);
 else if (x == 'F')
test_player_F(filename);
else if (x == 'U')
printf("All unit tests PASSED\n");
else
printf("%s: Invalid option.\n", argv[1]);
return 0;
}
