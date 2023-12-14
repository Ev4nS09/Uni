<template>
    <div class="container-fluid d-flex mx-0 my-1 pe-2 ps-2 pb-1 border-bottom"> 
		<div class="col d-flex"> 
			<router-link to = "/" id="homeIcon"><img src="@\assets\images\EF.png" height="55" width="85"></router-link>
		</div>

		<div class = "col d-flex justify-content-center align-items-center">
  			<input style = "outline: none;" class ="rounded-4 bg-black w-75 h-75 border-0 text-white" type="search" name="search" placeholder="Search products">
		</div>

		<div v-if="!userLoggedIn" class = "col d-flex justify-content-end align-items-center">
        	<router-link to = "/login" class ="btn btn-outline-light d-inline mx-1" role = "button">Login</router-link>
        	<router-link to = "/register" class =" btn btn-outline-light d-inline mx-1" role = "button">Register</router-link>
    	</div>
		<div v-else>
			<div class = "col d-flex justify-content-end align-items-center">
          		<h4 class = "text-white pe-3 pt-1">Welcome back!</h4>
          		<img class = "border-2 border-light rounded-2" src="@\assets\images\silly_cat.jpg"  height="45" width="45">
          		<h4 class = "text-white px-2 pt-1">{{user.name}}</h4>
          		<router-link to = "/logout" class="btn btn-outline-light d-inline mx-1" role="button">Logout</router-link>
        	</div>
		</div>
	</div>

</template>

<script>
import { useUserStore } from '@/store/user'
import { useBasketStore } from '@/store/basket'

export default {
	setup() {
		const baketStore = useBasketStore()
		const userStore = useUserStore()
		return { userStore}
  	},
	data() {
		return {
			user: this.userStore.getUser,
			userLoggedIn: Object.keys(this.userStore.getUser).length !== 0,
		}
	},
	mounted() {

	},
    methods: {
		
	},
	computed: {

	}
}
</script>
