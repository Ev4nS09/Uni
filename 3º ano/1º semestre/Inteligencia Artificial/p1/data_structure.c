#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <assert.h>

#include "data_structure.h"

Node *new_node(Node *next, void *value, void (*free_value)(void*)){
    Node *result = malloc(sizeof(Node));
    result->next = next;
    result->value = value;
    result->free_value = free_value;
    return result;
}

List *new_list(void (*free_value)(void*)){
    List *result = malloc(sizeof(List));
    result->first = new_node(NULL, NULL, free_value);
    result->last = new_node(NULL, NULL, free_value);
     result->free_value = free_value;
    result->size = 0;
    return result;
}

void free_node(Node *node){
    node->free_value(node->value);
    free(node);
}

void free_list(List *list){
    while(list->first != NULL){
        Node *node = list->first;
        list->first = list->first->next;
        free_node(node);
    }
    free(list);
}

int is_list_empty(List *list){
    return list->size == 0 ? 1 : 0;
}

void list_clear(List *list){
    while(list->first != NULL){
        Node *node = list->first;
        list->first = list->first->next;
        free_node(node);
    }
    list->size = 0;
}

int is_value_in_list(List *list, void *value, int(*is_equal)(void*, void*)){
    int result = 0;
    Node *current_node = list->first;
    while(current_node != NULL){
        if(is_equal(current_node->value, value)){
            result = 1;
            break;
        }
        current_node = current_node->next;
    }
    return result;
}

void list_add_empty(List *list, void* value){
    Node* node = new_node(NULL, value, list->free_value);
    list->first = node;
    list->last = node;
    list->size = 1;
}

void list_add(List *list, void* value){
    if(list->size == 0) return list_add_empty(list, value);
    Node *node = new_node(list->first, value, list->free_value);
    list->first = node;
    list->size = list->size + 1;
}

void list_add_last(List *list, void* value){
    if(list->size == 0) return list_add_empty(list, value);
    Node* node = new_node(NULL, value, list->free_value);
    list->last->next = node;
    list->last = node;
    list->size = list->size + 1;
}

void list_add_at_index(List *list, void* value, int index){
    if(index == 0) 
        list_add(list, value);
    else{
        Node *current_node = list->first;
        while(index != 1){
            current_node = current_node->next;
            index--;    
        }
        current_node->next = new_node(current_node->next, value, current_node->free_value);
        list->size = list->size + 1; 
    }
}

void list_add_sorted(List *list, void* value, int(*cmp)(void*, void*)){
    Node *current_node = list->first;
    if(is_list_empty(list) || cmp(list->first->value, value) < 0){
        list_add(list, value);
        return;
    }
    while(current_node->next != NULL){
        if(cmp(current_node->next->value, value) < 0){
            current_node->next = new_node(current_node->next, value, current_node->free_value);
            return;
        }
        current_node = current_node->next;
    }
    list_add_last(list, value);
}

void list_add_list(List *list_head, List *list_tail){
    Node *node_tail = list_tail->first;
    while(node_tail != NULL){
        list_add_last(list_head, node_tail->value);
        node_tail = node_tail->next;
    }
}

void list_add_list_sorted(List *list_head, List *list_tail, int(*cmp)(void*, void*)){
    Node *node_tail = list_tail->first;
    while(node_tail != NULL){
        list_add_sorted(list_head, node_tail->value, cmp);
        node_tail = node_tail->next;
    }
}

void list_set(List *list, int index, void* value){
    Node *node_temp = list->first;
    while(index != 0){
        node_temp = node_temp->next;
        index--;
    }
    node_temp->value = value;
}

void list_remove_first_node(List *list){
    if(list->size == 1){list_clear(list); return;}
    Node *node_temp = list->first;
    list->first = list->first->next;
    list->size = list->size - 1;
    free_node(node_temp);
}

void list_remove_last_node(List *list){
    if(list->size == 1){list_clear(list);return;}
    Node *current_node = list->first;
    Node *node_old_last = list->last;
    while(current_node->next->next != NULL){
        current_node = current_node->next;
    }
    current_node->next = current_node->next->next;
    list->last = current_node;
    list->size = list->size - 1;
    free_node(node_old_last);
}

void list_remove_at_index(List *list, int index){
    if(index == 0)
        list_remove_first_node(list);
    else if(index == list->size - 1)
        list_remove_last_node(list);
    else{
        Node *current_node = list->first;
        while(index != 1){
            current_node = current_node->next;
            index--;    
        }
        Node *node_temp = current_node->next;
        current_node->next = current_node->next->next;
        list->size = list->size - 1;
        free_node(node_temp);
    }
}

