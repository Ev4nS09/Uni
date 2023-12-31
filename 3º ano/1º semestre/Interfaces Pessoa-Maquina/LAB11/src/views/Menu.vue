<template>

<Header />
	<div class="container">
		<div class="row">
			<div v-for="product in products" :key="product.id" class="col-md">
				<div class="border rounded container p-3 m-3">
					<div class="text-center">
						<img class="border rounded" v-bind:src=getImage(product.image) height="150" width="150">
						<br>
						<h5 class="text-white">{{ product.name }}</h5>
						<button class="btn btn-md btn-outline-light d-inline mx-1" role="button" @click = "addProduct(product.id)">Buy {{ product.price }}$</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import Header from '@/components/Header.vue'

import { useBasketStore } from '@/store/basket'
import { useProductsStore } from '@/store/products'
import { useUserStore } from '@/store/user'

export default {
	setup() {
		const basketStore = useBasketStore()
		const productsStore = useProductsStore()
		const userStore = useUserStore()
		return { basketStore, productsStore, userStore }
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
			return "./images/" + image
		},
		addProduct(productId){
			this.basketStore.incrementProduct(productId)
			console.log(this.basketStore.getProducts.size)
		}
	},
    computed: {

    },
}



</script>

<style scoped>
</style>