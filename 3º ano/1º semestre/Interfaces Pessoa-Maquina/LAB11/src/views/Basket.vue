<template>
	<Header />
		<div class="container">
			<div class="row">
				<div v-for="product in basket.keys()" class="col-sm">
					<div class="border rounded container p-3 m-3">
						<div class="text-center">
							<img class="border rounded" v-bind:src=getImage(productsStore.getProduct(product).image) height="150" width="150">
							<br>
							<h5 class="text-white">{{ productsStore.getProduct(product).name }}</h5>
							<button class="btn btn-md btn-outline-light d-inline mx-1" role="button" @click = "decrementProduct(product)"> - </button>
							<h6 class="text-white">Quantity: {{ basketStore.getProducts.get(product) }}</h6>
							<button class="btn btn-md btn-outline-light d-inline mx-1" role="button" @click = "incrementProduct(product)"> + </button>
							<h6 class="text-white"> Price: {{ productsStore.getProduct(product).price *  basketStore.getProducts.get(product) }}$</h6>
						</div>
					</div>
				</div>
				<h4 class="text-white">Total Price: {{ totalPrice() }}$</h4>
				<button v-if=" totalPrice() > 0" class="btn btn-outline-light d-inline mx-1" @click="handleSubmit()"> Order </button>
			</div>
		</div>
	</template>
	
	<script>
	import Header from '@/components/Header.vue'
	import router from '@/router'
	
	import { useBasketStore } from '@/store/basket'
	import { useProductsStore } from '@/store/products'
	import { useUserStore } from '@/store/user'
	import { useOrdersStore } from '@/store/orders'
	
	export default {
		setup() {
			const basketStore = useBasketStore()
			const productsStore = useProductsStore()
			const userStore = useUserStore()
			const ordersStore = useOrdersStore()
			return { basketStore, productsStore, userStore, ordersStore }
		  },
		components: {
			Header
		},
		data() {
			return {
				products:[],
				basket:[],
			}
		},
		async mounted() {
			await this.productsStore.getProductsDB()
			this.products = this.productsStore.getProducts
	
			this.basket = this.basketStore.getProducts
		},
		methods: {
			getImage(image){
				return "src/assets/images/" + image
			},
			incrementProduct(productId){
				this.basketStore.incrementProduct(productId)
			},
			decrementProduct(productId){
				this.basketStore.decrementProduct(productId)
			},
			totalPrice(){
				let result = 0
				for (const product of this.basket.keys()) {
  					result += this.productsStore.getProduct(product).price * this.basket.get(product)
				}
				return result
			},
			getItems(){
      			let result = []
				for (const id of this.basket.keys()) {
					const product = this.productsStore.getProduct(id)
					result.push({
						id: id,
						quantity: this.basket.get(id)
					})
				}
				return result
			},
			async handleSubmit(){
				let order = { 
					user_id:this.userStore.getUser.id, 
					totalAmount: this.totalPrice(), 
					status_id: 1, 
					items: this.getItems() 
				}
				if(await this.ordersStore.addOrderDB(order)){
					this.basketStore.clearBasket()
					router.push({path: "/message/7"})
				}
				else
					router.push({path: "/message/7"})
			}

		},
		computed: {
		},
	}
	
	
	
	</script>
	
	<style scoped>
	</style>