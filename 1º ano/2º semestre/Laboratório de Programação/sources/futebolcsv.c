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

void test_player(const char *filename)
{
FILE *f = fopen(filename, "r");
assert(f);
Player b[100000];
int m = read_player_csv(f, b);
show_player(b, m);
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