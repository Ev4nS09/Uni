#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

#include "data_structure.h"

typedef struct State{
    struct State* father;
    int triple;
    int weight;
    int size;
}State;

State *new_state(State *father, int triple, int specific_weight){
    State *result = (State *) malloc(sizeof(State));
    result->father = father;
    result->triple = triple;
    if(father != NULL){
        result->weight = father->weight + specific_weight;
        result->size = father->size + 1;
    }
    else{
        result->weight = 0;
        result->size = 0;
    }
    return result;
}

void print_value(void* v){
    printf("%d", ((State *)v)->triple);
}

int state_is_equal(State* x, State* y){
    return x->triple == y->triple ? 1 : 0;
}

int state_is_equal_void(void* x, void* y){
    return state_is_equal((State*) x, (State*) y);
}

int state_priority(void* x, void* y){
    return ((State*)y)->weight - ((State*)x)->weight;
}

void* state_copy(void* state){
    State* s = (State*) state;
    return new_state(s->father, s->triple, s->weight - s->father->weight);
}

int state_tripe_equals(void* x, void* y){
    return *(int*)((KeyValue*) x)->key == *(int*)y ? 1 : 0;
}

List *get_succssessors(State *state_precedent){
    List *result = new_list();
    list_add_last(result, new_state(state_precedent, state_precedent->triple + 1, 1));
    list_add_last(result, new_state(state_precedent, state_precedent->triple - 1, 2));
    list_add_last(result, new_state(state_precedent, state_precedent->triple * 2, 3));
    return result;
}

List *get_not_closed_succssessors(State *state_start, Map* closed){
    List *result = new_list();
    List *succssessors = get_succssessors(state_start);
    for(int i = 0; i < succssessors->size; i++){
        State *succssessor_state = (State*) list_get_value_at_index(succssessors, i);
        int succ_triple = succssessor_state->triple;
        if(!map_contains_key(closed, &succ_triple, state_tripe_equals))
            list_add_sorted(result, succssessor_state, state_priority);
    }
    return result;
}

State *solve_puzzle(State *start, State *goal, Queue *open, Map *closed){
    State *result = NULL;
    queue_priority_add(open, start, state_priority);
    while(!is_queue_empty(open)){
        State *possible_solution = (State*) queue_remove(open);
        if(state_is_equal(possible_solution, goal)){
            result = new_state(possible_solution->father, possible_solution->triple, possible_solution->weight - possible_solution->father->weight);
            break;
        }
        queue_add_list_sorted(open, get_not_closed_succssessors(possible_solution, closed), state_priority);
        map_put(closed, &possible_solution->triple, possible_solution, state_copy);
    }
    return result;
}

int get_array_of_state(int *array_of_state, State *state){
    State *state_temp = state;
    int result = state->size + 1;
    int n = state->size + 1;
    while(state_temp != NULL){
        array_of_state[n - 1] = state_temp->triple;
        n--;
        state_temp = state_temp->father;
    }
    return result;
}

void print_state(State *state){
    int *array_of_state = (int *) malloc(1000 * sizeof(int));
    int n = get_array_of_state(array_of_state, state);
    for(int i = 0; i < n; i++)
        printf("%d\n", array_of_state[i]);
    printf("\n%d", state->weight);
    free(array_of_state);
}

int hash(Map* map, void* value){
    int result = (*(int*)value);
    return map->size == 0 ? 0 : result % map->size;
}

int main(){
    int input; scanf("%d", &input);
    int goal = input * 3;

    Queue *open = new_queue();
    Map *closed = new_map(hash, 1000000000, 2);

    State *solution = solve_puzzle(new_state(NULL, input, 0), new_state(NULL, goal, 0), open, closed);
    print_state(solution);

    free_queue(open);
    free_map(closed);
    return 0;
}