#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

const char *author = "Afonso Rio";

#include "our_ints.h"
#include "our_doubles.h"
#include "our_strings.h"

#define MAX_LANCHAS 10000

typedef struct {
    int x;
    int y;
    const char *lancha;
} Lanchas;

Lanchas lanchas(int x, int y, const char *lancha)
{
    Lanchas result;
    result.x = x;
    result.y = y;
    result.lancha = lancha;
    return result;
}

int lanchas_read(Lanchas *a)
{
    int result = 0;
    int x;
    int y;
    char lancha[32];
    while (scanf("%d%d%s", &x, &y, lancha) != EOF)
        a[result++] = lanchas(x, y, str_dup(lancha));
    return result;
}

void lanchas_write(Lanchas *a, int n)
{
    for (int i = 0; i < n; i++)
        printf("[%d][%d][%s]\n", a[i].x, a[i].y, a[i].lancha);
}

double calculo_distancia(Lanchas a, Lanchas x)
{
    return sqrt(pow(x.x - a.x, 2) + pow(x.y - a.y, 2));
}

double str_count_while_alt2(Lanchas *a, int n, Lanchas x)
{
    int result = 0;
    double distancia = 0;
    while (result < n)
    {
        if (strcmp(x.lancha, a[result].lancha) != 0)
            result++;
        else if (strcmp(x.lancha, a[result].lancha) == 0)
        {
            distancia = calculo_distancia(a[result], x);
            return distancia;
        }
    }
    return -1;
}

int find_lanchas(Lanchas *a, int n, const char **p)
{
    int result = 0;
    int i = 0;
    while (i < n)
    {
        double z = str_count_while_alt2(a+i+1, n-i-1, a[i]);
        if (z > 5 && strings_find(p, result, a[i].lancha) == - 1)
            p[result++] = str_dup(a[i].lancha);
        i++;
    }
    return result;
}

void test_lanchas_read_write(void)
{
    Lanchas lanchas[MAX_LANCHAS];
    const char *lancha[1000];
    int n_lanchas = lanchas_read(lanchas);
    int n_lanchas_rapidas = find_lanchas(lanchas, n_lanchas, lancha);
    printf("%d\n", n_lanchas_rapidas);
    strings_isort(lancha, n_lanchas_rapidas);
    strings_print_basic(lancha, n_lanchas_rapidas);
}

int main()
{
    test_lanchas_read_write();
    return 0;
}