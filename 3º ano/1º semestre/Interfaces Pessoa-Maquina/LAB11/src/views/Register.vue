<template>
<div>
		
	<Header />
    <form @submit.prevent="handleSubmit" class = "d-gird container-sm rounded-2 border border-2 justify-content-center align-text-center w-auto mt-6">
        <div class = "mb-4 d-flex justify-content-center align-text-center">
            <h1 class="text-white">Register</h1>
        </div>
        <div class="mb-3">
            <label for="formGroupExampleInput2" class="form-label text-white">Enter Username:</label>
            <input type="text" v-model="user.name" name="username" class="form-control bg-black text-white" id="formGroupExampleInput2">
        </div>
        <div class="mb-3">
            <label for="formGroupExampleInput2" class="form-label text-white">Enter email:</label>
            <input type="email" v-model="user.email"  name="email" class="form-control bg-black text-white" id="formGroupExampleInput2">
        </div>
        <div class="mb-3">
            <label for="formGroupExampleInput2" class="form-label text-white">Enter password:</label>
            <input type="password" v-model="user.password" class="form-control bg-black text-white" id="formGroupExampleInput2">
        </div>
        <div class="mb-3">
            <label for="formGroupExampleInput2" class="form-label text-white">Confirm password:</label>
            <input type="password" v-model="passwordConfirmation" name="confirm_password" class="form-control bg-black text-white" id="formGroupExampleInput2">
        </div>
        <div class="mb-4 d-flex justify-content-center align-text-center">
            <button type="submit" class = "btn btn-outline-light d-inline mt-4 mb-1">Register</button>
        </div>
        <div class=" d-flex justify-content-center align-text-center">
            <h6 class="text-white">Already have an account? <br> 
                <div class="d-flex justify-content-center align-text-center">
        	        <router-link to = "/login">Login</router-link>
                </div>
            </h6>
        </div>
        <div class="mt-3 d-flex justify-content-center align-text-center">
            <h6 class="text-white">Go home<router-link to = "/">here</router-link></h6>
        </div>
    </form>
	
</div>
</template>

<script>

import Header from '@/components/Header.vue'
import router from '@/router'

import { useUserStore } from '@/store/user'

export default {

	setup() {
		const userStore = useUserStore()
		return { userStore }
  	},	
	components: {
        Header
	},
	data() {
      return {	
        user: {
			name: '',
			email: '',
			password: '',
        },
		passwordConfirmation: '',
		submitting: true,
		error: true,	
        userLoggedIn: Object.keys(this.userStore.getUser).length !== 0,
      }
    },
	
	methods: {
        async handleSubmit(){
            const verifyemail = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/
            if(this.user.name.length === 0 || this.user.email.length === 0 || this.user.password.length === 0 || this.passwordConfirmation.length === 0){
                alert("All fields must be filled!")
            }
            else if(!verifyemail.test(this.user.email)){
                alert("Email must be valid!")
            }
            else if(this.user.password !== this.passwordConfirmation){
                alert("Passwords must be equal!")
            }
            else if(await this.userStore.userExistsDB(this.user)){
                alert("Email is already being used")
            }
            else{
                this.userStore.addUserDB()
                router.push({path:"/message/4"})
            }
        }
	},
	
	computed: {

	},
	directives: {

	},
	mounted() {
        if(this.userLoggedIn)
            router.push({path:"/"}) 
	}
}
</script>