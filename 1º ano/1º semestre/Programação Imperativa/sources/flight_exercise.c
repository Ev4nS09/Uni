#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include <string.h>

#define MAX_FLIGHTS 10000
#define MAX_LINE_LENGTH 100

typedef struct {
	const char *code;
	const char *destination;
	int departure;
} Flight;

const char *str_dup(const char *s)
{
  char *result = (char *) malloc(strlen(s) + 1);
  strcpy(result, s);
  return result;
}

Flight flight(const char *code, const char *destination, int departure)
{
	Flight result;
	result.code = code;
	result.destination = destination;
	result.departure = departure;
	return result;
}

int flights_read(FILE *f, Flight *a)
{
	int result = 0;
	char code[16];
	char destination[64];
	int departure;
	while (fscanf(f, "%d%s%s", &departure, code, destination) != EOF)
		a[result++] = flight(str_dup(code), str_dup(destination), departure);
	return result;
}

void flights_write(FILE *f, Flight *a, int n)
{
	for (int i = 0; i < n; i++)
		fprintf(f, "[%d][%s][%s]\n", a[i].departure, a[i].code, a[i].destination);
}

void test_flights_read_write(void)
{
	FILE *f = fopen("partidas_faro.txt", "r");
	assert(f != NULL);
	Flight flights[MAX_FLIGHTS];
	int n_flights = flights_read(f, flights);
	flights_write(stdout, flights, n_flights);
}

int flights_select_by_destination(const Flight *a, int n, char *destination, int *b)
{
	int result = 0;
	for (int i = 0; i < n; i++)
		if (strcmp(a[i].destination, destination) == 0)
			b[result++] = i;
	return result;
}

void flights_write_selection(FILE *f, const Flight *a, int *b, int n)
{
	for (int i = 0; i < n; i++)
		fprintf(f, "[%d][%s][%s]\n",
			a[b[i]].departure, a[b[i]].code, a[b[i]].destination);
}

void test_flights_select_destination(void)
{
	FILE *f = fopen("partidas_faro.txt", "r");
	assert(f != NULL);
	Flight flights[MAX_FLIGHTS];
	int n_flights = flights_read(f, flights);
	char line[MAX_LINE_LENGTH];
	while (scanf("%s", line) != EOF)
	{
		int b[n_flights];
		int n = flights_select_by_destination(flights, n_flights, line, b);
		flights_write_selection(stdout, flights, b, n);
	}
}

int main(int argc, char **argv)
{
	int x = 'A';
	if (argc > 1)
		x = *argv[1];
	if (x == 'A')
		test_flights_read_write();
	else if (x == 'B')
		test_flights_select_destination();
	else if (x == 'U')
		printf("All unit tests PASSED.\n");
	else
		printf("%s: Invalid option.\n", argv[1]);
	return 0;
}