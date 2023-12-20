 
  import { defineStore } from 'pinia'
  export const useBasketStore = defineStore({
  id: 'basket',
  state: () => ( {
    products: new Map()
  }),
  getters: {
    getProducts (state) {
      return state.products;
    },    
    getBasketSize (state) {
      return state.products.size
    },
  }, 
  actions: {
    clearBasket () {
        this.products = new Map()
    },
    incrementProduct (idToIncrement) {
      if(this.products.has(idToIncrement)){
        this.products.set(idToIncrement, this.products.get(idToIncrement) + 1)
      }
      else
        this.products.set(idToIncrement, 1)

      },    
      decrementProduct (idToDecrement) {
        if(this.products.get(idToDecrement) > 0){
          this.products.set(idToDecrement, this.products.get(idToDecrement) - 1)
        }
        else
          this.products.delete(idToDecrement)
      }

	},

})