void list_remove_value(List *list, void* value, int(*is_equal)(void*, void*)){

    if(is_equal(list->first->value, value))
        list_remove_first_node(list);
    else{
        Node *current_node = list->first;
        while(!is_equal(current_node->next->value, value)){
            current_node = current_node->next; 
            if(current_node->next == NULL) return;
        }

        Node *node_temp = current_node->next;
        current_node->next = current_node->next->next;
        list->size = list->size - 1;
        free_node(node_temp);

        if(list->last == NULL) list->last = current_node;
    }
}   

void list_remove_all_value(List *list, void *value, int(*is_equal)(void*, void*)){ 
    while(!is_list_empty(list) && is_equal(list->first->value, value)){list_remove_first_node(list);}
    Node *current_node = list->first;
    while(!is_list_empty(list) && current_node->next != NULL){
        if(is_equal(current_node->next->value, value)){
            Node *node_temp = current_node->next;
            current_node->next = current_node->next->next;
            free(node_temp);
            list->size = list->size - 1;
        }
        if(current_node->next != NULL && !is_equal(current_node->next->value, value))
            current_node = current_node->next;
    }
    if(list->last == NULL && current_node != NULL) list->last = current_node;
}


void* list_get_first(List *list){
    return list->first->value;
}

void* list_get_last(List *list){
    return list->last->value;
}

void* list_get_value(List* list, void* value, int(*is_equal)(void*, void*)){
    void* result = NULL;
    Node* current_node = list->first;
    while(current_node != NULL){
        if(is_equal(current_node->value, value)){
            result = current_node->value;
            break;
        }
        current_node = current_node->next;
    }
    return result;
}

int list_contains_value(List* list, void* value, int(*is_equal)(void*, void*)){
    int result = 0;
    Node* current_node = list->first;
    while(current_node != NULL){
        if(is_equal(current_node->value, value)){
           result = 1; 
           break;
        }
        current_node = current_node->next;
    }
    return result;
}

void* list_get_value_at_index(List *list, int index){
    Node *node_temp = list->first;
    while(index != 0){
        node_temp = node_temp->next;
        index--;
    }
    return node_temp->value;
}

void* list_get_and_remove_first(List* list, void*(*copy_value)(void*)){
    void* result = copy_value(list_get_first(list));
    list_remove_first_node(list);
    return result;
}

void list_swap(List* list, int index1, int index2){
    void* temp_index = list_get_value_at_index(list, index1);
    list_set(list, index1, list_get_value_at_index(list, index2));
    list_set(list, index2, temp_index);
}

void list_swap_node(Node *node1, Node *node2){
    void* value = node1->value;
    node1->value = node2->value;
    node2->value = value;
}

void list_sort(List *list, int (*cmp)(void*, void*)){
    Node* current_node_i = list->first;
    Node* current_node_j = list->first->next;
    for(int i = 0; i < list->size-1; i++){
        for(int j = i+1; j < list->size; j++){
            if(cmp(current_node_i->value, current_node_j->value))
                list_swap_node(current_node_i, current_node_j);
            current_node_j = current_node_j->next;
        }
        current_node_i = current_node_i->next;
        if(current_node_i->next != NULL)current_node_j = current_node_i->next;
    }
}

int list_to_array(List *list, void **array){
    Node *first_node = list->first;
    int result = 0;
    while(result < list->size){
        array[result++] = first_node->value;
        first_node = first_node->next;
    }
    return result;
}

List *array_to_list(void **array, int array_size, List *list, void (*free_value)(void*)){
    List *result = new_list(free_value);
    for(int i = 0; i < array_size; i++)
        list_add_last(result, array[i]);
    return result;
}

void print_list_linked(List *list, void (*print_value)(void*)){
    if(is_list_empty(list)){printf("%s\n", "List is empty.");return;}
    Node *node = list->first;
    while (node->next != NULL){
        print_value(node->value);
        printf("->");
        node = node->next;
    }
        print_value(node->value);
        printf("\n");
}

void print_list_array(List *list, void (*print_value)(void*)){
    Node *node = list->first;
    printf("[");
    while (node->next != NULL){
        print_value(node->value);
        printf(", ");
        node = node->next;
    }
    print_value(node->value);
    printf("]\n");
}

