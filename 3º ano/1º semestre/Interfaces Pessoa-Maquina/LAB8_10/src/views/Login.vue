<template>
    <Menu />
    <form @submit.prevent="handleSubmit" class = "d-gird container-sm rounded-2 border border-2 justify-content-center align-text-center w-auto">
        <div class = "mb-4 d-flex justify-content-center align-text-center">
            <h1 class="text-white">Login</h1>
        </div>
        <div class="mb-3">
            <label for="formGroupExampleInput2" class="form-label text-white">Enter email:</label>
            <input type="email" v-model="user.email"  name="email" class="form-control bg-black text-white" id="formGroupExampleInput2">
        </div>
        <div class="mb-3">
            <label for="formGroupExampleInput2" class="form-label text-white">Enter password:</label>
            <input type="password" v-model="user.password" class="form-control bg-black text-white" id="formGroupExampleInput2">
        </div>
        <div class="mb-4 d-flex justify-content-center align-text-center">
            <button type="submit" class = "btn btn-outline-light d-inline mt-4 mb-1">Login</button>
        </div>
        <div class=" d-flex justify-content-center align-text-center">
            <h6 class="text-white">Don't have an account? <br> 
                <div class="d-flex justify-content-center align-text-center">
					<router-link to = "/register">Register here</router-link>
                </div>
            </h6>
        </div>
        <div class="mt-3 d-flex justify-content-center align-text-center">
            <h6 class="text-white">Go home <router-link to = "/">here</router-link>
            </h6>
        </div>
    </form>
</template>

<script>

import Menu from '@/components/Menu.vue'
import router from '@/router'

import { useUserStore } from '@/store/user'

export default {

	setup() {
		const userStore = useUserStore()
		return { userStore }
  	},

	components: {
        Menu
	},   
	data() {
      return {	
        user: {
			email: '',
			password: '',
        },
		submitting: false,
		error: false,
		userLoggedIn: Object.keys(this.userStore.getUser).length !== 0,
      }
    },
	
	methods: {
		async handleSubmit(){
            const verifyemail = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/
            if(this.user.email.length === 0 || this.user.password.length === 0){
                alert("All fields must be filled!")
            }
            else if(!verifyemail.test(this.user.email)){
                alert("Email must be valid!")
            }
            else{
                if(await this.userStore.loginUserDB(this.user))
                    router.push({path: "/message/5"})
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

<style scoped>

</style>