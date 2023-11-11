//
//  our_ints_unit_tests.c
//
//  Created by Pedro Guerreiro on 2018/11/01.
//  Copyright © 2018 Pedro Guerreiro. All rights reserved.
//
// This just runs the unit tests from our_ints.c

// 2122 Edition

// Compile with
// gcc -Wall our_ints_unit_tests.c our_ints.c -o our_ints_unit_tests.out, in Linux/MacOS
// gcc -Wall our_ints_unit_tests.c our_ints.c -o our_ints_unit_tests.exe, in Windows

#include <stdio.h>

#include "our_ints.h"

int main(int argc, char **argv)
{
  our_ints_unit_tests();
  int x = 'U';
  if (argc > 1)
    x = *argv[1];
  if (x == 'U')
    printf("All unit tests PASSED.\n");
  else
    printf("%s: Invalid option.\n", argv[1]);
  return 0;
}

