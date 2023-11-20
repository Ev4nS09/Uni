<template>
    <div class="container-fluid d-flex mx-0 my-1 pe-2 ps-2 pb-1 border-bottom"> 
		<div class="col d-flex"> 
  			<a id="homeIcon"><img src="@\assets\images\EF.png" height="55" width="85"></a>
		</div>

		<div class = "col d-flex justify-content-center align-items-center">
  			<input style = "outline: none;" class ="rounded-4 bg-black w-75 h-75 border-0 text-white" type="search" name="search" placeholder="Search posts">
		</div>

		<div class = "col d-flex justify-content-end align-items-center">
        	<a class="btn btn-outline-light d-inline mx-1" href=# role="button" >Login</a>
        	<a class="btn btn-outline-light d-inline mx-1" href="@\views\Register.vue" role="button" >Register</a>
    	</div>
	</div>
</template>

<script>
import { useUserStore } from '@/store/user'

export default {
	setup() {
		const userStore = useUserStore()
		return { userStore}
  	},
	data() {
		return {
			user: {
				id: '', 
				name: '', 
				email: '', 
				session_id: ''
			},
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