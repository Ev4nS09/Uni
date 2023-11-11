#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

const char *author = "Afonso Rio";

#include "our_ints.h"
#include "our_doubles.h"
#include "our_strings.h"

int main()
{
	int a[100];
	ints_get(a);
	printf("%d", a[1]);
    return 0;
}