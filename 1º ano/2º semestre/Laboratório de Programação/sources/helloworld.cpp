#include <iostream>

using namespace std;

int ints_get(const int *a)
{
  int result = 0;
  int x[1000];
  while ( cin >> x != EOF)
    a[result++] = x;
  return result;
}


int main()
{
    int a[1000];
    ints_get(a);
    cout << a[1];
    return 0;
}
