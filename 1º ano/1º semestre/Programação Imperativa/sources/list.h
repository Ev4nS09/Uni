//
//  list.h
//
//  Created by Pedro Guerreiro on 06/11/2021.
//

#ifndef _H_list_
#define _H_list_

typedef struct lnode *List;

int is_empty(List s);
List cons(double x, List s);
double head(List s);    // pre !list_empty(s);
List tail(List s);      // pre !list_empty(s);

int lst_put(List s);
int lst_scan(List *s);

#endif
