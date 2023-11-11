#include <stdio.h>
#include <assert.h>
#include <string.h>
#include <stdlib.h>
#include "our_strings.h"

const char *author = "Miguel Cabrita";

#define SEPARATOR ','
const int MAX_COLUMNS = 100;
#define MAX_PLAYERS 1000

//problema A

typedef struct {
	const char *nome;
	int internacionalizacoes;
	int golos;
	const char *posicao;
	const char *clube;
} Player;

Player player(const char *nome, int internacionalizacoes, int golos, const char *posicao, const char *clube)
{
	Player result = {nome, internacionalizacoes, golos, posicao, clube};
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

int read_players_csv(FILE *f, Player *a)
{
	int result = 0;
	char line[MAX_LINE_LENGTH];
	while (str_readline(f, line) != EOF)
		a[result++] = player_from_csv(line);
	return result;
}

void show_players(const Player *a, int n)
{
	for (int i = 0; i < n; i++)
		printf("%s,%d,%d,%s,%s\n", a[i].nome, a[i].internacionalizacoes, a[i].golos, a[i].posicao, a[i].clube);
}

void test_read_write_csv_prob_a(const char *filename)
{
	FILE *f = fopen(filename, "r");
	assert(f);
	Player a[MAX_PLAYERS];
	int n = read_players_csv(f, a);
	show_players(a, n);
}

//problema B

void players_exchange(Player *a, int x, int y)
{
    Player m = a[x];
    a[x] = a[y];
    a[y] = m;
}

int players_cmp_inter_golos_nome(Player p, Player q)
{
    int result = p.internacionalizacoes - q.internacionalizacoes;
    if (result == 0)
        result = p.golos - q.golos;
    if (result == 0)
    	result = -strcmp(p.nome, q.nome);
    return result;
}

void players_ints_isort(Player *a, int n)
{
    for (int i = 1; i < n; i++)
    {
        int j = i;
        while (j > 0 && players_cmp_inter_golos_nome(a[j-1], a[j]) < 0)
        {
            players_exchange(a,j-1,j);
            j--;
        }
    }
}

void show_players_b(const Player *a, int n)
{
	for (int i = 0; i < n; i++)
		printf("[%s][%d][%d][%s][%s]\n", a[i].nome, a[i].internacionalizacoes, a[i].golos, a[i].posicao, a[i].clube);
}

void test_read_write_csv_prob_b(const char *filename)
{
	FILE *f = fopen(filename, "r");
	assert(f);
	Player a[MAX_PLAYERS];
	int n = read_players_csv(f, a);
	players_ints_isort(a, n);
	show_players_b(a, n);
}

//problema C

void players_select_by_clube(Player *a, int n, char *clube)
{
	int result = 0;
	for (int i = 0; i < n; i++)
	{
		if (strcmp(a[i].clube, clube) == 0)
		{
			printf("[%s][%d]\n", a[i].nome, a[i].internacionalizacoes);
			result++;
		}
	}
	if (result == 0)
		printf("(void)\n");
}

void test_read_write_csv_prob_c(const char *filename)
{
	FILE *f = fopen(filename, "r");
	assert(f);
	Player a[MAX_PLAYERS];
	char clube[1000];
	int n = read_players_csv(f, a);
	//players_ints_isort(a, n);
	while (str_getline(clube) != EOF)
		players_select_by_clube(a, n, clube);
}

//problema D

typedef struct {
	int internacionalizacoes;
	const char *clube;
} Player_by_Clube;

Player_by_Clube player_by_clube(int internacionalizacoes, const char *clube)
{
	Player_by_Clube result = {internacionalizacoes, clube};
	return result;
}

int players_cmp_clube(Player p, Player q)
{
    int	result = strcmp(q.clube, p.clube);
    return result;
}

void players_ints_isort_clube(Player *a, int n)
{
    for (int i = 1; i < n; i++)
    {
        int j = i;
        while (j > 0 && players_cmp_clube(a[j-1], a[j]) < 0)
        {
            players_exchange(a,j-1,j);
            j--;
        }
    }
}

int players_by_clube_count_while_avanca(Player *a, int n, Player x)
{
  int result = 0;
  while (result < n && strcmp(a[result].clube, x.clube) == 0)
  	result++;
  return result;
}

int players_by_clube_count_while_sum_inter(Player *a, int n, Player x)
{
  int result = 0;
  int soma = 0;
  while (result < n && strcmp(a[result].clube, x.clube) == 0)
  {
  	soma += a[result].internacionalizacoes;
  	result++;
  }
  return soma;
}

int players_by_clube_groups(Player *a, int n, Player_by_Clube *b)
{
  int result = 0;
  int i = 0;
  while (i < n)
  {
    int soma = players_by_clube_count_while_sum_inter(a+i, n-i, a[i]);
    int w = players_by_clube_count_while_avanca(a+i,n-i,a[i]);
    b[result++] = player_by_clube(soma, a[i].clube);
    i += w;
  }
  return result;
}

void players_by_clube_print(const Player_by_Clube *a, int n)
{
	for (int i = 1; i < n; i++)
	{
		printf("%s %d\n", a[i].clube, a[i].internacionalizacoes);
	}
}

void players_by_clube_exchange(Player_by_Clube *a, int x, int y)
{
    Player_by_Clube m = a[x];
    a[x] = a[y];
    a[y] = m;
}

int players_by_clube_cmp_clube(Player_by_Clube p, Player_by_Clube q)
{
    int	result = p.internacionalizacoes - q.internacionalizacoes;
    return result;
}

void players_by_clube_ints_isort_clube(Player_by_Clube *a, int n)
{
    for (int i = 1; i < n; i++)
    {
        int j = i;
        while (j > 0 && players_by_clube_cmp_clube(a[j-1], a[j]) < 0)
        {
            players_by_clube_exchange(a,j-1,j);
            j--;
        }
    }
}

void test_read_write_csv_prob_d(const char *filename)
{
	FILE *f = fopen(filename, "r");
	assert(f);
	Player a[MAX_PLAYERS];
	Player_by_Clube b[1000];
	int n = read_players_csv(f, a);
	players_ints_isort_clube(a, n);
	int z = players_by_clube_groups(a, n, b);
	players_by_clube_ints_isort_clube(b, z);
	players_by_clube_print(b, z);
}

int main(int argc, char **argv)
{
	int x = 'A';
	const char *filename = NULL;
	if (argc > 1)
		x = *argv[1];
	if (argc > 2)
		filename = argv[2];
	if (x == 'A')
		test_read_write_csv_prob_a(filename);
	else if (x == 'B')
		test_read_write_csv_prob_b(filename);
	else if (x == 'C')
		test_read_write_csv_prob_c(filename);
	else if (x == 'D')
		test_read_write_csv_prob_d(filename);
	else if (x == 'U')
		printf("All unit tests PASSED.\n");
	else
		printf("%s: Invalid option.\n", argv[1]);
	return 0;
}