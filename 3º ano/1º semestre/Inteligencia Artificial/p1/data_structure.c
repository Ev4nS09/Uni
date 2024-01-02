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
    if(node->free_value != NULL)
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

int is_value_in_list(List *list, void *value, Compare cmp){
    int result = 0;
    Node *current_node = list->first;
    while(current_node != NULL){
        if(cmp(current_node->value, value)){
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

void list_add_unique(List *list, void* value, Compare cmp){
    Node* currentNode = list->first;
    while(currentNode != NULL){
        if(cmp(currentNode->value, value) == 1)
            return;
        currentNode = currentNode->next;
    }
    list_add(list, value);
}

void list_add_unique_replace(List *list, void* value, Compare cmp){
    Node* currentNode = list->first;
    while(currentNode != NULL){
        if(cmp(currentNode->value, value) == 1){
            currentNode->value = value;
            return;
        }
        currentNode = currentNode->next;
    }
    list_add(list, value);
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

void list_add_sorted(List *list, void* value, Compare cmp){
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

void list_add_list_sorted(List *list_head, List *list_tail, Compare cmp){
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

void list_remove_value(List *list, void* value, Compare cmp){

    if(cmp(list->first->value, value))
        list_remove_first_node(list);
    else{
        Node *current_node = list->first;
        while(!cmp(current_node->next->value, value)){
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

void list_remove_all_value(List *list, void *value, Compare cmp){ 
    while(!is_list_empty(list) && cmp(list->first->value, value)){list_remove_first_node(list);}
    Node *current_node = list->first;
    while(!is_list_empty(list) && current_node->next != NULL){
        if(cmp(current_node->next->value, value)){
            Node *node_temp = current_node->next;
            current_node->next = current_node->next->next;
            free(node_temp);
            list->size = list->size - 1;
        }
        if(current_node->next != NULL && !cmp(current_node->next->value, value))
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

void* list_get_value(List* list, void* value, Compare cmp){
    void* result = NULL;
    Node* current_node = list->first;
    while(current_node != NULL){
        if(cmp(current_node->value, value)){
            result = current_node->value;
            break;
        }
        current_node = current_node->next;
    }
    return result;
}

int list_contains_value(List* list, void* value, Compare cmp){
    int result = 0;
    Node* current_node = list->first;
    while(current_node != NULL){
        if(cmp(current_node->value, value)){
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

void list_sort(List *list, Compare cmp){
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

void queue_priority_add(Queue *queue, void *value, Compare cmp){
    list_add_sorted(queue->list, value, cmp);
}

void queue_add_queue(Queue *queue1, Queue *queue2){
    list_add_list(queue1->list, queue2->list);
}

void queue_add_list_sorted(Queue *queue, List *list, Compare cmp){
    list_add_list_sorted(queue->list, list, cmp);
}

void queue_clear(Queue *queue){
    list_clear(queue->list);
}

Map* new_map(Hash hash, int size, int overload, void (*free_value)(void*)){
    Map *result = malloc(sizeof(Map));
    result->buckets = calloc(size, sizeof(List));
    result->hash = hash;
    result->size = size;
    result->overload = overload;
    result->free_value = free_value;
    return result;
}

Pair* new_pair(void* key, void* value){
    Pair *result = malloc(sizeof(Pair));
    result->key = key;
    result->value = value;
    return result;
}

void map_put(Map* map, void* key, void* value, Copy copy_value){
    Pair *pair = new_pair(copy_value(key), copy_value(value));
    int index = map->hash(pair->key, map->size);
    if(map->buckets[index] == 0){
       map->buckets[index] = new_list(map->free_value); 
    }
    map->size = map->size + 1;
    list_add(map->buckets[index], pair);
}

void* map_get(Map* map, void* key, Compare cmp){
    void* result1 = list_get_value(map->buckets[map->hash(key, map->size)], key, cmp);
    Pair* result2 = (Pair*) result1;
    return result2->value;
}

void free_map(Map* map){
    for(int i = 0; i < map->size; i++){
        if(map->buckets[i] != 0){
            free_list(map->buckets[i]);
        }
    }
    free(map->buckets);
    free(map);
}

int hash(int *key, int size){
    return *key % size;
}

int cmp_pair_int(Pair* x, int* y){
    int result = *((int*)(x->key)) == *y ? 1 : 0;
    return result;
}

void free_value(int* value){
    free(value);
}

void* copy_value(int* value){
    int* result = malloc(sizeof(int));
    *result = *value;
    return result;
}

int main(){
    while(1){
    Map* map = new_map((Hash) hash, 1000, 2, (Free) free_value);
    int x = 5;
    int y = 5;
    map_put(map, &x, &y, (Copy) copy_value);
    printf("%d\n", *(int*) map_get(map, &x, (Compare) cmp_pair_int));
    x = 7;
    printf("%d\n", *(int*) map_get(map, &y, (Compare) cmp_pair_int));
    free_map(map);
}
}