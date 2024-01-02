#ifndef LinkedList_h
#define LinkedList_h

typedef struct Node{
    struct Node *next;
    void *value;
    void (*free_value)(void*);
}Node;

Node *new_node(Node *next, void *value, void (*free_value)(void*));

typedef struct List{
    Node *first;
    Node *last;
    void (*free_value)(void*);
    int size;
}List;

List *new_list(void (*free_value)(void*));

typedef int (*Compare)(void*, void*);
typedef int (*Hash)(void*, int);

void free_node(Node *node);

void free_list(List *list);

int is_list_empty(List *list);

void list_clear(List *list);

int is_value_in_list(List *list, void *value, int(*is_equal)(void*, void*));

void list_add(List *list, void *value);

void list_add_last(List *list, void *value);

void list_add_at_index(List *list, void *value, int index);

void list_add_sorted(List *list, void *value, int(*cmp)(void*, void*));

void list_add_list(List *list_head, List *list_tail);

void list_add_list_sorted(List *list_head, List *list_tail, int(*cmp)(void*, void*));

void list_set(List *list, int index, void *value);

void list_remove_first_node(List *list);

void list_remove_last_node(List *list);

void list_remove_at_index(List *list, int index);

void list_remove_value(List *list, void *value, int(*is_equal)(void*, void*));

void list_remove_all_value(List *list, void *value, int(*is_equal)(void*, void*));

void *list_get_first(List *list);

void *list_get_last(List *list);

void *list_get_value_at_index(List *list, int index);

void list_swap(List* list, int index1, int index2);

void list_swap_node(Node *node1, Node *node2);

void list_sort(List *list, int (*cmp)(void*, void*));

int list_to_array(List *list, void **array);

List *array_to_list(void **array, int array_size, List *list, void (*free_value)(void*));

void print_list_linked(List *list, void (*print_value)(void*));

void print_list_array(List *list, void (*print_value)(void*));

void print_array(void **array, int n, void (*print_value)(void*));

typedef struct Queue{
    List *list;
    void (*free_value)(void*);
}Queue;

Queue *new_queue(void (*free_value)(void*));

void free_queue(Queue *queue);

int is_queue_empty(Queue *queue);

void queue_add(Queue *queue, void *value);

void *queue_peek(Queue *queue);

void *queue_remove(Queue *queue);

void queue_priority_add(Queue *queue, void *value, int (*cmp)(void*, void*));

void queue_add_queue(Queue *queue1, Queue *queue2);

void queue_add_list_sorted(Queue *queue, List *list, int(*cmp)(void*, void*));

void queue_clear(Queue *queue);

typedef struct Map{
    List** buckets; //copyright © Alex Rodrigues i20
    Hash hash;
    int size;
    int values;
    int overload;
    void (*free_value)(void*);
}Map;

Map* new_map(Hash hash, int size, int overload, void (*free_value)(void*));

typedef struct Pair{
    void* key;
    void* value;
}Pair;

Pair* new_pair(void* key, void* value);

#endif