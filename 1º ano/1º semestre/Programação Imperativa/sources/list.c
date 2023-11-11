//
//  list.c
//
//  Created by Pedro Guerreiro on 06/11/2021.
//

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <assert.h>

#include "list.h"

typedef struct lnode {
    double value;
    List next;
  } List_node;

List cons(double x, List s)
{
  List r = (List) malloc(sizeof(List_node));
  r -> value = x;
  r -> next = s;
  return r;
}

int is_empty(List s)
{
  return s == NULL;
}

double head(List s)
{
  assert(!is_empty(s));
  return s -> value;
}

List tail(List s)
{
  assert(!is_empty(s));
  return s->next;
}

static int lst_put_elements_rest(List s);

static int lst_put_elements(List s)
{
  int result = 0;
  if (!is_empty(s))
  {
    printf("%.20g", head(s));
    result = 1 + lst_put_elements_rest(tail(s));
  }
  return result;
}

static int lst_put_elements_rest(List s)
{
  int result = 0;
  if (!is_empty(s))
  {
    printf(" %.20g", head(s));
    result = 1 + lst_put_elements_rest(tail(s));
  }
  return result;
}

static int skip_spaces(void)
{
  int result;
  while ((void)(result = fgetc(stdin)), result != EOF && isspace(result) && result != '\n')
    ;
  return result;
}

int lst_scan(List *s)
{
  int result = 0;
  List t = NULL;
  int c;
  while ((void)(c = skip_spaces()), c != EOF && c != '\n')
  {
    ungetc(c, stdin);
    double x;
    scanf("%lf", &x);
    result++;
    t = cons(x, t);
  }
  *s = t;
  return c == EOF ? EOF : result;
}

int lst_put(List s)
{
  printf("<");
  int result = lst_put_elements(s);
  printf(">\n");
  return result;
}