void print_array(void **array, int n, void (*print_value)(void*)){
    printf("[");
    int i = 0;
    for(; i < n-1; i++){
        print_value(array[i]);
        printf(", ");
    }
    print_value(array[i]);
    printf("]\n");
}

Queue *new_queue(void (*free_value)(void*)){
    Queue *result = malloc(sizeof(Queue));
    result->list = new_list(free_value);
    result->free_value = free_value;
    return result;
}

void free_queue(Queue *queue){
    free_list(queue->list);
    free(queue);
}

int is_queue_empty(Queue *queue){
    return is_list_empty(queue->list) ? 1 : 0;
}

void queue_add(Queue *queue, void *value){
    list_add_last(queue->list, value);
}

void *queue_peek(Queue *queue){
    return list_get_first(queue->list);
}

void *queue_remove(Queue *queue){
    void* result = queue_peek(queue);
    list_remove_first_node(queue->list);
    return result;
}

void queue_priority_add(Queue *queue, void *value, int (*cmp)(void*, void*)){
    list_add_sorted(queue->list, value, cmp);
}

void queue_add_queue(Queue *queue1, Queue *queue2){
    list_add_list(queue1->list, queue2->list);
}

void queue_add_list_sorted(Queue *queue, List *list, int(*cmp)(void*, void*)){
    list_add_list_sorted(queue->list, list, cmp);
}

void queue_clear(Queue *queue){
    list_clear(queue->list);
}

Map* new_map(int (*hash)(Map*, void*), int size, int overload, void (*free_value)(void*)){
    Map *result = malloc(sizeof(Map));
    result->buckets = calloc(size, sizeof(List));
    result->hash = hash;
    result->size = size;
    result->keys = 0;
    result->overload = overload;
    result->free_value = free_value;
    return result;
}

KeyValue* new_keyvalue(void* key, void* value){
    KeyValue* result = malloc(sizeof(KeyValue));
    result->key = key;
    result->value = value;
    return result;
}

void free_hash_array(List** array, int size){
    for(int i = 0; i < size; i++)
        if(array[i] != 0)
            free_list(array[i]);
    free(array);
}

void free_map(Map* map){
    free_hash_array(map->buckets, map->size);
    free(map);
}

List** resize_array(Map *map, void*(*copy_value)(void*)){
    List** result = calloc(map->size * 2, sizeof(List));
    for(int i = 0; i < map->size; i++){
        while((map->buckets[i] != (long) 0) && !is_list_empty(map->buckets[i])){
            void* value = list_get_and_remove_first(map->buckets[i], copy_value); 
            result[i] = new_list(map->free_value);
            list_add(result[map->hash(map, ((KeyValue*)value)->key)], value);
        }
    }
    map->size = map->size * 2;
    return result;
}

void map_put(Map* map, void* key, void* value, void*(*copy_value)(void*)){
    KeyValue* keyvalue = new_keyvalue(key, value);
    int index = map->hash(map, keyvalue->key);
    map->keys = map->keys + 1;
    if(map->buckets[index] == 0) {
        map->buckets[index] = new_list(map->free_value); 
    }
    list_add(map->buckets[index], (void*) keyvalue);
    if(map->keys == map->size * map->overload){
        printf("RESIZE\n");
        List** array_temp = map->buckets;
        map->buckets = resize_array(map, copy_value);
        free_hash_array(array_temp, map->size/2);
    }
}

void* map_get(Map* map, void* key, int (*is_equal_keyvalue)(void*, void*)){
    int index = map->hash(map, key);
    return ((KeyValue*) list_get_value(map->buckets[index], key, is_equal_keyvalue))->value;
}

int map_contains_key(Map* map, void* key, int(*is_equal)(void*, void*)){
    int result = 0;
    if(map->keys != 0 && map->buckets[map->hash(map, key)] != (long) 0){
        result = list_contains_value(map->buckets[map->hash(map, key)], key, is_equal);
    }
    return result;
}

int hash(Map* map, void* value){
    int result = (*(int*)value);
    return map->size == 0 ? 0 : result % map->size;
}

void *cpy(void* x){
    int *result = malloc(sizeof(int));
    *(result) = *(int*)x;
    return result;
}

int is_equal(void* x, void *y) {
    return *(int*) x == *(int*) y ? 1 : 0;
}

void free_value(void * value){
    free(value);
}

int main(){
    Map* map = new_map(hash, 1000, 2, free_value);
    int x = 6;
    int y = 6;
    map_put(map, &x, &y, cpy);
    printf("%d\n", *(int*) map_get(map, &x, is_equal));
    free_map(map);
    return 0;
}